package com.wwxn.bms.pojo;

import java.io.Serializable;

public class ResultBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean success;

    private int resultCode;

    private String message;

    private Object data;


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
