package com.heima.article.service.impl;

import com.heima.article.mapper.ApArticleMapper;
import com.heima.article.service.ArticleService;
import com.heima.common.constants.ArticleConstants;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.user.dtos.ArticleHomeDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author 冯佳鑫
 * @Package：com.heima.article.service.impl
 * @Project：heima-leadnews
 * @name：ArticleServiceImpl
 * @Date：2023/9/24 16:18
 * @Filename：ArticleServiceImpl
 */
@Service
@Slf4j
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ApArticleMapper apArticleMapper;

    /**
     * 返回文章数据
     * @param loadtype
     * @param dto
     * @return
     */
    @Override
    public ResponseResult load(Short loadtype, ArticleHomeDto dto) {
       Integer size =  dto.getSize();
        //检验参数
        if (size  == 0 || size == null){
            dto.setSize(10);
        }
        size =  Math.min(size, 50);
        //检验type参数
        if (!loadtype.equals(ArticleConstants.LOADTYPE_LOAD_MORE)&&
          !loadtype.equals(ArticleConstants.LOADTYPE_LOAD_NEW)){
            loadtype = ArticleConstants.LOADTYPE_LOAD_MORE;
        }
        if(StringUtils.isBlank(dto.getTag())){
            dto.setTag(ArticleConstants.DEFAULT_TAG);
        }
        //时间校验
        if (dto.getMaxBehotTime()==null){
            dto.setMaxBehotTime(new Date());

        }
        if (dto.getMinBehotTime()==null){
            dto.setMinBehotTime(new Date());

        }
        List<ApArticle> apArticles = apArticleMapper.loadArticleList(dto, loadtype);
        if (apArticles==null){
           return ResponseResult.errorResult(AppHttpCodeEnum.DATA_EXIST);
        }
        return ResponseResult.okResult(apArticles);
    }
}
