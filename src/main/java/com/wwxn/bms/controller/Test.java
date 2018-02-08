package com.wwxn.bms.controller;

import com.alibaba.fastjson.JSON;
import com.wwxn.bms.po.User;
import com.wwxn.bms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
public class Test {
    @Resource
    private UserService userService;
    @RequestMapping("/")
    @ResponseBody
    public User test(){
        User user=userService.getUserByUsername("jacky");
        return user;
    }
}
