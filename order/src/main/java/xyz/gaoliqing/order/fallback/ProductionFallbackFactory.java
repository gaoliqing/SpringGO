package xyz.gaoliqing.order.fallback;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import xyz.gaoliqing.order.feign.ProductionFeignInterface;


/**
 * @author Mr.GaoLiqing
 * @create 2019-12-17 15:36
 * @description
 */
@Component
public class ProductionFallbackFactory implements FallbackFactory<ProductionFeignInterface> {

    @Override
    public ProductionFeignInterface create(Throwable throwable) {

        return (s) -> "我靠, order在调用production时 熔断了!";
    }
}
