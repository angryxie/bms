package com.wwxn.bms.ServiceImpl;

import com.wwxn.bms.dao.MenuDao;
import com.wwxn.bms.po.Menu;
import com.wwxn.bms.pojo.ResultBean;
import com.wwxn.bms.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuDao menuDao;
    @Override
    public ResultBean getMenuInfo(Integer userId) {
        ResultBean result=new ResultBean();
        List<Menu> menus=menuDao.getMenuInfo(userId);
        if (!menus.isEmpty()){
            menuFormat(menus);
        }
        result.setSuccess(true);
        result.setResultCode(200);
        result.setData(menus);
        return result;
    }

    protected  List<Menu> menuFormat(List<Menu> menus){
        for (Menu menu:menus){
            Set<Menu> subMenus=new HashSet<>();
            for (Menu subMenu:menus){
                if (menu.getId().equals(subMenu.getParent())){
                    subMenus.add(subMenu);
                }
            }
            menu.setChildrens(subMenus);
        }
        return  menus;
    }
}
