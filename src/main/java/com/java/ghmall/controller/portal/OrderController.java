package com.java.ghmall.controller.portal;

import com.github.pagehelper.PageInfo;
import com.java.ghmall.consts.MallConst;
import com.java.ghmall.enums.EzshipOrderStatus;
import com.java.ghmall.enums.ResponseEnum;
import com.java.ghmall.form.EzshipOrderForm;
import com.java.ghmall.form.EzshipResponseForm;
import com.java.ghmall.form.OrderCreateForm;
import com.java.ghmall.form.ShippingForm;
import com.java.ghmall.pojo.User;
import com.java.ghmall.service.IOrderService;
import com.java.ghmall.service.impl.EzshipServiceImpl;
import com.java.ghmall.vo.OrderVo;
import com.java.ghmall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

@RestController
@Slf4j
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private EzshipServiceImpl ezshipService;

    @PostMapping("/orders")
    public ResponseVo<OrderVo> create(@Valid @RequestBody OrderCreateForm form,
                                      HttpSession session) {
        User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        ResponseVo<OrderVo> orderVoResponseVo = orderService.create(user.getId(), form.getReceiverName(), form.getReceiverPhone(), form.getReceiverEmail(), form.getShippingId());
        if(orderVoResponseVo.getStatus() == ResponseEnum.SUCCESS.getCode()){
            EzshipOrderForm ezshipOrderForm = orderService.buildEzshipOrderForm(orderVoResponseVo.getData());
            ResponseVo<EzshipOrderForm> ezshipOrder = ezshipService.createEzshipOrder(ezshipOrderForm);
            return orderVoResponseVo;
        }else{
            return orderVoResponseVo;
        }
//        return orderService.create(user.getId(), form.getReceiverName(), form.getReceiverPhone(),form.getReceiverEmail(),form.getShippingId());
    }

    @GetMapping("/orders")
    public ResponseVo<PageInfo> list(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                     @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                     HttpSession session) {
        User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        return orderService.list(user.getId(), pageNum, pageSize);
    }

    @GetMapping("/orders/{orderNo}")
    public ResponseVo<OrderVo> detail(@PathVariable Long orderNo,
                                      HttpSession session) {
        User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        return orderService.detail(user.getId(), orderNo);
    }

    @PutMapping("/orders/{orderNo}")
    public ResponseVo cancel(@PathVariable Long orderNo,
                             HttpSession session) {
        User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        return orderService.cancel(user.getId(), orderNo);
    }





    //測試
//    http://c8db6df2e1f8.ngrok.io/orders/ezship?order_id=1111&sn_id=270023768&order_status=E01&webPara=
    @GetMapping("/orders/ezship")
    public ResponseVo returnMsg(
            @RequestParam Long order_id,
            @RequestParam String sn_id,
            @RequestParam String order_status,
            @RequestParam String webPara
    ) {
        log.info(String.valueOf(order_id));
        log.info(sn_id);
        log.info(order_status);
        log.info(webPara);

        if (!EzshipOrderStatus.S01.getOrderStatus().equals(order_status)){
            for(EzshipOrderStatus ezshipStatus:EzshipOrderStatus.values()){
                log.info(ezshipStatus.getOrderStatus());
                if (ezshipStatus.getOrderStatus().equals(order_status)){
                    return ResponseVo.error(ResponseEnum.ORDER_NOT_EXIST,ezshipStatus.getDesc());
                }
            }
            //訂單新增成功
        }
        return ResponseVo.success();
    }
}
