package com.java.ghmall.dao;

import com.java.ghmall.pojo.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKeyWithBLOBs(Product record);

    int updateByPrimaryKey(Product record);

    List<Product> selectByCategoryIdSet(@Param("categoryIdSet") Set<Integer> categoryIdSet);

    List<Product> selectByCategoryIdSetAll(@Param("categoryIdSet") Set<Integer> categoryIdSet,String query);

    List<Product> selectByProductIdSet(@Param("productIdSet") Set<Integer> productIdSet);

    List<Product> selectByNameAndProductId(String productName, Integer productId);
}