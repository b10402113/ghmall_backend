package com.java.ghmall.service.impl.manage;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.ghmall.dao.OrderMapper;
import com.java.ghmall.enums.OrderStatusEnum;
import com.java.ghmall.enums.ResponseEnum;
import com.java.ghmall.pojo.Order;
import com.java.ghmall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderManageServiceImpl implements IOrderManageService{
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public ResponseVo<PageInfo> list(Integer status, Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Order> orders = orderMapper.selectByStatus(status);
        PageInfo pageInfo = new PageInfo<>(orders);
        pageInfo.setList(orders);
        return ResponseVo.success(pageInfo);
    }

    @Override
    public ResponseVo detail(Long order_no) {
        Order order = orderMapper.selectByOrderNo(order_no);
        return ResponseVo.success(order);
    }

    @Override
    public ResponseVo setStatus(Long order_no,Integer status) {
        Order order = orderMapper.selectByOrderNo(order_no);
        if (order == null){
            return ResponseVo.error(ResponseEnum.ERROR,"no such order");

        }
//        OrderStatusEnum.
        if(!containEnum(status)){
            return ResponseVo.error(ResponseEnum.ERROR,"no such enum");
        }

        order.setStatus(status);
        int row = orderMapper.updateByPrimaryKeySelective(order);
        if (row <= 0) {
            return ResponseVo.error(ResponseEnum.ERROR);
        }
        return ResponseVo.success(order);
    }

    public boolean containEnum(Integer status){
        for(OrderStatusEnum e:OrderStatusEnum.values()){
            if(e.getCode().equals(status)){
                return true;
            }
        }
        return false;
    }
}
