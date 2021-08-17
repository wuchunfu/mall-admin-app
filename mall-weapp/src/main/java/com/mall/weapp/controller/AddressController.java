package com.mall.weapp.controller;

import com.mall.weapp.common.CommonResult;
import com.mall.weapp.model.vo.AddressListVo;
import com.mall.weapp.model.vo.AddressUpdateVo;
import com.mall.weapp.model.param.AddressFormParam;
import com.mall.weapp.service.AddressService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Resource
    private AddressService addressService;

    @GetMapping("/add")
    public CommonResult<Integer> addAddress(AddressFormParam addressParam){
        int count = addressService.add(addressParam);
        if (count > 0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @GetMapping("/delete")
    public CommonResult<Integer> deleteAddress(@RequestParam(value = "id") Long id){
        int count = addressService.delete(id);
        if (count > 0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @GetMapping("/update")
    public CommonResult<Integer> updateAddress(AddressFormParam addressParam){
        int count = addressService.update(addressParam);
        if (count > 0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @GetMapping("/info")
    public CommonResult<AddressUpdateVo> getAddressUpdateInfo(@RequestParam(value = "id") Long id){
        AddressUpdateVo updateInfo = addressService.getUpdateInfo(id);
        return CommonResult.success(updateInfo);
    }

    @GetMapping("/list")
    public CommonResult<List<AddressListVo>> getAddressList(@RequestParam(value = "userId") String userId){
        List<AddressListVo> addressList = addressService.getList(userId);
        return CommonResult.success(addressList);
    }
}
