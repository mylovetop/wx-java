package com.springapp.mvc.filter;

import com.springapp.mvc.common.*;
import com.springapp.mvc.dto.WxDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by zhangjie on 2015/6/3.
 */
@Component
public class WxFilter extends OncePerRequestFilter {

    protected Logger logger = LoggerFactory.getLogger(getClass());


    /**
     * 过滤器 处理微信回调
     * 此过滤器处理流程：
     * 1、对应静态资源部过滤，登录不拦截
     *
     * 3、已经进行了微信授权 且session保存了weixin的相关信息
     * @param request
     * @param response
     * @param filterChain
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Resource.setSelectMenu(getServletContext(),  request);

        HttpSession session = request.getSession();
        String[] menu = {"person-select.html", "enterprise-search.html"};
        //请求的uri
        String uri = request.getRequestURI();
        //如果含有code参数则是从微信回调回来的
        String code = request.getParameter("code");

        WxDto wxDtoSession = (WxDto) session.getAttribute(Constant.KEY_WX_DTO);

        //如果code不为空（从微信回调回来）且wxDtoSession为空（还没执行code换取access_token）
        if (null != code && null == wxDtoSession) {
            session.setAttribute(Constant.KEY_WX_CODE, code);
            WxDto wxDto = new WxDto();
            wxDto.setCode(code);
            WxDto wxDto1 = Common.getAccessToken(wxDto);

            session.setAttribute(Constant.KEY_WX_DTO, wxDto1);
            response.sendRedirect("/saveWxOpenId.html");
            return;
        }

        //1、对应静态资源部过滤，登录不拦截
        if (uri.indexOf("asset/") != -1 || uri.indexOf("login.html") != -1) {
            filterChain.doFilter(request, response);
        //2、检查 访问的url 是否带有code, 带有code参数的是微信返回来的回调参数。且检查wexDtoSession是否为空，若为空则是从微信菜单点击打开。
        } else if (null == code && null == wxDtoSession) {//没有进行oauth认证
            boolean isMenu = false;
            for (int i = 0; i < menu.length; i++) {
                String item = menu[i];
                if (uri.indexOf(item) != -1) {
                    WxDto wxDto = new WxDto();
                    wxDto.setRedirectUri(WxMenu.menuUrl(item));
                    session.setAttribute(Constant.KEY_CALL_BACK_URL, getUrl(uri));
                    String url = Common.wxUrlOAuth(wxDto);
                    response.sendRedirect(url);//oauth 授权认证
                    isMenu = true;
                    break;
                }
            }
//            if (!isMenu){
//                filterChain.doFilter(request, response);
//            }
        //3、已经进行了微信授权 且session保存了weixin的相关信息
        } else if (null != wxDtoSession) {//已经认证且保存了值

            if (uri.indexOf("sendSms.html") != -1 ||  uri.indexOf("saveWxOpenId.html") != -1) {
                filterChain.doFilter(request, response);
            }else {
                String phone = null;
                WxDto wxDto = (WxDto)session.getAttribute(Constant.KEY_WX_DTO);
                if (null != wxDto && null != wxDto.getPhone()){
                    phone = wxDto.getPhone();
                }

                if (null == phone) {
                    String[] tmp = uri.split("/");
                    session.setAttribute(Constant.KEY_CALL_BACK_URL, tmp[tmp.length - 1]);
                    response.sendRedirect("/login.html");
                    return;
                } else {
                    filterChain.doFilter(request, response);
                }
            }
        } else {
            filterChain.doFilter(request, response);
        }


    }

    private String getUrl(String uri){
        String[] tmp = uri.split("/");
        return tmp[tmp.length -1];
    }
}
