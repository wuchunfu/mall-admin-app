package com.mall.admin.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.mall.admin.model.param.UserInfoParam;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class UserInfo {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String username;
    private String password;
    private String avatar;
    private String role;
    private Integer status;
    private Date createTime;
    private Date updateTime;

    public UserInfo() { }
    public UserInfo(UserInfoParam userParam) {
        this.id = userParam.getId();
        this.role = userParam.getRole();
        this.status = userParam.getStatus();
    }
}
