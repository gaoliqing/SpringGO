package xyz.gaoliqing.production.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.gaoliqing.production.feign.StoresFeignInterface;
import xyz.gaoliqing.production.mapper.ProductionMapper;
import xyz.gaoliqing.production.pojo.AddForm;
import xyz.gaoliqing.production.pojo.Capsule;
import xyz.gaoliqing.production.pojo.UserAll;
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
    @Resource
    private ProductionMapper productionMapper;

    @Override
    public Map<String, List<Capsule>> getProducts(String search_name) throws JsonProcessingException {

        String info = storesFeignInterface.getProductionInfo("color-a");

        return mapper.readValue(info, Map.class);
    }

    @Override
    public List<Capsule> getImg() {
        return productionMapper.getImg();
    }

    @Override
    public int insertForm(AddForm addForm) {

        return productionMapper.insertForm(addForm);
    }

    @Override
    public PageInfo<UserAll> pageInfo(Integer pageNum, Integer pageSize) {

        // 使用PageHelper进行分页查询
        PageHelper.startPage(pageNum, pageSize);
        List<UserAll> userList = productionMapper.getUserAll();

        // 用PageInfo对结果进行包装
        return new PageInfo(userList);
    }
}
