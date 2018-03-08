package com.wwxn.bms.service;

import com.wwxn.bms.po.User;
import com.wwxn.bms.pojo.ResultBean;

public interface UserService {
    User getUserByUsername(String username);

    ResultBean getUserInfo();
}
