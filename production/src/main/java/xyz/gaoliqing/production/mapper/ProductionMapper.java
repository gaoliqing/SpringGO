package xyz.gaoliqing.production.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.gaoliqing.production.pojo.AddForm;

/**
 * @author Mr.GaoLiqing
 * @create 2020-03-17 14:35
 * @description
 */
@Mapper
public interface ProductionMapper {

    int insertForm(AddForm addForm);

}
