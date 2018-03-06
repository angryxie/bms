package com.wwxn.bms.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.jdbc.StringUtils;
import com.wwxn.bms.dao.OrderDao;
import com.wwxn.bms.po.Order;
import com.wwxn.bms.pojo.OrderData;
import com.wwxn.bms.pojo.ResultBean;
import com.wwxn.bms.service.OrderService;
import com.wwxn.bms.utils.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;
    @Override
    public ResultBean addOrUpdateOrder(OrderData orderData)throws ParseException {
        ResultBean result=new ResultBean();
        int count=0;
        if (StringUtils.isNullOrEmpty(orderData.getId())){
            count=orderDao.addOrder(convert(orderData));
        }
        else {
            count=orderDao.updateOrder(convert(orderData));
        }
        if (count>0){
            result.setSuccess(true);
            result.setResultCode(200);
            result.setMessage("操作成功");
        }
        else {
            result.setSuccess(false);
            result.setResultCode(500);
            result.setMessage("操作失败");
        }
        return result;
    }

    @Override
    public ResultBean getOrderPageData(OrderData orderData) {
        ResultBean resultBean=new ResultBean();
        PageHelper.startPage(orderData.getPage(),orderData.getSize());
        List<Order> orders=orderDao.getOrderPageData(orderData);
        List<OrderData> orderDatas=new ArrayList<>();
        PageInfo pageInfo=new PageInfo(orders);
        pageInfo.getList().forEach(o->{
            orderDatas.add(convert((Order)o));
        });
        pageInfo.setList(orderDatas);
        resultBean.setResultCode(200);
        resultBean.setSuccess(true);
        resultBean.setData(pageInfo);
        return resultBean;
    }

    protected Order convert(OrderData orderData) throws ParseException {
        Order order=new Order();
        if (!StringUtils.isNullOrEmpty(orderData.getId())){
            order.setId(Integer.parseInt(orderData.getId()));
        }
        if (!StringUtils.isNullOrEmpty(orderData.getOrderCode())){
            order.setOrderCode(orderData.getOrderCode());
        }
        else {
            order.setOrderCode(String.valueOf(new Date().getTime()));
        }
        if (!StringUtils.isNullOrEmpty(orderData.getName())){
            order.setName(orderData.getName());
        }
        if (!StringUtils.isNullOrEmpty(orderData.getPhone())){
            order.setPhone(Long.parseLong(orderData.getPhone()));
        }
        if (!StringUtils.isNullOrEmpty(orderData.getPrice())){
            order.setPrice(new BigDecimal(orderData.getPrice()));
        }
        if (!StringUtils.isNullOrEmpty(orderData.getPaid())){
            order.setPaid(new BigDecimal(orderData.getPaid()));
        }
        if (!StringUtils.isNullOrEmpty(orderData.getUnpaid())){
            order.setUnpaid(new BigDecimal(orderData.getUnpaid()));
        }
        if (!StringUtils.isNullOrEmpty(orderData.getAppointment())){
            order.setAppointment(DateUtils.parseDate(orderData.getAppointment()));
        }
        if (!StringUtils.isNullOrEmpty(orderData.getInvoiceCode())){
            order.setInvoiceCode(orderData.getInvoiceCode());
        }
        if (!StringUtils.isNullOrEmpty(orderData.getIsdelete())){
            order.setIsdelete(orderData.getIsdelete().equals("1"));
        }
        if (!StringUtils.isNullOrEmpty(orderData.getRemark())){
            order.setRemark(orderData.getRemark());
        }
        order.setOwner(1);
        //todo
        return order;
    }

    protected OrderData convert(Order order){
        OrderData orderData=new OrderData();
        orderData.setId(order.getId().toString());
        orderData.setOrderCode(order.getOrderCode());
        orderData.setName(order.getName());
        orderData.setPhone(order.getPhone()==null?"":order.getPhone().toString());
        orderData.setAppointment(order.getAppointment()==null?"":DateUtils.formatDate(order.getAppointment()));
        orderData.setPrice(order.getPrice()==null?"":order.getPrice().toString());
        orderData.setPaid(order.getPaid()==null?"":order.getPaid().toString());
        orderData.setUnpaid(order.getUnpaid()==null?"":order.getUnpaid().toString());
        orderData.setRemark(order.getRemark());
        orderData.setInvoiceCode(order.getInvoiceCode());
        return orderData;
    }
}
