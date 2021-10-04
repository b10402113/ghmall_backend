package com.java.ghmall.service;

import com.java.ghmall.form.CartAddForm;
import com.java.ghmall.form.CartUpdateForm;
import com.java.ghmall.pojo.Cart;
import com.java.ghmall.vo.CartVo;
import com.java.ghmall.vo.ResponseVo;

import java.util.List;

public interface ICartService {
    ResponseVo<CartVo> add(Integer uid, CartAddForm form);
    ResponseVo<CartVo> delete(Integer uid, Integer productId);
    ResponseVo<CartVo> update(Integer uid, Integer productId, CartUpdateForm form);
    ResponseVo<CartVo> list(Integer uid);
    ResponseVo<CartVo> selectAll(Integer uid);

    ResponseVo<CartVo> unSelectAll(Integer uid);

    ResponseVo<Integer> sum(Integer uid);
    List<Cart> listForCart(Integer uid);
}
