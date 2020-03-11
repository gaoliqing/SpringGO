package xyz.gaoliqing.stores.pojo;

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

    private String imgAddress;
    private String title;
    private String body;
    private String clickTitle;
    private String clickHref;
    private String color;
}
