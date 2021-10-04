package com.java.ghmall.service;

import com.github.pagehelper.PageInfo;
import com.java.ghmall.form.ProductForm;
import com.java.ghmall.pojo.Product;
import com.java.ghmall.vo.ProductDetailVo;
import com.java.ghmall.vo.ProductVo;
import com.java.ghmall.vo.ResponseVo;

public interface IProductService {
    ResponseVo<PageInfo> list(Integer categoryId, Integer pageNum, Integer pageSize);

    ResponseVo<ProductDetailVo> detail(Integer productId);

    public ResponseVo<String> setSaleStatus(Integer productId,Integer status);

    public ResponseVo saveOrUpdateProduct(ProductForm productForm);

    public ResponseVo deleteProduct(Integer productId);


    ResponseVo manageProductDetail(Integer productId);

    ResponseVo<PageInfo> searchProduct(String productName, Integer productId, Integer pageNum, Integer pageSize);
}
