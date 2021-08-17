package com.mall.admin.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Data
@Component
@ConfigurationProperties(prefix = "image.upload")
public class FileUploadUtil {

    public static String imgUrl;
    public String path;

    /**
     * 上传文件
     * @param multipartFile
     * @return 文件存储路径
     */
    public String upload(MultipartFile multipartFile) {
        // 文件存储位置，文件的目录要存在才行，可以先创建文件目录，然后进行存储
        String filePath = path + multipartFile.getOriginalFilename();
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 文件存储
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imgUrl = "http://localhost:8800/image/" + file.getName();
        return imgUrl;
    }
}
