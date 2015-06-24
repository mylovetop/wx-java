package com.springapp.mvc.controller;

import com.springapp.mvc.common.Common;
import com.springapp.mvc.common.Constant;
import com.springapp.mvc.common.WxMenu;
import com.springapp.mvc.dto.UserInfoDto;
import com.springapp.mvc.dto.WxDto;
import com.springapp.mvc.service.UserInfoService;
import com.springapp.mvc.vo.UserInfoVo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * Created by adm on 2015/5/13.
 */
@Controller
public class WxController extends AbstractController {

    @Autowired
    public UserInfoService userInfoService;


    /**
     * 微信自定义菜单
     * @return
     */
    @RequestMapping("/wxCreateMenu")
    @ResponseBody
    public String wxCreateMenu(){
        return WxMenu.create();
    }

    @RequestMapping("/insert.html")
    public String insert(UserInfoDto userInfoDto){
        UserInfoVo u = new UserInfoVo();
        BeanUtils.copyProperties(userInfoDto, u);
        userInfoService.insert(u);
        return "redirect:/";
    }


    @RequestMapping("/index.html")
    public String index(String t){
        return "index";
    }


    @RequestMapping("/wxOAuth.html")
    public String wxOAuth(HttpServletRequest request, WxDto wxDto){
        String code = wxDto.getCode();
        if (null != code){
            WxDto wxDto1 = Common.getAccessToken(wxDto);
//            saveWxOpenId(wxDto1);
        }
        return "redirect:/login.html";
    }


    /**
     * 保存从微信里面获取的openid等信息
     * @param request
     * @param wxDto
     * @return
     */
    @RequestMapping("/saveWxOpenId.html")
    public String saveWxOpenId(HttpServletRequest request, WxDto wxDto){
        HttpSession session = request.getSession();
        try {
            wxDto = (WxDto)session.getAttribute(Constant.KEY_WX_DTO);
            UserInfoVo userInfoVo = new UserInfoVo();
            userInfoVo.setAccessToken(wxDto.getAccessToken());
            userInfoVo.setOpenId(wxDto.getOpenId());
            userInfoVo.setRefreshToken(wxDto.getRefreshToken());
            userInfoVo.setScope(wxDto.getScope());
            UserInfoVo userInfoVo1 = userInfoService.findByOpenId(userInfoVo.getOpenId());

            if (null != userInfoVo1){
                userInfoService.update(userInfoVo);
            }else {
                userInfoService.insert(userInfoVo);
            }
        } catch (Exception e){
            logger.error("保存微信openid信息错误：{}",e);
        }
        return "redirect:/" + session.getAttribute( Constant.KEY_CALL_BACK_URL).toString();
    }

    /**
     * 根据openid 查询用户信息
     * @param request
     * @param openId
     * @return
     */
    public String findUserInfoByOpenId(HttpServletRequest request, String openId){
        try{
            openId = request.getParameter("openId");
            UserInfoVo userInfoVo = userInfoService.findByOpenId(openId);
            if (null !=userInfoVo) {
                WxDto wxDto = (WxDto)request.getSession().getAttribute(Constant.KEY_WX_DTO);
                wxDto.setPhone(userInfoVo.getPhone());
                return userInfoVo.getPhone();
            }
        }catch (Exception e){
            logger.error("findUserInfoByOpenId error:" + e);
        }
        return "";
    }

    /**
     * 检验signature
     * @param out
     * @param wxDto
     * @return
     */
    @RequestMapping(value = "/wxCheckSignature.html")
    public void wxCheckSignature(PrintWriter out, WxDto wxDto){
        String signature = wxDto.getSignature();
        String timestamp = wxDto.getTimestamp();
        String nonce = wxDto.getNonce();

        String token = Constant.KEY_WX_TOKEN;
        String[] array = new String[]{token, timestamp, nonce};
        //字典排序
        Arrays.sort(array);
        String str = array[0] + array[1] + array[2];

        //SHA1加密
        String digest = DigestUtils.sha1Hex(str);
        if (digest.equals(signature)){
            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
            out.print(wxDto.getEchostr());
        }else {
            logger.error("not weChat info");
        }

        out.flush();
        out.close();
    }



}
