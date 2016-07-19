package com.demo.web.core.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import com.demo.web.core.util.WebUtil;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.log.Log;

/**
 * 描述：GlobalInterceptor类
 * 
 */
public class GlobalInterceptor implements Interceptor {
    /**
     * 方法Logger.getLogger
     * 
     * @param GlobalInterceptor.class 传入参数
     * @return 返回值=
     */
    private static Log log = Log.getLog(GlobalInterceptor.class);

    /**
     * 方法intercept
     * 
     * @param ai 传入参数
     */
    public void intercept(Invocation ai) {
        Controller c = ai.getController();
        HttpServletRequest request = c.getRequest();
        String url = request.getRequestURL().toString();
        log.debug("GlobalInterceptor:" + url );
        try {
            if (url.indexOf("/admin/") == -1) {
                String paraUrl = c.getPara();
                if (WebUtil.sqlValidate(request, paraUrl) || WebUtil.checkBadChar(request)) {
                    throw new RuntimeException("包含非法参数的请求！");
                }
                if(paraUrl!=null){
                    if (!Jsoup.isValid(paraUrl, Whitelist.none())) {
                        throw new RuntimeException("包含非法参数的请求！");
                    }
                }
                c.setAttr("filted", "true");
            }else{
            	if(request.getSession().getAttribute("loginUser") == null){
            		ai.getController().redirect("/login/index");
            		return;
            	}
            }
            ai.invoke();
        } catch (Exception e) {
            log.error(WebUtil.getExceptionCauseStr(e));
            throw e;
        }
    }

}
