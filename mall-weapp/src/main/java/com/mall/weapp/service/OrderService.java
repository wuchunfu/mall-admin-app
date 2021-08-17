package com.mall.weapp.service;

import com.mall.weapp.model.vo.OrderListVo;

import java.util.List;

/**
 * 订单Service
 */
public interface OrderService {

    /**
     * 创建订单
     */
    int create(String userId);

    /**
     * 查询订单列表
     */
    List<OrderListVo> getList(String userId, String orderStatus);
}
