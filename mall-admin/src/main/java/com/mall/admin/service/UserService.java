package com.mall.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mall.admin.model.param.PasswordParam;
import com.mall.admin.model.param.UserInfoParam;
import com.mall.admin.model.vo.UserInfoVo;

import java.security.Principal;

/**
 * 用户管理Service
 */
public interface UserService {

    /**
     * 更新用户
     */
    int update(UserInfoParam userInfoParam);

    /**
     * 更新用户
     */
    int updatePwd(PasswordParam passwordParam, Principal principal);

    /**
     * 删除用户
     */
    int delete(Long id);

    /**
     * 分页查询用户列表
     */
    IPage<UserInfoVo> getList(Integer pageNum, Integer pageSize);
}
