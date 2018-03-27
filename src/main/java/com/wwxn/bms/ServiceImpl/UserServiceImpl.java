package com.wwxn.bms.ServiceImpl;

import com.wwxn.bms.dao.UserDao;
import com.wwxn.bms.po.User;
import com.wwxn.bms.pojo.OrderData;
import com.wwxn.bms.pojo.ResultBean;
import com.wwxn.bms.pojo.UserData;
import com.wwxn.bms.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;
    @Override
    public User getUserByUsername(String username) {
        List<User> result= userDao.getUserByUserName(username);
        return  result.isEmpty()?null:result.get(0);
    }

    @Override
    public ResultBean getUserInfo() {
        ResultBean result=new ResultBean();
        List<User> users=userDao.getUserInfo();
        result.setResultCode(200);
        result.setSuccess(true);
        result.setData(users.stream().map(u->convret(u)).collect(Collectors.toList()));
        return result;
    }

    private UserData convret(User user){
        UserData userData=new UserData();
        userData.setUserId(user.getUserId().toString());
        userData.setUserName(user.getUserName());
        userData.setName(user.getName());
        userData.setPhone(user.getPhone()==null?"":user.getPhone().toString());
        return userData;
    }
}
