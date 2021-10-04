package com.java.ghmall.service.impl.manage;

import com.java.ghmall.dao.ArticleMapper;
import com.java.ghmall.enums.ResponseEnum;
import com.java.ghmall.form.ArticleAddForm;
import com.java.ghmall.form.ArticleUpdateForm;
import com.java.ghmall.pojo.Article;
import com.java.ghmall.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleManageServiceImpl implements IArticleManageService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public ResponseVo<Article> add(ArticleAddForm form) {
        Article article = new Article();
        BeanUtils.copyProperties(form,article);
        int row = articleMapper.insertSelective(article);
        if(row == 0){
            return ResponseVo.error(ResponseEnum.ERROR,"新增文章失敗");
        }
        return ResponseVo.success(article);
    }

    @Override
    public ResponseVo<Article> update(Integer articleId, ArticleUpdateForm form) {
        Article article = articleMapper.selectByPrimaryKey(articleId);
        BeanUtils.copyProperties(form,article);
        int row = articleMapper.updateByPrimaryKeySelective(article);
        if(row == 0){
            return ResponseVo.error(ResponseEnum.ERROR,"修改文章失敗");
        }
        return ResponseVo.success(article);
    }


}
