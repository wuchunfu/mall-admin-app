package com.mall.weapp.controller;

import com.mall.weapp.common.CommonResult;
import com.mall.weapp.model.vo.CartVo;
import com.mall.weapp.service.CartService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Resource
    private CartService cartService;

    @GetMapping("/add")
    public CommonResult<Long> addCart(Long productId, String userId){
        long count = cartService.add(productId, userId);
        if (count > 0){
            return CommonResult.success(count, "添加成功！");
        }
        return CommonResult.failed();
    }

    @GetMapping("/delete")
    public CommonResult<Long> deleteCartItem(Long productId, String userId){
        long count = cartService.delete(productId, userId);
        if (count > 0){
            return CommonResult.success(count, "删除成功！");
        }
        return CommonResult.failed();
    }

    @GetMapping("/info")
    public CommonResult<CartVo> getCartInfo(String userId){
        CartVo cartInfo = cartService.getInfo(userId);
        if (!cartInfo.getCartItem().isEmpty()) {
            return CommonResult.success(cartInfo);
        }
        return CommonResult.success(cartInfo, "购物车竟然是空的");
    }
}
