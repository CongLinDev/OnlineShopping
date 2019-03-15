package chd.shoppingonline.service;
/*
 * @ClassName RecordService
 * @Author 从林
 * @Date 2019-03-15 16:00
 * @Description RecordService
 */

import chd.shoppingonline.entity.Record;

import java.util.List;

public interface RecordService {
    //生成订单
    Record addRecord(Long commodityId, Long buyerId);
    Record addRecord(Long commodityId, Long buyerId, String remarks);

    //查询订单
    Record findRecord(Long recordId);//通过订单ID查询
    List<Record> findRecordOfSeller(Long sellerId);//通过商家id查询商家所有订单
    List<Record> findRecordOfBuyer(Long buyerId);//通过买家id查询买家所有订单
}
