package com.demo.web.core.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述：CookieUtil
 * 
 */
public class CookieUtil {

    /**
     * 描述：Get cookie value by cookie name.
     * 
     * @param request
     * @param name
     * @param defaultValue
     * @return
     */
    public static String getCookie(HttpServletRequest request, String name, String defaultValue) {
        Cookie cookie = getCookieObject(request, name);
        return cookie != null ? cookie.getValue() : defaultValue;
    }

    /**
     * 描述：Get cookie value by cookie name.
     * 
     * @param request
     * @param name
     * @return
     */
    public static String getCookie(HttpServletRequest request, String name) {
        return getCookie(request, name, null);
    }

    /**
     * 描述：Get cookie value by cookie name and convert to Integer.
     * 
     * @param request
     * @param name
     * @return
     */
    public static Integer getCookieToInt(HttpServletRequest request, String name) {
        String result = getCookie(request, name);
        return result != null ? Integer.parseInt(result) : null;
    }

    /**
     * 描述：Get cookie value by cookie name and convert to Integer.
     * 
     * @param request
     * @param name
     * @param defaultValue
     * @return
     */
    public static Integer getCookieToInt(HttpServletRequest request, String name, Integer defaultValue) {
        String result = getCookie(request, name);
        return result != null ? Integer.parseInt(result) : defaultValue;
    }

    /**
     * 描述：Get cookie value by cookie name and convert to Long.
     * 
     * @param request
     * @param name
     * @return
     */
    public static Long getCookieToLong(HttpServletRequest request, String name) {
        String result = getCookie(request, name);
        return result != null ? Long.parseLong(result) : null;
    }

    /**
     * 描述：Get cookie value by cookie name and convert to Long.
     * 
     * @param request
     * @param name
     * @param defaultValue
     * @return
     */
    public static Long getCookieToLong(HttpServletRequest request, String name, Long defaultValue) {
        String result = getCookie(request, name);
        return result != null ? Long.parseLong(result) : defaultValue;
    }

    /**
     * 描述：Get cookie object by cookie name.
     * 
     * @param request
     * @param name
     * @return
     */
    public static Cookie getCookieObject(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
            for (Cookie cookie : cookies)
                if (cookie.getName().equals(name))
                    return cookie;
        return null;
    }

    /**
     * 描述：Get all cookie objects.
     * 
     * @param request
     * @return
     */
    public static Cookie[] getCookieObjects(HttpServletRequest request) {
        Cookie[] result = request.getCookies();
        return result != null ? result : new Cookie[0];
    }

    /**
     * 描述：Set Cookie.
     * 
     * @param response
     * @param name
     *            : cookie name
     * @param value
     *            : cookie value
     * @param maxAgeInSeconds
     *            -1: clear cookie when close browser. 0: clear cookie
     *            immediately. n>0 : max age in n seconds.
     * @param isHttpOnly
     *            true if this cookie is to be marked as HttpOnly, false
     *            otherwise
     */
    public static void setCookie(HttpServletResponse response, String name, String value, int maxAgeInSeconds,
            boolean isHttpOnly) {
        doSetCookie(response, name, value, maxAgeInSeconds, null, null, isHttpOnly);
    }

    /**
     * 描述：Set Cookie.
     * 
     * @param response
     * @param name
     *            cookie name
     * @param value
     *            cookie value
     * @param maxAgeInSeconds
     *            -1: clear cookie when close browser. 0: clear cookie
     *            immediately. n>0 : max age in n seconds.
     */
    public static void setCookie(HttpServletResponse response, String name, String value, int maxAgeInSeconds) {
        doSetCookie(response, name, value, maxAgeInSeconds, null, null, null);
    }

    /**
     * 描述：Set Cookie to response.
     * 
     * @param response
     * @param cookie
     */
    public static void setCookie(HttpServletResponse response, Cookie cookie) {
        response.addCookie(cookie);
    }

    /**
     * 描述：Set Cookie to response.
     * 
     * @param response
     * @param name
     *            cookie name
     * @param value
     *            cookie value
     * @param maxAgeInSeconds
     *            -1: clear cookie when close browser. 0: clear cookie
     *            immediately. n>0 : max age in n seconds.
     * @param path
     *            see Cookie.setPath(String)
     * @param isHttpOnly
     *            true if this cookie is to be marked as HttpOnly, false
     *            otherwise
     */
    public static void setCookie(HttpServletResponse response, String name, String value, int maxAgeInSeconds,
            String path, boolean isHttpOnly) {
        doSetCookie(response, name, value, maxAgeInSeconds, path, null, isHttpOnly);
    }

    /**
     * 描述：Set Cookie to response.
     * 
     * @param response
     * @param name
     *            cookie name
     * @param value
     *            cookie value
     * @param maxAgeInSeconds
     *            -1: clear cookie when close browser. 0: clear cookie
     *            immediately. n>0 : max age in n seconds.
     * @param path
     *            see Cookie.setPath(String)
     */
    public static void setCookie(HttpServletResponse response, String name, String value, int maxAgeInSeconds,
            String path) {
        doSetCookie(response, name, value, maxAgeInSeconds, path, null, null);
    }

    /**
     * 描述：Set Cookie to response.
     * 
     * @param response
     * @param name
     *            cookie name
     * @param value
     *            cookie value
     * @param maxAgeInSeconds
     *            -1: clear cookie when close browser. 0: clear cookie
     *            immediately. n>0 : max age in n seconds.
     * @param path
     *            see Cookie.setPath(String)
     * @param domain
     *            the domain name within which this cookie is visible; form is
     *            according to RFC 2109
     * @param isHttpOnly
     *            true if this cookie is to be marked as HttpOnly, false
     *            otherwise
     */
    public static void setCookie(HttpServletResponse response, String name, String value, int maxAgeInSeconds,
            String path, String domain, boolean isHttpOnly) {
        doSetCookie(response, name, value, maxAgeInSeconds, path, domain, isHttpOnly);
    }

    /**
     * 描述：Remove Cookie.
     * 
     * @param response
     * @param name
     */
    public static void removeCookie(HttpServletResponse response, String name) {
        doSetCookie(response, name, null, 0, null, null, null);
    }

    /**
     * 描述：Remove Cookie.
     * 
     * @param response
     * @param name
     * @param path
     */
    public static void removeCookie(HttpServletResponse response, String name, String path) {
        doSetCookie(response, name, null, 0, path, null, null);
    }

    /**
     * 描述：Remove Cookie.
     * 
     * @param response
     * @param name
     * @param path
     * @param domain
     */
    public static void removeCookie(HttpServletResponse response, String name, String path, String domain) {
        doSetCookie(response, name, null, 0, path, domain, null);
    }

    /**
     * 描述：Set Cookie
     * 
     * @param response
     * @param name
     * @param value
     * @param maxAgeInSeconds
     * @param path
     * @param domain
     * @param isHttpOnly
     */
    private static void doSetCookie(HttpServletResponse response, String name, String value, int maxAgeInSeconds,
            String path, String domain, Boolean isHttpOnly) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAgeInSeconds);
        // set the default path value to "/"
        if (path == null) {
            path = "/";
        }
        cookie.setPath(path);

        if (domain != null) {
            cookie.setDomain(domain);
        }
        if (isHttpOnly != null) {
            cookie.setHttpOnly(isHttpOnly);
        }
        response.addCookie(cookie);
    }

}
