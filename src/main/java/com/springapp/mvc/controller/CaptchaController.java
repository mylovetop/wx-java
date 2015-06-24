package com.springapp.mvc.controller;

import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;

/**
 * Created by zhangjie on 2015/5/19.
 */
@Controller
public class CaptchaController extends AbstractController {
    @Autowired
    private Producer captchaProducer;

    /**
     * 生成登录的验证码
     * @param session
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/login/captcha.png")
    public void handleRequest(HttpSession session, HttpServletResponse response) throws Exception {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control","no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/png");
        String capText = captchaProducer.createText();//生成的验证码
        session.setAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY, capText);//验证码写入会话
        BufferedImage bi = captchaProducer.createImage(capText);      //验证码相关的图片
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "png", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }
}
