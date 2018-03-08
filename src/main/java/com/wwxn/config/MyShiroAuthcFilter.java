package com.wwxn.config;

import com.alibaba.fastjson.JSON;
import com.wwxn.bms.pojo.ResultBean;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyShiroAuthcFilter extends AuthenticationFilter {
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        ResultBean resultBean=new ResultBean();
        resultBean.setMessage("请先登录");
        resultBean.setSuccess(false);
        resultBean.setResultCode(402);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(JSON.toJSONString(resultBean));
        return false;
    }
}
