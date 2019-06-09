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

import java.util.List;

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
    Page<Commodity> findAllCommodities(Pageable pageable);

    /**
     * 查询所有商品
     * @param pageNum 页号
     * @param pageLimit 页内信息数量
     * @return
     */
    Page<Commodity> findAllCommodities(int pageNum, int pageLimit);//按照ID逆序排列

    /**
     * 更新库存
     * @param  commodityId
     * @param decreaseStock 库存减少量
     */
    void updateCommodityStock(Long commodityId, Integer decreaseStock);



    /**
     * 在对应商品类中根据关键字查找商品
     * @param key 关键字
     * @param className 商品类
     * @param asc 按照顺序排列
     * @param orderColumn 排序列
     * @param page 页数
     * @param max 每页最大值
     * @return
     */
    List<Commodity> findCommodity(String key, String className, Boolean asc, String orderColumn, Integer page, Integer max);

    /**
     * 根据关键字查找商品 ,范围[(page-1)*max, page*max)
     * @param key 关键字
     * @param asc 按照顺序排列
     * @param orderColumn 排序列
     * @param page 页数
     * @param max 每页最大值
     * @return
     */
    List<Commodity> findCommodity(String key, Boolean asc, String orderColumn, Integer page, Integer max);

    /**
     * 查看商品类，返回对应类的商品集合
     * @param className 类别
     * @param asc 按照顺序排列
     * @param orderColumn 排序列
     * @param page 页数
     * @param max 每页最大值
     * @return
     */
    List<Commodity> findAllCommodities(String className, Boolean asc,  String orderColumn, Integer page, Integer max);

}
