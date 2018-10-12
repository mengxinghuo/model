package com.test.service.impl;

import com.google.common.collect.Lists;
import com.test.service.IFileService;
import com.test.util.FTPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service("iFileService")
public class FileServiceImpl implements IFileService {

    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    public String upload(MultipartFile file, String path) {
        //原始文件名
        String fileName = file.getOriginalFilename();
        //扩展名
        //abc.jpg
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        String uploadFileName = UUID.randomUUID().toString() + "." + fileExtensionName;
        logger.info("开始上传文件，上传文件的文件名:{},上传的路径:{},新文件名:{}", fileName, path, uploadFileName);

        //目录文件
        File fileDir = new File(path);
        if (!fileDir.exists()) {
            //赋予可写的权限
            fileDir.setWritable(true);
            fileDir.mkdirs();

        }
        File targetFile = new File(path, uploadFileName);
        try {
            file.transferTo(targetFile);
            //文件上传成功
            //todo 将targetFile上传到我们的FTP服务器上
            FTPUtil.uploadFile(Lists.newArrayList(targetFile));
            //todo 上传完之后删除upload下面的文件
            targetFile.delete();



        } catch (IOException e) {
            logger.error("上传文件异常", e);
            return null;

        }

        return targetFile.getName();
    }
}
