package com.heima.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.user.dtos.ArticleHomeDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 冯佳鑫
 * @Package：com.heima.article.service
 * @Project：heima-leadnews
 * @name：ArticleService
 * @Date：2023/9/24 16:18
 * @Filename：ArticleService
 */
@Service
public interface ArticleService {
    ResponseResult load(Short loadtype, ArticleHomeDto dto);
}
