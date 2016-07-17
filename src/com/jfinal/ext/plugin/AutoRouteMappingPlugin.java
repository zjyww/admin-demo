package com.jfinal.ext.plugin;

import java.util.List;

import com.jfinal.config.Routes;
import com.jfinal.core.Controller;
import com.jfinal.ext.anotation.RouteMapping;
import com.jfinal.ext.utils.ClassUtil;
import com.jfinal.kit.StrKit;
import com.jfinal.log.Log;

/**
 * 描述：自动添加访问路由
 */
public class AutoRouteMappingPlugin extends Routes {

    /**
     * 描述：
     */
    private static Log log = Log.getLog(AutoRouteMappingPlugin.class);

    /**
     * 暂时没用 Controller类名结尾，没有设Annation时用于生成controllerKey
     */
    private String suffix = "Controller";

    /**
     * 自动扫描的包路径
     */
    private String scanPackagePath = "com.demo.web";

    public AutoRouteMappingPlugin() {
    }

    /**
     * @param scanPackagePath
     *            自动扫描的包路径
     */
    public AutoRouteMappingPlugin(String scanPackagePath) {
        super();
        this.scanPackagePath = scanPackagePath;
    }

    /**
     * @param suffix
     *            Controller类名结尾，没有设Annation时用于生成controllerKey
     * @param scanPackagePath
     *            自动扫描的包路径
     */
    public AutoRouteMappingPlugin(String suffix, String scanPackagePath) {
        super();
        this.suffix = suffix;
        this.scanPackagePath = scanPackagePath;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void config() {
        try {
            List<Class<?>> controllerClasses = ClassUtil.getAllSubClasses(scanPackagePath, Controller.class);
            RouteMapping rm = null;
            log.debug("----开始设置访问路由...----");
            for (Class<?> c : controllerClasses) {
                rm = c.getAnnotation(RouteMapping.class);
                if (rm == null) {
                    // Controller的子类没有找到注释，就不加载映射。目的是为了统一风格，强制要求使用注释。
                    // this.add(controllerKey(controller), (Class<? extends
                    // Controller>) controller);
                    log.error("错误：Controller类[" + c.getName() + "]没有配制'@RouteMapping' 注释,将无法完成自动映射！");
                } else if (StrKit.isBlank(rm.viewPath())) {
                    this.add(rm.controllerKey(), (Class<? extends Controller>) c);
                    log.debug("成功添加访问路由[" + rm.controllerKey() + "-->" + c.getName() + "];");
                } else {
                    this.add(rm.controllerKey(), (Class<? extends Controller>) c, rm.viewPath());
                    log.debug("成功添加访问路由[" + rm.controllerKey() + "-->" + c + ",返回页面：" + rm.viewPath() + "];");
                }
            }
            log.debug("----访问路由设置完成！----");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 暂时没用 生成controllerKey
     * 
     * @param clazz
     * @return controllerKey
     */
    @SuppressWarnings("unused")
    private String controllerKey(Class<?> clazz) {
        if (!clazz.getSimpleName().endsWith(suffix)) {
            throw new RuntimeException(clazz
                    + " does not has a ControllerBind annotation and it,s name is not end with " + suffix);
        }
        String controllerKey = "/" + StrKit.firstCharToLowerCase(clazz.getSimpleName());
        controllerKey = controllerKey.substring(0, controllerKey.indexOf(suffix));
        return controllerKey;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public void setScanPackagePath(String scanPackagePath) {
        this.scanPackagePath = scanPackagePath;
    }
}