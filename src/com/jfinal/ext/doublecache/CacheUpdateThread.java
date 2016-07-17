package com.jfinal.ext.doublecache;

import java.lang.reflect.Method;
import java.util.concurrent.locks.ReentrantLock;

import com.demo.web.core.util.WebUtil;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.DbKit;
import com.jfinal.plugin.activerecord.DbPro;
import com.jfinal.plugin.activerecord.cache.ICache;
import com.jfinal.plugin.ehcache.CacheKit;

/**
 * 描述：
 * 
 */
public class CacheUpdateThread implements Runnable {
    /**
     * 描述：
     */
    private static final long serialVersionUID = -1967188633920340210L;
    /**
     * 描述：
     */
    private static Log log = Log.getLog(CacheUpdateThread.class);

    /**
     * the key used to get date from cache
     */
    private String cacheKey;
    /**
     * 描述：
     */
    private String methodName;
    /**
     * 描述：
     */
    private Class<?>[] parameterTypes;
    /**
     * 描述：
     */
    private Object[] parameter;
    
    /**
     * 描述：数据源名
     */
    private String dateSourceName;

    /**
     * 描述：
     * 
     * @param cacheKey
     *            : the key used to get date from cache
     * @param methodName
     *            : the name of method which method in
     *            com.jfinal.plugin.activerecord.Db .used to get date from db.
     * @param parameterTypes
     *            : the method's parameter types array
     * @param parameter
     *            : the method's parameter array
     */
    public CacheUpdateThread(
            String dateSourceName,
            String cacheKey,
            String methodName, 
            Class<?>[] parameterTypes,
            Object[] parameter) {
        super();
        this.dateSourceName = dateSourceName;
        this.cacheKey = cacheKey;
        this.methodName = methodName;
        this.parameterTypes = parameterTypes;
        this.parameter = parameter;
        init();
    }

    /**
     * 描述：
     */
    private void init() {
        if (CacheDbUtil.cacheLocks.get(cacheKey) == null) {
            ReentrantLock rl = new ReentrantLock();
            CacheDbUtil.cacheLocks.put(cacheKey, rl);
        }
    }

    /**
     * 描述：
     * @see java.lang.Runnable#run()
     */
    public void run() {
        ICache cache = DbKit.getConfig(dateSourceName).getCache();
        Object result = cache.get("L1Cache", cacheKey);
        // System.out.println("子线程："+Thread.currentThread().getName());
        if (result == null) {
            ReentrantLock lock = CacheDbUtil.cacheLocks.get(cacheKey);
            if (!lock.tryLock()) {
                log.debug(Thread.currentThread().getName() + "已退出。" + lock);
                return;
            }
            try {
                // 1. 执行SQL查询获取数据
                DbPro dp = Db.use(dateSourceName);
                Method m = dp.getClass().getMethod(methodName, parameterTypes);
                result = m.invoke(dp, parameter);
                // 2. 数据填充到 L1Cache
                cache.put("L1Cache", cacheKey, result);
                // 3. 数据填充到 L2Cache
                cache.put("L2Cache", cacheKey, result);
                log.debug(Thread.currentThread().getName() + "更新了缓存:" + cacheKey);
            } catch (Exception e) {
                //出错后，如果缓存中已有数据了，就不做操作，即不更新数据。
                //如果缓存中没有数据（第一次访问时）就存入NULL。这样主线程不会死循环。
                // e.printStackTrace();
                if (!CacheKit.getCacheManager().getCache("L1Cache").isKeyInCache(cacheKey)) {
                    cache.put("L1Cache", cacheKey, null);
                }
                if (!CacheKit.getCacheManager().getCache("L2Cache").isKeyInCache(cacheKey)) {
                    cache.put("L2Cache", cacheKey, null);
                }
                log.error("更新缓存[" + cacheKey + "]失败!异常如下:" + WebUtil.getExceptionCauseStr(e));
            } finally {
                lock.unlock();
            }
        }
    }
}