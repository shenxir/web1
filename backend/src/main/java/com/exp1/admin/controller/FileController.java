package com.exp1.admin.controller;

import com.exp1.admin.model.ApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/file")
public class FileController {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @PostMapping("/upload")
    public ApiResponse upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ApiResponse.fail("请选择文件");
        }

        // 获取原始文件名和后缀
        String originalFilename = file.getOriginalFilename();
        String suffix = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        }

        // 用 UUID 生成唯一文件名，防止重名覆盖
        String newFileName = UUID.randomUUID().toString().replace("-", "") + suffix;

        File dest = new File(uploadDir + File.separator + newFileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }

        try {
            file.transferTo(dest);
            // 返回可访问的相对路径
            return ApiResponse.ok("上传成功", "/pic/" + newFileName);
        } catch (IOException e) {
            return ApiResponse.fail("上传失败：" + e.getMessage());
        }
    }
}
