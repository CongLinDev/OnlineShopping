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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class RecordDetailServiceImpl implements RecordDetailService{
    @Autowired
    private RecordDetailRepository recordDetailRepository;

    @Override
    public RecordDetail addRecordDetail(RecordDetail recordDetail) {
        return recordDetailRepository.save(recordDetail);
    }

    @Override
    public RecordDetail findRecordDetail(Long recordDetailId) throws EmptyResultDataAccessException, IllegalArgumentException{
        return recordDetailRepository.findByRecordDetailId(recordDetailId);
    }

    @Override
    public List<RecordDetail> findRecordDetailByCommodityId(Long commodityId) throws EmptyResultDataAccessException, IllegalArgumentException {
        return recordDetailRepository.findAllByCommodityId(commodityId);
    }

    @Override
    public List<RecordDetail> findRecordDetailsByCommodityIdAndState(Long commodityId, Short state) throws EmptyResultDataAccessException, IllegalArgumentException  {
        return recordDetailRepository.findAllByCommodityIdAndRecordDetailState(commodityId, state);
    }

    @Override
    public List<RecordDetail> findCommentsByCommodityId(Long commodityId) {
        return recordDetailRepository.findAllByCommodityIdAndRecordDetailState(commodityId, RecordDetailState.DELIVERED.getShortValue());
    }


    @Override
    public Page<RecordDetail> findRecordDetailByCommodityIdAndState(Long commodityId, Short state, int page, int max) {
        Pageable pageable = PageRequest.of(page, max, new Sort(Sort.Direction.DESC, "recordDetailId"));
        return recordDetailRepository.findAllByCommodityIdAndState(commodityId, state, pageable);
    }

    @Override
    public List<RecordDetail> findRecordDetailByRecordId(Long recordId) {
        return recordDetailRepository.findAllByRecordId(recordId);
    }

    @Override
    public void updateRecordDetailExpressId(Long recordDetailId, String expressId) {
        recordDetailRepository.updateRecordDetailExpressId(recordDetailId, expressId);
    }

    @Override
    public void updateRecordDetailState(Long recordDetail, Short currentState, Short expectState) {
        recordDetailRepository.updateRecordDetailState(recordDetail, currentState, expectState);
    }

    @Override
    public void updateRecordDetailComment(Long recordDetailId, String comment) {
        recordDetailRepository.updateRecordDetailComment(recordDetailId, comment, LocalDateTime.now());
    }


    @Override
    public Integer countTradingVolume (Long commodityId){
        return recordDetailRepository.countAllValidByCommodityId(commodityId);
    }

    public Integer countTradingVolume(List<RecordDetail> recordDetails){
        if(recordDetails == null) return 0;
        return recordDetails.parallelStream()
                .filter(rd->rd.getRecordDetailState() == RecordDetailState.DELIVERED.getShortValue())
                .map(RecordDetail::getTradingVolume)
                .reduce(Integer::sum)
                .orElse(0);
    }

}
