package com.citytk.service.impl;

import com.citytk.dao.TpUsersMapper;
import com.citytk.pojo.TpUsers;
import com.citytk.service.UserService;
import com.citytk.utils.TokenUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private TpUsersMapper userDao;

    @Override
    public TpUsers findOne(@Param(value = "userId") Integer userId) {
        return userDao.selectByPrimaryKey(userId);
    }

}
