package com.mall.admin.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 分页数据封装类
 */
@Setter
@Getter
public class CommonPage<T> {
    /**
     * 当前页码
     */
    private Long pageNum;
    /**
     * 每页数量
     */
    private Long pageSize;
    /**
     * 总页数
     */
    private Long totalPage;
    /**
     * 总条数
     */
    private Long total;
    /**
     * 分页数据
     */
    private List<T> list;

    /**
     * 将MybatisPlus中IPage分页后的分页信息转为自定义的分页信息
     */
    public static <T> CommonPage<T> restPage(IPage<T> iPage) {
        CommonPage<T> result = new CommonPage<T>();
        result.setTotalPage(iPage.getPages());
        result.setPageNum(iPage.getCurrent());
        result.setPageSize(iPage.getSize());
        result.setTotal(iPage.getTotal());
        result.setList(iPage.getRecords());
        return result;
    }
}
