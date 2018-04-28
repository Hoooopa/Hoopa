package com.hoooopa.hoopa.hoopa.bean.cookbean.cooklist;

/**
 * Created by Pray on 2018/4/28.
 */

public class CookFromListBean {

    private String code;

    private boolean charge;

    private String msg;

    private FirstLvResultList result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public FirstLvResultList getResult() {
        return result;
    }

    public void setResult(FirstLvResultList result) {
        this.result = result;
    }

    public boolean isCharge() {
        return charge;
    }

    public void setCharge(boolean charge) {
        this.charge = charge;
    }
}
