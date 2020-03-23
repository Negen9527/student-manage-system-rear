package com.negen.controller;

import com.negen.common.ResponseEnum;
import com.negen.common.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ Author     ：Negen
 * @ Date       ：Created in 16:05 2020/3/21
 * @ Description：文件上传
 * @ Modified By：
 * @Version: 1.0
 */
@Slf4j
@RestController
@RequestMapping(name = "avatar")
public class FileController {
    @RequestMapping(name = "upload", method = RequestMethod.POST)
    ServerResponse uploadAvatar(@RequestParam("file")MultipartFile file){
        try{
            if(file.isEmpty()){
                return ServerResponse.getInstance().code(20000).message("file is empty");
            }else{
                String fileName = file.getOriginalFilename();
                log.info("fileName=========>" + fileName);
               return ServerResponse.getInstance().responseEnum(ResponseEnum.UPDATE_SUCCESS);
            }
        }catch (Exception e){
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }

    }
}
