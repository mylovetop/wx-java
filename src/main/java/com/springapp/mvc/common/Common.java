package com.springapp.mvc.common;

import com.springapp.mvc.dto.WxDto;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangjie on 2015/6/3.
 */
public class Common {

    private static Logger logger= LoggerFactory.getLogger(Common.class);
    /**
     * 1、微信授权
     * @return
     */
    public static String wxUrlOAuth(WxDto wxDto){
        StringBuilder sb = new StringBuilder();
        sb.append(Constant.KEY_URL_WX_OAUTH);

        //https://open.weixin.qq.com/connect/oauth2/authorize?appid=xx&redirect_uri=https%3A%2F%2Fwx.xxx.com.cn%2FwxOAuth.html&response_type=code&scope=snsapi_userinfo&state=xxx&%23wechat_redirect
        jointParams(Constant.KEY_WX_APP_ID, wxDto.getAppId(), sb);
        jointParams(Constant.KEY_WX_REDIRECT_URI, wxDto.getRedirectUri(), sb);
        jointParams(Constant.KEY_WX_RESPONSE_TYPE, wxDto.getResponseType(), sb);
        jointParams(Constant.KEY_WX_SCOPE, wxDto.getScope(), sb);
        jointParams(Constant.KEY_WX_STATE, wxDto.getState(), sb);
        sb.append(Constant.KEY_WX_WE_CHAT_REDIRECT);

        return  sb.toString();
    }

    /**
     * 2、使用1 返回的code 换取 access_token
     * @return
     */
    public static String wxUrlAccessToken(WxDto wxDto){
        StringBuilder sb = new StringBuilder();
        sb.append(Constant.KEY_URL_WX_ACCESS_TOKEN);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(Constant.KEY_WX_APP_ID, wxDto.getAppId());
        map.put(Constant.KEY_WX_SECRET, wxDto.getSecret());
        map.put(Constant.KEY_WX_CODE, wxDto.getCode());
        map.put(Constant.KEY_WX_GRANT_TYPE, wxDto.getGrantType());
        sb.append(jointParamsMap(map));
        return sb.toString();
    }

    /**
     * code换取access_token
     * @param wxDto
     * @return
     */
    public static   WxDto getAccessToken(WxDto wxDto){
        WxDto retVal = null;
        try {
            String json = HttpClientUtil.doGet(Common.wxUrlAccessToken(wxDto), new HashMap<String, Object>());
            ObjectMapper objectMapper = new ObjectMapper();
            Map map = objectMapper.readValue(json, Map.class);
            retVal = new WxDto();
            retVal.setAccessToken(map.get("access_token").toString());
            retVal.setExpiresIn(map.get("expires_in").toString());
            retVal.setRefreshToken(map.get("refresh_token").toString());
            retVal.setOpenId(map.get("openid").toString());
            retVal.setScope(map.get("scope").toString());
        } catch (Exception e){
            logger.error("httpClient close error=" + e);
        }
        return retVal;
    }

    /**
     * 3、刷新access_token
     * @return
     */
    public static String wxUrlRefreshToken(WxDto wxDto){
        StringBuilder sb = new StringBuilder();
        sb.append(Constant.KEY_URL_WX_REFRESH_TOKEN);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(Constant.KEY_WX_APP_ID, wxDto.getAppId());
        map.put(Constant.KEY_WX_GRANT_TYPE, wxDto.getGrantType());
        map.put(Constant.KEY_WX_STATE, wxDto.getState());
        map.put(Constant.KEY_WX_REFRESH_TOKEN, wxDto.getRefreshToken());

        sb.append(jointParamsMap(map));
        return sb.toString();
    }

    /**
     * 4、用户信息
     * @return
     */
    public static String wxUrlUserInfo(WxDto wxDto){
        StringBuilder sb = new StringBuilder();
        sb.append(Constant.KEY_URL_WX_USER_INFO);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(Constant.KEY_WX_ACCESS_TOKEN, wxDto.getAccessToken());
        map.put(Constant.KEY_WX_OPEN_ID, wxDto.getOpenId());
        sb.append(jointParamsMap(map));
        return sb.toString();
    }



    public static void jointParams(String key, String val, StringBuilder sb){
        sb.append(key);
        sb.append("=");
        sb.append(val);
        sb.append("&");
    }

    public static String jointParamsMap(Map<String, Object> map){
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : map.entrySet()){
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
            sb.append("&");
        }
        return sb.toString();
    }

    /**
     * 把空对象设置为默认值， 让jsp页面不需要再去判断设默认显示值
     * @param bean
     */
    public static void setObjectNullPropertyVal(Object bean){
        try {
            Class clazz = (Class)bean.getClass();
            //得到类中的所有集合
            Field[] fields = clazz.getDeclaredFields();
            String none = "（暂无）";
            for (Field item : fields){
                //设置属性是可以访问的
                item.setAccessible(true);
                Object val = item.get(bean);
                //得到此属性的类型
                String type = item.getType().toString();
                if (null == val){
                    item.set(bean,none);
                }
            }
        } catch (Exception e){
            throw  new RuntimeException(e);
        }

    }





}
