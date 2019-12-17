package xyz.gaoliqing.production.service.impl;

import org.springframework.stereotype.Service;
import xyz.gaoliqing.production.service.ProductionInfoService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mr.GaoLiqing
 * @create 2019-12-17 15:24
 * @description
 */
@Service
public class ProductionInfoServiceImpl implements ProductionInfoService {
    @Override
    public Map<String, Object> getProducts() {

        Map<String, Object> map = new HashMap<>();

        map.put("商品A", "666");
        map.put("商品B", "777");
        map.put("商品c", "888");
        map.put("商品d", "999");

        return map;
    }
}
