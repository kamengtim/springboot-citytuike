package com.citytk.controller;

import com.alibaba.fastjson.JSONObject;
import com.citytk.pojo.TpUsers;
import com.citytk.service.UserService;
import com.citytk.utils.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/user")
@Api(value = "用户API")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/findone"},produces = {"application/json;charset=UTF-8"},method = RequestMethod.GET)
    @ApiOperation(value = "获取用户信息", notes = "根据用户ID获取用户信息")
    public @ResponseBody String getUserInfo(@RequestParam(required = true)Integer userId){
        JSONObject jsonObj = new JSONObject();
        JSONObject data = new JSONObject();
        jsonObj.put("status", "0");
        jsonObj.put("msg", "登陆失败!");
        TpUsers tpUsers = userService.findOne(userId);
        if (null == tpUsers) {
            jsonObj.put("status", "0");
            jsonObj.put("msg", "请先登陆!");
            return jsonObj.toString();
        }
        String tokens = TokenUtil.getAccessToken();
        data.put("userId", tpUsers.getUserId());
        data.put("tokens", tokens);
        jsonObj.put("result", data);
        jsonObj.put("status", "1");
        jsonObj.put("msg", "ok!");

        return jsonObj.toString();
    }
}
