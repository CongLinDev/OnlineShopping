package chd.shoppingonline.service;
/*
 * @ClassName CommodityService
 * @Author 从林
 * @Date 2019-03-14 16:27
 * @Description 商品业务接口
 */

import chd.shoppingonline.entity.Commodity;

import java.util.List;

public interface CommodityService {
    //添加商品
    Commodity addCommodity(Commodity commodity);

    //删除商品
    void deleteCommodity(Long commodityId);

    //查询商品
    Commodity findCommodity(Long commodityId);
    List<Commodity> findCommodity(String commodityname);

    //查询所有商品
    List<Commodity> findAllCommodity();
}
