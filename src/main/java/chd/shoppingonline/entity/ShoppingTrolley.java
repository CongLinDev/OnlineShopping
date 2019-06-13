package chd.shoppingonline.entity;
/*
 * @ClassName ShoppingTrolley
 * @Author 从林
 * @Date 2019-06-12 23:10
 * @Description
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingTrolley{

    private Long commodityId;//商品ID

    private Integer number;//数量
}
