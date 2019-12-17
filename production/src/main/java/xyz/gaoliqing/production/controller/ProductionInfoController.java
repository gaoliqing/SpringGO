package xyz.gaoliqing.production.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.gaoliqing.production.service.ProductionInfoService;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author Mr.GaoLiqing
 * @create 2019-12-17 15:13
 * @description
 */
@RestController
public class ProductionInfoController {

    @Resource
    private ProductionInfoService productionInfoService;

    @GetMapping("/production-detail")
    public String getProductionInfo() {

        Map<String, Object> products = productionInfoService.getProducts();

        return products.toString();
    }

}
