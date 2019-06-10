package chd.shoppingonline.service.feature;
/*
 * @ClassName ShoppingTrolleyService
 * @Author 从林
 * @Date 2019-06-10 22:26
 * @Description
 */

import chd.shoppingonline.entity.Commodity;

import java.util.Set;

public interface ShoppingTrolleyService {

    /**
     * 将商品添加到购物车中
     * @param userId
     * @param commodityId
     */
    void addCommodity(Long userId, Long commodityId);

    /**
     * 将商品添加到购物车中
     * @param userId
     * @param commodity
     */
    void addCommodity(Long userId, Commodity commodity);

    /**
     * 移除商品
     * @param userId
     * @param commodityId
     */
    void removeCommodity(Long userId, Long commodityId);

    /**
     * 移除商品id
     * @param userId
     * @param commodity
     */
    void removeCommodity(Long userId, Commodity commodity);




    /**
     * 获取指定用户的购物车的所有商品
     * @param userId
     * @return
     */
    Set<Commodity> getAllCommodities(Long userId);
}
