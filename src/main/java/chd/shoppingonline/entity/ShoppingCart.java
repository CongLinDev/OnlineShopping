package chd.shoppingonline.entity;
/*
 * @ClassName ShoppingCart
 * @Author 从林
 * @Date 2019-03-14 15:47
 * @Description 购物车（买家和商品之间的关联类）
 */

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "shoppingcart", schema = "shoppingcart")
@Data
public class ShoppingCart {
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "commodity_id")
    private Long commodityId;

}
