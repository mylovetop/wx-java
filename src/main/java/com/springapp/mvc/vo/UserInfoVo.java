package com.springapp.mvc.vo;

/**
 * Created by zhangjie on 2015/5/18.
 */
public class UserInfoVo extends AbstractVo {
    private static final long serialVersionUID  = 1L;

    /**
     * 微信openid
     */
    protected String openId;
    /**
     * 微信accessToken
     */
    private String accessToken;
    /**
     * 微信昵称
     */
    private String nickName;
    /**
     * 微信里获取的性别
     */
    private int sex;
    /**
     * 微信省份
     */
    private String province;
    /**
     * 微信城市
     */
    private String city;
    /**
     * 微信国家
     */
    private String country;
    /**
     * 微信的头像
     */
    private String headImgUrl;
    /**
     * 微信的头像
     */
    private String privilege;
    private String unionid;
    private String refreshToken;
    /**
     * 微信应用授权作用域
     */
    private String scope;
    /**
     * 用户验证的手机号码
     */
    private String phone;

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }


    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
