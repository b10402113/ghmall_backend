package com.java.ghmall.service;

import com.github.pagehelper.PageInfo;
import com.java.ghmall.pojo.Article;
import com.java.ghmall.vo.ArticleVo;
import com.java.ghmall.vo.CartVo;
import com.java.ghmall.vo.ResponseVo;

public interface IArticleService {
    ResponseVo<PageInfo> list(Integer pageNum, Integer pageSize);

    ResponseVo<ArticleVo> detail(Integer articleId);
}
