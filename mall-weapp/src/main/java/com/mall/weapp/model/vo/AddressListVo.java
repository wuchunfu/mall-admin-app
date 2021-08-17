package com.mall.weapp.model.vo;

import com.mall.weapp.model.entity.Address;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressListVo {

    private Long id;
    private String name;
    private String mobile;
    private String province;
    private String city;
    private String district;
    private String detailedAddress;
    private Integer isDefault;

    public AddressListVo() {}
    public AddressListVo(Address address){
        this.id = address.getId();
        this.name = address.getName();
        this.mobile = address.getMobile();
        this.province = address.getProvince();
        this.city = address.getCity();
        this.district = address.getDistrict();
        this.detailedAddress = address.getDetailedAddress();
        this.isDefault = address.getIsDefault();
    }
}
