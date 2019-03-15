package chd.shoppingonline.dao;
/*
 * @ClassName RecordRepository
 * @Author 从林
 * @Date 2019-03-15 16:34
 * @Description 处理Record与数据库的交互
 */

import chd.shoppingonline.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long> {
    List<Record> findAllByBuyerId(Long buyerId);
    List<Record> findAllBySellerId(Long sellerId);
}
