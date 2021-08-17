package com.mall.admin.model.param;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductQueryParam {

    private Long id;
    private Integer categoryId;
    private String title;
    private Integer brandId;
    private Integer status;
    private Integer creatorId;
}
