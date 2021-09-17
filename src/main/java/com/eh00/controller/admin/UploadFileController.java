package com.eh00.controller.admin;

import com.eh00.dto.JsonResult;
import com.eh00.dto.UploadFileVO;
import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Calendar;

@Log4j
@RestController
@RequestMapping("/admin/upload")
public class UploadFileController {
    // 文件保存路径 物理路径
    private final String rootPath = "/Users/wudi/code/ForestBlog-master/uploads";
    // 允许文件后缀名
    private final String allowSuffix = ".bmp.jpg.jpeg.png.gif.pdf.doc.zip.rar.gz";

    @RequestMapping(value = "/img", method = RequestMethod.POST)
    public JsonResult uploadFile(@RequestParam("file") MultipartFile file) {
        // 1.文件后缀过滤
        String fileName = file.getOriginalFilename();
        String name = fileName.substring(0, fileName.indexOf("."));
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        if (allowSuffix.indexOf(suffix) == -1) {
            return new JsonResult().fail("不允许上传该后缀的文件！");
        }

        // 2.创建文件
        // 创建年月日目录
        Calendar calendar = Calendar.getInstance();
        File dateDir = new File(calendar.get(Calendar.YEAR) + File.separator
                + calendar.get(Calendar.MONTH)+1);
        // 目标文件
        File descFile = new File(rootPath + File.separator
                + dateDir + File.separator
                + fileName);
        //若文件存在，重命名
        int i = 1;
        String newFileName = fileName;
        while (descFile.exists()) {
            newFileName  = name + "(" + i + ")" + suffix;
            String parentPath = descFile.getParent();
            descFile = new File(parentPath + File.separator + newFileName);
            i++;
        }
        //判断目标文件所在的目录是否存在
        if (!descFile.getParentFile().exists()) {
            //创建父目录
            descFile.getParentFile().mkdirs();
        }

        // 3.存储文件
        // 将内存的数据写入磁盘
        try {
            file.transferTo(descFile);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("上传失败, cause:{}", e);
        }
        // 完整的url
        String fileUrl = "/uploads/" + dateDir + "/" + newFileName;

        // 4.返回url
        UploadFileVO uploadFileVO = new UploadFileVO();
        uploadFileVO.setTitle(fileName);
        uploadFileVO.setSrc(fileUrl);
        return new JsonResult().ok(uploadFileVO);

    }
}
