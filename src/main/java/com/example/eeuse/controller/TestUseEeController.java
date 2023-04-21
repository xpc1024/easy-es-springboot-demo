package com.example.eeuse.controller;


import cn.easyes.core.conditions.select.LambdaEsQueryWrapper;
import com.example.eeuse.mapper.DocMapper;
import com.example.eeuse.model.Doc;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.client.RestHighLevelClient;
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
    private final DocMapper docMapper;
    private final RestHighLevelClient restHighLevelClient;

    @GetMapping("/insert")
    public Integer insert() {
        // 初始化-> 新增数据
        for (int i = 0; i < 9999; i++) {
            Doc doc = new Doc();
            doc.setTitle("老汉" + i);
            doc.setContent("hello world my name is test laohantuiche" + i);
            docMapper.insert(doc);
        }
        return 9999;
    }

    @GetMapping("/list")
    public List<Doc> list() {
        // 查询出所有标题为老汉的文档列表
        LambdaEsQueryWrapper<Doc> wrapper = new LambdaEsQueryWrapper<>();
        wrapper.match(Doc::getContent, "hello");
        return docMapper.selectList(wrapper);
    }
}
