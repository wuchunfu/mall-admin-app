package com.mall.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mall.admin.common.CommonPage;
import com.mall.admin.common.CommonResult;
import com.mall.admin.model.param.BrandFormParam;
import com.mall.admin.model.vo.BrandOptionsVo;
import com.mall.admin.model.vo.BrandVo;
import com.mall.admin.service.BrandService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/brand")
public class BrandController {

    @Resource
    private BrandService brandService;

    /**
     * 新建商品品牌
     */
    @GetMapping("/create")
    public CommonResult<Integer> createBrand(BrandFormParam brandParam){
        int count = brandService.create(brandParam);
        if (count > 0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    /**
     * 删除商品品牌
     */
    @GetMapping("/delete")
    public CommonResult<Integer> deleteBrand(@RequestParam(value = "id") Long id){
        int count = brandService.delete(id);
        if (count > 0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    /**
     * 修改商品品牌
     */
    @GetMapping("/update")
    public CommonResult<Integer> updateBrand(BrandFormParam brandParam){
        int count = brandService.update(brandParam);
        if (count > 0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    /**
     * 查询品牌选项列表
     */
    @GetMapping("/options")
    public CommonResult<List<BrandOptionsVo>> getBrandOptionsList(){
        List<BrandOptionsVo> optionsList = brandService.getOptions();
        return CommonResult.success(optionsList);
    }

    /**
     * 分页查询商品品牌列表
     */
    @GetMapping("/list")
    public CommonResult<CommonPage<BrandVo>> getBrandList(BrandFormParam brandParam,
                                                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize){
        IPage<BrandVo> brandList = brandService.getList(brandParam, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(brandList));
    }
}
