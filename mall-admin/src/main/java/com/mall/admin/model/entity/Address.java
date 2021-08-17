package com.mall.admin.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Address {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String userId;
    private String name;
    private String mobile;
    private String province;
    private String city;
    private String district;
    private String detailedAddress;
    private Integer isDefault;
    private Date createTime;
    private Date updateTime;
}
