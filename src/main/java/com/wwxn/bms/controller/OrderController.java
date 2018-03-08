package com.wwxn.bms.controller;

import com.wwxn.bms.pojo.OrderData;
import com.wwxn.bms.pojo.ResultBean;
import com.wwxn.bms.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.ParseException;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService;

    @RequestMapping("/addOrUpdate")
    @ResponseBody
    public ResultBean addOrUpdateOrder(@RequestBody OrderData orderData) throws ParseException {
        return orderService.addOrUpdateOrder(orderData);
    }

    @ResponseBody
    @RequestMapping("/getOrderPageData")
    public ResultBean getOrderPageData(@RequestBody OrderData orderData){
        return orderService.getOrderPageData(orderData);
    }

    @ResponseBody
    @RequestMapping("/deleteOrder")
    public ResultBean deleteOrder(@RequestBody OrderData orderData){
        return orderService.deleteOrder(orderData);
    }
}
