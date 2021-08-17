package com.mall.admin.model.param;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryParam {

    private Long id;
    private String name;
    private Integer level;
    private Integer sort;
    private Long parentId;
}
