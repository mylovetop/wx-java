package com.springapp.mvc.common;

import com.springapp.mvc.dto.WxDto;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangjie on 2015/6/9.
 */
public class WxMenu {

    private static Logger logger= LoggerFactory.getLogger(WxMenu.class);

    public static String menuUrl(String key){
        String url = "https%3A%2F%2Fwx.xxx.com.cn%2F" + key;
        return url;
    }

    public static void main(String[] args){
        create();
    }
    /**
     * 自定义菜单
     * @return
     */
    public static String create(){
        String str = "";
        try {
            List<Map<String, Object>> button = new ArrayList<Map<String, Object>>(3);
            String url = "https://wx.xx.com.cn";

            button.add(menuItem("view", "企业", url, "", ""));


            String personUrl = "https%3A%2F%2Fwx.xxx.com.cn%2Flogin.html";
            WxDto wxDto = new WxDto();
            wxDto.setRedirectUri(personUrl);
            button.add(menuItem("view", "个人", Common.wxUrlOAuth(wxDto), "", ""));

            List<Map<String, Object>> memberCenterSubMenu = new ArrayList<Map<String, Object>>(5);
            memberCenterSubMenu.add(menuItem("view", "账号", url, "", ""));
            memberCenterSubMenu.add(menuItem("view", "密码", url, "", ""));
            button.add(menuItemHasSub("会员中心", memberCenterSubMenu));

            Map<String, Object> menu = menu(button);
//={"access_token":"OezXcEiiBSKSxW0eoylIeIsKIPl3r5JngNaz-HirXO5zL8uNbMX8cEk4we18yb3aykjLdIf7PMie48WidsE5suUexNTFqj6Froj6cilV7LjBKs5GZrpraBBJYopiQWRCXPAfU5e3XwTV5l0tYks-xw","expires_in":7200,"refresh_token":"OezXcEiiBSKSxW0eoylIeIsKIPl3r5JngNaz-HirXO5zL8uNbMX8cEk4we18yb3a_1W3RFFPaka_2huzDoqE4LAseVsG0WJkyTPTut1J4iUQ5WO8SjQtZ6Bchg8KelMupYOY7fYrD-p4r7kD63s6Zw","openid":"oja_ft09poWWKZ8Ky6Y2FuqSkWIs","scope":
           // "snsapi_userinfo"}
            ObjectMapper objectMapper = new ObjectMapper();
            str = objectMapper.writeValueAsString(menu);
            String aurl = Constant.KEY_URL_WX_MENU + "access_token=OezXcEiiBSKSxW0eoylIeIsKIPl3r5JngNaz-HirXO5zL8uNbMX8cEk4we18yb3aykjLdIf7PMie48WidsE5suUexNTFqj6Froj6cilV7LjBKs5GZrpraBBJYopiQWRCXPAfU5e3XwTV5l0tYks-xw";
            HttpClientUtil.doPostHttpsJson(aurl, str);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        System.out.println(str);
        return str;
    }

    private static Map<String, Object> menu(List<Map<String, Object>> button){
        Map<String, Object> menu = new HashMap<String, Object>(1);
        menu.put("button", button);
        return menu;
    }

    /**
     * 子菜单
     * @param type
     * @param name
     * @param url
     * @return
     */
    private static Map<String, Object> menuItem(String type, String name, String url, String key, String mediaId){
        Map<String, Object> item = new HashMap<String, Object>(3);
        item.put("type", type);
        item.put("name", name);
        item.put("url", url);
        return item;
    }


    /**
     * 含有二级菜单的一级菜单
     * @param name
     * @param subButton
     * @return
     */
    private static Map<String, Object> menuItemHasSub(String name, List<Map<String, Object>> subButton){
        Map<String, Object> item = new HashMap<String, Object>(3);
        item.put("name", name);
        item.put("sub_button", subButton);
        return item;
    }
}
