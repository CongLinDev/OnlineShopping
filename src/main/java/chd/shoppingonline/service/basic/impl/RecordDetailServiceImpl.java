package chd.shoppingonline.service.basic.impl;
/*
 * @ClassName RecordDetailServiceImpl
 * @Author 从林
 * @Date 2019-06-03 14:36
 * @Description
 */

import chd.shoppingonline.common.state.RecordDetailState;
import chd.shoppingonline.dao.RecordDetailRepository;
import chd.shoppingonline.entity.RecordDetail;
import chd.shoppingonline.service.basic.RecordDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RecordDetailServiceImpl implements RecordDetailService{
    @Autowired
    private RecordDetailRepository recordDetailRepository;

    @Override
    public RecordDetail addRecordDetail(RecordDetail recordDetail) {
        return recordDetailRepository.save(recordDetail);
    }

    @Override
    public RecordDetail findRecordDetail(Long recordDetailId) {
        return recordDetailRepository.findById(recordDetailId).orElse(null);
    }

    @Override
    public void paid(Long recordDetailId) {
        recordDetailRepository.updateRecordDetailState(recordDetailId,
                RecordDetailState.ARREARAGE.getShortValue(),
                RecordDetailState.PREPARE_SHIPMENT.getShortValue());
    }

    @Override
    public void shipment(Long recordDetailId, String expressId) {
        recordDetailRepository.updateRecordDetailState(recordDetailId,
                RecordDetailState.PREPARE_SHIPMENT.getShortValue(),
                RecordDetailState.SHIPMENT.getShortValue());
        recordDetailRepository.updateRecordDetailExpressId(recordDetailId, expressId);
    }

    @Override
    public void deliver(Long recordDetailId, Short star, String comment) {
        recordDetailRepository.updateRecordDetailState(recordDetailId,
                RecordDetailState.SHIPMENT.getShortValue(),
                RecordDetailState.DELIVERED.getShortValue());
        recordDetailRepository.updateRecordDetailCommentAndStar(recordDetailId, comment, star, LocalDateTime.now());
    }

    @Override
    public void returned(Long recordDetailId) {
        recordDetailRepository.updateRecordDetailState(recordDetailId,
                RecordDetailState.DELIVERED.getShortValue(),
                RecordDetailState.RETURNED.getShortValue());
    }
}
