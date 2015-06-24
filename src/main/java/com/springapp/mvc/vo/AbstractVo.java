package com.springapp.mvc.vo;

import java.io.Serializable;

/**
 * Created by zhangjie on 2015/5/18.
 */
public abstract class AbstractVo implements Serializable {
    private static final long serialVersionUID  = 1L;

    protected int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
