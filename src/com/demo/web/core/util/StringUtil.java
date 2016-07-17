package com.demo.web.core.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;

/**
 * StringUtil类
 * 
 * @version 1.0
 */
public class StringUtil {

    // 国标码和区位码转换常量
    /**
     * 属性160
     */
    private static final int GBSPDIFF = 160;
    // 存放国标一级汉字不同读音的起始区位码
    /**
     * 属性2078,
     */
    private static final int[] SECPOSVALUELIST = { 1601, 1637, 1833, 2078, 2274, 2302, 2433, 2594
        , 2787, 3106, 3212,3472, 3635, 3722, 3730, 3858, 4027, 4086, 4390, 4558, 4684, 4925, 5249, 5600 };

    /**
     * 属性'f',
     */
    private static final char[] FIRSTLETTER = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'o'
        ,'p', 'q', 'r', 's', 't', 'w', 'x', 'y', 'z' };

    /**
     * 获取一个字符串的拼音码的首字母 比方说:输入胡裕必胜 返回的结果将会是:hybs
     * 
     * @param oriStr
     * @return
     */
    public static String getFirstLetter(String oriStr) {
        String str = oriStr.toLowerCase();
        StringBuffer buffer = new StringBuffer();
        char ch;
        char[] temp;
        for (int i = 0; i < str.length(); i++) { // 依次处理str中每个字符
            ch = str.charAt(i);
            temp = new char[] { ch };
            byte[] uniCode = new String(temp).getBytes();
            if (uniCode[0] < 128 && uniCode[0] > 0) { // 非汉字
                buffer.append(temp);
            } else {
                buffer.append(convert(uniCode));
            }
        }
        return buffer.toString();
    }

    /**
     * 获取一个汉字的拼音首字母。
     * 
     * 　　* GB码两个字节分别减去160，转换成10进制码组合就可以得到区位码
     * 
     * 　　* 例如汉字“你”的GB码是0xC4/0xE3，分别减去0xA0（160）就是0x24/0x43
     * 
     * 　　* 0x24转成10进制就是36，0x43是67，那么它的区位码就是3667，在对照表中读音为‘n’ 　
     */
    private static char convert(byte[] bytes) {
        char result = '-';
        int secPosValue = 0;
        int i;
        for (i = 0; i < bytes.length; i++) {
            bytes[i] -= GBSPDIFF;
        }
        secPosValue = bytes[0] * 100 + bytes[1];
        for (i = 0; i < 23; i++) {
            if (secPosValue >= SECPOSVALUELIST[i] && secPosValue < SECPOSVALUELIST[i + 1]) {
                result = FIRSTLETTER[i];
                break;
            }
        }
        return result;
    }

    /**
     * 方法convertQuot
     * 
     * @param orgStr
     *            传入参数
     * @return 返回值String
     */
    public static String convertQuot(String orgStr) {
        return orgStr.replace("'", "\\'").replace("\"", "\\\"");
    }

    /**
     * 获取加密配置为SHA-256的加密码
     * 
     * @param inputStr
     * @return
     */
    public static synchronized String encryptSha256(String inputStr) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(inputStr.getBytes("UTF-8"));
            return new String(Base64.encodeBase64(digest));
        } catch (Exception e) {
        }
        return null;
    }
    /**
     * 获取加密为MD5的算法字符串
     * 
     * @param inputStr
     * @return
     */
    public static String encryptMd5(String inputStr) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(inputStr.getBytes("utf8"));
            StringBuilder ret = new StringBuilder(bytes.length << 1);
            for (int i = 0; i < bytes.length; i++) {
                ret.append(Character.forDigit((bytes[i] >> 4) & 0xf, 16));
                ret.append(Character.forDigit(bytes[i] & 0xf, 16));
            }
            return ret.toString();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 方法htmlEntityToString
     * 
     * @param dataStr
     *            传入参数
     * @return 返回值String
     */
    public static String htmlEntityToString(String dataStr) {
        int start = 0;
        int end = 0;
        StringBuffer buffer = new StringBuffer();
        while (start > -1) {
            int system = 10;
            if (start == 0) {
                int t = dataStr.indexOf("&#");
                if (start != t)
                    start = t;
            }
            end = dataStr.indexOf(";", start + 2);
            String charStr = "";
            if (end != -1) {
                charStr = dataStr.substring(start + 2, end);

                char s = charStr.charAt(0);
                if ((s == 'x') || (s == 'X')) {
                    system = 16;
                    charStr = charStr.substring(1);
                }
            }
            try {
                char letter = (char) Integer.parseInt(charStr, system);
                buffer.append(new Character(letter).toString());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

            start = dataStr.indexOf("&#", end);
            if (start - end > 1) {
                buffer.append(dataStr.substring(end + 1, start));
            }

            if (start == -1) {
                int length = dataStr.length();
                if (end + 1 != length)
                    buffer.append(dataStr.substring(end + 1, length));
            }
        }

        return buffer.toString();
    }

    /**
     * 方法stringToHtmlEntity
     * 
     * @param str
     *            传入参数
     * @return 返回值String
     */
    public static String stringToHtmlEntity(String str) {
        char c;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); ++i) {
            c = str.charAt(i);

            switch (c) {
                case '\n':
                    sb.append(c);
                    break;
                case '<':
                    sb.append("&lt;");
                    break;
                case '>':
                    sb.append("&gt;");
                    break;
                case '&':
                    sb.append("&amp;");
                    break;
                case '\'':
                    sb.append("&apos;");
                    break;
                case '"':
                    sb.append("&quot;");
                    break;
                default:
                    if ((c < ' ') || (c > '~')) {
                        sb.append("&#x");
                        sb.append(Integer.toString(c, 16));
                        sb.append(';');
                    } else {
                        sb.append(c);
                    }
            }
        }
        return sb.toString();
    }

    /**
     * 方法stringToUnicode
     * 
     * @param s
     *            传入参数
     * @return 返回值String
     */
    public static String stringToUnicode(String s) {
        String unicode = "";
        char[] charAry = new char[s.length()];
        for (int i = 0; i < charAry.length; ++i) {
            charAry[i] = s.charAt(i);
            unicode = unicode + "\\u" + Integer.toString(charAry[i], 16);
        }
        return unicode;
    }

    /**
     * 方法unicodeToString
     * 
     * @param unicodeStr
     *            传入参数
     * @return 返回值String
     */
    public static String unicodeToString(String unicodeStr) {
        StringBuffer sb = new StringBuffer();
        String[] str = unicodeStr.toUpperCase().split("\\\\U");
        for (int i = 0; i < str.length; ++i) {
            if (str[i].equals(""))
                continue;
            char c = (char) Integer.parseInt(str[i].trim(), 16);
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * 方法main
     * 
     * @param args
     *            传入参数
     */
    public static void main(String[] args) {
        String vm = "abc.em";
        System.out.println(stringToHtmlEntity(vm));
    }

    /**
     * 把html转换成文本
     * 
     * @param inputString
     * @return
     */
    public static String html2Text(String inputString) {
        String htmlStr = inputString;
        String textStr = "";
        try {
            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
            String regEx_html = "<[^>]+>";
            Pattern p_script = Pattern.compile(regEx_script, 2);
            Matcher m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll("");
            Pattern p_style = Pattern.compile(regEx_style, 2);
            Matcher m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll("");
            Pattern p_html = Pattern.compile(regEx_html, 2);
            Matcher m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll("");
            textStr = htmlStr;
        } catch (Exception e) {
            System.err.println("Html2Text: " + e.getMessage());
        }

        return textStr;
    }

    /**
     * 方法formatMsg
     * 
     * @param msgWithFormat
     *            传入参数
     * @return 返回值StringBuilder
     */
    public static StringBuilder formatMsg(CharSequence msgWithFormat, boolean autoQuote, Object[] args) {
        int argsLen = args.length;
        boolean markFound = false;
        StringBuilder sb = new StringBuilder(msgWithFormat);
        if (argsLen > 0) {
            for (int i = 0; i < argsLen; i++) {
                String flag = "%" + (i + 1);
                int idx = sb.indexOf(flag);

                while (idx >= 0) {
                    markFound = true;
                    sb.replace(idx, idx + 2, toString(args[i], autoQuote));
                    idx = sb.indexOf(flag);
                }
            }
            if ((args[(argsLen - 1)] instanceof Throwable)) {
                StringWriter sw = new StringWriter();
                ((Throwable) args[(argsLen - 1)]).printStackTrace(new PrintWriter(sw));
                sb.append("\n").append(sw.toString());
            } else if ((argsLen == 1) && (!markFound)) {
                sb.append(args[(argsLen - 1)].toString());
            }
        }
        return sb;
    }

    /**
     * 方法formatMsg
     * 
     * @param msgWithFormat
     *            传入参数
     * @param args
     *            传入参数
     * @return 返回值StringBuilder
     */
    public static StringBuilder formatMsg(String msgWithFormat, Object[] args) {
        return formatMsg(new StringBuilder(msgWithFormat), true, args);
    }

    /**
     * 方法toString
     * 
     * @param obj
     *            传入参数
     * @param autoQuote
     *            传入参数
     * @return 返回值String
     */
    public static String toString(Object obj, boolean autoQuote) {
        StringBuilder sb = new StringBuilder();
        if (obj == null) {
            sb.append("NULL");
        } else if ((obj instanceof Object[])) {
            for (int i = 0; i < ((Object[]) obj).length; i++) {
                sb.append(((Object[]) obj)[i]).append(", ");
            }
            if (sb.length() > 0)
                sb.delete(sb.length() - 2, sb.length());
        } else {
            sb.append(obj.toString());
        }
        if ((autoQuote) && (sb.length() > 0) && ((sb.charAt(0) != '[') || (sb.charAt(sb.length() - 1) != ']'))
                && ((sb.charAt(0) != '{') || (sb.charAt(sb.length() - 1) != '}'))) {
            sb.insert(0, "[").append("]");
        }
        return sb.toString();
    }

    /**
     * 字符串数组转为字符串
     * 
     * @param array
     * @param split
     * @return
     * @author LH Sep 11, 2011 9:05:01 PM
     */
    public static String getStringByArray(String[] array, String split) {
        String str = "";
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                str += array[i] + split;
            }
            str = str.substring(0, str.length() - split.length());
        }
        return str;
    }

    /**
     * 将数组转换成以逗号拼接的字符串
     * 
     * @param array
     * @return
     */
    public static String getStringByArray(String[] array) {
        StringBuffer str = new StringBuffer();
        for (String a : array) {
            str.append(a).append(",");
        }
        if (str.length() > 0) {
            str.deleteCharAt(str.length() - 1);
        }
        return str.toString();
    }

    /**
     * 字符串集合转成字符串
     * 
     * @param list
     * @param split
     * @return LH Sep 11, 2011 9:05:14 PM
     */
    public static String getStringByList(List<String> list, String split) {
        String str = "";
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                str += list.get(i) + split;
            }
            if (str.length() > split.length()) {
                str = str.substring(0, str.length() - split.length());
            }
        }
        return str;
    }

    /**
     * 方法convertClassFieldName
     * 
     * @param clazz
     *            传入参数
     * @return 返回值String[]
     */
    @SuppressWarnings("unchecked")
    public static String[] convertClassFieldName(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();// 根据Class对象获得属性 私有的也可以获得
        List<String> list = new ArrayList<String>();
        int j = 0;
        for (Field f : fields) {
            String name = f.getName();
            if (name.startsWith("wf")) {
                list.add(convertFieldName(name));
                j++;
            }
        }
        String[] fieldArr = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            fieldArr[i] = list.get(i);
        }
        return fieldArr;
    }

    /**
     * 方法convertFieldName
     * 
     * @param name
     *            传入参数
     * @return 返回值String
     */
    public static String convertFieldName(String name) {
        String str = "";
        char[] names = name.toCharArray();
        StringBuffer append = new StringBuffer("");
        for (int i = 0; i < names.length; i++) {
            if (Character.isUpperCase(names[i])) {
                append.append("_").append(String.valueOf(names[i]).toLowerCase());
            } else {
                append.append(names[i]);
            }
        }
        str = append.toString();
        append = null;
        return str;
    }

    /**
     * 方法nullToString
     * 
     * @param str
     *            传入参数
     * @return 返回值String
     */
    public static String nullToString(String str) {
        return str == null ? "" : str;
    }

    /**
     * 方法nullORSpaceToDefault
     * 
     * @param str
     *            传入参数
     * @param default_value
     *            传入参数
     * @return 返回值String
     */
    public static String nullORSpaceToDefault(String str, String default_value) {
        return str == null || str.trim().equals("") ? default_value : str;
    }

    /*
     * 通用状态显示
     * 
     * @param String型 STR1------提示消息字符串
     * 
     * @param String型 STR2------提示消息字符串
     * 
     * @param String型 SIGN1-----状态标志值
     * 
     * @param String型 SIGN2-----状态标志值
     * 
     * @return 返回字符串STR1或STR2
     */
    public static String commShowST(String SIGN1, String SIGN2, String STR1, String STR2) {
        return SIGN1.equals(SIGN2) ? STR1 : STR2;
    }

    /**
     * 把字符串转成Long数组,这个用于批量删除
     * 
     * @param str
     *            :字符串数组
     * @return
     */
    public static Long[] convertStringToLong(String[] str) {
        Long[] ids = new Long[str.length];
        for (int i = 0; i < str.length; i++) {
            ids[i] = Long.parseLong(str[i]);
        }
        return ids;
    }

    /**
     * 格式化字符串为金钱类型
     * 
     * @param value
     * @return
     */
    public static Double parseDouble(String value) {
        DecimalFormat formator = new DecimalFormat("###,###,###.##");
        try {
            return formator.parse(value).doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    /**
     * 格式化double类型
     * 
     * @param value
     * @return
     */
    public static Double formatDouble(Double value) {
        DecimalFormat formator = new DecimalFormat("#.##");
        return Double.parseDouble(formator.format(value));
    }

    /**
     * 获取格式化Double数据后的字符串,用金钱方式显示
     * 
     * @param value
     * @return
     */
    public static String getStrOfDouble(Double value) {
        DecimalFormat formator = new DecimalFormat("###,###,##0.00");
        return formator.format(value);
        // NumberFormat numberFormat =
        // NumberFormat.getCurrencyInstance(Locale.CHINA);
        // return numberFormat.format(value);
    }

    /**
     * 字符串替换
     * 
     * @param replaceStr
     *            :要替换的字符串
     * @param head
     *            :字符串头
     * @param tail
     *            :字符串尾部
     * @return
     */
    public static String replace(String targetStr, String replaceStr, String head, String tail) {
        while (targetStr.lastIndexOf(tail) != -1 && targetStr.lastIndexOf(head) != -1) {
            String frontSql = targetStr.substring(0, targetStr.lastIndexOf(tail) - 1);
            frontSql = frontSql.substring(0, frontSql.lastIndexOf(head));
            String lastSql = targetStr.substring(targetStr.lastIndexOf(tail) + tail.length() + 1);
            targetStr = frontSql + replaceStr + lastSql;
        }
        return targetStr;
    }

    /**
     * 判断字符串是否是数字类型
     * 
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    /**
     * 将传入的字符串格式例如a,b,c,转化SQL语句IN sql语句使用('a','b','c')
     */
    public static String getValueArray(String values) {
        StringBuffer sb = new StringBuffer();
        String[] valuesArray = values.split(",");
        for (String value : valuesArray) {
            sb.append(",'" + value + "'");
        }
        sb.delete(0, 1);
        sb.insert(0, "(");
        sb.append(")");
        return sb.toString();
    }

    /**
     * 反格式化byte
     * 
     * @param s
     * @return
     */
    public static byte[] hex2byte(String s) {
        byte[] src = s.toLowerCase().getBytes();
        byte[] ret = new byte[src.length / 2];
        for (int i = 0; i < src.length; i += 2) {
            byte hi = src[i];
            byte low = src[i + 1];
            hi = (byte) ((hi >= 'a' && hi <= 'f') ? 0x0a + (hi - 'a') : hi - '0');
            low = (byte) ((low >= 'a' && low <= 'f') ? 0x0a + (low - 'a') : low - '0');
            ret[i / 2] = (byte) (hi << 4 | low);
        }
        return ret;
    }

    /**
     * 二进制转字符串
     * 
     * @param b
     * @return
     */
    public static String byte2hex(byte[] b) {
        StringBuffer sb = new StringBuffer();
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1) {
                sb.append("0" + stmp);
            } else {
                sb.append(stmp);
            }
        }
        return sb.toString();
    }

    /**
     * 根据索引位置进行字符的替换
     * 
     *            :索引位置
     * @param orignalStr
     *            :原始字符串
     * @param replaceStr
     *            :要替换成什么样的串
     * @return
     */
    public static String replace(int index, String orignalStr, String replaceStr) {
        StringBuffer sb = new StringBuffer(orignalStr);
        sb.deleteCharAt(index);
        sb.insert(index, replaceStr);
        return sb.toString();
    }

    /**
     * @Title: stringStripSpacebar
     * @Description:清除字符串前后空格
     * @return
     * @date 2014年6月16日 18:39:10
     */
    public static String stringStripSpacebar(String a) {
        char[] aa = a.toCharArray();
        // 代替前全角空格和半角空格为半角空格
        for (int i = 0; i < aa.length; i++) {
            if (aa[i] == '　' || aa[i] == ' ')
                aa[i] = ' ';
            else
                break;
        }
        // 代替后全角空格和半角空格为半角空格
        for (int i = aa.length - 1; i >= 0; i--) {
            if (aa[i] == '　' || aa[i] == ' ')
                aa[i] = ' ';
            else
                break;
        }
        a = new String(aa).trim();
        return a;
    }

    /**
     * 
     * 描述 获取格式化的字符串,例如原值为10， 长度为6,那么格式化为000010
     * 
     * @created 2015年3月27日 下午6:15:07
     * @param formatLength
     * @param value
     * @return
     */
    public static String getFormatNumber(int formatLength, String value) {
        int oldLength = value.length();
        if (oldLength < formatLength) {
            StringBuffer zeros = new StringBuffer("");
            for (int i = 0; i < formatLength - oldLength; i++) {
                zeros.append("0");
            }
            zeros.append(value);
            return zeros.toString();
        } else {
            return value;
        }
    }
}