package com.mall.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mall.admin.model.param.CategoryParam;
import com.mall.admin.model.vo.CategoryOptionsVo;
import com.mall.admin.model.vo.CategoryVo;

import java.util.List;

/**
 * 商品类目Service
 */
public interface CategoryService {

    /**
     * 创建类目
     */
    Long create(CategoryParam categoryParam);

    /**
     * 删除类目
     */
    int delete(Long id);

    /**
     * 更新类目
     */
    int update(CategoryParam categoryParam);

    /**
     * 查询类目选项列表
     */
    List<CategoryOptionsVo> getOptions();

    /**
     * 按条件分页查询类目列表
     */
    IPage<CategoryVo> getList(CategoryParam categoryParam, Integer pageNum, Integer pageSize);
}
