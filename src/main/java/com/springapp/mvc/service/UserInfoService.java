package com.springapp.mvc.service;

import com.springapp.mvc.dto.UserInfoDto;

import com.springapp.mvc.vo.UserInfoVo;

import java.util.List;

/**
 * Created by zhangjie on 2015/5/18.
 */
public interface UserInfoService {
    /**
     * 根据openid 查找用户信息
     * @param openId
     * @return
     */
    public UserInfoVo findByOpenId(String openId);


    /**
     * 更新
     * @param userInfoVo
     */
    public void update(UserInfoVo userInfoVo);

    /**
     * 新增
     * @param userInfoVo
     */
    public void insert(UserInfoVo userInfoVo);




}
