package com.mall.weapp.controller;

import com.mall.weapp.common.CommonResult;
import com.mall.weapp.model.vo.OrderListVo;
import com.mall.weapp.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/create")
    public CommonResult<Integer> createOrder(String userId){
        int count = orderService.create(userId);
        if (count > 0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @GetMapping("/list")
    public CommonResult<List<OrderListVo>> getOrderList(String userId, String orderStatus){
        List<OrderListVo> productList = orderService.getList(userId, orderStatus);
        if (!productList.isEmpty()){
            return CommonResult.success(productList);
        }
        return CommonResult.success(productList, "暂无订单");
    }
}
