package com.wwxn.bms.ServiceImpl;

import com.mysql.jdbc.StringUtils;
import com.wwxn.bms.dao.CustomerDao;
import com.wwxn.bms.po.Customer;
import com.wwxn.bms.pojo.CustomerData;
import com.wwxn.bms.pojo.ResultBean;
import com.wwxn.bms.service.CustomerService;
import com.wwxn.bms.utils.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private CustomerDao customerDao;
    @Override
    public ResultBean addCustomer(CustomerData customerData) throws ParseException {
        ResultBean result=new ResultBean();
        Customer customer=new Customer();
        customer.setAddress(customerData.getAddress());
        if (!StringUtils.isNullOrEmpty(customerData.getBirth())){
            customer.setBirth(DateUtils.formatDate("yyyy-MM-dd",customerData.getBirth()));
        }
        customer.setGender(Integer.parseInt(customerData.getGender()));
        customer.setName(customerData.getName());
        customer.setPhone(Long.parseLong(customerData.getPhone()));
        int insertResult=customerDao.addCustomer(customer);
        if (insertResult>0){
            result.setMessage("添加用户成功");
            result.setResultCode(200);
            result.setSuccess(true);
        }
        else {
            result.setSuccess(false);
            result.setResultCode(500);
            result.setMessage("添加用户失败");
        }
        return result;
    }
}
