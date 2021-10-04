package com.java.ghmall.interceptor;

import com.java.ghmall.consts.MallConst;
import com.java.ghmall.enums.ResponseEnum;
import com.java.ghmall.exception.UserLoginException;
import com.java.ghmall.pojo.User;
import com.java.ghmall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class UserLoginInterceptor implements HandlerInterceptor {

    /**
     * true 表示继续流程，false表示中断
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle...");
        log.info("CURRENT_USER:{} ",MallConst.CURRENT_USER);

        User user = (User) request.getSession().getAttribute(MallConst.CURRENT_USER);
        if (user == null) {
            log.info("user=null");
            throw new UserLoginException();
//            return false;

//			response.getWriter().print("error");
//			return false;
//			return ResponseVo.error(ResponseEnum.NEED_LOGIN);
        }
        return true;
    }
}