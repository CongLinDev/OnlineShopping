package chd.shoppingonline.service.basic.impl;
/*
 * @ClassName RecordServiceImpl
 * @Author 从林
 * @Date 2019-03-15 17:33
 * @Description RecordService实现
 */

import chd.shoppingonline.common.state.RecordDetailState;
import chd.shoppingonline.dao.RecordRepository;
import chd.shoppingonline.entity.Commodity;
import chd.shoppingonline.entity.Record;
import chd.shoppingonline.entity.RecordDetail;
import chd.shoppingonline.service.basic.CommodityService;
import chd.shoppingonline.service.basic.RecordDetailService;
import chd.shoppingonline.service.basic.RecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class RecordServiceImpl implements RecordService {
    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private RecordDetailService recordDetailService;

    @Autowired
    private CommodityService commodityService;


    @Override
    public Record addRecord(Long consigneeInformationId, List<RecordDetail> recordDetails){
        Record record = Record.builder().consigneeInformationId(consigneeInformationId).build();
        Record savedRecord = recordRepository.save(record);

        //添加订单细节
        for(RecordDetail r : recordDetails){
            try{
                commodityService.updateCommodityStock(r.getCommodityId(), r.getTradingVolume());
            }catch (IllegalArgumentException e){
                log.error(r.getCommodityId() + "库存不足");
                continue;
            }
            r = r.toBuilder()
                    .recordDetailId(null)
                    .recordId(savedRecord.getRecordId())
                    .recordDetailState(RecordDetailState.ARREARAGE.getShortValue())
                .build();
            recordDetailService.addRecordDetail(r);
        }
        return savedRecord;
    }

    @Override
    public Record findRecord(Long recordId) throws EmptyResultDataAccessException, IllegalArgumentException {
        log.info("查询订单：ID="+recordId.toString());
        return recordRepository.findByRecordId(recordId);
    }

    @Override
    public List<RecordDetail> findRecordDetailsBySellerId(Long userId) {
        return commodityService.findCommodityByUserID(userId)
                .parallelStream()
                .map(Commodity::getCommodityId)
                .map(recordDetailService::findRecordDetailByCommodityId)
                .flatMap(Collection::parallelStream)
                .collect(Collectors.toList());

    }

    @Override
    public List<RecordDetail> findRecordDetailsByBuyerId(Long userId) {
        return recordRepository.findAllByBuyerId(userId)
                .parallelStream()
                .map(Record::getRecordId)
                .map(recordDetailService::findRecordDetailByRecordId)
                .flatMap(Collection::parallelStream)
                .collect(Collectors.toList());
    }

    @Override
    public List<RecordDetail> findUndeliverRecordDetailsByBuyerId(Long userId) {
        return recordRepository.findAllByBuyerId(userId)
                .parallelStream()
                .map(Record::getRecordId)
                .map(recordDetailService::findRecordDetailByRecordId)
                .flatMap(Collection::parallelStream)
                .filter( rd -> rd.getRecordDetailState() < RecordDetailState.DELIVERED.getShortValue())
                .collect(Collectors.toList());
    }

    @Override
    public List<RecordDetail> findRecordDetailsByBuyerIdAndState(Long userId, Short state) {
        return recordRepository.findAllByBuyerId(userId)
                .parallelStream()
                .map(Record::getRecordId)
                .map(recordDetailService::findRecordDetailByRecordId)
                .flatMap(Collection::parallelStream)
                .filter( rd -> rd.getRecordDetailState() == state)
                .collect(Collectors.toList());
    }


}
