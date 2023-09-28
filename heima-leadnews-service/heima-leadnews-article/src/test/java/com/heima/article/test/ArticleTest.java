package com.heima.article.test;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.heima.article.mapper.ApArticleContentMapper;
import com.heima.article.mapper.ApArticleMapper;
import com.heima.file.service.FileStorageService;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.article.pojos.ApArticleContent;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.HashMap;

/**
 * @author 冯佳鑫
 * @Package：com.heima.article.test
 * @Project：heima-leadnews02
 * @name：ArticleTest
 * @Date：2023/9/27 23:45
 * @Filename：ArticleTest
 */
@SpringBootTest
public class ArticleTest {
    @Autowired
    ApArticleContentMapper apArticleContentMapper;
    @Autowired
    Configuration configuration;
    @Autowired
    FileStorageService fileStorageService;
    @Autowired
    ApArticleMapper apArticleMapper;
    /**
     * 通过freemarker生成html，并且上传到minio
     */
    @Test
    void test() throws IOException, TemplateException {
        //1.获取文章内容
        ApArticleContent apArticleContent = apArticleContentMapper.selectOne(Wrappers.<ApArticleContent>lambdaQuery().
                eq(ApArticleContent::getArticleId, 1302862387124125698L));

        //文章内容通过freemarker生成html
        Template template = configuration.getTemplate("article.ftl");
        String content = apArticleContent.getContent();
        if (content!=null&&apArticleContent!=null) {
            JSONArray objects = JSONArray.parseArray(apArticleContent.getContent());
            HashMap<String, Object> map = new HashMap<>();
            map.put("content", objects);
            StringWriter out = new StringWriter();
            template.process(map, out);
            //将html上传到minio
            ByteArrayInputStream inputStream = new ByteArrayInputStream(out.toString().getBytes());
            String path = fileStorageService.uploadHtmlFile("", apArticleContent.getId() + ".html",
                    inputStream);
            //4.修改ap_article表，保存static_url字段
            ApArticle apArticle = new ApArticle();
            apArticle.setId(apArticleContent.getArticleId());
            apArticle.setStaticUrl(path);
            apArticleMapper.updateById(apArticle);
        }

    }
}
