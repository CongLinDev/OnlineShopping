package chd.shoppingonline.service.basic;
/*
 * @ClassName CommodityService
 * @Author 从林
 * @Date 2019-03-14 16:27
 * @Description 商品业务接口
 */

import chd.shoppingonline.entity.Commodity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommodityService {
    /**
     * 添加商品
     * @param commodity 商品
     * @return
     */
    Commodity addCommodity(Commodity commodity);

    /**
     * 删除商品
     * @param commodityId 商品ID
     * @return
     */
    void deleteCommodity(Long commodityId);

    /**
     * 查询商品
     * @param commodityId 商品ID
     * @return
     */
    Commodity findCommodity(Long commodityId);

    /**
     * 查询商品
     * @param search 关键字
     * @param pageable 页信息
     * @return
     */
    Page<Commodity> findCommodity(String search, Pageable pageable);

    /**
     * 查询商品
     * @param search 关键字
     * @param pageNum 页号
     * @param pageLimit 页内信息数量
     * @return
     */
    Page<Commodity> findCommodity(String search, int pageNum, int pageLimit);


    /**
     * 查询所有商品
     * @param pageable 页信息
     * @return
     */
    Page<Commodity> findAllCommodity(Pageable pageable);

    /**
     * 查询所有商品
     * @param pageNum 页号
     * @param pageLimit 页内信息数量
     * @return
     */
    Page<Commodity> findAllCommodity(int pageNum, int pageLimit);//按照ID逆序排列

    /**
     * 更新库存
     * @param  commodityId
     * @param decreaseStock 库存减少量
     */
    void updateCommodityStock(Long commodityId, Integer decreaseStock);

}
