package com.wwxn.bms.dao;

import com.wwxn.bms.po.Order;
import com.wwxn.bms.po.OrderEntry;
import com.wwxn.bms.pojo.OrderData;

import java.util.List;

public interface OrderDao {
    int addOrder(Order order);

    List<Order> getOrderPageData(OrderData orderData);

    int deleteOrder(Integer id);

    int updateOrder(Order order);

    int addOrderEntry(OrderEntry orderEntry);

    int updateOrderEntry(OrderEntry orderEntry);

    int deleteOrderEntry(Integer id);
}
