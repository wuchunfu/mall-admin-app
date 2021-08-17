package com.mall.weapp.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Product {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long categoryId;
    private Integer kind;
    private String title;
    private Long brandId;
    private String name;
    private String origin;
    private String productionDate;
    private Double price;
    private Integer amount;
    private Integer sales;
    private String imageUrl;
    private String sendAddress;
    private String parcelType;
    private String salesService;
    private Long creatorId;
    private Integer status;
    private Date createTime;
    private Date updateTime;

}
