package com.wwxn.bms.dao;

import com.wwxn.bms.po.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleDao {
    List<Role> getRolesByUserId(@Param("userId") Integer userId);
}
