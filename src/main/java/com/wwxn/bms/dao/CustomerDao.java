package com.wwxn.bms.dao;

import com.wwxn.bms.po.Customer;

import java.util.List;

public interface CustomerDao {
    int addCustomer(Customer customer);

    int updateCustomer(Customer customer);


    List<Customer> getCustomerPageData(Customer customer);
}
