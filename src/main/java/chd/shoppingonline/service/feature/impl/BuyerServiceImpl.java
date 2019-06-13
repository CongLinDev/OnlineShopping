package chd.shoppingonline.service.feature.impl;
/*
 * @ClassName BuyerServiceImpl
 * @Author 从林
 * @Date 2019-06-13 8:49
 * @Description
 */

import chd.shoppingonline.common.state.RecordDetailState;
import chd.shoppingonline.entity.Record;
import chd.shoppingonline.entity.RecordDetail;
import chd.shoppingonline.service.basic.*;
import chd.shoppingonline.service.feature.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private RecordDetailService recordDetailService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private CommodityService commodityService;

    @Autowired
    private UserService userService;

    @Autowired
    private RecordService recordService;

    @Override
    public void paid(Long recordDetailId) {
        recordDetailService.updateRecordDetailState(recordDetailId,
                RecordDetailState.ARREARAGE.getShortValue(),
                RecordDetailState.PREPARE_SHIPMENT.getShortValue());
    }

    @Override
    public void shipment(Long recordDetailId, String expressId) {
        recordDetailService.updateRecordDetailState(recordDetailId,
                RecordDetailState.PREPARE_SHIPMENT.getShortValue(),
                RecordDetailState.SHIPMENT.getShortValue());
        recordDetailService.updateRecordDetailExpressId(recordDetailId, expressId);
    }

    @Override
    public void deliver(Long recordDetailId) {
        recordDetailService.updateRecordDetailState(recordDetailId,
                RecordDetailState.SHIPMENT.getShortValue(),
                RecordDetailState.DELIVERED.getShortValue());
    }

    @Override
    public void requestReturn(Long recordDetailId) {
        RecordDetail recordDetail = recordDetailService.findRecordDetail(recordDetailId);
        short curState = recordDetail.getRecordDetailState();

        if(curState <= RecordDetailState.FLAG || curState != RecordDetailState.ARREARAGE.getShortValue()){
            recordDetailService.updateRecordDetailState(recordDetailId,
                    curState,
                    (short)(curState +  RecordDetailState.FLAG) );
        }
    }

    @Override
    public void permitReturn(Long recordDetailId) {

        RecordDetail recordDetail = recordDetailService.findRecordDetail(recordDetailId);

        short curState = recordDetail.getRecordDetailState();

        recordDetailService.updateRecordDetailState(recordDetailId,
                curState,
                RecordDetailState.RETURNED.getShortValue());
        //返还钱
        RecordDetail rd = recordDetailService.findRecordDetail(recordDetailId);
        Record record = recordService.findRecord(rd.getRecordId());
        Double price = paymentService.countPrice(rd.getRecordDetailId());
        paymentService.pay(userService.findUser().getUserId(), record.getBuyerId(), price);
    }

    @Override
    public void rejectReturn(Long recordDetailId) {
        RecordDetail recordDetail = recordDetailService.findRecordDetail(recordDetailId);
        short curState = recordDetail.getRecordDetailState();
        if(curState > RecordDetailState.FLAG){
            recordDetailService.updateRecordDetailState(recordDetailId,
                    curState,
                    (short) (curState - RecordDetailState.FLAG));
        }

    }

    @Override
    public void comment(Long recordDetailId, String comment) {
        recordDetailService.updateRecordDetailComment(recordDetailId, comment);
    }

    @Override
    @Transactional
    public void payRecord(Long recordId) {
        List<RecordDetail> recordDetails = recordDetailService.findRecordDetailByRecordId(recordId);

        for(RecordDetail recordDetail : recordDetails){
            Double price = paymentService.countPrice(recordDetail.getRecordDetailId());
            Long sellerId = commodityService.findCommodity(recordDetail.getCommodityId()).getCreatedBy();
            paymentService.pay(userService.findUser().getUserId(), sellerId, price);
            paid(recordDetail.getRecordDetailId());
        }

    }
}
