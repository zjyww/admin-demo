package com.jfinal.ext.render;

import com.jfinal.render.IErrorRenderFactory;
import com.jfinal.render.Render;

/**
 * 描述：自定义导常响应工厂类，在webConfig是设置些类
 * 
 */
public class ErrorRenderFactory implements IErrorRenderFactory {
    /**
     * 描述：
     * 
     * @param errorCode
     * @param view
     * @return
     * @see com.jfinal.render.IErrorRenderFactory#getRender(int,
     *      java.lang.String)
     */
    public Render getRender(int errorCode, String view) {
        return new ErrorRender(errorCode, view);
    }
}