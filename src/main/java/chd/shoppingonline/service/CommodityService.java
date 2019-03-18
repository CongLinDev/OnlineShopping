package chd.shoppingonline.service;
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
    //添加商品
    Commodity addCommodity(Commodity commodity);

    //删除商品
    void deleteCommodity(Long commodityId);

    //查询商品
    Commodity findCommodity(Long commodityId);
    //List<Commodity> findCommodity(String commodityname);//弃用
    Page<Commodity> findCommodity(String commodityname, Pageable pageable);
    Page<Commodity> findCommodity(String commodityname, int pageNum, int pageLimit);//按照ID逆序排列

    //查询所有商品
    //List<Commodity> findAllCommodity();//弃用
    Page<Commodity> findAllCommodity(Pageable pageable);
    Page<Commodity> findAllCommodity(int pageNum, int pageLimit);//按照ID逆序排列

    /*******************以下方法不建议Controller层调用**************************************************************/
    //交易商品
    Boolean transactCommodity(Long commodityId);
}
