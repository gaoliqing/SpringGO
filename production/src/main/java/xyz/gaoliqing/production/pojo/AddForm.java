package xyz.gaoliqing.production.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Mr.GaoLiqing
 * @create 2020-03-17 10:02
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddForm {

    private String goodsPlace;
    private String title;
    private String time;
    private String desc;
    private String imgAddress;

}
