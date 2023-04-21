package com.example.eeuse.model;

import cn.easyes.annotation.IndexField;
import cn.easyes.annotation.IndexName;
import cn.easyes.annotation.rely.Analyzer;
import cn.easyes.annotation.rely.FieldType;
import lombok.Data;

/**
 * ES数据模型
 * <p>
 * Copyright © 2021 xpc1024 All Rights Reserved
 **/
@Data
@IndexName("t_doc")
public class Doc {
    private String id;
    private String title;
    @IndexField(fieldType = FieldType.TEXT,analyzer = Analyzer.IK_SMART)
    private String content;
    private String author;
    @IndexField(fieldType = FieldType.INTEGER)
    private Integer star;
}
