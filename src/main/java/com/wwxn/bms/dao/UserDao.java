package com.wwxn.bms.dao;

import com.wwxn.bms.po.User;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.util.CollectionUtils;

import java.util.List;

public interface UserDao {
    List<User> getUserByUserName(@Param("userName") String userName);

    List<User> getUserInfo();
}
