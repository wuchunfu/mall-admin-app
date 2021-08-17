package com.mall.weapp.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@TableName("`order`")
public class Order {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Integer paymentStatus;
    private String productItem;
    private Double totalPrice;
    private String status;
    private Long addressId;
    private String userId;
    private Long adminId;
    private Date createTime;
    private Date updateTime;
}
