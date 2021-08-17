package com.mall.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.admin.mapper.BrandMapper;
import com.mall.admin.model.entity.Brand;
import com.mall.admin.model.param.BrandFormParam;
import com.mall.admin.model.vo.BrandOptionsVo;
import com.mall.admin.model.vo.BrandVo;
import com.mall.admin.service.BrandService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Resource
    private BrandMapper brandMapper;

    @Override
    public int create(BrandFormParam brandParam) {
        Brand brand = BeanUtil.copyProperties(brandParam, Brand.class);
        brand.setCreateTime(new Date());
        return brandMapper.insert(brand);
    }

    @Override
    public int delete(Long id) {
        return brandMapper.deleteById(id);
    }

    @Override
    public int update(BrandFormParam brandParam) {
        Brand brand = BeanUtil.copyProperties(brandParam, Brand.class);
        return brandMapper.updateById(brand);
    }

    @Override
    public List<BrandOptionsVo> getOptions() {
        List<Brand> brandList = brandMapper.selectList(null);
        return BeanUtil.copyToList(brandList, BrandOptionsVo.class);
    }

    @Override
    public IPage<BrandVo> getList(BrandFormParam brandParam, Integer pageNum, Integer pageSize) {

        Page<Brand> page = new Page<>(pageNum, pageSize);
        IPage<Brand> brandPage = brandMapper.selectPage(page, null);
        return brandPage.convert(brand -> BeanUtil.copyProperties(brand, BrandVo.class));
    }
}
