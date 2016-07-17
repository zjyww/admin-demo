package com.jfinal.ext.render;

import java.io.IOException;
import java.io.PrintWriter;

import com.jfinal.render.Render;
import com.jfinal.render.RenderException;
import com.jfinal.render.RenderFactory;

/**
 * 描述：自定义ErrorRender
 * 
 */
public class ErrorRender extends Render {

    /**
     * 描述：contentType
     */
    protected static String contentType = "text/html; charset=" + getEncoding();
    /**
     * 描述：
     */
    protected static String jsonContentType = "application/json;charset=" + getEncoding();

    /**
     * 描述：
     */
    protected static String json404 = "404 Not Found";
    /**
     * 描述：
     */
    protected static String json500 = "500 Internal Server Error";

    /**
     * 描述：
     */
    protected static String json401 = "401 Unauthorized";
    /**
     * 描述：
     */
    protected static String json403 = "403 Forbidden";
    /**
     * 描述：version
     */
    protected static String version = "<center><b>Powered by</b></center>";

    /**
     * 描述：html404
     */
    protected static String html404 = "<html><head><title>404 Not Found</title></head>"
            + "<body bgcolor='white'><center><h1>404 Not Found</h1></center><hr>"
            + version + "</body></html>";
    /**
     * 描述：html500
     */
    protected static String html500 = "<html><head><title>500 Internal Server Error</title></head>"
            + "<body bgcolor='white'><center><h1>500 Internal Server Error</h1></center><hr>"
            + version + "</body></html>";

    /**
     * 描述：html401
     */
    protected static String html401 = "<html><head><title>401 Unauthorized</title></head>"
            + "<body bgcolor='white'><center><h1>401 Unauthorized</h1></center><hr>"+ version + "</body></html>";
    /**
     * 描述：html403
     */
    protected static String html403 = "<html><head><title>403 Forbidden</title></head>"
            + "<body bgcolor='white'><center><h1>403 Forbidden</h1></center><hr>"
            + version + "</body></html>";

    /**
     * 描述：errorCode
     */
    protected int errorCode;

    public ErrorRender(int errorCode, String view) {
        this.errorCode = errorCode;
        this.view = view;
    }

    /**
     * 描述：
     * @see com.jfinal.render.Render#render()
     */
    public void render() {

        response.setStatus(getErrorCode()); // HttpServletResponse.SC_XXX_XXX

        // render with view
        String view = getView();
        if (view != null) {
            RenderFactory.me().getRender(view).setContext(request, response).render();
            return;
        }
        String xRequestedWith = request.getHeader("X-Requested-With");
        boolean isAjax = ("XMLHttpRequest").equalsIgnoreCase(xRequestedWith);

        PrintWriter writer = null;
        try {
            if (isAjax) {
                String userAgent = request.getHeader("User-Agent").toLowerCase();
                boolean forIE = userAgent.indexOf("msie") > 0 ? true : false;
                response.setHeader("Pragma", "no-cache");
                response.setHeader("Cache-Control", "no-cache");
                response.setDateHeader("Expires", 0);
                response.setContentType(forIE ? contentType : jsonContentType);
                writer = response.getWriter();
                writer.write(getErrorJson());
                writer.flush();
            } else {
                response.setContentType(contentType);
                writer = response.getWriter();
                writer.write(getErrorHtml());
                writer.flush();
            }
        } catch (IOException e) {
            throw new RenderException(e);
        } finally {
            if (writer != null)
                writer.close();
        }
    }

    /**
     * 描述：
     * @return
     */
    public String getErrorHtml() {
        int errorCode = getErrorCode();
        if (errorCode == 404)
            return html404;
        if (errorCode == 500)
            return html500;
        if (errorCode == 401)
            return html401;
        if (errorCode == 403)
            return html403;
        return "<html><head><title>" + errorCode + " Error</title></head><body bgcolor='white'><center><h1>"
                + errorCode + " Error</h1></center><hr>" + version + "</body></html>";
    }

    /**
     * 描述：
     * @return
     */
    public String getErrorJson() {
        int errorCode = getErrorCode();
        if (errorCode == 404)
            return json404;
        if (errorCode == 500)
            return json500;
        if (errorCode == 401)
            return json401;
        if (errorCode == 403)
            return json403;
        return errorCode + " Error";
    }

    /**
     * 描述：
     * @return
     */
    public int getErrorCode() {
        return errorCode;
    }
}
