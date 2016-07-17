package com.demo.web.core.sql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bsh.EvalError;
import bsh.Interpreter;


/**
 * 描述：动态SQL解释
 * ${paraName:java运算表达式:queryValue}如不指定queryValue默认为paraName对应的值。
 */
public class SQLParser {
    /**
     * 描述：声名开始标示符
     */
    private static String statementStart = "[";
    /**
     * 描述：声名结束标示符
     */
    private static String statementEnd = "]";
    /**
     * 描述：表达式开始标示符
     */
    private static String expressionStart = "${";
    /**
     * 描述：表达式结束标示符
     */
    private static String expressionEnd = "}";
    /**
     * 描述：表达式中的分割符
     */
    private static String separatorToken = ":";

    /**
     * 描述：
     * @param args
     */
    public static void main(String[] args) {
        //${paraName:java运算表达式:queryValue}如不指定queryValue默认为paraName对应的值。
        String parseSql = "select * from tb_business_notice " +
                "where 1=1 [and bid=${bid}] [and bname =${bname:\"sdd\".equals(bname1):\"aa\"}] order by id desc";
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("bname", "sdd");
        SqlResult pr = parse(parseSql,paraMap);
        System.out.println(pr.getParsedSql());
        System.out.println(pr.getParameterValueString());
    }
//select * from tb_business_notice where 1=1 [and bid=${bid}] [and bname =${bname}] order by id desc

    /**
     * 描述：解释SQL
     * @param sourceSql 如 "select * from tb_business_notice " +
     *           "where 1=1 [and bid=${bid}] [and bname =${bname:\"sdd\".equals(bname):\"aa\"}] order by id desc";
     *   ${paraName:java运算表达式:queryValue}如不指定queryValue默认为paraName对应的值。
     * @param paraMap
     * @return
     */
    public static SqlResult parse(final String sourceSql, final Map<String, Object> paraMap) {
        List<ParameterResult> paraList = new ArrayList<ParameterResult>();
        StringBuilder builder = new StringBuilder();
        if (sourceSql != null && sourceSql.length() > 0) {
            char[] src = sourceSql.toCharArray();
            int offset = 0;
            int start = sourceSql.indexOf(statementStart, offset);
            while (start > -1) {
                if (start > 0 && src[start - 1] == '\\') {
                    // the variable is escaped. remove the backslash.
                    builder.append(src, offset, start - 1).append(statementStart);
                    offset = start + statementStart.length();
                } else {
                    int end = sourceSql.indexOf(statementEnd, start);
                    if (end == -1) {
                        builder.append(src, offset, src.length - offset);
                        offset = src.length;
                    } else {
                        builder.append(src, offset, start - offset);
                        offset = start + statementStart.length();
                        String statementText = new String(src, offset, end - offset);
                        ParameterResult parameter = parseParameter(statementText,paraMap);
                        if (parameter!=null) {
                            paraList.add(parameter);
                            builder.append(parameter.getStatementText());
                        }
                        offset = end + statementEnd.length();
                    }
                }
                start = sourceSql.indexOf(statementStart, offset);
            }
            if (offset < src.length) {
                builder.append(src, offset, src.length - offset);
            }
        }
        String sql = builder.toString();
        Pattern p = Pattern.compile("\\s+");
        Matcher m = p.matcher(sql);
        String parsedSql= m.replaceAll(" ");
        SqlResult sqlResult = new SqlResult();
        sqlResult.setSourceSql(sourceSql);
        sqlResult.setParsedSql(parsedSql);
        sqlResult.setParaMap(paraMap);
        sqlResult.setParaList(paraList);
        return sqlResult;
    }

    /**
     * 描述：解释参数
     * @param statementText
     * @param paraMap
     * @return
     */
    private static ParameterResult parseParameter(String statementText,final Map<String, Object> paraMap) {
//        StringBuilder builder = new StringBuilder();
        ParameterResult parameter = null;
        char[] src = statementText.toCharArray();
        int start = statementText.indexOf(expressionStart);
        int end = statementText.indexOf(expressionEnd);
        String expression = statementText.substring(start,end+expressionEnd.length());
        String expressionText = expression.replace(expressionStart, "").replace(expressionEnd, "");
        String[] expressionArray = expressionText.split(separatorToken);
        String paraName = expressionArray[0];
        Object paraValue = paraMap.get(paraName);
        if (paraValue!=null&&(!"".equals(paraValue))) {
            if (expressionArray.length>1) {//${paraName:java运算表达式:queryValue}如不指定queryValue默认为paraName对应的值。
                String javaExpression = expressionArray[1];
                boolean result = runExpression(javaExpression,paraMap);
                if (result) {
                    parameter = new ParameterResult();
                    String parsedStatementText = statementText.replace(expression, "?");
                    parameter.setStatementText(parsedStatementText);
                    Object queryValue = expressionArray.length==3?expressionArray[2]:paraValue;
                    parameter.setParaName(paraName);
                    parameter.setParaValue(queryValue);
                }
            } else {//${paraName}
                parameter = new ParameterResult();
                String parsedStatementText = statementText.replace(expression, "?");
                parameter.setStatementText(parsedStatementText);
                parameter.setParaName(paraName);
                parameter.setParaValue(paraValue);
            }
        }
        return parameter;
    }
    
    /**
     * 描述：执行SQL中的java 运算表达式
     * @param javaExpression
     * @param paraMap
     * @return
     */
    private static boolean runExpression(String javaExpression, Map<String, Object> paraMap) {
        StringBuffer exeCode = new StringBuffer("rs=(").append(javaExpression).append(")");
        Interpreter it = new Interpreter();
        Iterator<String> keys = paraMap.keySet().iterator();
        while(keys.hasNext()) {
            String key = (String) keys.next();
            Object value = paraMap.get(key);
            try {
                it.set(key, value);
            } catch (EvalError e) {
                System.out.println(2);
                e.printStackTrace();
            }
        }
        try {
            Object s = it.eval(exeCode.toString());
            System.out.println(s);
            return (Boolean) it.get("rs");
        } catch (EvalError e) {
            e.printStackTrace();
        }
        return false;
    }


}
