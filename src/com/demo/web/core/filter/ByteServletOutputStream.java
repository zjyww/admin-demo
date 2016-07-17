package com.demo.web.core.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;


/**
 * 描述：ByteServletOutputStream类
 * 
 * @created 2015-3-3 上午11:46:35
 */
public class ByteServletOutputStream extends ServletOutputStream {

    /**
     * 描述：
     */
    ByteArrayOutputStream baos;

    /**
     * 方法_构造方法
     * 
     * @param baos 传入参数
     */
    public ByteServletOutputStream(ByteArrayOutputStream baos) {
        super();
        this.baos = baos;
    }

    /**
     * 方法write
     * 
     * @param b 传入参数
     */
    public void write(int b) throws IOException {
        baos.write(b);
    }
}
