package com.mall.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mall.admin.model.param.ProductFormParam;
import com.mall.admin.model.param.ProductQueryParam;
import com.mall.admin.model.vo.ProductListVo;
import com.mall.admin.model.vo.ProductUpdateVo;
import org.apache.ibatis.annotations.Param;

/**
 * 商品管理Service
 */
public interface ProductService {

    /**
     * 创建商品
     */
    int create(ProductFormParam productParam);

    /**
     * 删除商品
     */
    int delete(Long id);

    /**
     * 更新商品
     */
    int update(ProductFormParam productParam);

    /**
     * 分页查询商品列表
     */
    IPage<ProductListVo> getList(ProductQueryParam productParam, Integer pageNum, Integer pageSize);

    /**
     * 通过id获取更新信息
     */
    ProductUpdateVo getUpdateInfo(@Param("id") Long id);
}
