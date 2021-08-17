package com.mall.weapp.service;

import com.mall.weapp.model.vo.AddressListVo;
import com.mall.weapp.model.vo.AddressUpdateVo;
import com.mall.weapp.model.param.AddressFormParam;

import java.util.List;

/**
 * 地址管理Service
 */
public interface AddressService {

    /**
     * 添加地址
     */
    int add(AddressFormParam addressParam);

    /**
     * 删除地址
     */
    int delete(Long id);

    /**
     * 更新地址
     */
    int update(AddressFormParam addressParam);

    /**
     * 获取地址更新信息
     */
    AddressUpdateVo getUpdateInfo(Long id);

    /**
     * 获取地址列表
     */
    List<AddressListVo> getList(String userId);
}
