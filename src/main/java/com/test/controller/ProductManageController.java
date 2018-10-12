package com.test.controller;

import com.google.common.collect.Maps;
import com.test.common.ServerResponse;
import com.test.service.IFileService;
import com.test.util.PropertiesUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping("/product/")
public class ProductManageController {

    @Autowired
    private IFileService iFileService;

    @RequestMapping("upload.do")
    @ResponseBody
    public ServerResponse upload(MultipartFile file, HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("upload");
        String targetFileName = iFileService.upload(file, path);
        String url=PropertiesUtil.getProperty("ftp.server.http.prefix")+targetFileName;

        Map fileMap=Maps.newHashMap();
        fileMap.put("uri",targetFileName);
        fileMap.put("url",url);
        return ServerResponse.createBySuccess(fileMap);
    }

    @RequestMapping("image_upload.do")
    @ResponseBody
    public Map image_upload(MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        Map resultMap=Maps.newHashMap();

        //富文本中对于返回值有自己的要求，我们使用的是simditor的要求进行返回

        String path = request.getSession().getServletContext().getRealPath("upload");
        String targetFileName = iFileService.upload(file, path);
        if(StringUtils.isBlank(targetFileName)){
            resultMap.put("success",false);
            resultMap.put("msg","上传失败");
        }



        String url=PropertiesUtil.getProperty("ftp.server.http.prefix")+targetFileName;

        resultMap.put("success",true);
        resultMap.put("msg","上传成功");
        resultMap.put("file",file);

        response.addHeader("Access-Control-Headers","X-File-Name");
        return resultMap;
    }
}
