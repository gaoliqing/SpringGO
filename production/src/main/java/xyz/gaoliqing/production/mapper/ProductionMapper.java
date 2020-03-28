package xyz.gaoliqing.production.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import xyz.gaoliqing.production.pojo.AddForm;
import xyz.gaoliqing.production.pojo.Capsule;
import xyz.gaoliqing.production.pojo.UserAll;

import java.util.List;

/**
 * @author Mr.GaoLiqing
 * @create 2020-03-17 14:35
 * @description
 */
@Mapper
public interface ProductionMapper {

    int insertForm(AddForm addForm);

    List<UserAll> getUserAll();

    @Select("select * from capsule")
    List<Capsule> getImg();
}
