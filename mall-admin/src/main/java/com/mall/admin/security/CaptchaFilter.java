package com.mall.admin.security;

import com.mall.admin.util.RedisUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CaptchaFilter extends OncePerRequestFilter {

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private CaptchaFailureHandler captchaFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        String url = httpServletRequest.getRequestURI();

        if ("/login".equals(url) && httpServletRequest.getMethod().equals("POST")) {

            try{
                // 校验验证码
                validate(httpServletRequest);
            } catch (CaptchaException e) {

                // 交给验证码认证失败处理器
                captchaFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    // 校验验证码逻辑
    private void validate(HttpServletRequest httpServletRequest) {

        String code = httpServletRequest.getParameter("code");
        String key = httpServletRequest.getParameter("key");

        if (code.length() == 0 || key.length() == 0) {
            throw new CaptchaException("验证码错误");
        }

        if (!code.equals(redisUtil.hget("captcha", key))) {
            throw new CaptchaException("验证码错误");
        }

        // 一次性使用
        redisUtil.hdel("captcha", key);
    }
}
