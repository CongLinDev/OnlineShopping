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
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
    Page<Record> findAllByBuyerId(Long buyerId, Pageable pageable);
    Page<Record> findAllBySellerId(Long sellerId, Pageable pageable);
//    List<String> findCommentsByCommodityId(Long commodityId);

    @Transactional
    @Modifying
    @Query(value = "update Record record set record.comment = :comment, record.isFinished = :isFinished where record.id = :recordId")
    void updateById(@Param("recordId")Long recordId, @Param("comment")String comment, @Param("isFinished")Boolean isFinished);
}
