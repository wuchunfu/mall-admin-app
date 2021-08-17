package com.mall.weapp.config;

import com.mall.weapp.security.JwtAuthenticationEntryPoint;
import com.mall.weapp.security.JwtAuthenticationFilter;
import com.mall.weapp.security.LoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private LoginSuccessHandler loginSuccessHandler;

    @Resource
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter
                = new JwtAuthenticationFilter(authenticationManager());
        return jwtAuthenticationFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()

                // 登录规则配置
                .formLogin()
                .successHandler(loginSuccessHandler)

                // 禁用Session
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .antMatchers("/login","/image/**").permitAll()
                .anyRequest().authenticated()

                // 配置异常处理器
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)

                // 配置自定义过滤器
                .and()
                .addFilter(jwtAuthenticationFilter());
    }
}
