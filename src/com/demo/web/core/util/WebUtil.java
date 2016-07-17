package com.demo.web.core.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import com.demo.web.core.config.WebConstants;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.StrKit;
import com.jfinal.log.Log;

/**
 * 描述：
 * 
 */
public class WebUtil {
    /**
     * logger
     */
    private static Log log = Log.getLog(WebUtil.class);

    /**
     * 获取异常的祥细信息，用于记录到日志中。 RuntimeException只会在控制台打印，不会记录到日志文件中，所以需要此方法
     * 
     * @param e
     * @return
     */
    public static String getExceptionCauseStr(final Exception e) {
        java.io.StringWriter sw = new java.io.StringWriter();
        e.printStackTrace(new java.io.PrintWriter(sw, true));
        return sw.toString();
    }

    /*
     * 通配符：? 匹配任意一个字符 通配符：* 匹配任意多个字符，但不能跨越目录 通配符：** 可以匹配任意多个字符，可以跨越目录
     * 
     * @param patternUrl
     * 
     * @param url 请求的URL
     * 
     * @return
     * 
     * public static boolean pathMatchesUrl(String patternUrl, String
     * requestUrl) { if ("/**".equals(patternUrl) || "**".equals(patternUrl)) {
     * return true; } PathMatcher pathMatcher = new AntPathMatcher(); return
     * pathMatcher.match(patternUrl, requestUrl); }
     */

    /**
     * 描述：获取访问者IP 在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。
     * 本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)，
     * 如果还不存在则调用Request .getRemoteAddr()。
     * 
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        } else {
            return request.getRemoteAddr();
        }
    }

    /**
     * 从指定集合中递归获取指定节点下的所有子节点 。用于生成json树。生成的树状结构在rootNode中。
     * 
     * @param rootNode
     *            指定节点
     * @param allList
     *            指定集合
     */

    /*
     * public static void recurrenTree(JsonTree rootNode, List<JsonTree>
     * allList){ allList.remove(rootNode);//删除掉本身。 List<JsonTree> tempList = new
     * ArrayList<JsonTree>();//除去已被用过栏目的集合 tempList.addAll(allList);
     * List<JsonTree> children = new ArrayList<JsonTree>();//本级子栏目 for (JsonTree
     * node : allList) { if
     * (rootNode.getEntityId().equals(node.getEntityParentId())){
     * children.add(node); //从allChannelList中删除被加入的栏目。避免不必要的循环
     * tempList.remove(node); } } rootNode.setChildren(children); if
     * (children.size() == 0) { return; } else { for (JsonTree temp : children)
     * { recurrenTree(temp, tempList); } } }
     */

