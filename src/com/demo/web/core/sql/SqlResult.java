package com.demo.web.core.sql;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 描述：sql结果集
 * 
 */
public class SqlResult implements Serializable {
    /**
     * 描述：
     */
    private static final long serialVersionUID = 9111451153700670849L;
    /**
     * 描述：原始sql
     */
    private String sourceSql;
    /**
     * 描述：解释后的最终sql
     */
    private String parsedSql;
    /**
     * 描述：jfinal分页用。SQL中from之前的sql段
     */
    private String select;
    /**
     * 描述：jfinal分页用。SQL中from之后的sql段
     */
    private String sqlExceptSelect;
    
    /**
     * 描述：原始参数map
     */
    private Map<String, Object> paraMap;
    /**
     * 描述：解释后的最终参数值集合
     */
    private List<ParameterResult> paraList;

    public String getSourceSql() {
        return sourceSql;
    }

    public void setSourceSql(String sourceSql) {
        this.sourceSql = sourceSql;
    }

    public String getParsedSql() {
        return parsedSql;
    }

    public void setParsedSql(String parsedSql) {
        this.parsedSql = parsedSql;
    }

    public Map<String, Object> getParaMap() {
        return paraMap;
    }

    public void setParaMap(Map<String, Object> paraMap) {
        this.paraMap = paraMap;
    }

    public List<ParameterResult> getParaList() {
        return paraList;
    }

    public void setParaList(List<ParameterResult> paraList) {
        this.paraList = paraList;
    }

    public Object[] getParameterArray() {
        int size = paraList.size();
        Object[] result = new Object[paraList.size()];

        for (int i = 0; i < size; i++) {
            result[i] = paraList.get(i);
        }
        return result;
    }

    public Object[] getParameterValueArray() {
        int size = paraList.size();
        Object[] result = new Object[paraList.size()];

        for (int i = 0; i < size; i++) {
            result[i] = paraList.get(i).getParaValue();
        }
        return result;
    }
    
    public String getParameterValueString(){
        StringBuilder paraValuesString = new StringBuilder();
        for (ParameterResult para : paraList) {
            paraValuesString
//                .append(para.getParaName())
//                .append(":")
                .append(para.getParaValue())
                .append(".");
        }
        return paraValuesString.substring(0, paraValuesString.length()-1);
    }

    public String getSelect() {
        return parsedSql.substring(0, parsedSql.indexOf("from"));
    }

    public String getSqlExceptSelect() {
        return parsedSql.substring(parsedSql.indexOf("from"));
    }
}
