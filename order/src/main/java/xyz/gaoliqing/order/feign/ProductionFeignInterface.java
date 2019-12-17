package xyz.gaoliqing.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import xyz.gaoliqing.order.fallback.ProductionFallbackFactory;

/**
 * @author Mr.GaoLiqing
 * @create 2019-12-17 15:32
 * @description
 */
@FeignClient(value = "PRODUCTION", fallbackFactory = ProductionFallbackFactory.class)
@Component
public interface ProductionFeignInterface {

    @GetMapping("/production-detail")
    String getProductionInfo();

}
