package com.mall.admin.security;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mall.admin.mapper.UserInfoMapper;
import com.mall.admin.model.entity.UserInfo;
import com.mall.admin.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private JwtUtil jwtUtil;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager){
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader("Authorization");
        if (StrUtil.isBlankOrUndefined(token)){
            chain.doFilter(request,response);
            return;
        }

        // 验证token
        Claims claims = jwtUtil.verifyToken(token);
        String username;

        if (claims == null || jwtUtil.isTokenExpired(claims)){
            throw new JwtException("暂未登录或token已经过期");
        }else { username = claims.getSubject(); }

        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        UserInfo userInfo = userInfoMapper.selectOne(wrapper);
        // 获取用户权限等信息
        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(userInfo.getRole());

        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(username,null,auths);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request,response);
    }
}
