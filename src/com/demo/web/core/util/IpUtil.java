package com.demo.web.core.util;

/**
 * 描述：IpUtil类
 * 
 */
public class IpUtil {
    /**
     * 判断IP限制
     * 
     * iptype
     *            <IP限制类型>
     * iptext
     *            <IP限制地址 >
     * @param ip
     * @return 判断IP限制
     */
    public static boolean convertIp(Object type, String ipText, String ip) {
        try {
            if (!"null".equals(type) && !"null".equals(ipText) && null != type && !"".equals(type) && null != ip
                    && !"".equals(ip)) {
                int ipType = Integer.parseInt(type.toString());
                boolean isok = isIp(ipText, ip);
                if (ipType == 1) {// IP白名单
                    if (isok) {
                        return true;
                    } else {
                        return false;
                    }
                } else if (ipType == 2) {// IP黑名单
                    if (isok) {
                        return false;
                    } else {
                        return true;
                    }
                } else {// 默认
                    return true;
                }
            } else {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 方法isIp
     * 
     * @param ipText 传入参数
     * @param ip 传入参数
     * @return 返回值boolean
     */
    public static boolean isIp(String ipText, String ip) {
        if (null == ipText || "" == ipText) {
            return false;
        }
        boolean isok = false;
        String[] iptext1 = ipText.split(";");
        for (int i = 0; i < iptext1.length; i++) {
            String[] iptext2 = iptext1[i].split(",");
            for (int j = 0; j < iptext2.length; j++) {
                if (iptext2[j].indexOf("-") >= 0) {// IP网段
                    int ip1 = Integer.parseInt(iptext2[j].split("-")[0].split("\\.")[3]);
                    int ip2 = Integer.parseInt(iptext2[j].split("-")[1].split("\\.")[3]);
                    int ip3 = Integer.parseInt(ip.split("\\.")[3]);
                    if (iptext2[j].split("-")[0].split("\\.")[0].equals(ip.split("\\.")[0])
                            && iptext2[j].split("-")[0].split("\\.")[1].equals(ip.split("\\.")[1])
                            && iptext2[j].split("-")[0].split("\\.")[2].equals(ip.split("\\.")[2])) {
                        if (ip1 > ip2) {
                            if (ip3 >= ip2 && ip3 <= ip1) {
                                isok = true;
                                return isok;
                            } else {
                                isok = false;
                            }
                        } else {
                            if (ip3 <= ip2 && ip3 >= ip1) {
                                isok = true;
                                return isok;
                            } else {
                                isok = false;
                            }
                        }
                    } else {
                        isok = false;
                    }
                } else {// 单个IP
                    if (ip.equals(iptext2[j])) {
                        isok = true;
                        return isok;
                    } else {
                        isok = false;
                    }
                }
            }
        }
        return isok;
    }
}