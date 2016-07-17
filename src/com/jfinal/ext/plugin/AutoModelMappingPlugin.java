package com.jfinal.ext.plugin;

import java.util.List;

import com.jfinal.ext.anotation.ModelMapping;
import com.jfinal.ext.utils.ClassUtil;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.IDataSourceProvider;
import com.jfinal.plugin.activerecord.Model;

/**
 * 描述：自动添加表与model的映射
 * 
 */
public class AutoModelMappingPlugin extends ActiveRecordPlugin {

    /**
     * 描述：
     */
    private static Log log = Log.getLog(AutoModelMappingPlugin.class);


    /**
     * 描述：
     * @param configName
     * @param dataSourceProvider
     * @param transactionLevel
     */
    public AutoModelMappingPlugin(String configName, IDataSourceProvider dataSourceProvider, int transactionLevel) {
        super(configName, dataSourceProvider, transactionLevel);
    }


    /**
     * 描述：
     * @param configName
     * @param dataSourceProvider
     */
    public AutoModelMappingPlugin(String configName, IDataSourceProvider dataSourceProvider) {
        super(configName, dataSourceProvider);
    }

    /**
     * 描述：
     * @param dataSourceProvider
     */
    public AutoModelMappingPlugin(IDataSourceProvider dataSourceProvider) {
        super(dataSourceProvider);
    }


    /**
     * 自动映射表与模型的关系
     * 
     * @param scanPackagePath
     */
    @SuppressWarnings("unchecked")
    public void autoMapping(String scanPackagePath) {
        log.debug("----开始自动映射表...----");
        try {
            String[] packagePathArray = scanPackagePath.split(",");
            for (int i = 0; i < packagePathArray.length; i++) {
                String packagePath = packagePathArray[i];
                List<Class<?>> modelClasses = ClassUtil.getAllSubClasses(packagePath, Model.class);
                for (Class<?> c : modelClasses) {
                    ModelMapping tm = c.getAnnotation(ModelMapping.class);
                    if (tm == null) {
                        log.error("错误：类[" + c.getName() + "]没有配制'@TableMapping' 注释,将无法完成自动映射！");
                    } else {
                        this.addMapping(tm.tableName(), tm.pkName(), (Class<? extends Model<?>>) c);
                        log.debug("成功:将表" + tm.tableName() + "映射到实体类" + c.getName() + "上，主键id为：" + tm.pkName());
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        log.debug("----自动映射表结束!----");
    }

}
