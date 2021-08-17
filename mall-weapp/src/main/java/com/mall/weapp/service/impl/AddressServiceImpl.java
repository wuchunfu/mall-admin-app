package com.mall.weapp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mall.weapp.mapper.AddressMapper;
import com.mall.weapp.model.entity.Address;
import com.mall.weapp.model.vo.AddressListVo;
import com.mall.weapp.model.vo.AddressUpdateVo;
import com.mall.weapp.model.param.AddressFormParam;
import com.mall.weapp.service.AddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Resource
    private AddressMapper addressMapper;

    @Override
    public int add(AddressFormParam addressParam) {
        Address address = new Address(addressParam);
        address.setCreateTime(new Date());
        return addressMapper.insert(address);
    }

    @Override
    public int delete(Long id) {
        return addressMapper.deleteById(id);
    }

    @Override
    public int update(AddressFormParam addressParam) {
        Address address = new Address(addressParam);
        address.setUpdateTime(new Date());
        QueryWrapper<Address> wrapper = new QueryWrapper<>();
        wrapper.eq("id", address.getId());
        return addressMapper.update(address, wrapper);
    }

    @Override
    public AddressUpdateVo getUpdateInfo(Long id) {
        Address address = addressMapper.selectById(id);
        return new AddressUpdateVo(address);
    }

    @Override
    public List<AddressListVo> getList(String userId) {
        QueryWrapper<Address> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        List<Address> list = addressMapper.selectList(wrapper);
        List<AddressListVo> addressList = new ArrayList<>();
        for (Address address : list) {
            addressList.add(new AddressListVo(address));
        }
        return addressList;
    }
}
