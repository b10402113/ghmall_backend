package com.java.ghmall.service.impl;

import com.java.ghmall.consts.MallConst;
import com.java.ghmall.form.EzshipOrderForm;
import com.java.ghmall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service
public class EzshipServiceImpl {


    private static String suId = "germanhealthbuy@gmail.com";


//    su_id: "germanhealthbuy@gmail.com",
//    order_id: 123,
//    order_status: "A02",
//    order_amount: "100",
//    order_type: "1",
//    rv_name: "鄭鄭1",
//    rv_email: "b10402113@gmail.com",
//    rv_mobile: "0975518436",
//    st_code: "TLF4273",
//    rtn_url: "http://08370475f085.ngrok.io/orders/ezship"
    public ResponseVo createEzshipOrder(EzshipOrderForm ezshipOrderForm) {
        Map<String, String> paramMap = new HashMap<>(16);
        paramMap.put("su_id", MallConst.suId);
        paramMap.put("order_id", ezshipOrderForm.getOrder_id().toString());
        paramMap.put("order_status", MallConst.ezShipOrderStatus);
        paramMap.put("order_amount", ezshipOrderForm.getOrder_amount().toString());
        paramMap.put("order_type", "1");
        paramMap.put("rv_name", ezshipOrderForm.getRv_name());
        paramMap.put("rv_email", ezshipOrderForm.getRv_email());
        paramMap.put("rv_mobile", ezshipOrderForm.getRv_mobile());
        paramMap.put("st_code", ezshipOrderForm.getSt_code());
        paramMap.put("rtn_url", MallConst.EZSHIP_RETURN_URL);

        ResponseVo object = getForObject(MallConst.ezShipCreateOrderUrl, paramMap);
        return ResponseVo.success(ezshipOrderForm);
    }


    private static ResponseVo getForObject(String url, Object object) {
        StringBuffer stringBuffer = new StringBuffer(url);
        if (object instanceof Map) {
            Iterator iterator = ((Map) object).entrySet().iterator();
            if (iterator.hasNext()) {
                stringBuffer.append("?");
                Object element;
                while (iterator.hasNext()) {
                    element = iterator.next();
                    Map.Entry<String, Object> entry = (Map.Entry) element;
                    //过滤value为null，value为null时进行拼接字符串会变成 "null"字符串
                    if (entry.getValue() != null) {
                        stringBuffer.append(element).append("&");
                    }
                    url = stringBuffer.substring(0, stringBuffer.length() - 1);
                }
            }
        } else {
            throw new RuntimeException("url请求:" + url + "请求参数有误不是map类型");
        }
        System.out.println(url);
        return new RestTemplate().getForObject(url, ResponseVo.class);
    }
}
