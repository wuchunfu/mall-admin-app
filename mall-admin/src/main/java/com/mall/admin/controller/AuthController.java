package com.mall.admin.controller;

import com.google.code.kaptcha.Producer;
import com.mall.admin.common.CommonResult;
import com.mall.admin.util.RedisUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class AuthController {

    @Resource
    private Producer producer;
    @Resource
    private RedisUtil redisUtil;

    @GetMapping("/captcha")
    public CommonResult<Map<String, String>> captcha() throws IOException {
        String key = UUID.randomUUID().toString();
        String code = producer.createText();

        BufferedImage image = producer.createImage(code);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);
        BASE64Encoder encoder = new BASE64Encoder();
        String str = "data:image/jpeg;base64,";

        String base64Img = str + encoder.encode(outputStream.toByteArray());

        redisUtil.hset("captcha", key, code, 120);

        Map<String, String> map = new HashMap<>();
        map.put("key", key);
        map.put("captchaImg", base64Img);

        return CommonResult.success(map);
    }
}
