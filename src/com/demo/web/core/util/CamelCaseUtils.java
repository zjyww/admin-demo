package com.demo.web.core.util;

/**
 * 描述：驼峰命名法(CamelCase)和下划线风格(UnderScoreCase)字符串之间的转换工具类
 * 
 * CamelCaseUtils.toCamelCase("hello_world") == "helloWorld"
 * CamelCaseUtils. toCapitalizeCamelCase("hello_world") == "HelloWorld"
 * CamelCaseUtils.toUnderScoreCase("helloWorld") = "hello_world"
 */
public class CamelCaseUtils {

    /**
     * 属性'_'
     */
    private static final char SEPARATOR = '_';

    /**
     * 方法toUnderlineName
     * 
     * @param s 传入参数
     * @return 返回值String
     */
    public static String toUnderlineName(String s) {
        if (s == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            boolean nextUpperCase = true;

            if (i < (s.length() - 1)) {
                nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
            }

            if ((i >= 0) && Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    if (i > 0)
                        sb.append(SEPARATOR);
                }
                upperCase = true;
            } else {
                upperCase = false;
            }

            sb.append(Character.toLowerCase(c));
        }

        return sb.toString();
    }

    /**
     * 方法toCamelCase
     * 
     * @param s 传入参数
     * @return 返回值String
     */
    public static String toCamelCase(String s) {
        if (s == null) {
            return null;
        }

        s = s.toLowerCase();

        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    /**
     * 方法toCapitalizeCamelCase
     * 
     * @param s 传入参数
     * @return 返回值String
     */
    public static String toCapitalizeCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = toCamelCase(s);
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

}
