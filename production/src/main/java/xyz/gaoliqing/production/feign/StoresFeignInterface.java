package xyz.gaoliqing.production.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import xyz.gaoliqing.production.fallback.StoresFallbackFactory;
import xyz.gaoliqing.production.pojo.Capsule;

import java.util.List;

/**
 * @author Mr.GaoLiqing
 * @create 2019-12-18 16:00
 * @description
 */
@FeignClient(value = "STORES", fallbackFactory = StoresFallbackFactory.class)
public interface StoresFeignInterface {

    @GetMapping("/store/{name}")
    String getProductionInfo(@PathVariable(value = "name") String name);

}
