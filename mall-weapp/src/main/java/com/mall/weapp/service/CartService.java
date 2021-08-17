package com.mall.weapp.service;

import com.mall.weapp.model.vo.CartVo;

/**
 * 购物车Service
 */
public interface CartService {

    /**
     * 添加购物车
     */
    long add(Long productId, String userId);

    /**
     * 删除购物项
     */
    long delete(Long productId, String userId);

    /**
     * 获取购物车信息
     */
    CartVo getInfo(String userId);
}
