package xyz.gaoliqing.production.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import xyz.gaoliqing.production.pojo.Capsule;

import java.util.List;
import java.util.Map;

/**
 * @author Mr.GaoLiqing
 * @create 2019-12-17 15:15
 * @description
 */
public interface ProductionInfoService {

    Map<String, List<Capsule>> getProducts(String serch_name) throws JsonProcessingException;
}
