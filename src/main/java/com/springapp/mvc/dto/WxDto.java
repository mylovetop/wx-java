package com.springapp.mvc.dto;

/**
 * Created by zhangjie on 2015/6/3.
 */
public class WxDto extends AbstractDto {

    /**
     * 用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
     */
    private String openId;
    /**
     * 应用授权作用域，snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid），
     * snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息）
     */
    private String scope = "snsapi_userinfo";
    /**
     * 重定向后会带上state参数，开发者可以填写任意参数值
     */
    private String state = "xxx";
    /**
     * 直接在微信打开链接，可以不填此参数。做页面302重定向时候，必须带此参数
     */
    private String weChatRedirect = "#wechat_redirect";
    /**
     * 用户刷新access_token
     */
    private String refreshToken;
    /**
     * access_token接口调用凭证超时时间，单位（秒）
     */
    private String expiresIn;
    private String accessToken;
    private String phone;
    /**
     * 公众号的唯一标识
     */
    private String appId = "xx";
    /**
     * 公众号的appsecret
     */
    private String secret = "xx";
    private String code;
    /**
     * authorization_code
     */
    private String grantType = "authorization_code";
    /**
     * 授权后重定向的回调链接地址
     */
    private String redirectUri = "https%3A%2F%2Fwx.xx.com.cn%2FwxOAuth.html";
    /**
     * 返回类型 默认code
     */
    private String responseType = "code";

    /**
     * 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
     */
    private String signature;
    /**
     * 时间搓
     */
    private String timestamp;
    /**
     * 随机数
     */
    private String nonce;
    /**
     * 随机字符
     */
    private String echostr;

    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     */
    private String sex;
    /**
     * 用户个人资料填写的省份
     */
    private String province;
    /**
     * 普通用户个人资料填写的城市
     */
    private String city;
    /**
     * 国家，如中国为CN
     */
    private String country;
    /**
     * 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空
     */
    private String headImgUrl;
    /**
     * 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
     */
    private String privilege;


    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getWeChatRedirect() {
        return weChatRedirect;
    }

    public void setWeChatRedirect(String weChatRedirect) {
        this.weChatRedirect = weChatRedirect;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
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

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getEchostr() {
        return echostr;
    }

    public void setEchostr(String echostr) {
        this.echostr = echostr;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
