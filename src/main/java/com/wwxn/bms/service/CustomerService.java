package com.wwxn.bms.service;

import com.wwxn.bms.po.Customer;
import com.wwxn.bms.pojo.CustomerData;
import com.wwxn.bms.pojo.PageData;
import com.wwxn.bms.pojo.ResultBean;

import java.text.ParseException;

public interface CustomerService {

    ResultBean addOrUpdateCustomer(CustomerData customerData) throws ParseException;

    ResultBean getCustomerPageData(CustomerData customerData);
}
