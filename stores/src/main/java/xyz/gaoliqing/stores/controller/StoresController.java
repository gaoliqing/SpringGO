package xyz.gaoliqing.stores.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import xyz.gaoliqing.stores.mapper.StoreMapper;
import xyz.gaoliqing.stores.pojo.Capsule;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mr.GaoLiqing
 * @create 2019-12-18 15:51
 * @description
 */
@RestController
@Slf4j
public class StoresController {

    @Resource
    private StoreMapper storeMapper;

    /**
     * 库存数量查询接口
     *
     * @param name 商品的名称
     * @return 该商品的库存数量
     */
    @GetMapping("/store/{name}")
    public Map<String, Object> getStoresNumber(@PathVariable(value = "name") String name) {

        log.info("到stores了,传入参数是:"+ name);

        List<Capsule> capsules = storeMapper.getCapsule();

        Map<String, Object> map = new HashMap<>();
        map.put("data", capsules);

        return map;
    }

}
