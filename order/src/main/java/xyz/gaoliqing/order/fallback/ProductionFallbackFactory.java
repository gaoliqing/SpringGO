package xyz.gaoliqing.order.fallback;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import xyz.gaoliqing.order.feign.ProductionFeignInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mr.GaoLiqing
 * @create 2019-12-17 15:36
 * @description
 */
@Component
public class ProductionFallbackFactory implements FallbackFactory<ProductionFeignInterface> {

    @Override
    public ProductionFeignInterface create(Throwable throwable) {
        return new ProductionFeignInterface() {
            @Override
            public String getProductionInfo() {

                Map<String, Object> map = new HashMap<>();
                map.put("message", "抢购的人太多了,服务熔断喽喽!");

                return map.toString();
            }
        };
    }
}
