package chd.shoppingonline.dao;
/*
 * @ClassName CommodityRepository
 * @Author 从林
 * @Date 2019-03-14 16:19
 * @Description 用于商品与数据库的交互
 */

import chd.shoppingonline.entity.Commodity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CommodityRepository extends JpaRepository<Commodity, Long>, JpaSpecificationExecutor<Commodity> {
    Page<Commodity> findAllByCommodityname(String commodityname, Pageable pageable);
    Page<Commodity> findAll(Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "update Commodity commodity set commodity.stock = ?3 where commodity.commodityId = ?1 and commodity.stock = ?2")
    void updateStockByCommodityId(Long commodityId, Integer currentStock,Integer expectStock);
}
