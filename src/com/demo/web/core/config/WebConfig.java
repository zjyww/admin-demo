package com.demo.web.core.config;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.demo.web.core.interceptor.GlobalInterceptor;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.plugin.AutoModelMappingPlugin;
import com.jfinal.ext.plugin.AutoRouteMappingPlugin;
import com.jfinal.ext.render.ErrorRenderFactory;
import com.jfinal.kit.StrKit;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.SqlReporter;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.druid.DruidStatViewHandler;
import com.jfinal.plugin.redis.RedisPlugin;
import com.jfinal.render.ViewType;


/**
 * 描述：工程配置
 * 
 */
public class WebConfig extends JFinalConfig {
    /**
     * logger
     */
    private static Log log = Log.getLog(WebConfig.class);

    /**
     * 配置常量
     */
    public void configConstant(Constants c) {
        loadPropertyFile("webconfig.properties"); // 加载少量必要配置，随后可用getProperty(...)获取值
        c.setDevMode(getPropertyToBoolean("devMode", false));
        c.setViewType(ViewType.JSP); // 设置视图类型为Jsp，否则默认为FreeMarker
        c.setErrorRenderFactory(new ErrorRenderFactory());
        c.setBaseUploadPath(getProperty("fileUploadPath"));
    }

    /**
     * 配置路由
     */
    public void configRoute(Routes r) {
        String[] aliasArray = getProperty("aliases").split(",");
        for (String alias : aliasArray) {
            String scanControllerPakage = getProperty(alias+"_scanControllerPakage");
            if(!"".equals(scanControllerPakage)){
                AutoRouteMappingPlugin autoRoutes = new AutoRouteMappingPlugin(scanControllerPakage);
                r.add(autoRoutes);
            }
        }
    }

    /**
     * 配置插件
     */
    public void configPlugin(Plugins p) {
        System.setProperty("java.net.preferIPv4Stack", "true"); // Disable IPv6 in JVM
        // 如果碰到意外情况，断电或服务器挂掉，重启后 ehcache 能自动加载之前的缓存
        System.setProperty("net.sf.ehcache.enableShutdownHook", getProperty("enableShutdownHook"));
//        p.add(new EhCachePlugin()); 
        //p.add(new J2CachePlugin());
        String[] aliasArray = getProperty("aliases").split(",");
        for (String alias : aliasArray) {
            // DruidPlugin连接池插件
            // https://github.com/alibaba/druid/wiki/%E5%B8%B8%E8%A7%81%E9%97%AE%E9%A2%98
            DruidPlugin dp = new DruidPlugin(
                    getProperty(alias+"_jdbcUrl"), 
                    getProperty(alias+"_userName"),
                    getProperty(alias+"_password"),
                    getProperty(alias+"_jdbcDriver")
            );
            
            dp.setMaxPoolPreparedStatementPerConnectionSize(
                    getPropertyToInt(alias+"_maxPoolPreparedStatementPerConnectionSize")
            );
            // 使用oracle mysql5.5以上版本时可以设值。
            dp.setMaxActive(getPropertyToInt(alias+"_maxActive"));
            dp.setMaxWait(getPropertyToInt(alias+"_maxWait"));
            dp.setTimeBetweenEvictionRunsMillis(getPropertyToInt(alias+"_timeBetweenEvictionRunsMillis"));
            dp.setMinEvictableIdleTimeMillis(getPropertyToInt(alias+"_minEvictableIdleTimeMillis"));
            dp.setValidationQuery(getProperty(alias+"_testSql"));
            dp.setRemoveAbandoned(getPropertyToBoolean(alias+"_removeAbandoned"));
            dp.setRemoveAbandonedTimeoutMillis(getPropertyToInt(alias+"_removeAbandonedTimeoutMillis"));
            dp.setLogAbandoned(getPropertyToBoolean(alias+"_logAbandoned"));
            if (getPropertyToBoolean("druidStatView")) {
                dp.addFilter(new StatFilter());
            }
            WallFilter wall = new WallFilter();
            String dbType = getProperty(alias+"_dbType");
            wall.setDbType(dbType);
            dp.addFilter(wall);
            p.add(dp);
    
    
            // 配置AutoActiveRecordPlugin插件
            AutoModelMappingPlugin arp = new AutoModelMappingPlugin(alias,dp);
            //arp.setCache(new J2Cache());
            arp.setShowSql(getPropertyToBoolean(alias+"_showSql"));
            SqlReporter.setLog(getPropertyToBoolean(alias+"_reporterSqlInLog"));// 开发环境时才用。把SQL记录到日志文件中。
            // 配置Oracle方言
            arp.setDialect(new MysqlDialect());
            // 配置属性名(字段名)大小写不敏感容器工厂
            arp.setContainerFactory(new CaseInsensitiveContainerFactory(true));
            String scanControllerPakage = getProperty(alias+"_scanControllerPakage");
            if(!"".equals(scanControllerPakage)){
                arp.autoMapping(getProperty(alias+"_scanModelPackage"));
            }
            //arp.setTransactionLevel(4);
            p.add(arp);
        }
     // 用于缓存bbs模块的redis服务
    }

    /**
     * 配置全局拦截器
     */
    public void configInterceptor(Interceptors i) {
        i.add(new GlobalInterceptor());
    }

    /**
     * 配置处理器
     */
    public void configHandler(Handlers h) {
        //全局访问控制，有攻击过网站的IP都会跳转到验证码页面。输入验证码后解锁访问
       // h.add(new AccessHandler());
        //h.add(new NoActionSkipHandler());
        // Druid查看连接池监控
        if (getPropertyToBoolean("druidStatView")) {
            DruidStatViewHandler dvh = new DruidStatViewHandler("/druid");
            h.add(dvh);
        }
    }

    /**
     * 运行此 main 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
     */
    public static void main(String[] args) {
        // JFinal.start("WebRoot", 80, "/", 5);
    }

    /**
     * 方法afterJFinalStart
     * 
     */
    @Override
    public void afterJFinalStart() {
        super.afterJFinalStart();
        WebConstants.fileUploadPath = getProperty("fileUploadPath");
        WebConstants.paramFilterChar = getProperty("paramFilterChar");
        WebConstants.platName = getProperty("platName");
    }
}