package com.alivecaren.service.impl;

import com.alivecaren.config.DB;
import com.alivecaren.enums.DBTypeEnum;
import com.alivecaren.mapper.UserMapper;
import com.alivecaren.model.User;
import com.alivecaren.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    @DB(DBTypeEnum.CLOUD_DB)
    @Transactional
    public List<User> findCloudDataSource() {
        List<User> users = userMapper.selectByExample(null);
        return users;
    }

    @Override
    @DB(DBTypeEnum.TEST_DB)
    @Transactional
    public List<User> findTestDataSource() {
        List<User> users = userMapper.selectByExample(null);
        return users;
    }
}
