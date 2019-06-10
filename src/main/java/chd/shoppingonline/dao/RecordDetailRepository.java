package chd.shoppingonline.dao;
/*
 * @ClassName RecordDetailRepository
 * @Author 从林
 * @Date 2019-06-03 14:04
 * @Description
 */

import chd.shoppingonline.entity.RecordDetail;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface RecordDetailRepository extends JpaRepository<RecordDetail, Long> {

    @Modifying
    @Transactional
    @Query("update RecordDetail r set r.recordDetailState = ?3 where r.recordDetailId = ?1 and r.recordDetailState =?2")
    void updateRecordDetailState(Long recordDetailId, Short currentState, Short expectState);

    @Modifying
    @Transactional
    @Query("update RecordDetail r set r.expressId =?2 where r.recordDetailId = ?1")
    void updateRecordDetailExpressId(Long recordDetailId, String expressId);

    @Modifying
    @Transactional
    @Query("update RecordDetail r set r.comment = ?2, r.star = ?3, r.commentDate =?4 where  r.recordDetailId = ?1")
    void updateRecordDetailCommentAndStar(Long recordDetailId, String comment, Short star, LocalDateTime time);

    RecordDetail findByRecordDetailId(Long recordDetailId)  throws EmptyResultDataAccessException, IllegalArgumentException;

    @Query("select count (rd) from  RecordDetail rd where rd.recordDetailState = 4")
    Integer countAllValidByCommodityId(Long commodityId);

    List<RecordDetail> findAllByCommodityId(Long commodityId) throws EmptyResultDataAccessException, IllegalArgumentException;
}
