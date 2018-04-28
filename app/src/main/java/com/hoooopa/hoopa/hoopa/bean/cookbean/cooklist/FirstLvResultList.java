package com.hoooopa.hoopa.hoopa.bean.cookbean.cooklist;

/**
 * Created by Pray on 2018/4/28.
 */

public class FirstLvResultList {

    private String status;

    private String msg;

    private SecondLvResultList result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public SecondLvResultList getResult() {
        return result;
    }

    public void setResult(SecondLvResultList result) {
        this.result = result;
    }
}
