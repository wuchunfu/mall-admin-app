package com.mall.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.admin.mapper.AddressMapper;
import com.mall.admin.mapper.OrderMapper;
import com.mall.admin.mapper.ProductMapper;
import com.mall.admin.model.entity.Address;
import com.mall.admin.model.entity.Order;
import com.mall.admin.model.entity.Product;
import com.mall.admin.model.param.OrderParam;
import com.mall.admin.model.vo.OrderDetailVo;
import com.mall.admin.model.vo.OrderListVo;
import com.mall.admin.model.vo.ProductItemVo;
import com.mall.admin.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private AddressMapper addressMapper;

    @Resource
    private ProductMapper productMapper;

    @Override
    public int delete(Long orderId) {
        return orderMapper.deleteById(orderId);
    }

    @Override
    public int update(String status,Long orderId) {
        Order order = new Order();
        order.setId(orderId);
        order.setStatus(status);
        return orderMapper.updateById(order);
    }

    @Override
    public IPage<OrderListVo> getList(OrderParam orderParam, Integer pageNum, Integer pageSize) {

        Page<Order> page = new Page<>(pageNum, pageSize);
        IPage<Order> orderPage = orderMapper.selectPage(page, null);

        return orderPage.convert(order -> BeanUtil.copyProperties(order, OrderListVo.class));
    }

    @Override
    public OrderDetailVo getDetail(Long orderId) {

        OrderDetailVo orderDetailVo = new OrderDetailVo();

        // 查询订单信息
        Order order = orderMapper.selectById(orderId);
        orderDetailVo.setId(order.getId());
        orderDetailVo.setCreateTime(order.getCreateTime());
        orderDetailVo.setPaymentStatus(order.getPaymentStatus());
        orderDetailVo.setStatus(order.getStatus());

        // 查询地址信息
        Address address = addressMapper.selectById(order.getAddressId());
        BeanUtil.copyProperties(address, orderDetailVo, "id");

        // 查询商品信息
        Collection<Long> productIdList = new ArrayList<>();
        String[] IdArray = order.getProductItem().split(",");
        for (String item : IdArray) {
            productIdList.add(Long.valueOf(item));
        }
        List<Product> products = productMapper.selectBatchIds(productIdList);
        List<ProductItemVo> productItemList = BeanUtil.copyToList(products, ProductItemVo.class);
        orderDetailVo.setProductItem(productItemList);

        return orderDetailVo;
    }
}
