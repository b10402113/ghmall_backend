package com.java.ghmall.controller.backend;


import com.java.ghmall.common.Const;
import com.java.ghmall.enums.ResponseEnum;
import com.java.ghmall.form.ProductForm;
import com.java.ghmall.pojo.User;
import com.java.ghmall.service.IOrderService;
import com.java.ghmall.service.IUserService;
import com.java.ghmall.service.impl.manage.IOrderManageService;
import com.java.ghmall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/manage/order")
public class OrderManageController {
    @Autowired
    IOrderManageService orderManageService;

    @Autowired
    IOrderService orderService;

    @Autowired
    IUserService userService;

    @GetMapping("")
    public ResponseVo getOrders(HttpSession session, @RequestParam(required = false) Integer status,
                                @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                @RequestParam(required = false, defaultValue = "10") Integer pageSize){
        User user = (User)session.getAttribute(Const.CURRENT_USER);

        if(userService.checkAdminRole(user).isSuccess()){
            //填充我们增加产品的业务逻辑
            return orderManageService.list(status,pageNum,pageSize);
        }else{
            return ResponseVo.error(ResponseEnum.ERROR,"无权限操作");
        }
    }

    @PutMapping("")
    public ResponseVo setOrderStatus(HttpSession session, @RequestParam Long order_no,@RequestParam Integer status
                                     ){
        User user = (User)session.getAttribute(Const.CURRENT_USER);

        if(userService.checkAdminRole(user).isSuccess()){
            //填充我们增加产品的业务逻
            return orderManageService.setStatus(order_no,status);
        }else{
            return ResponseVo.error(ResponseEnum.ERROR,"无权限操作");
        }
    }

    @GetMapping("/detail")
    public ResponseVo getOrderDetail(HttpSession session, @RequestParam Long order_no){
        User user = (User)session.getAttribute(Const.CURRENT_USER);

        if(userService.checkAdminRole(user).isSuccess()){
            //填充我们增加产品的业务逻辑
            return orderService.order_detail(order_no);
        }else{
            return ResponseVo.error(ResponseEnum.ERROR,"无权限操作");
        }
    }



}
