package com.wwxn.bms.po;

import java.util.Set;

public class Menu {

    private Integer id;

    private String name;

    private Integer level;

    public String getRouterName() {
        return routerName;
    }

    public void setRouterName(String routerName) {
        this.routerName = routerName;
    }

    private String routerName;

    public Set<Menu> getChildrens() {
        return childrens;
    }

    public void setChildrens(Set<Menu> childrens) {
        this.childrens = childrens;
    }

    private Set<Menu> childrens;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    private Integer parent;
}
