package com.java.ghmall.service.impl.manage;

import com.github.pagehelper.PageInfo;
import com.java.ghmall.vo.ResponseVo;

public interface IOrderManageService {
    ResponseVo<PageInfo> list(Integer status, Integer pageNum, Integer pageSize);
    ResponseVo detail(Long order_no);
    ResponseVo setStatus(Long order_no,Integer status);

}
