package com.java.ghmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.ghmall.common.ServerResponse;
import com.java.ghmall.consts.MallConst;
import com.java.ghmall.dao.CategoryMapper;
import com.java.ghmall.enums.ResponseEnum;
import com.java.ghmall.form.CateAddForm;
import com.java.ghmall.form.CateUpdateForm;
import com.java.ghmall.pojo.Category;
import com.java.ghmall.service.ICategoryService;
import com.java.ghmall.vo.CategoryVo;
import com.java.ghmall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 耗时：http(请求微信api) > 磁盘 > 内存
     * mysql(内网+磁盘)
     * @return
     */
    @Override
    public ResponseVo<List<CategoryVo>> selectAll() {
        List<Category> categories = categoryMapper.selectAll();
        //查出parent_id=0
//		for (Category category : categories) {
//			if (category.getParentId().equals(ROOT_PARENT_ID)) {
//				CategoryVo categoryVo = new CategoryVo();
//				BeanUtils.copyProperties(category, categoryVo);
//				categoryVoList.add(categoryVo);
//			}
//		}

        //lambda + stream
        List<CategoryVo> categoryVoList = categories.stream()
                .filter(e -> e.getParentId().equals(MallConst.ROOT_PARENT_ID))
                .map(this::category2CategoryVo)
                .sorted(Comparator.comparing(CategoryVo::getSortOrder).reversed())
                .collect(Collectors.toList());

        //查询子目录
        findSubCategory(categoryVoList, categories);

        return ResponseVo.success(categoryVoList);
    }

    @Override
    public ResponseVo<List<CategoryVo>> selectAllByManage() {
        List<Category> categories = categoryMapper.selectAllByManage();

        //lambda + stream
        List<CategoryVo> categoryVoList = categories.stream()
                .filter(e -> e.getParentId().equals(MallConst.ROOT_PARENT_ID))
                .map(this::category2CategoryVo)
                .sorted(Comparator.comparing(CategoryVo::getSortOrder).reversed())
                .collect(Collectors.toList());

        //查询子目录
        findSubCategory(categoryVoList, categories);

        return ResponseVo.success(categoryVoList);
    }

    @Override
    public void findSubCategoryId(Integer id, Set<Integer> resultSet) {
        List<Category> categories = categoryMapper.selectAll();
        findSubCategoryId(id, resultSet, categories);
    }

    @Override
    public ResponseVo addCategory(CateAddForm cateAddForm) {
        Category category = new Category();
        BeanUtils.copyProperties(cateAddForm,category);
        category.setStatus(true);//这个分类是可用的
        int rowCount = categoryMapper.insertSelective(category);
        if(rowCount > 0){
            return ResponseVo.successByMsg("添加品类成功");
        }
        return ResponseVo.error(ResponseEnum.ERROR,"添加品类失败");
    }

    @Override
    public ResponseVo updateCategoryName(Integer categoryId, String categoryName) {
        if(categoryId == null || StringUtils.isBlank(categoryName)){
            return ResponseVo.error(ResponseEnum.ERROR,"更新品类参数错误");
        }
        Category category = new Category();
        category.setId(categoryId);
        category.setName(categoryName);

        int rowCount = categoryMapper.updateByPrimaryKeySelective(category);
        if(rowCount > 0){
            return ResponseVo.successByMsg("更新品类名字成功");
        }
        return ResponseVo.error(ResponseEnum.ERROR,"更新品类名字失败");
    }

    @Override
    public ResponseVo updateCategory(CateUpdateForm cateUpdateForm) {
        Category category = categoryMapper.selectByPrimaryKey(cateUpdateForm.getId());
        if(category == null){
            return ResponseVo.error(ResponseEnum.ERROR,"更新品类名字失败");
        }
        BeanUtils.copyProperties(cateUpdateForm,category);
        int rowCount = categoryMapper.updateByPrimaryKeySelective(category);
        if(rowCount > 0 ){
            return ResponseVo.successByMsg("更新品类訊息成功");
        }
        return ResponseVo.error(ResponseEnum.ERROR,"更新品类名字失败");
    }

    @Override
    public ResponseVo updateCategorySortOrder(Integer sortOrder, Integer categoryId) {
        if(categoryId == null || sortOrder ==null
        ){
            return ResponseVo.error(ResponseEnum.ERROR,"更新品类参数错误");
        }
        Category category = new Category();
        category.setId(categoryId);
        category.setSortOrder(sortOrder);

        int rowCount = categoryMapper.updateByPrimaryKeySelective(category);
        if(rowCount > 0){
            return ResponseVo.successByMsg("更新產品order成功");
        }
        return ResponseVo.error(ResponseEnum.ERROR,"更新產品order失敗");
    }

    @Override
    public ResponseVo deleteCategoryById(Integer categoryId) {
        if(categoryId == null){
            return  ResponseVo.error(ResponseEnum.ERROR,"刪除失敗");
        }
        int row = categoryMapper.deleteByPrimaryKey(categoryId);
        if(row > 0){
            return ResponseVo.successByMsg("刪除分類成功");
        }
        return ResponseVo.error(ResponseEnum.ERROR,"刪除分類失敗");
    }

    @Override
    public ResponseVo setCategoryStatus(Integer categoryId, Boolean status) {
        if(categoryId == null || status == null){
            return ResponseVo.error(ResponseEnum.ERROR,"更新品类参数错误");
        }
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        if (category == null){
            return ResponseVo.error(ResponseEnum.ERROR,"沒有此類別");
        }
        category.setStatus(status);
        int rowCount = categoryMapper.updateByPrimaryKeySelective(category);
        if(rowCount > 0){
            return ResponseVo.successByMsg("成功設置類別狀態");
        }
        return ResponseVo.error(ResponseEnum.ERROR,"更新類別狀態失败");
    }

    private void findSubCategoryId(Integer id, Set<Integer> resultSet, List<Category> categories) {
        for (Category category : categories) {
            if (category.getParentId().equals(id)) {
                resultSet.add(category.getId());
                findSubCategoryId(category.getId(), resultSet, categories);
            }
        }
    }


    private void findSubCategory(List<CategoryVo> categoryVoList, List<Category> categories) {
        for (CategoryVo categoryVo : categoryVoList) {
            List<CategoryVo> subCategoryVoList = new ArrayList<>();

            for (Category category : categories) {
                //如果查到内容，设置subCategory, 继续往下查
                if (categoryVo.getId().equals(category.getParentId())) {
                    CategoryVo subCategoryVo = category2CategoryVo(category);
                    subCategoryVoList.add(subCategoryVo);
                }

                subCategoryVoList.sort(Comparator.comparing(CategoryVo::getSortOrder).reversed());
                categoryVo.setSubCategories(subCategoryVoList);

                findSubCategory(subCategoryVoList, categories);
            }
        }
    }



    private CategoryVo category2CategoryVo(Category category) {
        CategoryVo categoryVo = new CategoryVo();
        BeanUtils.copyProperties(category, categoryVo);
        return categoryVo;
    }

}
