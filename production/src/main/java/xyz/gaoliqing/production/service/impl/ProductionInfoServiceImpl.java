package xyz.gaoliqing.production.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.gaoliqing.production.feign.StoresFeignInterface;
import xyz.gaoliqing.production.pojo.Capsule;
import xyz.gaoliqing.production.service.ProductionInfoService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Mr.GaoLiqing
 * @create 2019-12-17 15:24
 * @description
 */
@Service
public class ProductionInfoServiceImpl implements ProductionInfoService {

    @Resource
    private ObjectMapper mapper;
    @Resource
    private StoresFeignInterface storesFeignInterface;

    @Override
    public Map<String, List<Capsule>> getProducts(String search_name) throws JsonProcessingException {

        String info = storesFeignInterface.getProductionInfo("color-a");

        return mapper.readValue(info, Map.class);
    }
}
