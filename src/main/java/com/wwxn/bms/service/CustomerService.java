package com.wwxn.bms.service;

import com.wwxn.bms.pojo.CustomerData;
import com.wwxn.bms.pojo.ResultBean;

import java.text.ParseException;

public interface CustomerService {

    ResultBean addCustomer(CustomerData customerData) throws ParseException;
}
