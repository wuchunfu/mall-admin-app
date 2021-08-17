package com.mall.weapp.service;

import com.mall.weapp.model.vo.CollectItemVo;

import java.util.List;

/**
 * 商品收藏Service
 */
public interface CollectionService {

    /**
     * 收藏商品
     */
    long collect(Long productId, String userId);

    /**
     * 获取收藏列表
     */
    List<CollectItemVo> getList(String userId);

    /**
     * 删除收藏列表
     */
    int deleteList(String userId);

}
