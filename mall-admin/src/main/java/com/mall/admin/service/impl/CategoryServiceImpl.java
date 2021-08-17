package com.mall.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.admin.mapper.CategoryMapper;
import com.mall.admin.model.entity.Category;
import com.mall.admin.model.param.CategoryParam;
import com.mall.admin.model.vo.CategoryOptionsVo;
import com.mall.admin.model.vo.CategoryVo;
import com.mall.admin.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public Long create(CategoryParam categoryParam) {
        Category category = BeanUtil.copyProperties(categoryParam, Category.class);
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.eq("name", category.getName());
        Category categoryObject= categoryMapper.selectOne(wrapper);
        if (categoryObject != null){
            return categoryObject.getId();
        }
        category.setCreateTime(new Date());
        categoryMapper.insert(category);
        return category.getId();
    }

    @Override
    public int delete(Long id) {

        // 待删除的类目列表
        List<Long> idList = new ArrayList<>();
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", id);
        categoryMapper.selectList(wrapper).forEach(category -> idList.add(category.getId()));
        categoryMapper.delete(wrapper);
        idList.forEach(cid -> {
            QueryWrapper<Category> cIdWrapper = new QueryWrapper<>();
            System.out.println(cid);
            cIdWrapper.eq("parent_id", cid);
            categoryMapper.delete(cIdWrapper);
        });
        return categoryMapper.deleteById(id);
    }

    @Override
    public int update(CategoryParam categoryParam) {
        Category category = BeanUtil.copyProperties(categoryParam, Category.class);
        return categoryMapper.updateById(category);
    }

    @Override
    public List<CategoryOptionsVo> getOptions() {
        List<Category> categoryList = categoryMapper.selectList(null);
        return generateTreeOptions(0L,categoryList);
    }

    // 将选项数据封装成树形结构
    public List<CategoryOptionsVo> generateTreeOptions(Long id, List<Category> list){
        List<CategoryOptionsVo> OptionsList = new ArrayList<>();
        // 递归过滤一级、二级、三级类目
        List<Category> oneLevel = list.stream().filter(cate -> cate.getParentId().equals(id))
                .collect(Collectors.toList());
        oneLevel.forEach(one -> {
            CategoryOptionsVo options = new CategoryOptionsVo();
            options.setLabel(one.getName());
            options.setValue(one.getId());
            if (one.getLevel() == 1 || one.getLevel() == 2 | one.getLevel() == 3 ){
                options.setChildren(generateTreeOptions(one.getId(), list));
                if (one.getLevel() == 3){
                    options.setChildren(null);
                }
            }
            OptionsList.add(options);
        });
        return OptionsList;
    }

    @Override
    public IPage<CategoryVo> getList(CategoryParam categoryParam, Integer pageNum, Integer pageSize) {

        Category category = BeanUtil.copyProperties(categoryParam, Category.class);
        // 分页查询
        Page<Category> page = new Page<>(pageNum, pageSize);

        // 按条件进行查询
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        if (!StrUtil.isBlankOrUndefined(category.getName())){
            wrapper.eq("name", category.getName());
        }
        if (category.getLevel() != null){
            wrapper.eq("level", category.getLevel());
        }
        if (category.getParentId() != null){
            wrapper.eq("level", category.getParentId());
        }
        // 默认查询一级类目
        wrapper.eq("level", 1);
        IPage<Category> categoryPage = categoryMapper.selectPage(page, wrapper);
        return categoryPage.convert(cate -> BeanUtil.copyProperties(cate, CategoryVo.class));
    }
}
