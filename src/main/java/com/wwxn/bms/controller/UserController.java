package com.wwxn.bms.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.wwxn.bms.pojo.ResultBean;
import org.apache.catalina.manager.util.SessionUtils;
import org.apache.shiro.SecurityUtils;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class UserController {

    private Logger logger= LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/doLogin",method =RequestMethod.POST)
    @ResponseBody
    public String doLogin(String userName, String password) throws Exception {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser!=null&&currentUser.isAuthenticated()){
            logger.info("已经登录过了");
            return "success1";
        }
        else {
            try {
                currentUser.login(new UsernamePasswordToken(userName,password));
                return "success";
            }
            catch (Exception e){
                logger.error(e.getMessage(),e);
                return "error";
            }
        }
    }

    @RequestMapping("/index")
    public String index(){
        return  "index";
    }

    @RequestMapping("/perms")
    @ResponseBody
    @RequiresPermissions("user:delete")
    public ResultBean permsTest(){
        ResultBean result=new ResultBean();
        result.setSuccess(true);
        result.setResultCode(200);
        result.setMessage("success");
        return result;
    }

}
