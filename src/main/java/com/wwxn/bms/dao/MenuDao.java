package com.wwxn.bms.dao;

import com.wwxn.bms.po.Menu;

import java.util.List;

public interface MenuDao {
    List<Menu> getMenuInfo(Integer userId);
}
