package xyz.gaoliqing.production.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.gaoliqing.production.exception.CustomException;
import xyz.gaoliqing.production.exception.CustomExceptionType;
import xyz.gaoliqing.production.pojo.AddForm;
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

    /**
     * 模拟Feign调用及链路追踪
     *
     * @param name 查询参数
     * @return  ...
     * @throws JsonProcessingException 字符串转json对象的异常
     */
    @GetMapping("/production/{name}")
    public AjaxResponse getProductionInfo(@PathVariable("name") String name) throws JsonProcessingException {

        Map<String, List<Capsule>> products = productionInfoService.getProducts(name);

        return AjaxResponse.success(products);
    }

    /**
     *
     * @param file 上传封面图片
     * @return 返回图片的存储地址
     * @throws IOException IO异常
     */
    @PostMapping("/upload")
    public AjaxResponse upload(MultipartFile file) throws IOException {

        String fileName = file.getOriginalFilename();
        InputStream in = file.getInputStream();
        if (file.isEmpty()) throw new CustomException(CustomExceptionType.USER_INPUT_ERROR, "上传文件不能为空");
        String fileUploadPath = FtpUtil.fileUpload("152.136.206.111", 21, "glq", "gaoliqing4832", "/home/glq/", fileName, in);

        return AjaxResponse.success(fileUploadPath);
    }

    /**
     *
     * @param addForm 上传表单数据
     * @return ...
     */
    @PostMapping("/addform")
    public AjaxResponse addForm(@RequestBody AddForm addForm) {

        productionInfoService.insertForm(addForm);

        return AjaxResponse.success();
    }

}
