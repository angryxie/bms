package com.wwxn.bms.ServiceImpl;

import com.wwxn.bms.dao.RoleDao;
import com.wwxn.bms.po.Role;
import com.wwxn.bms.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleDao roleDao;
    @Override
    public List<Role> getRolesByUserId(Integer userId) {
        return roleDao.getRolesByUserId(userId);
    }
}
