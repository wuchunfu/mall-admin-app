package com.mall.weapp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mall.weapp.mapper.OrderMapper;
import com.mall.weapp.mapper.ProductMapper;
import com.mall.weapp.model.entity.Order;
import com.mall.weapp.model.entity.Product;
import com.mall.weapp.model.vo.CartItemVo;
import com.mall.weapp.model.vo.CartVo;
import com.mall.weapp.model.vo.OrderListVo;
import com.mall.weapp.service.CartService;
import com.mall.weapp.service.OrderService;
import com.mall.weapp.util.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private CartService cartService;

    @Resource
    private RedisUtil redisUtil;

    @Override
    public int create(String userId) {

        CartVo cartInfo = cartService.getInfo(userId);
        List<CartItemVo> cartItem = cartInfo.getCartItem();

        StringBuilder productItemStr = new StringBuilder();
        for (CartItemVo item : cartItem) {
            productItemStr.append(item.getId());
            productItemStr.append(",");
        }
        Order order = new Order();
        order.setProductItem(productItemStr.toString());
        order.setTotalPrice(cartInfo.getTotalPrice());
        order.setUserId(userId);
        order.setCreateTime(new Date());
        if (orderMapper.insert(order) > 0){
            String key = "user:" + userId + ":cart";
            for (CartItemVo item : cartItem) {
                redisUtil.setRemove(key, item.getId());
            }
            return 1;
        }
        return 0;
    }

    @Override
    public List<OrderListVo> getList(String userId, String orderStatus) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        wrapper.eq("status", orderStatus);
        List<Order> orders = orderMapper.selectList(wrapper);
        List<OrderListVo> productList = new ArrayList<>();

        if (!orders.isEmpty()){
            // 拼接订单列表的所有商品项
            StringBuilder productIdStr = new StringBuilder();
            orders.forEach(order -> productIdStr.append(order.getProductItem()));
            String[] productIdArray = productIdStr.toString().split(",");
            // 过滤重复的商品项id
            Collection<Long> productIdSet = new HashSet<>();
            for (String item : productIdArray) { productIdSet.add(Long.valueOf(item));}
            // 批量查询商品列表
            List<Product> products = productMapper.selectBatchIds(productIdSet);
            products.forEach(product -> productList.add(new OrderListVo(product)));
            return productList;
        }
        return productList;
    }
}
