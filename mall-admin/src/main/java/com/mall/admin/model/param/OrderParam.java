package com.mall.admin.model.param;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderParam {

    private Double amount;
    private Integer paymentStatus;
    private Integer productCount;
    private Double total;
    private String status;
    private Long userId;
    private Long productId;
    private Long addressId;
}
