package com.demo.web.core.config;

import java.text.SimpleDateFormat;

/**
 * 工程是的常量
 */
public class WebConstants {
    /**
     * FRONT_PATH
     */
    public static final String FRONT_PATH = "jhtml";
    /**
     * format
     */
    public static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    /**
     * SQL_NAME_FORMAT
     */
    public static final SimpleDateFormat SQL_NAME_FORMAT = new SimpleDateFormat("yyyy_MM_dd");
    /**
     * EMAIL_USERNAME
     */
    public static String emailUsername;
    /**
     * EMAIL_PASSWORD
     */
    public static String emailPassword;
    /**
     * EMAIL_PROTOCOL
     */
    public static String emailProtocol;
    /**
     * EMAIL_HOST
     */
    public static String emailHost;
    /**
     * 要过滤非法字符。多多用逗号分开。如：<,>,'
     */
    public static String paramFilterChar;


    /**
     * FILE_UPLOAD_PATH
     */
    public static String fileUploadPath;
    
    /**
     * 描述：后台工程名
     */
    public static String platName;

}
