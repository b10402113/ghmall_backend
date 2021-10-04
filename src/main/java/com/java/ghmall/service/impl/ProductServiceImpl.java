package com.java.ghmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.java.ghmall.common.ResponseCode;
import com.java.ghmall.common.ServerResponse;
import com.java.ghmall.dao.CategoryMapper;
import com.java.ghmall.dao.ProductMapper;
import com.java.ghmall.enums.ProductStatusEnum;
import com.java.ghmall.enums.ResponseEnum;
import com.java.ghmall.form.ProductForm;
import com.java.ghmall.pojo.Category;
import com.java.ghmall.pojo.Product;
import com.java.ghmall.service.ICategoryService;
import com.java.ghmall.service.IProductService;
import com.java.ghmall.utils.PropertiesUtil;
import com.java.ghmall.vo.ProductDetailVo;
import com.java.ghmall.vo.ProductVo;
import com.java.ghmall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public ResponseVo<PageInfo> list(Integer categoryId, Integer pageNum, Integer pageSize) {
        Set<Integer> categoryIdSet = new HashSet<>();
        if(categoryId != null){
            categoryService.findSubCategoryId(categoryId,categoryIdSet);
            categoryIdSet.add(categoryId);
        }
        PageHelper.startPage(pageNum,pageSize);
        List<Product> productList = productMapper.selectByCategoryIdSet(categoryIdSet);
        List<ProductVo> productVoList = productList.stream()
                .map(e ->{
                    ProductVo productVo = new ProductVo();
                    BeanUtils.copyProperties(e,productVo);
                    return productVo;
                })
                .collect(Collectors.toList());

        PageInfo pageInfo = new PageInfo<>(productList);
        pageInfo.setList(productVoList);
        return ResponseVo.success(pageInfo);
    }

    @Override
    public ResponseVo<ProductDetailVo> detail(Integer productId) {
        Product product = productMapper.selectByPrimaryKey(productId);
        if(product == null){
            return ResponseVo.error(ResponseEnum.PRODUCT_NOT_EXIST);
        }
        if (product.getStatus().equals(ProductStatusEnum.OFF_SALE.getCode())
                || product.getStatus().equals(ProductStatusEnum.DELETE.getCode())) {
            return ResponseVo.error(ResponseEnum.PRODUCT_OFF_SALE_OR_DELETE);
        }
        ProductDetailVo productDetailVo = new ProductDetailVo();
        BeanUtils.copyProperties(product, productDetailVo);
        // todo 要修改
        productDetailVo.setImageHost("http://1234567");
        productDetailVo.setStock(product.getStock() > 100 ? 100 : product.getStock());
        return ResponseVo.success(productDetailVo);

    }



    public ResponseVo saveOrUpdateProduct(ProductForm productForm){
        Product product = new Product();
        BeanUtils.copyProperties(productForm,product);
        if(product != null)
        {
            if(StringUtils.isNotBlank(product.getSubImages())){
                String[] subImageArray = product.getSubImages().split(",");
                if(subImageArray.length > 0){
                    product.setMainImage(subImageArray[0]);
                }
            }

            if(product.getId() != null){
                int rowCount = productMapper.updateByPrimaryKeySelective(product);
                if(rowCount > 0){
                    return ResponseVo.successByMsg("更新产品成功");
                }
                return ResponseVo.successByMsg("更新产品失败");
            }else{
                int rowCount = productMapper.insertSelective(product);
                if(rowCount > 0){
                    return ResponseVo.successByMsg("新增产品成功");
                }
                return ResponseVo.successByMsg("新增产品失败");
            }
        }
        return ResponseVo.error(ResponseEnum.ERROR,"新增或更新产品参数不正确");
    }

    @Override
    public ResponseVo deleteProduct(Integer productId) {
        if(productId == null){
            return ResponseVo.error(ResponseEnum.ERROR,"商品不存在");
        }
        int row = productMapper.deleteByPrimaryKey(productId);
        if(row > 0){
            return ResponseVo.successByMsg("刪除產品成功");
        }
        return ResponseVo.error(ResponseEnum.ERROR,"刪除產品失敗");
    }


    public ResponseVo<String> setSaleStatus(Integer productId,Integer status){
        if(productId == null || status == null){
            return ResponseVo.error(ResponseEnum.ERROR,"參數不正確");
        }
        Product product = new Product();
        product.setId(productId);
        product.setStatus(status);
        int rowCount = productMapper.updateByPrimaryKeySelective(product);
        if(rowCount > 0){
            return ResponseVo.successByMsg("修改产品销售状态成功");
        }
        return ResponseVo.error(ResponseEnum.ERROR,"修改产品销售状态失败");
    }

    @Override
    public ResponseVo manageProductDetail(Integer productId) {
        if(productId == null){
            return ResponseVo.error(ResponseEnum.ERROR,"輸入參數錯誤");
        }
        Product product = productMapper.selectByPrimaryKey(productId);
        if(product == null){
                return ResponseVo.error(ResponseEnum.PRODUCT_NOT_EXIST);
        }
        ProductDetailVo productDetailVo = assembleProductDetailVo(product);
        return ResponseVo.success(productDetailVo);
    }

    @Override
    public ResponseVo<PageInfo> searchProduct(String productName, Integer productId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        if(StringUtils.isNotBlank(productName)){
            productName = new StringBuilder().append("%").append(productName).append("%").toString();
        }
        List<Product> productList = productMapper.selectByNameAndProductId(productName,productId);
        List<ProductVo> productListVoList = Lists.newArrayList();
        for(Product productItem : productList){
            ProductVo productListVo = assembleProductListVo(productItem);
            productListVoList.add(productListVo);
        }
        PageInfo pageResult = new PageInfo(productList);
        pageResult.setList(productListVoList);
        return ResponseVo.success(pageResult);
    }
    private ProductVo assembleProductListVo(Product productItem){
        ProductVo productVo = new ProductVo();
        BeanUtils.copyProperties(productItem,productVo);
        return productVo;
    }

    private ProductDetailVo assembleProductDetailVo(Product product){
        ProductDetailVo productDetailVo = new ProductDetailVo();
        productDetailVo.setId(product.getId());
        productDetailVo.setSubtitle(product.getSubtitle());
        productDetailVo.setPrice(product.getPrice());
        productDetailVo.setMainImage(product.getMainImage());
        productDetailVo.setSubImages(product.getSubImages());
        productDetailVo.setCategoryId(product.getCategoryId());
        productDetailVo.setDetail(product.getDetail());
        productDetailVo.setName(product.getName());
        productDetailVo.setStatus(product.getStatus());
        productDetailVo.setStock(product.getStock());

        productDetailVo.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix","http://img.happymmall.com/"));

        Category category = categoryMapper.selectByPrimaryKey(product.getCategoryId());
        if(category == null){
            productDetailVo.setParentCategoryId(0);//默认根节点
        }else{
            productDetailVo.setParentCategoryId(category.getParentId());
        }

        productDetailVo.setCreateTime(product.getCreateTime());
        productDetailVo.setUpdateTime(product.getUpdateTime());
        return productDetailVo;
    }


}
