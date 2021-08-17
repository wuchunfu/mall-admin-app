package com.mall.weapp.service;

import com.mall.weapp.model.vo.ProductDetailVo;
import com.mall.weapp.model.vo.ProductListVo;

import java.util.List;

/**
 * 微信小程序商品信息Service
 */
public interface ProductService {

    /**
     * 获取商品列表
     */
    List<ProductListVo> getList();

    /**
     * 获取商品详情
     */
    ProductDetailVo getDetail(Long id);

}
