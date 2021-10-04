package com.java.ghmall.controller.portal;


import com.github.pagehelper.PageInfo;
import com.java.ghmall.common.Const;
import com.java.ghmall.common.ResponseCode;
import com.java.ghmall.common.ServerResponse;
import com.java.ghmall.service.IProductService;
import com.java.ghmall.vo.ProductDetailVo;
import com.java.ghmall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping("/products")
    public ResponseVo<PageInfo> list(@RequestParam(required = false) Integer categoryId,
                                     @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                     @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return productService.list(categoryId, pageNum, pageSize);
    }

    @GetMapping("/products/{productId}")
    public ResponseVo<ProductDetailVo> detail(@PathVariable Integer productId) {
        return productService.detail(productId);
    }

    @GetMapping("/products/search")
    public ResponseVo<PageInfo> productSearch(@RequestParam(required = false) String productName,@RequestParam(required = false) Integer productId, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return productService.searchProduct(productName, productId, pageNum, pageSize);
    }
}
