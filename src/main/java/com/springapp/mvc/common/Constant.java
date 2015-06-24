package com.springapp.mvc.common;

/**
 * Created by adm on 2015/5/13.
 */
public class Constant {

    /**
     * 验证码
     */
    public static String KEY_AUTH_CODE = "keyAuthCode";
    public static String KEY_WX_APP_ID = "appid";
    public static String KEY_WX_REDIRECT_URI = "redirect_uri";
    public static String KEY_WX_RESPONSE_TYPE = "response_type";
    public static String KEY_WX_SCOPE = "scope";
    public static String KEY_WX_STATE = "state";
    public static String KEY_WX_WE_CHAT_REDIRECT = "%23wechat_redirect";
    public static String KEY_WX_REFRESH_TOKEN = "refresh_token";
    public static String KEY_WX_ACCESS_TOKEN = "access_token";
    public static String KEY_WX_GRANT_TYPE = "grant_type";
    public static String KEY_WX_OPEN_ID = "openid";
    public static String KEY_WX_SECRET = "secret";
    public static String KEY_WX_CODE = "code";

    public static String KEY_WX_DTO = "wxDto";


    public static String KEY_MENU_ENT = "menuEnt";
    public static String KEY_MENU_PERSON = "menuPerson";
    public static String KEY_CALL_BACK_URL = "callbackUrl";
    public static String KEY_URL_HOME = "urlHome";
    /**
     * 微信开发者模式开启使用的token
     */
    public static String KEY_WX_TOKEN = "spring";


    /**
     * 1、微信授权
     * 参数：
     *  appid	是	公众号的唯一标识
     *  redirect_uri	是	授权后重定向的回调链接地址
     *  response_type	是	返回类型，请填写code
     *  scope	是	应用授权作用域，snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid），snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息）
     *  state	否	重定向后会带上state参数，开发者可以填写任意参数值
     *  #wechat_redirect	否	直接在微信打开链接，可以不填此参数。做页面302重定向时候，必须带此参数
     *
     *  返回结果：
     *   跳转到auth2.php页面，执行
     *    界面上显示的就是code，这时候通过右上角按钮中的复制链接，得到链接如下：
     *    http://www.xx.com/oauth2.php?code=xxx&state=1
     *    我们成功得到了code了。
     *    "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx9a8dec6daa2276e4&redirect_uri=http://www.xx.com/oauth2.php&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect";
     */
    public static String KEY_URL_WX_OAUTH = "https://open.weixin.qq.com/connect/oauth2/authorize?";

    /**
     * 2、使用code换取access_token
     * 参数：
     *   appid	是	公众号的唯一标识
     *   secret	是	公众号的appsecret
     *   code	是	填写第一步获取的code参数
     *   grant_type	是	填写为authorization_code
     *
     *   返回结果：
     *   {
     *      "access_token": "xx",
     *      "expires_in": 7200,
     *      "refresh_token": "xx",
     *      "openid": "xx",
     *      "scope": "snsapi_userinfo,"
     *  }
     *
     *   access_token	网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
     *   expires_in	access_token接口调用凭证超时时间，单位（秒）
     *   refresh_token	用户刷新access_token
     *   openid	用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
     *   scope	用户授权的作用域，使用逗号（,）分隔
     *   "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
     */
    public static String KEY_URL_WX_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?";

    /**
     * 3、刷新access_token
     * 参数：
     *   appid	是	公众号的唯一标识
     *   grant_type	是	填写为refresh_token
     *   refresh_token	是	填写通过access_token获取到的refresh_token参数
     *   https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN
     */
    public static String KEY_URL_WX_REFRESH_TOKEN = "https://api.weixin.qq.com/sns/oauth2/refresh_token?";

    /**
     * 使用access_token获取用户信息
     * 网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
     * 返回结果：
     * {
     "openid": "xx",
     "nickname": "宝宝",
     "sex": 1,
     "language": "简体中文",
     "city": "xx",
     "province": "xx",
     "country": "xx",
     "headimgurl": "http://xx",
     "privilege": []
     }
     https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID"
     */
    public static String KEY_URL_WX_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?";

    /**
     * 自定义创建菜单
     * http请求方式：POST（请使用https协议）
     * https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN
     */
    public static String KEY_URL_WX_MENU = "https://api.weixin.qq.com/cgi-bin/menu/create?";


    public static final int STATUSCODE_200 = 200;
    public static final int STATUSCODE_404 = 404;
    public static final int STATUSCODE_500 = 500;
    public static final int STATUSCODE_501 = 501;
    public static final int STATUSCODE_502 = 502;

}
