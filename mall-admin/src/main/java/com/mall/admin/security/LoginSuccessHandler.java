package com.mall.admin.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.admin.common.CommonResult;
import com.mall.admin.mapper.UserInfoMapper;
import com.mall.admin.model.entity.UserInfo;
import com.mall.admin.util.JwtUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();

        // 生成jwt返回
        String token = jwtUtil.generateToken(authentication.getName());
        response.setHeader("Authorization", token);

        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("username", authentication.getName());
        UserInfo userInfo = userInfoMapper.selectOne(wrapper);

        CommonResult<Long> result = CommonResult.success(userInfo.getId(),"登录成功！");

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResult = objectMapper.writeValueAsString(result);
        outputStream.write(jsonResult.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
