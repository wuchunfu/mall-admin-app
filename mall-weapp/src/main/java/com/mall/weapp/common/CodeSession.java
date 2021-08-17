package com.mall.weapp.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 微信小程序登录凭证校验
 */
@Data
@Component
@ConfigurationProperties(prefix = "mini.app")
public class CodeSession {

    private String api;
    private String appId;
    private String secret;

    public String getUrl(String code){
        StringBuffer url = new StringBuffer();
        url.append(api);
        url.append("?appid=");
        url.append(appId);
        url.append("&secret=");
        url.append(secret);
        url.append("&js_code=");
        url.append(code);
        url.append("&grant_type=authorization_code");
        return url.toString();
    }
}
