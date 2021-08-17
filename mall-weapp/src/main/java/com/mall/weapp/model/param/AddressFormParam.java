package com.mall.weapp.model.param;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressFormParam {

    private Long id;
    private String userId;
    private String name;
    private String mobile;
    private String province;
    private String city;
    private String district;
    private String detailedAddress;
    private Integer isDefault;
}
