package com.wwxn.bms.service;

import com.wwxn.bms.po.User;

public interface UserService {
    User getUserByUsername(String username);
}
