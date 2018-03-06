package com.wwxn.bms.controller;

import com.wwxn.bms.pojo.CustomerData;
import com.wwxn.bms.pojo.ResultBean;
import com.wwxn.bms.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.ParseException;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Resource
    private CustomerService customerService;

    @RequestMapping("/addOrUpdate")
    @ResponseBody
    public ResultBean addCustomer(@RequestBody CustomerData customer) throws ParseException {
        return  customerService.addOrUpdateCustomer(customer);
    }

    @RequestMapping("/getCustomerPageData")
    @ResponseBody
    public ResultBean getCustomerPageData(@RequestBody CustomerData customerData ){
        return  customerService.getCustomerPageData(customerData);
    }
}
