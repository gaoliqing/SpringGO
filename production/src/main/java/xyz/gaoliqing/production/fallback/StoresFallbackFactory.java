package xyz.gaoliqing.production.fallback;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import xyz.gaoliqing.production.exception.CustomException;
import xyz.gaoliqing.production.exception.CustomExceptionType;

/**
 * @author Mr.GaoLiqing
 * @create 2019-12-18 17:28
 * @description
 */
@Component
public class StoresFallbackFactory implements FallbackFactory<CustomException> {

    @Override
    public CustomException create(Throwable throwable) {

        return new CustomException(CustomExceptionType.SYSTEM_ERROR, "p调用s时熔断了");
    }
}
