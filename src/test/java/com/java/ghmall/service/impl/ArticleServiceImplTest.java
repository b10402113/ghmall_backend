package com.java.ghmall.service.impl;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.java.ghmall.GhmallApplicationTests;
import com.java.ghmall.form.ArticleAddForm;
import com.java.ghmall.form.ArticleUpdateForm;
import com.java.ghmall.service.IArticleService;
import com.java.ghmall.service.impl.manage.IArticleManageService;
import com.java.ghmall.vo.ArticleVo;
import com.java.ghmall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class ArticleServiceImplTest extends GhmallApplicationTests {
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @Autowired
    IArticleManageService articleManageService;

    @Autowired
    IArticleService articleService;

    @Test
    public void add() {
        log.info("【新增文章...】");
        ArticleAddForm form = new ArticleAddForm();
        form.setTitle("測試的文章3");
        form.setDetail("這是一篇測試的文章4");
        ResponseVo responseVo = articleManageService.add(form);

//        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }

    @Test
    public void update() {
        log.info("【修改文章...】");
        ArticleUpdateForm form = new ArticleUpdateForm();
        form.setDetail("修改了");
        form.setTitle("修改的文章2");
        form.setIsHot(true);
        form.setType(1);
        ResponseVo responseVo = articleManageService.update(146,form);

//        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }

    @Test
    public void select() {
        log.info("【選取文章...】");
        ResponseVo<PageInfo> list = articleService.list(1, 10);
        log.info("result={}", gson.toJson(list));

//        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }

    @Test
    public void detail() {
        log.info("【選取文章...】");
        ResponseVo<ArticleVo> detail = articleService.detail(146);
        log.info("result={}", gson.toJson(detail));

//        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }
}
