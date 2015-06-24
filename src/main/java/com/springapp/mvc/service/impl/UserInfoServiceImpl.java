package com.springapp.mvc.service.impl;


import com.springapp.mvc.dto.UserInfoDto;
import com.springapp.mvc.mapper.UserInfoMapper;
import com.springapp.mvc.service.AbstractService;
import com.springapp.mvc.service.UserInfoService;

import com.springapp.mvc.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangjie on 2015/5/18.
 */
@Service("userInfoService")
public class UserInfoServiceImpl extends AbstractService implements UserInfoService {


    @Autowired
    public UserInfoMapper userInfoMapper;

    @Override
    public UserInfoVo findByOpenId(String openId) {
//        return userInfoDao.findByOpenId(openId);
        return userInfoMapper.findByOpenId(openId);
    }

    @Override
    @Transactional
    public void update(UserInfoVo userInfoVo) {
        userInfoMapper.update(userInfoVo);
    }

    @Override
    @Transactional
    public void insert(UserInfoVo userInfoVo) {
        userInfoMapper.insert(userInfoVo);
    }


}
