package com.wwxn.bms.dao;

import com.wwxn.bms.po.Order;
import com.wwxn.bms.pojo.OrderData;

import java.util.List;

public interface OrderDao {
    int addOrder(Order order);

    List<Order> getOrderPageData(OrderData orderData);

    int updateOrder(Order order);
}
