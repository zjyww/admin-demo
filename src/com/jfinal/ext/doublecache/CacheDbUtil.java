package com.jfinal.ext.doublecache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import com.demo.web.core.util.WebUtil;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.DbKit;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.cache.ICache;
import com.jfinal.plugin.ehcache.CacheKit;

/**
 * 描述：从缓存中获取数据的工具类， 此类中的方法对应类com.jfinal.plugin.activerecord.Db中的方法。
 * 
 */
public class CacheDbUtil {
    /**
     * 描述：
     */
    private static Log log = Log.getLog(CacheDbUtil.class);
    /**
     * 描述：
     */
    public static final Object[] NULL_PARA_ARRAY = new Object[0];
    /**
     * 描述：缓存更新锁。有锁的线程才可更新缓存。防止缓存被多线程击穿。
     */
    public static Map<String, ReentrantLock> cacheLocks = new HashMap<String, ReentrantLock>();

    /**
     * 从缓存中获取String类型字段
     * 
     * @param cacheKey
     * @param sql
     * @param paras
     * @return
     */
    public static <T> T queryColumn(String cacheKey, String sql) {
        return findFromDoubleCache(null,"queryColumn", cacheKey, sql, NULL_PARA_ARRAY);
    }
    /**
     * 从缓存中获取String类型字段
     * 
     * @param cacheKey
     * @param sql
     * @param paras
     * @return
     */
    public static <T> T queryColumnUseDsName(String dateSourceName,String cacheKey, String sql) {
        return findFromDoubleCache(dateSourceName,"queryColumn", cacheKey, sql, NULL_PARA_ARRAY);
    }


    /**
     * 从缓存中获取String类型字段
     * 
     * @param cacheKey
     * @param sql
     * @param paras
     * @return
     */
    public static <T> T queryColumn(String cacheKey, String sql, Object... paras) {
        return findFromDoubleCache(null,"queryColumn", cacheKey, sql, paras);
    }
    /**
     * 从缓存中获取String类型字段
     * 
     * @param cacheKey
     * @param sql
     * @param paras
     * @return
     */
    public static <T> T queryColumnUseDsName(String dateSourceName,String cacheKey, String sql, Object... paras) {
        return findFromDoubleCache(dateSourceName,"queryColumn", cacheKey, sql, paras);
    }

    /**
     * 从缓存中获取String类型字段
     * 
     * @param cacheKey
     * @param sql
     * @param paras
     * @return
     */
    public static String queryStr(String cacheKey, String sql) {
        return findFromDoubleCache(null,"queryStr", cacheKey, sql, NULL_PARA_ARRAY);
    }
    /**
     * 从缓存中获取String类型字段
     * 
     * @param cacheKey
     * @param sql
     * @param paras
     * @return
     */
    public static String queryStrUseDsName(String dateSourceName,String cacheKey, String sql) {
        return findFromDoubleCache(dateSourceName,"queryStr", cacheKey, sql, NULL_PARA_ARRAY);
    }

    /**
     * 从缓存中获取String类型字段
     * 
     * @param cacheKey
     * @param sql
     * @param paras
     * @return
     */
    public static String queryStr(String cacheKey, String sql, Object... paras) {
        return findFromDoubleCache(null,"queryStr", cacheKey, sql, paras);
    }
    /**
     * 从缓存中获取String类型字段
     * 
     * @param cacheKey
     * @param sql
     * @param paras
     * @return
     */
    public static String queryStrUseDsName(String dateSourceName,String cacheKey, String sql, Object... paras) {
        return findFromDoubleCache(dateSourceName,"queryStr", cacheKey, sql, paras);
    }

    /**
     * 从缓存中获取Long类型字段
     * 
     * @param cacheKey
     * @param sql
     * @param paras
     * @return
     */
    public static Integer queryInt(String cacheKey, String sql) {
        return findFromDoubleCache(null,"queryInt", cacheKey, sql, NULL_PARA_ARRAY);
    }
    /**
     * 从缓存中获取Long类型字段
     * 
     * @param cacheKey
     * @param sql
     * @param paras
     * @return
     */
    public static Integer queryIntUseDsName(String dateSourceName,String cacheKey, String sql) {
        return findFromDoubleCache(dateSourceName,"queryInt", cacheKey, sql, NULL_PARA_ARRAY);
    }

