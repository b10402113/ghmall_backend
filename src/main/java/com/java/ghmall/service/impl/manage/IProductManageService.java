package com.java.ghmall.service.impl.manage;

import com.github.pagehelper.PageInfo;
import com.java.ghmall.vo.ResponseVo;

public interface IProductManageService {
    ResponseVo<PageInfo> list(Integer categoryId, Integer pageNum, Integer pageSize,String query);
}
