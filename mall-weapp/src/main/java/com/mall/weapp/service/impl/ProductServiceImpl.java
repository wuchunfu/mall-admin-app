package com.mall.weapp.service.impl;

import com.mall.weapp.mapper.ProductMapper;
import com.mall.weapp.model.entity.Product;
import com.mall.weapp.model.vo.ProductDetailVo;
import com.mall.weapp.model.vo.ProductListVo;
import com.mall.weapp.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductMapper productMapper;

    @Override
    public List<ProductListVo> getList() {
        List<Product> list = productMapper.selectList(null);
        List<ProductListVo> productList = new ArrayList<>();
        for (Product product : list) {
            productList.add(new ProductListVo(product));
        }
        return productList;
    }

    @Override
    public ProductDetailVo getDetail(Long id) {
        return new ProductDetailVo(productMapper.selectById(id));
    }
}
