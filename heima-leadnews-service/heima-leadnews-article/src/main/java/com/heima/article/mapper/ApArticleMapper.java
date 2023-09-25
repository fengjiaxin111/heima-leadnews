package com.heima.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heima.model.user.dtos.ArticleHomeDto;
import com.heima.model.article.pojos.ApArticle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 冯佳鑫
 * @Package：com.heima.article.mapper
 * @Project：heima-leadnews
 * @name：ApArticleMapper
 * @Date：2023/9/24 15:05
 * @Filename：ApArticleMapper
 */
@Mapper
public interface ApArticleMapper extends BaseMapper<ApArticle> {
    /**
     *type
     * @param dto
     * @param type 1加载更多 2加载最新
     * @return
     */
    public List<ApArticle> loadArticleList(@Param("dto") ArticleHomeDto dto, @Param("type") Short type);
}
