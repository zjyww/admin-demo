package com.jfinal.ext.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.core.JFinal;
import com.jfinal.handler.Handler;

/**
 * 描述：没有找到Action时默认跳转到action/index.jsp。404让jsp自己处理。jfinal不处理no action的404
 * 
 */
public class NoActionSkipHandler extends Handler {

    /**
     * 描述：
     * 
     * @param target
     * @param request
     * @param response
     * @param isHandled
     */
    public void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandled) {
        boolean hasSuffix = (target.indexOf(".") != -1);// 有后缀名的URL
        if (hasSuffix || target.indexOf("druid") != -1) {
            next.handle(target, request, response, isHandled);
            return;
        }
        String[] urlPara = { null };
        Object action = JFinal.me().getAction(target, urlPara);
        if (action == null) {
            isHandled[0] = true;// isHandled[0] = true;请求到此为止，也不会去执行后面的Filter
            target += "index.jsp";
            String queryString = request.getQueryString();
            if (queryString != null) {
                target += ("?" + queryString);
            }
            try {
                response.sendRedirect(JFinal.me().getContextPath() + target);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            next.handle(target, request, response, isHandled);
        }
    }
}
