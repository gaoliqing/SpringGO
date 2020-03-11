package xyz.gaoliqing.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import xyz.gaoliqing.order.feign.ProductionFeignInterface;

import javax.annotation.Resource;

/**
 * @author Mr.GaoLiqing
 * @create 2019-12-17 15:47
 * @description
 */
@RestController
@Slf4j
public class OrderManagermentController {

    @Resource
    private ProductionFeignInterface productionFeignInterface;

    @GetMapping("/order/{name}")
    public String getOrder(@PathVariable(value = "name") String search_name) {

        log.info("order输入了"+search_name);
        return productionFeignInterface.getProductionInfo(search_name);
    }

}
