package com.java.ghmall.service;

import com.github.pagehelper.PageInfo;
import com.java.ghmall.form.CateAddForm;
import com.java.ghmall.form.CateUpdateForm;
import com.java.ghmall.pojo.Category;
import com.java.ghmall.vo.CategoryVo;
import com.java.ghmall.vo.ResponseVo;

import java.util.List;
import java.util.Set;

public interface ICategoryService {
    ResponseVo<List<CategoryVo>> selectAll();
    void findSubCategoryId(Integer id, Set<Integer> resultSet);

    ResponseVo addCategory(CateAddForm cateAddForm);

    ResponseVo updateCategoryName(Integer categoryId, String categoryName);

    ResponseVo updateCategory(CateUpdateForm cateUpdateForm);

    ResponseVo setCategoryStatus(Integer categoryId, Boolean status);

    ResponseVo<List<CategoryVo>> selectAllByManage();

    public ResponseVo updateCategorySortOrder(Integer sortOrder, Integer categoryId);

    ResponseVo deleteCategoryById(Integer categoryId);
}