    /**
     * 从缓存中获取Long类型字段
     * 
     * @param cacheKey
     * @param sql
     * @param paras
     * @return
     */
    public static Integer queryInt(String cacheKey, String sql, Object... paras) {
        return findFromDoubleCache(null,"queryInt", cacheKey, sql, paras);
    }
    /**
     * 从缓存中获取Long类型字段
     * 
     * @param cacheKey
     * @param sql
     * @param paras
     * @return
     */
    public static Integer queryIntUseDsName(String dateSourceName,String cacheKey, String sql, Object... paras) {
        return findFromDoubleCache(dateSourceName,"queryInt", cacheKey, sql, paras);
    }

    /**
     * 从缓存中获取Long类型字段
     * 
     * @param cacheKey
     * @param sql
     * @param paras
     * @return
     */
    public static Long queryLong(String cacheKey, String sql) {
        return findFromDoubleCache(null,"queryLong", cacheKey, sql, NULL_PARA_ARRAY);
    }
    /**
     * 从缓存中获取Long类型字段
     * 
     * @param cacheKey
     * @param sql
     * @param paras
     * @return
     */
    public static Long queryLongUseDsName(String dateSourceName,String cacheKey, String sql) {
        return findFromDoubleCache(dateSourceName,"queryLong", cacheKey, sql, NULL_PARA_ARRAY);
    }

    /**
     * 从缓存中获取Long类型字段
     * 
     * @param cacheKey
     * @param sql
     * @param paras
     * @return
     */
    public static Long queryLong(String cacheKey, String sql, Object... paras) {
        return findFromDoubleCache(null,"queryLong", cacheKey, sql, paras);
    }
    /**
     * 从缓存中获取Long类型字段
     * 
     * @param cacheKey
     * @param sql
     * @param paras
     * @return
     */
    public static Long queryLongUseDsName(String dateSourceName,String cacheKey, String sql, Object... paras) {
        return findFromDoubleCache(dateSourceName,"queryLong", cacheKey, sql, paras);
    }

    /**
     * 从缓存中获取Record
     * 
     * @param cacheKey
     * @param sql
     * @param paras
     * @return
     */
    public static Record findFirst(String cacheKey, String sql) {
        return findFromDoubleCache(null, "findFirst", cacheKey, sql, NULL_PARA_ARRAY);
    }
    /**
     * 从缓存中获取Record
     * 
     * @param cacheKey
     * @param sql
     * @param paras
     * @return
     */
    public static Record findFirstUseDsName(String dateSourceName,String cacheKey, String sql) {
        return findFromDoubleCache(dateSourceName,"findFirst", cacheKey, sql, NULL_PARA_ARRAY);
    }

    /**
     * 从缓存中获取Record
     * 
     * @param cacheKey
     * @param sql
     * @param paras
     * @return
     */
    public static Record findFirst(String cacheKey, String sql, Object... paras) {
        return findFromDoubleCache(null,"findFirst", cacheKey, sql, paras);
    }
    /**
     * 从缓存中获取Record
     * 
     * @param cacheKey
     * @param sql
     * @param paras
     * @return
     */
    public static Record findFirstUseDsName(String dateSourceName, String cacheKey, String sql, Object... paras) {
        return findFromDoubleCache(dateSourceName,"findFirst", cacheKey, sql, paras);
    }

    /**
     * 从缓存中获取List<Record>
     * 
     * @param cacheKey
     * @param sql
     * @param paras
     * @return
     */
    public static List<Record> findList(String cacheKey, String sql) {
        return findFromDoubleCache(null,"find", cacheKey, sql, NULL_PARA_ARRAY);
    }
    /**
     * 从缓存中获取List<Record>
     * 
     * @param cacheKey
     * @param sql
     * @param paras
     * @return
     */
    public static List<Record> findListUseDsName(String dateSourceName, String cacheKey, String sql) {
        return findFromDoubleCache(dateSourceName,"find", cacheKey, sql, NULL_PARA_ARRAY);
    }

    /**
     * 从缓存中获取List<Record>
     * 
     * @param cacheKey
     * @param sql
     * @param paras
     * @return
     */
    public static List<Record> findList(String cacheKey, String sql, Object... paras) {
        return findFromDoubleCache(null,"find", cacheKey, sql, paras);
    }
    /**
     * 从缓存中获取List<Record>
     * 
     * @param cacheKey
     * @param sql
     * @param paras
     * @return
     */
    public static List<Record> findListUseDsName(String dateSourceName, String cacheKey, String sql, Object... paras) {
        return findFromDoubleCache(dateSourceName,"find", cacheKey, sql, paras);
    }

