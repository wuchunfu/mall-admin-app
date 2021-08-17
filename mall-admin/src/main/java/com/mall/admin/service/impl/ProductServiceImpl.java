package com.mall.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.admin.mapper.ProductMapper;
import com.mall.admin.model.entity.Product;
import com.mall.admin.model.param.ProductFormParam;
import com.mall.admin.model.param.ProductQueryParam;
import com.mall.admin.model.vo.ProductListVo;
import com.mall.admin.model.vo.ProductUpdateVo;
import com.mall.admin.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductMapper productMapper;

    @Override
    public int create(ProductFormParam productParam) {
        Product product = BeanUtil.copyProperties(productParam, Product.class);
        product.setCreateTime(new Date());
        return productMapper.insert(product);
    }

    @Override
    public int delete(Long id) {
        return productMapper.deleteById(id);
    }

    @Override
    public int update(ProductFormParam productParam) {
        Product product = BeanUtil.copyProperties(productParam, Product.class);
        product.setUpdateTime(new Date());
        return productMapper.updateById(product);
    }

    @Override
    public IPage<ProductListVo> getList(ProductQueryParam productParam, Integer pageNum, Integer pageSize) {
        Page<Product> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Product> wrapper = new QueryWrapper<>();

        // 按条件查询
        if (!StrUtil.isBlankOrUndefined(productParam.getTitle())){
            wrapper.like("title", productParam.getTitle());
        }
        if (productParam.getId() != null){
            wrapper.eq("id", productParam.getId());
        }
        if (productParam.getCategoryId() != null){
            wrapper.eq("category_id", productParam.getCategoryId());
        }
        if (productParam.getBrandId() != null){
            wrapper.eq("brand_id", productParam.getBrandId());
        }
        if (productParam.getStatus() != null){
            wrapper.eq("status", productParam.getStatus());
        }
        if (productParam.getCreatorId() != null){
            wrapper.eq("creator_id", productParam.getCreatorId());
        }
        IPage<Product> productPage = productMapper.selectPage(page, wrapper);
        return productPage.convert(product -> BeanUtil.copyProperties(product, ProductListVo.class));
    }

    @Override
    public ProductUpdateVo getUpdateInfo(Long id) {
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        Product product = productMapper.selectOne(wrapper);
        return BeanUtil.copyProperties(product, ProductUpdateVo.class);
    }
}
