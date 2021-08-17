package com.mall.admin.model.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductUpdateVo {

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
}
