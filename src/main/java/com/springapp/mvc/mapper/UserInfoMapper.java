package com.springapp.mvc.mapper;

import com.springapp.mvc.vo.UserInfoVo;
import org.springframework.stereotype.Repository;

/**
 * Created by zhangjie on 2015/5/18.
 */
@Repository("userInfoMapper")
public interface UserInfoMapper{
    /**
     * 查找用户信息
     * @param openId
     * @return
     */
    public UserInfoVo findByOpenId(String openId);

    /**
     * 更新用户信息
     * @param userInfoVo
     */
    public void update(UserInfoVo userInfoVo);

    /**
     * 插入新用户
     * @param userInfoVo
     */
    public void insert(UserInfoVo userInfoVo);
}
