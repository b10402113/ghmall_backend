package com.java.ghmall.service.impl.manage;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.ghmall.dao.CategoryMapper;
import com.java.ghmall.dao.ProductMapper;
import com.java.ghmall.pojo.Product;
import com.java.ghmall.service.ICategoryService;
import com.java.ghmall.vo.ProductVo;
import com.java.ghmall.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductManageServiceImpl implements IProductManageService{
    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ProductMapper productMapper;



    public ResponseVo<PageInfo> list(Integer categoryId, Integer pageNum, Integer pageSize,String query) {
        System.out.println(categoryId);
        Set<Integer> categoryIdSet = new HashSet<>();
        if(categoryId != null){
            categoryService.findSubCategoryId(categoryId,categoryIdSet);
            categoryIdSet.add(categoryId);
        }
        PageHelper.startPage(pageNum,pageSize);
        List<Product> productList = productMapper.selectByCategoryIdSetAll(categoryIdSet,query);
        PageInfo pageInfo = new PageInfo<>(productList);
        pageInfo.setList(productList);
        return ResponseVo.success(pageInfo);
    }

}
