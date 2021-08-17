package com.mall.admin.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class ProductListVo {

    private Long id;
    private String title;
    private Double price;
    private String imageUrl;
    private Integer amount;
    private Integer sales;
    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
