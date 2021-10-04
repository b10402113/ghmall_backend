package com.java.ghmall.service;

import com.github.pagehelper.PageInfo;
import com.java.ghmall.form.EzshipOrderForm;
import com.java.ghmall.vo.OrderVo;
import com.java.ghmall.vo.ResponseVo;

public interface IOrderService {
    public ResponseVo<OrderVo> create(Integer uid,String receiverName,String receiverPhone,String receiverEmail, Integer shippingId);

    ResponseVo<PageInfo> list(Integer uid, Integer pageNum, Integer pageSize);

    ResponseVo<OrderVo> detail(Integer uid, Long orderNo);

    ResponseVo<OrderVo> order_detail(Long orderNo);

    ResponseVo cancel(Integer uid, Long orderNo);

    void paid(Long orderNo);

    EzshipOrderForm buildEzshipOrderForm(OrderVo orderVo);

}
