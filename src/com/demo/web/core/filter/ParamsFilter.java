package com.demo.web.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.web.core.util.WebUtil;
import com.jfinal.log.Log;

/**
 * 描述： 过滤jsp上的参数，防sql注入与跨站脚本
 * 
 */
public class ParamsFilter implements Filter {
    /**
     * 描述：
     */
    private static Log log = Log.getLog(ParamsFilter.class);

    /**
     * 方法init
     * 
     * @param config 传入参数
     */
    public void init(FilterConfig config) throws ServletException {
    }

    /**
     * 方法doFilter
     * 
     * @param request 传入参数
     * @param response 传入参数
     * @param chain 传入参数
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String url = req.getRequestURL().toString();
        
        if (url.indexOf("/admin/") == -1) {
            log.debug("ParamsFilter:" + req.getRequestURL());
            // 过滤请求参数。已过滤的url加个标识，判断有标识的就不要再过滤了。
            if (WebUtil.checkBadChar(req)) {
                res.sendRedirect(req.getContextPath() + "/error.html");
                return;
            }
        }
        chain.doFilter(req, res);
    }

    /**
     * 方法destroy
     * 
     */
    public void destroy() {
    }

}