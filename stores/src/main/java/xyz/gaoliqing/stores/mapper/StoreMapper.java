package xyz.gaoliqing.stores.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xyz.gaoliqing.stores.pojo.Capsule;

import java.util.List;

/**
 * @author Mr.GaoLiqing
 * @create 2020-01-15 11:47
 * @description
 */
@Mapper
public interface StoreMapper {

    String getCounts(@Param(value = "name") String name);
    List<Capsule> getCapsule();
}
