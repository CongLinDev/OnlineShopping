package chd.shoppingonline.service.impl;
/*
 * @ClassName RecordServiceImpl
 * @Author 从林
 * @Date 2019-03-15 17:33
 * @Description RecordService实现
 */

import chd.shoppingonline.common.state.RecordDetailState;
import chd.shoppingonline.dao.RecordRepository;
import chd.shoppingonline.entity.Record;
import chd.shoppingonline.entity.RecordDetail;
import chd.shoppingonline.service.basic.RecordDetailService;
import chd.shoppingonline.service.basic.RecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class RecordServiceImpl implements RecordService {
    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private RecordDetailService recordDetailService;

    @Override
    public Record addRecord(Long buyerId, Long consigneeInformationId, List<RecordDetail> recordDetails){
        Record record = Record.builder().buyerId(buyerId).consigneeInformationId(consigneeInformationId).build();
        Record savedRecord = recordRepository.save(record);

        for(RecordDetail r : recordDetails){
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
    public Record findRecord(Long recordId){
        log.debug("查询订单：ID="+recordId.toString());
        return recordRepository.findById(recordId).orElse(null);
    }
}
