package com.mall.admin.model.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CategoryOptionsVo {

    private Long value;
    private String label;
    private List<CategoryOptionsVo> children;
}
