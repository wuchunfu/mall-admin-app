package com.mall.admin.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class OrderDetailVo {

    // 订单信息
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private Integer paymentStatus;
    private String status;

    // 地址信息
    private String name;
    private String mobile;
    private String province;
    private String city;
    private String district;
    private String detailedAddress;

    // 商品列表信息
    List<ProductItemVo> productItem;
}
