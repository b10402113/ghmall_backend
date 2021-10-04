package com.java.ghmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.ghmall.dao.ArticleMapper;
import com.java.ghmall.pojo.Article;
import com.java.ghmall.pojo.Product;
import com.java.ghmall.service.IArticleService;
import com.java.ghmall.vo.ArticleVo;
import com.java.ghmall.vo.ProductVo;
import com.java.ghmall.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements IArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public ResponseVo<PageInfo> list(Integer pageNum, Integer pageSize) {
        List<Article> articles = articleMapper.selectAll();

        List<ArticleVo> articleVoList = articles.stream()
                .map(e ->{
                    ArticleVo articleVo = new ArticleVo();
                    BeanUtils.copyProperties(e,articleVo);
                    return articleVo;
                })
                .collect(Collectors.toList());

        PageInfo pageInfo = new PageInfo<>(articles);
        pageInfo.setList(articleVoList);
        return ResponseVo.success(pageInfo);
    }

    @Override
    public ResponseVo<ArticleVo> detail(Integer articleId) {
        Article article = articleMapper.selectByPrimaryKey(articleId);
        ArticleVo articleVo = new ArticleVo();
        BeanUtils.copyProperties(article,articleVo);

        return ResponseVo.success(articleVo);
    }
}
