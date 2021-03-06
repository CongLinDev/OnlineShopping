package chd.shoppingonline.dao;
/*
 * @ClassName CommodityRepository
 * @Author 从林
 * @Date 2019-03-14 16:19
 * @Description 用于商品与数据库的交互
 */

import chd.shoppingonline.entity.Commodity;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CommodityRepository extends JpaRepository<Commodity, Long>, JpaSpecificationExecutor<Commodity> {
    @Query("select c from Commodity c where c.commodityType = 0 and c.commodityName like %?1%")
    Page<Commodity> findAllByCommodityName(String commodityName, Pageable pageable)throws EmptyResultDataAccessException, IllegalArgumentException;

    @Query("select c from Commodity c where c.commodityType = 0 and c.commodityType = ?2 and c.commodityName like %?1%")
    Page<Commodity> findAllByCommodityNameAndCommodityType(String commodityName, String commodityType, Pageable pageable) throws EmptyResultDataAccessException, IllegalArgumentException;

    @Query("select c from Commodity c where c.commodityType = 0 and c.commodityType = ?1")
    Page<Commodity> findAllByCommodityType(String commodityType, Pageable pageable) throws EmptyResultDataAccessException, IllegalArgumentException;

    @Query("select c from  Commodity c where c.commodityType = 0")
    Page<Commodity> findAll(Pageable pageable) throws EmptyResultDataAccessException, IllegalArgumentException;

    @Transactional
    @Modifying
    @Query(value = "update Commodity commodity set commodity.stock = ?3 where commodity.commodityId = ?1 and commodity.stock = ?2")
    void updateStockByCommodityId(Long commodityId, Integer currentStock,Integer expectStock);

    Commodity findByCommodityId(Long commodityId) throws EmptyResultDataAccessException, IllegalArgumentException;

    @Query("select c from Commodity c where c.commodityType = 0 and c.createdBy = ?1")
    List<Commodity> findAllByCreatedBy(Long createBy) throws EmptyResultDataAccessException, IllegalArgumentException;

    @Modifying
    @Query("update Commodity c set c.commodityType = ?3 where c.commodityId = ?1 and c.createdBy = ?2")
    @Transactional
    void updateStateByCommodityId(Long commodityId, Long createBy, Short state);

}
