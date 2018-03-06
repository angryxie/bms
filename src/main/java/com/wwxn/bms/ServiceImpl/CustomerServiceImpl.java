package com.wwxn.bms.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.jdbc.StringUtils;
import com.wwxn.bms.dao.CustomerDao;
import com.wwxn.bms.po.Customer;
import com.wwxn.bms.pojo.CustomerData;
import com.wwxn.bms.pojo.PageData;
import com.wwxn.bms.pojo.ResultBean;
import com.wwxn.bms.service.CustomerService;
import com.wwxn.bms.utils.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private CustomerDao customerDao;
    @Override
    public ResultBean addOrUpdateCustomer(CustomerData customerData) throws ParseException {
        ResultBean result=new ResultBean();
        int daoResult=0;
        if (StringUtils.isNullOrEmpty(customerData.getId())){
            daoResult=customerDao.addCustomer(convert(customerData));
        }
        else {
            daoResult=customerDao.updateCustomer(convert(customerData));
        }
        if (daoResult>0){
            result.setMessage("操作成功");
            result.setResultCode(200);
            result.setSuccess(true);
        }
        else {
            result.setSuccess(false);
            result.setResultCode(500);
            result.setMessage("操作失败");
        }
        return result;
    }

    @Override
    public ResultBean getCustomerPageData(CustomerData customerData) {
        ResultBean result=new ResultBean();
        PageHelper.startPage(customerData.getPage(),customerData.getSize());
        Customer customer=new Customer();
        if (!StringUtils.isNullOrEmpty(customerData.getName())){
            customer.setName("%"+customerData.getName()+"%");
        }
        if (!StringUtils.isNullOrEmpty(customerData.getPhone())){
            customer.setPhone(Long.parseLong(customerData.getPhone()));
        }
        List<Customer> datas=customerDao.getCustomerPageData(customer);
        PageInfo pageInfo=new PageInfo(datas);
        List<CustomerData> customerDataList=new ArrayList<>();
        pageInfo.getList().forEach(c->{
            customerDataList.add(convert((Customer) c));
        });
        pageInfo.setList(customerDataList);
        result.setData(pageInfo);
        result.setResultCode(200);
        result.setSuccess(true);
        return result;
    }

    protected CustomerData convert(Customer customer){
        CustomerData customerData=new CustomerData();
        if (customer.getId()!=null){
            customerData.setId(customer.getId().toString());
        }
        if (!StringUtils.isNullOrEmpty(customer.getAddress())){
            customerData.setAddress(customer.getAddress());
        }
        if (!StringUtils.isNullOrEmpty(customer.getName())){
            customerData.setName(customer.getName());
        }
        if (customer.getGender()!=null){
            customerData.setGender(customer.getGender().toString());
        }
        if (customer.getPhone()!=null){
            customerData.setPhone(customer.getPhone().toString());
        }
        if (customer.getBirth()!=null){
            customerData.setBirth(DateUtils.formatDate("yyyy-MM-dd",customer.getBirth()));
        }
        return  customerData;
    }

    protected Customer convert(CustomerData customerData) throws ParseException {
        Customer customer=new Customer();
        if (!StringUtils.isNullOrEmpty(customerData.getId())){
            customer.setId(Integer.parseInt(customerData.getId()));
        }
        if (!StringUtils.isNullOrEmpty(customerData.getAddress())){
            customer.setAddress(customerData.getAddress());
        }
        if (!StringUtils.isNullOrEmpty(customerData.getPhone())){
            customer.setPhone(Long.parseLong(customerData.getPhone()));
        }
        if (!StringUtils.isNullOrEmpty(customerData.getName())){
            customer.setName(customerData.getName());
        }
        if (!StringUtils.isNullOrEmpty(customerData.getBirth())){
            customer.setBirth(DateUtils.parseDate("yyyy-MM-dd",customerData.getBirth()));
        }
        if (!StringUtils.isNullOrEmpty(customerData.getGender())){
            customer.setGender(Integer.parseInt(customerData.getGender()));
        }
        if (!StringUtils.isNullOrEmpty(customerData.getIsdelete())){
            customer.setIsdelete(Integer.parseInt(customerData.getIsdelete()));
        }
        return  customer;

    }
}
