package com.groupe4.pidev.mapper;

import com.groupe4.pidev.entities.Article;
import com.groupe4.pidev.entities.ArticleRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.web.multipart.MultipartFile;

@Mapper
public interface ArticleMapper {

    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);
    Article articleRequestBodyToArticle(ArticleRequestBody articleRequestBody);

}
