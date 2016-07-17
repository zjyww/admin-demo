package com.demo.web.core.sql;

import java.io.Serializable;

/**
 * 描述：sql参数解释
 * 
 */
public class ParameterResult implements Serializable {
    /**
     * 描述：
     */
    private static final long serialVersionUID = -1625242457407413039L;
    /**
     * 描述：声名段的结果
     */
    private String statementText;
    /**
     * 描述：声名段中参数的名称
     */
    private String paraName;
    /**
     * 描述：声名段中参数的值
     */
    private Object paraValue;

    /**
     * 描述：
     */
    public ParameterResult() {
    }

    /**
     * 描述：
     * @return
     */
    public String getStatementText() {
        return statementText;
    }

    /**
     * 描述：
     * @param statementText
     */
    public void setStatementText(String statementText) {
        this.statementText = statementText;
    }

    /**
     * 描述：
     * @return
     */
    public String getParaName() {
        return paraName;
    }

    /**
     * 描述：
     * @param paraName
     */
    public void setParaName(String paraName) {
        this.paraName = paraName;
    }

    /**
     * 描述：
     * @return
     */
    public Object getParaValue() {
        return paraValue;
    }

    /**
     * @param paraValue
     */
    public void setParaValue(Object paraValue) {
        this.paraValue = paraValue;
    }

}
