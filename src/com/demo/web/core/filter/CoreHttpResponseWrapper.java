package com.demo.web.core.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * 描述：
 * 
 */
public class CoreHttpResponseWrapper extends HttpServletResponseWrapper {

    /**
     * 描述：
     */
    ByteServletOutputStream servletOutputStream;
    /**
     * 描述：
     */
    PrintWriter printWriter;

    /**
     * 方法_构造方法
     * 
     * @param response 传入参数
     * @param buffer 传入参数
     */
    public CoreHttpResponseWrapper(HttpServletResponse response, ByteArrayOutputStream buffer) {
        super(response);
        servletOutputStream = new ByteServletOutputStream(buffer);
    }

    /**
     * 方法getOutputStream
     * 
     * @return 返回值ServletOutputStream
     */
    public ServletOutputStream getOutputStream() throws IOException {
        return servletOutputStream;
    }

    /**
     * 方法getWriter
     * 
     * @return 返回值PrintWriter
     */
    public PrintWriter getWriter() throws IOException {
        if (printWriter == null) {
            printWriter = new PrintWriter(servletOutputStream);
        }
        return printWriter;
    }

    /**
     * 方法flushBuffer
     * 
     */
    public void flushBuffer() throws IOException {
        servletOutputStream.flush();
        if (printWriter != null) {
            printWriter.flush();
        }
    }
}
