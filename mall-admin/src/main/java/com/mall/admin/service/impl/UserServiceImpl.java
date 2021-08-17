package com.mall.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.admin.mapper.UserInfoMapper;
import com.mall.admin.model.entity.UserInfo;
import com.mall.admin.model.param.PasswordParam;
import com.mall.admin.model.param.UserInfoParam;
import com.mall.admin.model.vo.UserInfoVo;
import com.mall.admin.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.Principal;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserInfoMapper userInfoMapper;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public int update(UserInfoParam userInfoParam) {
        UserInfo userInfo = new UserInfo(userInfoParam);
        return userInfoMapper.updateById(userInfo);
    }

    @Override
    public int updatePwd(PasswordParam passwordParam, Principal principal) {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("username", principal.getName());
        UserInfo userInfo = userInfoMapper.selectOne(wrapper);
        if (userInfo != null){
            boolean matches = passwordEncoder.matches(passwordParam.getOldPass(), userInfo.getPassword());
            if (!matches){
                return 0;
            }
            UserInfo user = new UserInfo();
            user.setId(userInfo.getId());
            user.setPassword(passwordEncoder.encode(passwordParam.getNewPass()));
            return userInfoMapper.updateById(user);
        }
        return 0;
    }

    @Override
    public int delete(Long id) {
        return userInfoMapper.deleteById(id);
    }

    @Override
    public IPage<UserInfoVo> getList(Integer pageNum, Integer pageSize) {
        // 分页查询
        Page<UserInfo> page = new Page<>(pageNum, pageSize);
        IPage<UserInfo> userInfoPage = userInfoMapper.selectPage(page, null);
        return userInfoPage.convert(UserInfo -> BeanUtil.copyProperties(UserInfo, UserInfoVo.class));
    }
}
