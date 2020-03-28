package xyz.gaoliqing.production.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageInfo;
import xyz.gaoliqing.production.pojo.AddForm;
import xyz.gaoliqing.production.pojo.Capsule;
import xyz.gaoliqing.production.pojo.UserAll;

import java.util.List;
import java.util.Map;

/**
 * @author Mr.GaoLiqing
 * @create 2019-12-17 15:15
 * @description
 */
public interface ProductionInfoService {

    Map<String, List<Capsule>> getProducts(String serch_name) throws JsonProcessingException;

    List<Capsule> getImg();

    int insertForm(AddForm addForm);

    PageInfo<UserAll> pageInfo(Integer pageNum, Integer pageSize);
}
