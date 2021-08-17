package com.mall.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mall.admin.model.param.OrderParam;
import com.mall.admin.model.vo.OrderDetailVo;
import com.mall.admin.model.vo.OrderListVo;

/**
 * 订单管理Service接口
 */
public interface OrderService {

    /**
     * 删除订单
     */
    int delete(Long orderId);

    /**
     * 更新订单状态
     */
    int update(String status, Long orderId);

    /**
     * 分页查询订单列表
     */
    IPage<OrderListVo> getList(OrderParam orderParam, Integer pageNum, Integer pageSize);

    /**
     * 查询订单详情
     */
    OrderDetailVo getDetail(Long orderId);
}
