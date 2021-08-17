package com.mall.weapp.controller;

import com.mall.weapp.common.CommonResult;
import com.mall.weapp.model.vo.CollectItemVo;
import com.mall.weapp.service.CollectionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/collection")
public class CollectionController {

    @Resource
    private CollectionService collectionService;

    @GetMapping("/collect")
    public CommonResult<Long> collectProduct(Long productId, String userId) {
        long count = collectionService.collect(productId, userId);
        if (count > 0){
            return CommonResult.success(count, "收藏成功!");
        }
        return CommonResult.failed();
    }

    @GetMapping("/delete")
    public CommonResult<Integer> deleteCollectList(String userId) {
        int count = collectionService.deleteList(userId);
        if (count > 0){
            return CommonResult.success(count, "删除全部!");
        }
        return CommonResult.failed();
    }

    @GetMapping("/list")
    public CommonResult<List<CollectItemVo>> getCollectionList(String userId) {
        List<CollectItemVo> collectList = collectionService.getList(userId);
        if(!collectList.isEmpty()){
            return CommonResult.success(collectList);
        }
        return CommonResult.success(collectList, "暂无收藏");
    }
}
