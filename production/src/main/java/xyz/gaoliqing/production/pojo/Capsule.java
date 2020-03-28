package xyz.gaoliqing.production.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Mr.GaoLiqing
 * @create 2020-02-29 9:52
 * @description 返回前台的信息胶囊
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Capsule {

    private String goodsPlace;
    private String goodsTime;
    private String title;
    private String goodsDesc;
    private String color;
    private String imgAddress;
}
