package com.citytk.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.citytk.dao.TpUsersMapper;
import com.citytk.pojo.TpUsers;
import com.citytk.service.UserService;
import com.citytk.utils.OrgInfo;
import com.citytk.utils.TokenUtil;
import io.swagger.client.ApiException;
import io.swagger.client.api.UsersApi;
import io.swagger.client.model.RegisterUsers;
import io.swagger.client.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private TpUsersMapper userDao;

    private UsersApi api = new UsersApi();
    @Override
    public TpUsers findOne(@Param(value = "userId") Integer userId) {
        return userDao.selectByPrimaryKey(userId);
    }

    @Override
    public Object createNewIMUserBatch(RegisterUsers userObject) throws ApiException {
        Object object = null;
        object = api.orgNameAppNameUsersPost(OrgInfo.ORG_NAME, OrgInfo.APP_NAME, userObject, TokenUtil.getAccessToken());
        return object;
    }

    @Override
    public Object getIMUserAllChatRooms(String userName) {
        Object object =  "";
        try {
            object = api.orgNameAppNameUsersUsernameJoinedChatroomsGet(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),userName);
        } catch (ApiException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return object;
    }

    @Override
    public Object getIMUserStatus(String userName) {
        Object object = "";
        try {
            object = api.orgNameAppNameUsersUsernameStatusGet(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),userName);
        } catch (ApiException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return object;
    }

}
