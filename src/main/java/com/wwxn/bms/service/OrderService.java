package com.wwxn.bms.service;

import com.wwxn.bms.po.Order;
import com.wwxn.bms.pojo.OrderData;
import com.wwxn.bms.pojo.OrderEntryData;
import com.wwxn.bms.pojo.ResultBean;

import java.text.ParseException;

public interface OrderService {
    ResultBean addOrUpdateOrder(OrderData orderData)throws ParseException;

    ResultBean getOrderPageData(OrderData orderData);

    ResultBean deleteOrder(OrderData orderData);

    ResultBean addOrUpdateOrderEntry(OrderEntryData orderEntryData);

    ResultBean deleteOrderEntry(Integer id);

    ResultBean getOrderEntryByOrderId(Integer orderId);
}
