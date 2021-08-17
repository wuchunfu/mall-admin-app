package com.mall.weapp.service.impl;

import com.mall.weapp.mapper.ProductMapper;
import com.mall.weapp.model.entity.Product;
import com.mall.weapp.model.vo.CollectItemVo;
import com.mall.weapp.service.CollectionService;
import com.mall.weapp.util.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class CollectionServiceImpl implements CollectionService {

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private ProductMapper productMapper;

    @Override
    public long collect(Long productId, String userId) {
        String key = "user:" + userId + ":collect";
        return redisUtil.sSet(key, productId);
    }

    @Override
    public List<CollectItemVo> getList(String userId) {
        String key = "user:" + userId + ":collect";
        Set<Object> productIdSet = redisUtil.sGet(key);
        Collection<Long> collection = new HashSet<>();
        for (Object item : productIdSet) {
            collection.add(Long.valueOf(String.valueOf(item)));
        }
        List<CollectItemVo> collectItemList = new ArrayList<>();
        if (!collection.isEmpty()){
            List<Product> list = productMapper.selectBatchIds(collection);
            for (Product product : list) {
                collectItemList.add(new CollectItemVo(product));
            }
        }
        return collectItemList;
    }

    @Override
    public int deleteList(String userId) {
        String key = "user:" + userId + ":collect";
        Set<Object> productIdSet = redisUtil.sGet(key);
        for (Object item : productIdSet) {
            redisUtil.setRemove(key, item);
        }
        return 1;
    }
}
