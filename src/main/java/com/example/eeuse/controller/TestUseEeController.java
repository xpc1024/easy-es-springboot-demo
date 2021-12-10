package com.example.eeuse.controller;

import com.example.eeuse.mapper.DocumentMapper;
import com.example.eeuse.model.Document;
import com.xpc.easyes.core.conditions.LambdaEsIndexWrapper;
import com.xpc.easyes.core.conditions.LambdaEsQueryWrapper;
import com.xpc.easyes.core.enums.FieldType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 测试使用Easy-ES
 * <p>
 * Copyright © 2021 xpc1024 All Rights Reserved
 **/
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestUseEeController {
    private final DocumentMapper documentMapper;

    @GetMapping("/index")
    public Boolean index() {
        // 初始化-> 创建索引,相当于MySQL建表 | 此接口须首先调用,只调用一次即可
        LambdaEsIndexWrapper<Document> indexWrapper = new LambdaEsIndexWrapper<>();
        indexWrapper.indexName(Document.class.getSimpleName().toLowerCase());
        indexWrapper.mapping(Document::getTitle, FieldType.KEYWORD)
                .mapping(Document::getContent, FieldType.TEXT);
        documentMapper.createIndex(indexWrapper);
        return Boolean.TRUE;
    }

    @GetMapping("/insert")
    public Integer insert() {
        // 初始化-> 新增数据
        Document document = new Document();
        document.setTitle("老汉");
        document.setContent("推*技术过硬");
        return documentMapper.insert(document);
    }

    @GetMapping("/search")
    public List<Document> search() {
        // 查询出所有标题为老汉的文档列表
        LambdaEsQueryWrapper<Document> wrapper = new LambdaEsQueryWrapper<>();
        wrapper.eq(Document::getTitle, "老汉");
        return documentMapper.selectList(wrapper);
    }
}
