package com.springapp.mvc.dto;

import java.util.List;

/**
 * Created by zhangjie on 2015/6/12.
 */
public class MenuDto {
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 菜单孩子节点
     */
    private List<MenuDto> children;
    /**
     * 菜单对应连接
     */
    private String url;
    /**
     * 当前菜单是否被选中
     */
    private String selected;
    /**
     * 返回主页按钮对应的连接
     */
    private String home;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MenuDto> getChildren() {
        return children;
    }

    public void setChildren(List<MenuDto> children) {
        this.children = children;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }
}
