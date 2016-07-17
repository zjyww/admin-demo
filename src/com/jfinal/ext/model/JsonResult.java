package com.jfinal.ext.model;

/**
 * 返回前台的json
 * 
 */
public class JsonResult implements java.io.Serializable {

    /**
     * 描述：
     */
    private static final long serialVersionUID = -4437028685184313261L;

    /**
     * 描述：
     */
    private boolean success = false;

    /**
     * 描述：
     */
    private String msg = "";

    /**
     * 描述：
     */
    private Object result = null;

    /**
     * @return type
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * @param success
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * @return type
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return type
     */
    public Object getResult() {
        return result;
    }

    /**
     * @param result
     */
    public void setResult(Object result) {
        this.result = result;
    }

}
