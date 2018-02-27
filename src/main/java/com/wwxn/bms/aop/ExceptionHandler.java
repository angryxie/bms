package com.wwxn.bms.aop;

import com.wwxn.bms.pojo.ResultBean;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ResponseBody
public class ExceptionHandler {

    private Logger logger= LoggerFactory.getLogger(ExceptionHandler.class);

    @org.springframework.web.bind.annotation.ExceptionHandler(value = org.apache.shiro.authz.UnauthorizedException.class)
    public ResultBean authException(HttpServletRequest request,
                                    Exception exception){
        logger.error(exception.getMessage(),exception);
        ResultBean resultBean=new ResultBean();
        resultBean.setMessage("你没有此权限");
        resultBean.setResultCode(403);
        resultBean.setSuccess(false);
        return resultBean;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {UnknownAccountException.class,IncorrectCredentialsException.class})
    public ResultBean unKnownUsernameOrPassword(Exception e){
        logger.error(e.getMessage(),e);
        ResultBean resultBean=new ResultBean();
        resultBean.setMessage("账号或者密码错误");
        resultBean.setResultCode(401);
        resultBean.setSuccess(false);
        return resultBean;
    }
}
