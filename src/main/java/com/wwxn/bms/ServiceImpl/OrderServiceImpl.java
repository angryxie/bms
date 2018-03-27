package com.wwxn.bms.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.jdbc.StringUtils;
import com.wwxn.bms.dao.OrderDao;
import com.wwxn.bms.po.Order;
import com.wwxn.bms.po.OrderEntry;
import com.wwxn.bms.po.User;
import com.wwxn.bms.pojo.OrderData;
import com.wwxn.bms.pojo.OrderEntryData;
import com.wwxn.bms.pojo.ResultBean;
import com.wwxn.bms.service.OrderService;
import com.wwxn.bms.utils.DateUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.naming.Name;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
        formatResult(result, count);
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

    @Override
    public ResultBean deleteOrder(OrderData orderData) {
        ResultBean result=new ResultBean();
        int count=orderDao.deleteOrder(Integer.parseInt(orderData.getId()));
        if (count>0){
            result.setSuccess(true);
            result.setResultCode(200);
            result.setMessage("删除订单成功");
        }
        else {
            result.setMessage("删除订单失败");
            result.setSuccess(false);
            result.setResultCode(500);
        }
        return result;
    }

    @Override
    public ResultBean addOrUpdateOrderEntry(OrderEntryData orderEntryData) {
        ResultBean result=new ResultBean();
        int count=0;
        if (StringUtils.isNullOrEmpty(orderEntryData.getId())){
            count=orderDao.addOrderEntry(convert(orderEntryData));
        }
        else {
            count=orderDao.updateOrderEntry(convert(orderEntryData));
        }
        formatResult(result, count);
        return result;
    }

    private void formatResult(ResultBean result, int count) {
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
    }

    @Override
    public ResultBean deleteOrderEntry(Integer id) {
        ResultBean result=new ResultBean();
        int count=orderDao.deleteOrderEntry(id);
        formatResult(result,count);
        return result;
    }

    @Override
    public ResultBean getOrderEntryByOrderId(Integer orderId) {
        ResultBean result=new ResultBean();
        List<OrderEntry> datas=orderDao.getOrderEntryByOrderId(orderId);
        result.setResultCode(200);
        result.setSuccess(true);
        result.setData(datas.stream().map(o->convert(o)).collect(Collectors.toList()));
        return result;
    }

    protected  OrderEntryData convert(OrderEntry orderEntry){
        OrderEntryData orderEntryData=new OrderEntryData();
        orderEntryData.setId(orderEntry.getId().toString());
        orderEntryData.setEntryCode(orderEntry.getEntryCode());
        orderEntryData.setName(orderEntry.getName());
        orderEntryData.setPrice(orderEntry.getPrice().toString());
        orderEntryData.setRemark(orderEntry.getRemark());
        orderEntryData.setOwner(orderEntry.getOwner().toString());
        orderEntryData.setCreateDate(DateUtils.formatDate(orderEntry.getCreateDate()));
        orderEntryData.setOrderId(orderEntry.getOrderId().toString());
        orderEntryData.setOwnerName(orderEntry.getOwnerName());
        return  orderEntryData;
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
        User user= (User) SecurityUtils.getSubject().getPrincipal();
        order.setOwner(user.getUserId());
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

    protected OrderEntry convert(OrderEntryData orderEntryData){
        OrderEntry orderEntry=new OrderEntry();
        if (!StringUtils.isNullOrEmpty(orderEntryData.getId())){
            orderEntry.setId(Integer.parseInt(orderEntryData.getId()));
        }
        if (StringUtils.isNullOrEmpty(orderEntryData.getEntryCode())){
            orderEntry.setEntryCode(String.valueOf(new Date().getTime()));
        }
        if(!StringUtils.isNullOrEmpty(orderEntryData.getName())){
            orderEntry.setName(orderEntryData.getName());
        }
        if (!StringUtils.isNullOrEmpty(orderEntryData.getPrice())){
            orderEntry.setPrice(new BigDecimal(orderEntryData.getPrice()));
        }
        if (!StringUtils.isNullOrEmpty(orderEntryData.getRemark())){
            orderEntry.setRemark(orderEntryData.getRemark());
        }
        if (!StringUtils.isNullOrEmpty(orderEntryData.getOrderId())){
            orderEntry.setOrderId(Integer.parseInt(orderEntryData.getOrderId()));
        }
        if (!StringUtils.isNullOrEmpty(orderEntryData.getOwner())){
            orderEntry.setOwner(Integer.parseInt(orderEntryData.getOwner()));
        }
        return orderEntry;
    }
}
