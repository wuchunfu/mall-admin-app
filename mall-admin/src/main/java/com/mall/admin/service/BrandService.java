package com.mall.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mall.admin.model.param.BrandFormParam;
import com.mall.admin.model.vo.BrandOptionsVo;
import com.mall.admin.model.vo.BrandVo;

import java.util.List;

/**
 * 商品品牌Service
 */
public interface BrandService {

    /**
     * 新建品牌
     */
    int create(BrandFormParam brandParam);

    /**
     * 删除品牌
     */
    int delete(Long id);

    /**
     * 修改品牌
     */
    int update(BrandFormParam brandParam);

    /**
     * 查询品牌选项列表
     */
    List<BrandOptionsVo> getOptions();

    /**
     * 分页获取品牌列表
     */
    IPage<BrandVo> getList(BrandFormParam brandParam, Integer pageNum, Integer pageSize);
}
