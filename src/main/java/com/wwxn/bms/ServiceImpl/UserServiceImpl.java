package com.wwxn.bms.ServiceImpl;

import com.wwxn.bms.dao.UserDao;
import com.wwxn.bms.po.User;
import com.wwxn.bms.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;
    @Override
    public User getUserByUsername(String username) {
        List<User> result= userDao.getUserByUserName(username);
        return  result.get(0);
    }
}
