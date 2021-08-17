package com.mall.weapp.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.mall.weapp.model.param.AddressFormParam;
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

    public Address() {}
    public Address(AddressFormParam addressParam) {
        this.id = addressParam.getId();
        this.userId = addressParam.getUserId();
        this.name = addressParam.getName();
        this.mobile = addressParam.getMobile();
        this.province = addressParam.getProvince();
        this.city = addressParam.getCity();
        this.district = addressParam.getDistrict();
        this.detailedAddress = addressParam.getDetailedAddress();
        this.isDefault = addressParam.getIsDefault();
    }
}