    /**
     * 转换文件大小
     * 
     * @param fileLength
     * @return
     */
    public static String getFormatFileSize(long fileLength) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileLength < 1024) {
            fileSizeString = df.format((double) fileLength) + "B";
        } else if (fileLength < 1048576) {
            fileSizeString = df.format((double) fileLength / 1024) + "K";
        } else if (fileLength < 1073741824) {
            fileSizeString = df.format((double) fileLength / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileLength / 1073741824) + "G";
        }
        return fileSizeString;
    }

    /**
     * 生成静态页
     * 
     * @param urlStr
     * @param filePath
     * @return
     * @return
     * @throws Exception
     */
    public static synchronized void creatHtml(String urlStr, String filePath) {
        HttpURLConnection con = null;
        InputStream in = null;
        try {
            URL url = new URL(urlStr);
            con = (HttpURLConnection) url.openConnection();
            con.setReadTimeout(5 * 1000);
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "");
            con.connect();
            int responseCode = con.getResponseCode();
            if (responseCode == 200) {
                in = con.getInputStream();
                File file = new File(filePath);
                FileUtils.copyInputStreamToFile(in, file);
            } else {
                in = con.getErrorStream();
                log.error("生成静态页时远程主机发生" + responseCode + "错误！地址：" + urlStr);
            }
        } catch (ConnectException e) {
            log.error("生成静态页时连接失败！地址：" + urlStr);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            con.disconnect();
            try {
                in.close();
            } catch (IOException e) {
            }
        }
    }

    /**
     * 压力测试
     */
    public static void yltest() {
        // final File file1 = new File("E:\\New.html");
        // final int b = 0;
        for (int i = 0; i < 150; i++) {
            // 2.创建实现Runnable接口的对象
            final int a = i;
            Thread t = new Thread(new Runnable() {
                public void run() {
                    // for (int j = 0; j < 10; j++) {
                    // System.out.println("Thread=" + a + ";j=" + j);
                    try {// ct_2927_35938 ct_6218_36592
                        final URL url = new URL("http://127.0.0.1:88/fzqsngWeb/getR/ct_6218_36592");
                        // final URL url = new
                        // URL("http://192.168.5.66:8081/fzqsng/siteController.do?queryUser");
                        URLConnection con = url.openConnection();
                        con.setDoOutput(true);
                        con.setRequestProperty("User-Agent", a + "");
                        // InputStream in = (InputStream) con.getContent();
                        con.getContent().toString();
                        // File file = new File("E:\\test\\"+a+j+".html");
                        // FileUtils.copyInputStreamToFile(in, file);
                        // in.close();
                    } catch (Exception e) {
                        // e.printStackTrace();
                    }
                    // }
                }
            }); // 3.创建一个Thread类的对象
            t.start(); // 4.启动线程
        }

    }

    /**
     * 压力测试
     */
    public static void cachetest() {
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new Runnable() {
                public void run() {
                    creatHtml("http://192.168.1.123:88/fzgaweb/pages/xx/subSiteInfoRank.jsp", PathKit.getWebRootPath()
                            + "/subSiteInfoRank.html");

                    // for (int j = 0; j < 120; j++) {
                    // new CacheUpdateThread().run();
                    // }
                }
            });
            t.start(); // 4.启动线程
            // System.err.println(i);
        }
    }

    /**
     * 截取字符串
     * 
     * @param str
     * @param subLenght
     * @param ellipsis
     *            true/false
     * @return 返回合适在页面显示的长度。
     */
    public static String subString4JSPveiw(String str, int subLength, boolean ellipsis) {
        return subString4JSPveiw(str, subLength, ellipsis, null, 0);
    }

    public static String subString4JSPveiw(String str, int subLength, Boolean ellipsis, String objdate, int timeout) {
        int bytesLength = subLength;
        if (str == null) {
            return "";
        }
        if (str.indexOf("&#8226;") != -1) {
            str = str.replace("&#8226;", "•");
        }

        // 2015年7月2日 10:52:46增加 清除HTML标签
        Pattern p_html = Pattern.compile("<[^>]+>", Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(str);
        str = m_html.replaceAll("");

        double d = 0; // 总长度。 length
        int n = 0; // char length
        double othersize = 0;
        if (ellipsis) {
            othersize = 1.5;
        }
        String newImg = showNew(objdate, timeout);
        if (newImg.length() > 0) {
            othersize += 2;
        }

        for (int i = 0; i < str.length(); i++) {
            d = ((int) str.charAt(i)) > 256 ? d + 1 : d + 0.5;
            if (d + (othersize >= 2 ? 2 : 0) > bytesLength) {
                n = (int) (n == 0 ? i - (othersize >= 2 ? 2 : othersize) : n);
            }
        }

        if (n > 0) {
            str = str.substring(0, n);
            if (ellipsis) {
                str += "...";
            }
        }
        return str += newImg;
    }

    /**
     * 
     * @param input
     *            发布时间
     * @param shownewtime
     *            显示天数
     * @return
     */
    public static String showNew(String input, int shownewtime) {
        if (input == null || "".equals(input) || shownewtime <= 0) {
            return "";
        }
        Date inputDate;
        try {
            if (input.length() > 10) {
                inputDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(input);
            } else {
                inputDate = new SimpleDateFormat("yyyy-MM-dd").parse(input);
            }
        } catch (ParseException e) {
            // e.printStackTrace();
            return "";
        }
        boolean overdue = (new Date().getTime() - inputDate.getTime()) / 86400000 < shownewtime;
        // 是否显示new图标。
        String img = "";
        if (shownewtime == -1 || overdue) {
            img = "<img class=\"newimg\" src=\"" + JFinal.me().getContextPath() + "/common/images/news.gif\" ></img>";
        }
        return img;
    }

    /**
     * 过滤请求参数
     * 
     * @param req
     * @throws IOException
     * @throws ServletException
     */
    public static boolean checkBadChar(HttpServletRequest req) {
        // 获得所有请求参数名
        Enumeration params = req.getParameterNames();
        String names = "";
        String values = "";
        String query = req.getQueryString();
        while (params.hasMoreElements()) {
            // 得到参数名
            String name = params.nextElement().toString();
            names = names + name;
            // 得到参数对应值
            String[] value = req.getParameterValues(name);
            for (int i = 0; i < value.length; i++) {
                values = values + value[i];
            }
        }

        if (query != null) {
            if (sqlValidate(req, query) || !Jsoup.isValid(query, Whitelist.none())) {
                return true;
            }
        }
        if (names != "") {
            if (sqlValidate(req, names)) {
                return true;
            }
        }
        if (values != "") {
            if (sqlValidate(req, values) || !Jsoup.isValid(values, Whitelist.none())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 有非法字符就返回true
     * 
     * @param servletRequest
     * @param str
     * @return
     */
    public static boolean sqlValidate(HttpServletRequest servletRequest, String str) {
        if (StrKit.isBlank(str)) {
            return false;
        }
        str = str.toLowerCase();// 统一转为小写
        String badStr = WebConstants.paramFilterChar;

        String[] badStrs = badStr.split(",");
        String lockIp = WebUtil.getIpAddr(servletRequest);
        for (int i = 0; i < badStrs.length; i++) {
            if (str.indexOf(badStrs[i]) != -1) {
                // 记入黑名单。禁访问。
                StringBuffer cause = new StringBuffer("请求的url:").append(servletRequest.getRequestURL().toString())
                        .append("参数：").append("中含有非法字符：").append(badStrs[i]);
                log.error("从ip地址：" + lockIp + cause);
                return true;
            }
        }
        return false;
    }

    /**
     * 描述：验证请求是否来源于站内。防止跨域提交表单
     * 
     * @param request
     * @return true 站内提交，验证通过。false 站外提交，验证失败
     */
    public static boolean validateReferer(HttpServletRequest request) {
        String referer = "";
        boolean referer_sign = true; // true 站内提交，验证通过 //false 站外提交，验证失败
        Enumeration headerValues = request.getHeaders("Referer");
        while (headerValues.hasMoreElements()) {
            referer = (String) headerValues.nextElement();
        }
        // 判断是否存在请求页面
        if (referer == null || referer.length() < 1)
            referer_sign = false;
        else {
            // 判断请求页面和getRequestURI是否相同
            String servername_str = request.getServerName();
            if (servername_str != null || servername_str.length() > 0) {
                int index = 0;
                if (StringUtils.indexOf(referer, "https://") == 0) {
                    index = 8;
                } else if (StringUtils.indexOf(referer, "http://") == 0) {
                    index = 7;
                }
                if (referer.length() - index < servername_str.length()) // 长度不够
                    referer_sign = false;
                else { // 比较字符串（主机名称）是否相同
                    String referer_str = referer.substring(index, index + servername_str.length());
                    if (!servername_str.equalsIgnoreCase(referer_str))
                        referer_sign = false;
                }
            } else {
                referer_sign = false;
            }
        }
        return referer_sign;
    }

    public static void main(String[] args) {
        /*
         * String unsafe =
         * "filename=1\" onclick=\"prompt(5627), append: \" onclick=\"prompt(5627), 133"
         * ; boolean safe = Jsoup.isValid(unsafe, Whitelist.none());
         * System.out.println(safe);
         */
        // creatHtml("http://www.fjhi.gov.cn", "d:/index222.html");
        yltest();
        // cachetest();
        // String a =
        // "2013年10月25日上午，市局召,.,开竞,,,,ss争上岗科级领dw导任前集c,,,,..体谈话会。市局党委委员、政治部主任叶宝华，人事训练处处长徐恒祥，市局监察室副主任陈成东及有关单位领导出席了会议。";
        // String a =
        // "<h3><span style=\"float:left;\">专题专栏</span>份全</h3>
        // <a
        // href=\"<%=path%>/site/fz/xx/ztindex.jsp\" target=\"_top\" style=\"color:#346AB2;\">更多&gt;&gt;&nbsp;</a>";
        // String b = "争上岗科级上午";
        // System.out.println((int)b.charAt(0));
        // System.out.println(Jsoup.clean(a, Whitelist.none()));
        // System.out.println(subString4JSPveiw(b,5,true));
        /*
         * String s1 = s.replaceFirst(",", ""); for (int i = 0; i < args.length;
         * i++) { String string = args[i]; System.out.println(string); }
         */

        // yltest();
        // cachetest();

        //
        // try {
        // System.out.println(PasswordEncoder.encodePassword("123456", "cce"));
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
        // try {
        // System.out.println(PasswordEncoder.isPasswordValid("ceb4f32325eda6142bd65215f4c0f371",
        // "admin", "admin1"));
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
        // System.out.println(pathMatchesUrl("/sys/sysUser/*save*","/sys/sysUser/saveOrupdateBefore"));;

        // try {
        // System.out.println(new
        // String("360鎴浘20120411084435043.jpg".getBytes(), "UTF-8"));
        // } catch (UnsupportedEncodingException e) {
        // e.printStackTrace();
        // }
        //
        // for (int i = 0; i < 4; i++) {
        // System.out.println(a.get(i)+","+b.get(i)+","+c.get(i));
        // }
    }
}