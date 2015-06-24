package com.springapp.mvc.common;

import com.springapp.mvc.dto.MenuDto;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangjie on 2015/6/12.
 */
public class Resource implements WebApplicationInitializer{

    public static Boolean isMenuEnt = false;

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.setAttribute(Constant.KEY_MENU_ENT, this.menuEnt());
        servletContext.setAttribute(Constant.KEY_MENU_PERSON, this.menuPerson());
    }

    private List<MenuDto> menuEnt(){
       return null;
    }

    private List<MenuDto> menuPerson(){
       return null;
    }

    /**
     * 设置哪个菜单被选中
     */
    public static void setSelectMenu(ServletContext servletContext, HttpServletRequest request){
        String uri = request.getRequestURI().toString();
        HttpSession session = request.getSession();
        List<MenuDto> entMenu = (List<MenuDto>)servletContext.getAttribute(Constant.KEY_MENU_ENT);
        List<MenuDto> person = (List<MenuDto>)servletContext.getAttribute(Constant.KEY_MENU_PERSON);

        isMenuEnt = setSelect(entMenu, uri);

        session.setAttribute(Constant.KEY_URL_HOME, entMenu.get(0).getHome());
        if (!isMenuEnt){
            setSelect(person, uri);
            session.setAttribute(Constant.KEY_URL_HOME, person.get(0).getHome());
        }
    }

    private static boolean setSelect(List<MenuDto> list, String uri){
        boolean retVal = false;
        for (MenuDto menuDto : list){
            List<MenuDto> children = menuDto.getChildren();
            for (MenuDto item : children){
                if (uri.indexOf(item.getUrl()) != -1){
                    item.setSelected("1");
                    retVal = true;
                }else {
                    item.setSelected("0");
                }
            }
        }
        return retVal;
    }
}
