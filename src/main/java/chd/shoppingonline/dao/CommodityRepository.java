package chd.shoppingonline.dao;
/*
 * @ClassName CommodityRepository
 * @Author 从林
 * @Date 2019-03-14 16:19
 * @Description 用于商品与数据库的交互
 */

import chd.shoppingonline.entity.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommodityRepository extends JpaRepository<Commodity, Long> {
    List<Commodity> findAllByCommodityname(String commodityname);
}
