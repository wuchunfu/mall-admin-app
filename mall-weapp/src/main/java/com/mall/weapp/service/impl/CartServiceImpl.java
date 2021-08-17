package com.mall.weapp.service.impl;

import com.mall.weapp.mapper.ProductMapper;
import com.mall.weapp.model.entity.Product;
import com.mall.weapp.model.vo.CartItemVo;
import com.mall.weapp.model.vo.CartVo;
import com.mall.weapp.service.CartService;
import com.mall.weapp.util.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class CartServiceImpl implements CartService {

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private ProductMapper productMapper;

    @Override
    public long add(Long productId, String userId) {
        String key = "user:" + userId + ":cart";
        return redisUtil.sSet(key, productId);
    }

    @Override
    public long delete(Long productId, String userId) {
        String key = "user:" + userId + ":cart";
        return redisUtil.setRemove(key, productId);
    }

    @Override
    public CartVo getInfo(String userId) {
        String key = "user:" + userId + ":cart";
        Collection<Long> collection = new HashSet<>();
        for (Object item: redisUtil.sGet(key)) {
            collection.add(Long.valueOf(String.valueOf(item)));
        }
        List<CartItemVo> cartItem = new ArrayList<>();
        double totalPrice = 0;
        if (!collection.isEmpty()){
            List<Product> products = productMapper.selectBatchIds(collection);
            for (Product item : products){
                cartItem.add(new CartItemVo(item));
                totalPrice = totalPrice + item.getPrice();
            }
        }
        CartVo cart = new CartVo();
        cart.setCartItem(cartItem);
        cart.setTotalPrice(totalPrice);
        return cart;
    }
}
