package chd.shoppingonline.dao;
/*
 * @ClassName RecordRepository
 * @Author 从林
 * @Date 2019-03-15 16:34
 * @Description 处理Record与数据库的交互
 */

import chd.shoppingonline.entity.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
    Page<Record> findAllByBuyerId(Long buyerId, Pageable pageable);
    Page<Record> findAllBySellerId(Long sellerId, Pageable pageable);

}
