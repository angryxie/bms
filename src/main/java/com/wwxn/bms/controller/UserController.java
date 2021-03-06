package com.wwxn.bms.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.wwxn.bms.po.User;
import com.wwxn.bms.pojo.ResultBean;
import com.wwxn.bms.service.MenuService;
import com.wwxn.bms.service.UserService;
import org.apache.catalina.manager.util.SessionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger= LoggerFactory.getLogger(UserController.class);

    @Resource
    private MenuService menuService;

    @Resource
    private UserService userService;
    @RequestMapping(value = "/doLogin",method =RequestMethod.POST)
    @ResponseBody
    public ResultBean doLogin(@RequestBody User user) throws Exception {
        ResultBean result=new ResultBean();
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser!=null&&currentUser.isAuthenticated()){
            result.setSuccess(true);
            result.setResultCode(200);
            result.setMessage("您已经登录过了");
        }
        else {
            UsernamePasswordToken token=new UsernamePasswordToken(user.getUserName(),user.getPassword());
            currentUser.login(token);
            result.setMessage("登录成功");
            result.setSuccess(true);
            result.setResultCode(200);
        }
        return  result;
    }

    @RequestMapping("/getMenuInfo")
    @ResponseBody
    public ResultBean getMenuInfo(){
        //User userInfo= (User) SecurityUtils.getSubject().getPrincipal();
        return menuService.getMenuInfo(1);
    }

    @ResponseBody
    @RequestMapping("/getUserInfo")
    public ResultBean getUserInfo(){
        return userService.getUserInfo();
    }

    @RequestMapping("/ispermision/{permision}")
    @ResponseBody
    public Boolean ispermision(@PathVariable("permision") String permision){
        logger.info("==========================="+permision);
        return  true;
    }


}
