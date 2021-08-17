package com.mall.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mall.admin.common.CommonPage;
import com.mall.admin.common.CommonResult;
import com.mall.admin.model.param.ProductFormParam;
import com.mall.admin.model.param.ProductQueryParam;
import com.mall.admin.model.vo.ProductListVo;
import com.mall.admin.model.vo.ProductUpdateVo;
import com.mall.admin.service.ProductService;
import com.mall.admin.util.FileUploadUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Resource
    private ProductService productService;

    @Resource
    private FileUploadUtil fileUploadUtil;

    /**
     * 创建商品
     */
    @GetMapping("/create")
    public CommonResult<Integer> createProduct(ProductFormParam productParam){
        int count = productService.create(productParam);
        if (count > 0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    /**
     * 删除商品
     */
    @GetMapping("/delete")
    public CommonResult<Integer> deleteProduct(@RequestParam(value = "id") Long id){
        int count = productService.delete(id);
        if (count > 0){
            return CommonResult.success(count, "删除成功");
        }
        return CommonResult.failed();
    }

    /**
     * 更新商品
     */
    @GetMapping("/update")
    public CommonResult<Integer> updateProduct(ProductFormParam productParam) {
        int count = productService.update(productParam);
        if (count > 0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    /**
     * 上传商品图片
     */
    @PostMapping("/upload")
    public CommonResult<String> upload(@RequestParam("image") MultipartFile multipartFile) {

        // replaceAll 用来替换windows中的\\ 为 /
        String path = fileUploadUtil.upload(multipartFile).replaceAll("\\\\", "/");
        return CommonResult.success(path);
    }

    /**
     * 查询商品
     */
    @GetMapping("/list")
    public CommonResult<CommonPage<ProductListVo>> selectProductList(ProductQueryParam productParam,
                                                                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                             @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize){
        IPage<ProductListVo> page = productService.getList(productParam, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(page));
    }

    /**
     * 获取商品更新信息
     */
    @GetMapping("/info")
    public CommonResult<ProductUpdateVo> updateState(@RequestParam(value = "id") Long id) {
        ProductUpdateVo updateInfo = productService.getUpdateInfo(id);
        return CommonResult.success(updateInfo);
    }
}
