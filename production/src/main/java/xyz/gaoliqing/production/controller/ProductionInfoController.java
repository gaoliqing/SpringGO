package xyz.gaoliqing.production.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.gaoliqing.production.exception.CustomException;
import xyz.gaoliqing.production.exception.CustomExceptionType;
import xyz.gaoliqing.production.pojo.Capsule;
import xyz.gaoliqing.production.service.ProductionInfoService;
import xyz.gaoliqing.production.utils.AjaxResponse;
import xyz.gaoliqing.production.utils.FtpUtil;

import javax.annotation.Resource;
import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * @author Mr.GaoLiqing
 * @create 2019-12-17 15:13
 * @description
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
public class ProductionInfoController {

    @Resource
    private ProductionInfoService productionInfoService;
    @Value("${server.version}")
    private String version;

    @GetMapping("/production/{name}")
    public AjaxResponse getProductionInfo(@PathVariable("name") String name) throws CloneNotSupportedException, JsonProcessingException {

        Map<String, List<Capsule>> products = productionInfoService.getProducts(name);

        return AjaxResponse.success(products);
    }

    @PostMapping("/upload")
    public String upload(MultipartFile file) throws IOException {

        String fileName = file.getOriginalFilename();
        InputStream in = file.getInputStream();
        if (file.isEmpty()) throw new CustomException(CustomExceptionType.USER_INPUT_ERROR, "上传文件不能为空");
        FtpUtil.fileUpload("152.136.206.111", 21, "glq", "gaoliqing4832","/home/glq/", fileName, in);

        return "OK";
    }

}
