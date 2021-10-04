package com.java.ghmall.service.impl.manage;


import com.java.ghmall.form.ArticleAddForm;
import com.java.ghmall.form.ArticleUpdateForm;
import com.java.ghmall.pojo.Article;
import com.java.ghmall.vo.ArticleVo;
import com.java.ghmall.vo.ResponseVo;

public interface IArticleManageService {
    ResponseVo<Article> add(ArticleAddForm form);
    ResponseVo<Article> update(Integer articleId, ArticleUpdateForm form);
}
