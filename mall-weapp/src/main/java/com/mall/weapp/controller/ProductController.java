package com.mall.weapp.controller;

import com.mall.weapp.common.CommonResult;
import com.mall.weapp.model.vo.ProductDetailVo;
import com.mall.weapp.model.vo.ProductListVo;
import com.mall.weapp.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductService productService;

    @GetMapping("/list")
    public CommonResult<List<ProductListVo>> selectProductList(){
        List<ProductListVo> productList = productService.getList();
        return CommonResult.success(productList);
    }

    @GetMapping("/detail")
    public CommonResult<ProductDetailVo> selectProductDetail(@RequestParam("id") Long id) {
        ProductDetailVo productDetail = productService.getDetail(id);
        return CommonResult.success(productDetail);
    }
}
