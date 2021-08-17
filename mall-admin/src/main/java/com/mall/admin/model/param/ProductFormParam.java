package com.mall.admin.model.param;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductFormParam {

    private Long id;
    private Integer categoryId;
    private Integer kind;
    private String title;
    private Integer brandId;
    private String name;
    private String origin;
    private String productionDate;
    private Double price;
    private Integer amount;
    private String imageUrl;
    private String sendAddress;
    private String parcelType;
    private String salesService;
    private Integer creatorId;
    private Integer status;
}
