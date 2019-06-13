package chd.shoppingonline.service.feature;
/*
 * @ClassName ShoppingTrolleyService
 * @Author 从林
 * @Date 2019-06-10 22:26
 * @Description
 */

import chd.shoppingonline.entity.ShoppingTrolley;

import java.util.List;

public interface ShoppingTrolleyService {
    /**
     * 将商品添加到购物车中
     * @param userId
     * @param commodityId
     */
    void add(Long userId, Long commodityId, Integer num);


    /**
     * 将商品添加到购物车中
     * @param userId
     * @param shoppingTrolley
     */
    void add(Long userId, ShoppingTrolley shoppingTrolley);

    /**
     * 将商品添加到购物车中
     * @param userId
     * @param shoppingTrolleys
     */
    void add(Long userId, List<ShoppingTrolley> shoppingTrolleys);


    /**
     * 移除商品
     * @param userId
     * @param commodityId
     */
    void remove(Long userId, Long commodityId) ;

    /**
     * 获取指定用户的购物车的所有商品的ID
     * @param userId
     * @return
     */
    List<ShoppingTrolley> getAll(Long userId);
}
