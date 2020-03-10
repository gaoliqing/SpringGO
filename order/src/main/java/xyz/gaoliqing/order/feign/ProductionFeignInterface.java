package xyz.gaoliqing.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import xyz.gaoliqing.order.fallback.ProductionFallbackFactory;

/**
 * @author Mr.GaoLiqing
 * @create 2019-12-17 15:32
 * @description
 */
@FeignClient(value = "PRODUCTION", fallbackFactory = ProductionFallbackFactory.class)
public interface ProductionFeignInterface {

    @GetMapping(path = "/production_id/{name}")
    String getProductionInfo(@PathVariable("name") String name);

}
