package com.citytk.controller;

import com.alibaba.fastjson.JSONObject;
import com.citytk.pojo.TpUsers;
import com.citytk.service.UserService;
import com.citytk.utils.ToolUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.client.ApiException;
import io.swagger.client.model.RegisterUsers;
import io.swagger.client.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/user")
@Api(value = "用户API")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/new_imuser"},produces = {"application/json;charset=UTF-8"},method = RequestMethod.GET)
    @ApiOperation(value = "用户注册", notes = "根据用户ID注册环信用户")
    public @ResponseBody String newImuser(@RequestParam(required = true)Integer userId) throws ApiException {
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
        if (null != tpUsers.getImId()){
            jsonObj.put("status", "0");
            jsonObj.put("msg", "已注册!");
            return jsonObj.toString();
        }
        String num = "P0000000000";
        num = num.substring(0, num.length() - tpUsers.getUserId().toString().length()) + tpUsers.getUserId();
        User user1 = new User();
        user1.setPassword(ToolUtil.generateString(32));
        user1.setUsername(num);
        RegisterUsers registerUsers = new RegisterUsers();
        registerUsers.add(user1);
        Object userResult = userService.createNewIMUserBatch(registerUsers);

        data.put("userId", tpUsers.getUserId());
        data.put("userResult", userResult.toString());
        jsonObj.put("result", data);
        jsonObj.put("status", "1");
        jsonObj.put("msg", "ok!");

        return jsonObj.toString();
    }
    @RequestMapping(value = {"/get_imuser"},produces = {"application/json;charset=UTF-8"},method = RequestMethod.GET)
    @ApiOperation(value = "获取用户信息", notes = "根据用户ID获取用户信息")
    public @ResponseBody String getImuser(@RequestParam(required = true)Integer userId) throws ApiException {
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
        if (null == tpUsers.getImId()){
            jsonObj.put("status", "0");
            jsonObj.put("msg", "请先注册!");
            return jsonObj.toString();
        }
        Object userResult = userService.getIMUserAllChatRooms(tpUsers.getImId());
        data.put("userId", tpUsers.getUserId());
        data.put("userResult", userResult);
        jsonObj.put("result", data);
        jsonObj.put("status", "1");
        jsonObj.put("msg", "ok!");

        return jsonObj.toString();
    }
    @RequestMapping(value = {"/get_imuser_status"},produces = {"application/json;charset=UTF-8"},method = RequestMethod.GET)
    @ApiOperation(value = "查看用户在线状态", notes = "根据用户ID查看用户在线状态")
    public @ResponseBody String getImuserStatus(@RequestParam(required = true)Integer userId) throws ApiException {
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
        if (null == tpUsers.getImId()){
            jsonObj.put("status", "0");
            jsonObj.put("msg", "请先注册!");
            return jsonObj.toString();
        }
        Object userResult = userService.getIMUserStatus(tpUsers.getImId());
        data.put("userId", tpUsers.getUserId());
        data.put("userResult", userResult);
        jsonObj.put("result", data);
        jsonObj.put("status", "1");
        jsonObj.put("msg", "ok!");

        return jsonObj.toString();
    }
}
