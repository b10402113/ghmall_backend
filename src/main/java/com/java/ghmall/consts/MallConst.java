package com.java.ghmall.consts;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MallConst {
    public static final String CURRENT_USER = "currentUser";
    public static final Integer ROOT_PARENT_ID = 0;
    public static final Integer ROLE_ADMIN = 1;
//    public static final String WEB_SITE_URL = "http://germanhealth.com";
    public static final String twtUrl = "http://api.twsms.com/json/sms_send.php?username=germanhealthbuy&password=lucky2951&mobile={phone}&message={msg}";
    public static final String suId = "germanhealthbuy@gmail.com";
//    public static final String ezshipRtn_url = "http://germanhealth.com/orders/ezship";
    public static final String ezShipCreateOrderUrl = "https://www.ezship.com.tw/emap/ezship_request_order_api_ex.jsp";
    public static final String ezShipOrderStatus = "A02";

    public static final int POSTAGE_FREE = 2000;


    public static String FILE_UPLOAD_DIR;
    public static String EZSHIP_RETURN_URL;
    public static String WEB_SITE_URL;

    @Value("${file.upload.dir}")
    public void setFileUploadDir(String fileUploadDir){
        FILE_UPLOAD_DIR = fileUploadDir;
    }

    @Value("${ghmall.ezship_rtn}")
    public void setezshipRtn_url(String ezshipRtn_url){
        EZSHIP_RETURN_URL = ezshipRtn_url;
    }

    @Value("${ghmall.website}")
    public void setWebSiteUrl(String webSiteUrl){
        WEB_SITE_URL = webSiteUrl;
    }
}
