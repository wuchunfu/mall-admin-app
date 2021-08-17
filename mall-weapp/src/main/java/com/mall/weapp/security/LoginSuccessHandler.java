package com.mall.weapp.security;

import cn.hutool.json.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.weapp.common.CodeSession;
import com.mall.weapp.common.CommonResult;
import com.mall.weapp.util.JwtUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
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
    private CodeSession codeSession;

    @Resource
    private JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String code = request.getParameter("code");
        HttpGet httpGet = new HttpGet(codeSession.getUrl(code));
        CloseableHttpResponse httpResponse = HttpClientBuilder.create().build().execute(httpGet);
        String openIdAndSessionKey = EntityUtils.toString(httpResponse.getEntity());
        String openId = new JSONObject(openIdAndSessionKey).get("openid").toString();

        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();

        // 生成token返回
        String token = jwtUtil.generateToken(openId);
        response.setHeader("Authorization", token);

        CommonResult<String> result = CommonResult.success(openId,"登录成功！");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResult = objectMapper.writeValueAsString(result);
        outputStream.write(jsonResult.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
