package com.java.ghmall.controller.portal;

import com.java.ghmall.consts.MallConst;
import com.java.ghmall.form.ShippingForm;
import com.java.ghmall.pojo.User;
import com.java.ghmall.service.IShippingService;
import com.java.ghmall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

@RestController
@Slf4j
public class ShippingController {

    @Autowired
    private IShippingService shippingService;


    @PostMapping("/shippings")
    public ResponseVo add(@Valid @RequestBody ShippingForm form,
                          HttpSession session) {
        User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        Integer shippingType = 0;
        return shippingService.add(user.getId(), form, shippingType);
    }

//    http://map.ezship.com.tw/ezship_map_web.jsp?suID=buyer@myweb.com.tw&processID=155922&stCate=&stCode=&rtURL=https://www.ezship.com.tw/emap/ezship_simulation_mappg_hy.jsp&webPara=simulationpage
    @PostMapping("/shipping")
    public RedirectView addByReomteStore(
            @Validated  ShippingForm shippingForm
    ) {
        log.info("shippingForm={}",shippingForm);
        Integer userId = Integer.parseInt(shippingForm.getWebPara());

        Integer shippingType = 1;
        ResponseVo<Map<String, Integer>> result = shippingService.add(userId, shippingForm, shippingType);
        log.info("ResponseVo={}",result);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(MallConst.WEB_SITE_URL+"cart/shopDelivery");
        return redirectView;
//        成功：
//        https://map.ezship.com.tw/ezship_map_web.jsp?
//        suID=buyer@myweb.com.tw&processID=155922&
//        stCate=&
//        stCode=&
//        rtURL=http://97f993b6b8ab.ngrok.io/shipping&
//        webPara=simulationpage
//        http://map.ezship.com.tw/ezship_map_web.jsp?suID=buyer@myweb.com.tw&processID=155922&stCate=&stCode=&rtURL=http://97f993b6b8ab.ngrok.io/shipping&webPara=simulationpage
    }


    @DeleteMapping("/shippings/{shippingId}")
    public ResponseVo delete(@PathVariable Integer shippingId,
                             HttpSession session) {
        User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        return shippingService.delete(user.getId(), shippingId);
    }

    @PutMapping("/shippings/{shippingId}")
    public ResponseVo update(@PathVariable Integer shippingId,
                             @Valid @RequestBody ShippingForm form,
                             HttpSession session) {
        User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        return shippingService.update(user.getId(), shippingId, form);
    }

    @GetMapping("/shippings")
    public ResponseVo list(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                           HttpSession session) {
        User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        return shippingService.list(user.getId(), pageNum, pageSize);
    }


}
