package chd.shoppingonline.dao;
/*
 * @ClassName RecordRepository
 * @Author 从林
 * @Date 2019-03-15 16:34
 * @Description 处理Record与数据库的交互
 */

import chd.shoppingonline.entity.Record;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
    Record findByRecordId(Long recordId)  throws EmptyResultDataAccessException, IllegalArgumentException;

    List<Record> findAllByBuyerId(Long buyerId);
}
