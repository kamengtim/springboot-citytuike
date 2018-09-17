package com.citytk.service;

import com.alibaba.fastjson.JSONObject;
import com.citytk.pojo.TpUsers;
import io.swagger.client.ApiException;
import io.swagger.client.model.RegisterUsers;

public interface UserService {
    TpUsers findOne(Integer userId);

    Object createNewIMUserBatch(RegisterUsers userObject) throws ApiException;

    Object getIMUserAllChatRooms(String userName);

    Object getIMUserStatus(String userName);
}
