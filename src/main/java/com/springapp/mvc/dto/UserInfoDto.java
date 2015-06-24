package com.springapp.mvc.dto;

import com.springapp.mvc.vo.UserInfoVo;

import java.util.Date;

/**
 * Created by zhangjie on 2015/5/19.
 */
public class UserInfoDto extends UserInfoVo  {

    /**
     * 身份证号
     */
    private String cardNo;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 固话
     */
    private String tel;

    /**\
     * 用户名
     */
    private String userName;

    /**
     * 验证码
     */
    private String authCode;

    private String t;

    /**
     * 登录回调url
     */
    private String callbackUrl;




    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }


    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }
}
