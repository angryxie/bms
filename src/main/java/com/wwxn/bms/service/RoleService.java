package com.wwxn.bms.service;

import com.wwxn.bms.po.Role;

import java.util.List;

public interface RoleService {
    List<Role> getRolesByUserId(Integer userId);
}
