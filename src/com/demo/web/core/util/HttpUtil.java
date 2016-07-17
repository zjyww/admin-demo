package com.demo.web.core.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

/**
 * 描述：
 */
public class HttpUtil {
    /**
     * 获取客户端IP地址
     * 
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 获取客户端的浏览器
     * 
     * @param request
     * @param type
     * @return
     */
    public static String getClientBrowser(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent").toLowerCase();
        StringTokenizer st = new StringTokenizer(userAgent, ";");
        st.nextToken();
        String brower = "";
        try {
            brower = st.nextToken().trim();
        } catch (Exception e) {
            brower = "未知浏览器";
        }
        // 得到用户的浏览器名
        return brower;
    }

    /**
     * 利用HTTP方式POST参数
     * 
     * @author nice
     * @param params
     * @param url
     * @return
     */
    public static String postParams(List<NameValuePair> params, String url) {
        HttpPost request = new HttpPost(url);
        request.setEntity(HttpUtil.makeEntity(params));
        String result = HttpUtil.queryStringForPost(request);
        return result;
    }

    /**
     * 描述：
     * @param request
     * @return
     */
    public static String queryStringForPost(HttpPost request) {
        String result = null;
        try {
            HttpResponse response = HttpUtil.getHttpResponse(request);
            if (response.getStatusLine().getStatusCode() == 200) {
                result = EntityUtils.toString(response.getEntity());
                return result;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            result = "网络异常！";
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            result = "网络异常！";
            return result;
        }
        return null;
    }

    /**
     * 描述：
     * @param request
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static HttpResponse getHttpResponse(HttpPost request) throws ClientProtocolException, IOException {
        HttpResponse response = new DefaultHttpClient().execute(request);
        return response;
    }

    /**
     * 描述：
     * @param params
     * @return
     */
    public static UrlEncodedFormEntity makeEntity(List<NameValuePair> params) {
        try {
            return new UrlEncodedFormEntity(params, HTTP.UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}