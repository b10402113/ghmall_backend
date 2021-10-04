package com.java.ghmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.ghmall.dao.ShippingMapper;
import com.java.ghmall.dao.UserMapper;
import com.java.ghmall.enums.ResponseEnum;
import com.java.ghmall.form.ShippingForm;
import com.java.ghmall.pojo.Shipping;
import com.java.ghmall.service.IShippingService;
import com.java.ghmall.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShippingServiceImpl implements IShippingService {

    @Autowired
    private ShippingMapper shippingMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResponseVo<Map<String, Integer>> add(Integer uid, ShippingForm form,Integer shippingType) {
        // check uid is valid
        int userCount = userMapper.countByUid(uid);

        if (userCount == 0){
            return ResponseVo.error(ResponseEnum.SHIPPING_ADD_ERROR);
        }

        Shipping shipping = new Shipping();
        BeanUtils.copyProperties(form, shipping);
        shipping.setUserId(uid);
        shipping.setShippingType(shippingType);
        // 因為有加上useGeneratedKeys keyProperty因此shippingId會被補上
        int row = shippingMapper.insertSelective(shipping);
        if (row == 0) {
            return ResponseVo.error(ResponseEnum.ERROR);
        }

        Map<String, Integer> map = new HashMap<>();
        map.put("shippingId", shipping.getId());

        return ResponseVo.success(map);
    }


    @Override
    public ResponseVo delete(Integer uid, Integer shippingId) {
        int row = shippingMapper.deleteByIdAndUid(uid, shippingId);
        if (row == 0) {
            return ResponseVo.error(ResponseEnum.DELETE_SHIPPING_FAIL);
        }

        return ResponseVo.success();
    }

    @Override
    public ResponseVo update(Integer uid, Integer shippingId, ShippingForm form) {
        Shipping shipping = new Shipping();
        BeanUtils.copyProperties(form, shipping);
        shipping.setUserId(uid);
        shipping.setId(shippingId);
        int row = shippingMapper.updateByPrimaryKeySelective(shipping);
        if (row == 0) {
            return ResponseVo.error(ResponseEnum.ERROR);
        }
        return ResponseVo.success();
    }

    @Override
    public ResponseVo<PageInfo> list(Integer uid, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Shipping> shippings = shippingMapper.selectByUid(uid);
        PageInfo pageInfo = new PageInfo(shippings);
        return ResponseVo.success(pageInfo);
    }
}