    /**
     * 从缓存中获取Page<Record>
     * 
     * @param cacheKey
     * @param pageNumber
     * @param pageSize
     * @param select
     * @param sqlExceptSelect
     * @param paras
     * @return
     */
    public static Page<Record> paginate(String cacheKey, int pageNumber, int pageSize, String select,
            String sqlExceptSelect, Object... paras) {
        return findFromDoubleCache(null,"paginate", cacheKey, pageNumber, pageSize, select, sqlExceptSelect, paras);
    }
    /**
     * 从缓存中获取Page<Record>
     * 
     * @param cacheKey
     * @param pageNumber
     * @param pageSize
     * @param select
     * @param sqlExceptSelect
     * @param paras
     * @return
     */
    public static Page<Record> paginateUseDsName(String dateSourceName, String cacheKey,
            int pageNumber, int pageSize, String select,
            String sqlExceptSelect, Object... paras) {
        return findFromDoubleCache(dateSourceName,"paginate", cacheKey,
                pageNumber, pageSize, select, sqlExceptSelect, paras);
    }

    /**
     * 从1缓存中获取数据，没有就从2级缓中获取，并从数据库中获取数据更新1级，2级缓存，。
     * 
     * @param methodName
     *            对应com.jfinal.plugin.activerecord.Db 中的方法名（以byCache结尾的除外）。
     * @param cacheKey
     * @param sql
     * @param paras
     *            无参数时可以传：CacheDbutil.NULL_PARA_ARRAY
     * @return
     */
    private static <T> T findFromDoubleCache(String dateSourceName, String methodName,
            String cacheKey, String sql, Object... paras) {
        return findFromDoubleCache(dateSourceName,methodName, cacheKey, -1, -1, sql, null, paras);
    }

    /**
     * 描述：
     * @param cacheKey
     * @param pageNumber
     * @param pageSize
     * @param sql
     * @param sqlExceptSelect
     * @param paras
     * @return
     */
    private static <T> T findFromDoubleCache(String dateSourceName,String methodName, String cacheKey,
            Integer pageNumber, Integer pageSize,
            String sql, String sqlExceptSelect, Object... paras) {
        if (dateSourceName==null||"".equals(dateSourceName)) {
            dateSourceName = DbKit.MAIN_CONFIG_NAME;
        }
        ICache cache = DbKit.getConfig(dateSourceName).getCache();
        T result = cache.get("L1Cache", cacheKey);// 1.从一级缓存中读取数据，有则直接返回
        if (result == null) {// 2.L1Cache一级缓存中没数据则启动数据更新线程，并从 L2Cache 读数据，有则返回
            CacheUpdateThread updateThread = null;
            if (pageNumber != -1) {
                updateThread = new CacheUpdateThread(
                        dateSourceName,
                        cacheKey,
                        methodName, 
                        new Class[] { int.class, int.class,String.class, String.class, Object[].class },
                        new Object[] { pageNumber, pageSize, sql,sqlExceptSelect, paras }
                );
            } else {
                updateThread = new CacheUpdateThread(
                        dateSourceName,
                        cacheKey,
                        methodName,
                        new Class[] { String.class, Object[].class },
                        new Object[] { sql, paras });
            }
            Thread thread = new Thread(updateThread);
            thread.setName(Thread.currentThread().getName()+"_updateThread");
            thread.start();
            result = cache.get("L2Cache", cacheKey);
            if (result == null) {// 3.L2Cache无数据
                try {
                    thread.join();// 4.合并父子线程。让主线程等子线程执行完，再从二级缓存中取数据。
                    result = cache.get("L1Cache", cacheKey);// 5.再从一级缓存中取数据。
                    if(result == null){
                        int tt = 0;
                        while (tt < 3000) {// 防止tomcat刚开时，L2Cache中没有缓存。
                            tt += 100;
//                            System.out.println("主线程2："+Thread.currentThread().getName()+"sssssssssssss");
                            // 5.子线程执行完，再判断二级缓存中有没有数据，有就返回，没有就一直取，直到有。
                            if (CacheKit.getCacheManager().getCache("L2Cache").isKeyInCache(cacheKey)) {
                                break;
                            } else {
                                Thread.sleep(100);
                            }
                        }
                        result = cache.get("L2Cache", cacheKey);// 5.再从二级缓存中取数据。
                    }
                } catch (Exception e) {
                    log.error(WebUtil.getExceptionCauseStr(e));
                }
            }
        }
        return result;
    }
}
