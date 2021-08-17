package com.mall.admin.model.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryVo {

    private Long id;
    private String name;
    private Integer level;
    private Integer sort;
    private Long parentId;
}
