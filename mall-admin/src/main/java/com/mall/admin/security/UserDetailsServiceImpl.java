package com.mall.admin.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mall.admin.mapper.UserInfoMapper;
import com.mall.admin.model.entity.UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        UserInfo userInfo = userInfoMapper.selectOne(wrapper);
        if (userInfo == null){
            throw new UsernameNotFoundException("用户名不存在！");
        }
        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(userInfo.getRole());
        return new User(userInfo.getUsername(),userInfo.getPassword(),auths);
    }
}
