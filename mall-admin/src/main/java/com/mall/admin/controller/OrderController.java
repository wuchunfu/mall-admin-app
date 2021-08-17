package com.mall.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mall.admin.common.CommonPage;
import com.mall.admin.common.CommonResult;
import com.mall.admin.model.param.OrderParam;
import com.mall.admin.model.vo.OrderDetailVo;
import com.mall.admin.model.vo.OrderListVo;
import com.mall.admin.service.OrderService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/delete")
    public CommonResult<Integer> deleteOrder(@RequestParam(value = "orderId") Long orderId){
        int count = orderService.delete(orderId);
        if (count > 0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @GetMapping("/update")
    public CommonResult<Integer> updateOrder(String status, Long orderId){
        int count = orderService.update(status, orderId);
        if (count > 0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @GetMapping("/list")
    public CommonResult<CommonPage<OrderListVo>> getOrderList(OrderParam orderParam,
                                                              @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                              @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize){
        IPage<OrderListVo> orderListPage = orderService.getList(orderParam, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(orderListPage));
    }

    @GetMapping("/detail")
    public CommonResult<OrderDetailVo> getOrderDetail(@Param("orderId") Long orderId){
        OrderDetailVo orderDetail = orderService.getDetail(orderId);
        return CommonResult.success(orderDetail);
    }
}
