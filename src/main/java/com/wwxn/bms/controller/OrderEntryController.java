package com.wwxn.bms.controller;

import com.wwxn.bms.pojo.OrderData;
import com.wwxn.bms.pojo.OrderEntryData;
import com.wwxn.bms.pojo.ResultBean;
import com.wwxn.bms.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orderEntry")
public class OrderEntryController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/addOrUpdate/{orderId}")
    public ResultBean addOrUpdate(@RequestBody OrderEntryData orderEntryData, @PathVariable String orderId){
        orderEntryData.setOrderId(orderId);
        return orderService.addOrUpdateOrderEntry(orderEntryData);
    }

    @RequestMapping("getByOrderId")
    public ResultBean getByOrderId(@RequestBody OrderData orderData){
        return orderService.getOrderEntryByOrderId(Integer.parseInt(orderData.getId()));
    }

    @RequestMapping("/delete/{id}")
    public ResultBean delete(@PathVariable String id){
            return  orderService.deleteOrderEntry(Integer.parseInt(id));
    }
}
