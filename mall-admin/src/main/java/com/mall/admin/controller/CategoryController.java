package com.mall.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mall.admin.common.CommonPage;
import com.mall.admin.common.CommonResult;
import com.mall.admin.model.param.CategoryParam;
import com.mall.admin.model.vo.CategoryOptionsVo;
import com.mall.admin.model.vo.CategoryVo;
import com.mall.admin.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping("/create")
    public CommonResult<Long> createCategory(CategoryParam categoryParam){
        Long id = categoryService.create(categoryParam);
        if (id > 0){
            return CommonResult.success(id);
        }
        return CommonResult.failed();
    }

    @GetMapping("/delete")
    public CommonResult<Integer> deleteCategory(@RequestParam(value = "id") Long id){
        int count = categoryService.delete(id);
        if (count > 0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @GetMapping("/update")
    public CommonResult<Integer> updateCategory(CategoryParam categoryParam){
        int count = categoryService.update(categoryParam);
        if (count > 0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @GetMapping("/options")
    public CommonResult<List<CategoryOptionsVo>> getCategoryOptionsList() {
        List<CategoryOptionsVo> optionsList = categoryService.getOptions();
        return CommonResult.success(optionsList);
    }

    @GetMapping("/list")
    public CommonResult<CommonPage<CategoryVo>> getCategoryList(CategoryParam categoryParam,
                                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize){
        IPage<CategoryVo> categoryVoPage = categoryService.getList(categoryParam, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(categoryVoPage));
    }
}
