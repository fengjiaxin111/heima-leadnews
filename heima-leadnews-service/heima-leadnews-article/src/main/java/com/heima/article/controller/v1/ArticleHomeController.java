package com.heima.article.controller.v1;

import com.heima.article.mapper.ApArticleMapper;
import com.heima.article.service.impl.ArticleServiceImpl;
import com.heima.common.constants.ArticleConstants;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.user.dtos.ArticleHomeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 冯佳鑫
 * @Package：com.heima.article.controller.v1
 * @Project：heima-leadnews
 * @name：ArticleHomeController
 * @Date：2023/9/24 15:03
 * @Filename：ArticleHomeController
 */
@RestController
@RequestMapping("/api/v1/article")
public class ArticleHomeController {
    @Autowired
    ArticleServiceImpl articleService;
    /**
     * 加载首页
     * @param dto
     * @return
     */
    @PostMapping("/load")
    public ResponseResult load(@RequestBody ArticleHomeDto dto) {

        ResponseResult load = articleService.load(ArticleConstants.LOADTYPE_LOAD_MORE, dto);

        return load;
    }

    /**
     * 加载更多
     * @param dto
     * @return
     */
    @PostMapping("/loadmore")
    public ResponseResult loadMore(@RequestBody ArticleHomeDto dto) {
        ResponseResult load = articleService.load(ArticleConstants.LOADTYPE_LOAD_MORE, dto);

        return load;
    }

    /**
     * 加载最新
     * @param dto
     * @return
     */
    @PostMapping("/loadnew")
    public ResponseResult loadNew(@RequestBody ArticleHomeDto dto) {
        ResponseResult load = articleService.load(ArticleConstants.LOADTYPE_LOAD_NEW, dto);

        return load;
    }
}
