package com.jfinal.ext.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.jfinal.kit.PathKit;

/**
 * 功能描述：类操作工具类
 * 
 *        <p>
 *        修改历史：(修改人，修改时间，修改原因/内容)
 *        </p>
 */
public class ClassUtil {
    public static void main(String[] args) throws IOException {
        /*
         * (Class<?> c : getAllSubClasses(pk,A.class)) {
         * System.out.println(c.getName()); } } catch (ClassNotFoundException e)
         * { e.printStackTrace(); }
         */
    }

    /**
     * 迭代查找所有子类
     * 
     * @param pk
     * @param cls
     *            父类的类类别
     * @return
     * @throws ClassNotFoundException
     */
    public static List<Class<?>> getAllSubClasses(String pk, Class<?> cls) throws ClassNotFoundException {
        String path = pk.replace('.', '/');
        File dir = new File(PathKit.getRootClassPath()+'/'+path);
        List<Class<?>> classes = new ArrayList<Class<?>>();
        if (!dir.exists()) {
            return classes;
        }
        for (File f : dir.listFiles()) {
            String fileName = f.getName();
            if (f.isDirectory()) {
                for (Class<?> c : getAllSubClasses(pk + "." + fileName, cls)) {
                    if (cls.isAssignableFrom(c) && !cls.equals(c)) {
                        classes.add(c);
                    }
                }
            }
            if (fileName.endsWith(".class")) {
                Class<?> c = Class.forName(pk + "." + fileName.substring(0, fileName.length() - 6));
                if (cls.isAssignableFrom(c) && !cls.equals(c)) {
                    classes.add(c);
                }
            }
        }
        return classes;
    }

}
