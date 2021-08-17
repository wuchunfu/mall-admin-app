package com.mall.admin.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Category {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;
    private Long parentId;
    private Integer level;
    private Integer sort;
    private Date createTime;
    private Date updateTime;
}
