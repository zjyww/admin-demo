package com.demo.web.core.controller;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.demo.web.admin.model.SysUser;
import com.demo.web.core.sql.SQLParser;
import com.demo.web.core.sql.SqlResult;
import com.demo.web.core.util.PasswordUtil;
import com.jfinal.core.Controller;
import com.jfinal.ext.anotation.RouteMapping;
import com.jfinal.ext.render.PoiRender;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.render.JsonRender;

/**
 * 描述：基础Controller 所有Controller继承这个
 * 
 */
@RouteMapping(controllerKey = "/")
public class BaseController extends Controller {
    /**
     * 描述：
     */
    protected Log log = Log.getLog(BaseController.class);

    /**
     * 描述：
     */
    RouteMapping routeMapping;

    /**
     * 描述：
     * 
     */
    public BaseController() {
        super();
        routeMapping = this.getClass().getAnnotation(RouteMapping.class);
    }

    /**
     * 描述：前台统一首页地址
     */
    public void index() {
    	render("/login/login.jsp");
    }

    /**
     * 描述：通用的动态条件查询分页方法
     */
    public void paginate() {
        // 获取请求中的所有参数
      /*  int pageNumber = getParaToInt("currentPage", 1);
        int pageSize = getParaToInt("pageSize", 10);
        int dsId = getParaToInt("dsid");
        String dsname = getPara("dsname", "main");
        String prefix = getPara("prefix", "filter");
        Record ds = TplModelDs.DAO.findByIdFCache(dsId);
        String sourceSql = ds.getStr("ds_code");
        Map<String, Object> paraMap = filterQueryParas(prefix, getParaMap());
        SqlResult sqlResult = SQLParser.parse(sourceSql, paraMap);
        String cacheName = ds.getStr("ds_cache_region").toLowerCase();
        StringBuffer cacheKey = new StringBuffer();
        cacheKey.append(dsId);
        cacheKey.append("." + sqlResult.getParameterValueString());
        cacheKey.append("." + pageNumber);
        cacheKey.append("." + pageSize);
        Page<Record> page = Db.use(dsname)
                .paginateByCache(cacheName, cacheKey.toString(), pageNumber, pageSize, sqlResult
                .getSelect(), sqlResult.getSqlExceptSelect(), sqlResult.getParameterValueArray());
        renderJson(page);*/
    }
    
    /**
     * 描述：通用的查询列表方法
     */
    public void list() {
       /* String dsname = getPara("dsname", "main");
        int dsId = getParaToInt("dsid");
        int begin = getParaToInt("begin", 0);//默认为0，不可改
        int end = getParaToInt("end", 0);//默认为0，不可改
        boolean cacheable = getParaToBoolean("cacheable", true);
        String paras = getPara("paras");
        String clearCachePWD = getPara("clearCachePWD");
        List<Record> items = DbUtil.find(dsname, dsId, begin, end, cacheable, paras, clearCachePWD);
        renderJson(items);*/
    }

    /**
     * 描述：通用获取对象方法。
     */
    public void map() {
       /* int dsId = getParaToInt("dsid");
        String dsname = getPara("dsname", "main");
        String paras = getPara("paras");
        boolean cacheable = getParaToBoolean("cacheable", true);
        String clearCachePWD = getPara("clearCachePWD");
        Record r = DbUtil.findFirst(dsname, dsId, paras, cacheable, clearCachePWD);
        renderJson(r);*/
    }
    
    /**
     * 描述：导出excel
     * 
     * @param data
     * @param fileName
     * @param headers
     * @param columns
     */
    public void renderExcel(List<?> data, String fileName, String[] headers, String[] columns) {
        PoiRender excel = PoiRender.me(data);
        excel.fileName(fileName);
        excel.headers(headers);
        excel.columns(columns);
        excel.cellWidth(5000);
        render(excel);
    }

    /**
     * 生成验证码
     */
    public void imgCode() {
//        CaptchaRender img = new CaptchaRender(4);
        renderCaptcha();
//        this.setSessionAttr(CaptchaRender.DEFAULT_CAPTCHA_MD5_CODE_KEY, img.getMd5RandonCode());
    }


    /**
     * 描述：
     * 
     * @param msg
     */
    public void renderJsonError(String msg) {
        msg = msg == null ? "没有任何修改或 服务器错误" : msg;
        renderText("{\"success\":false,\"msg\":\"" + msg + "\"}");
    }

    /**
     * 描述：
     * 
     * @param msg
     */
    public void renderJsonSuccess(String msg) {
        msg = msg == null ? "操作成功" : msg;
        renderText("{\"success\":true,\"msg\":\"" + msg + "\"}");
    }

    /**
     * 描述：获取查询参数
     * 
     * @param parasMap
     * @param prefix 参数前缀
     * @return
     */
    private static final Map<String, Object> filterQueryParas(String prefix, Map<String, String[]> parasMap) {
        String prefixAndDot = prefix + ".";
        Map<String, Object> queryParasMap = new HashMap<String, Object>();
        for (Entry<String, String[]> e : parasMap.entrySet()) {
            String paraKey = e.getKey();
            if (paraKey.startsWith(prefixAndDot)) {
                String paraName = paraKey.substring(prefixAndDot.length());
                String[] paraValue = e.getValue();
                if (paraValue.length > 1) {
                    StringBuffer paraStr = new StringBuffer();
                    for (String para : paraValue) {
                        paraStr.append(para).append(",");
                    }
                    queryParasMap.put(paraName, paraStr.substring(0, paraStr.length() - 1));
                } else {
                    queryParasMap.put(paraName, paraValue[0]);
                }
            }
        }
        return queryParasMap;
    }
    
}