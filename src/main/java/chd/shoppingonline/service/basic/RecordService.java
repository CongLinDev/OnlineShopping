package chd.shoppingonline.service.basic;
/*
 * @ClassName RecordService
 * @Author 从林
 * @Date 2019-03-15 16:00
 * @Description RecordService
 */

import chd.shoppingonline.entity.Record;
import chd.shoppingonline.entity.RecordDetail;

import java.util.List;

public interface RecordService {
    /**
     * 生成订单
     * 生成的同时将订单细节加入数据库
     * 并将二者关联
     * @param consigneeInformationId 买家收货地址ID
     * @param recordDetails 购买的商品记录细节
     * @return
     */
    Record addRecord(Long consigneeInformationId, List<RecordDetail> recordDetails);

    /**
     * 查询订单（通过订单ID查询）
     * @param recordId 订单ID
     * @return
     */
    Record findRecord(Long recordId);

    /**
     * 查看卖家的所有订单
     * @param userId
     * @return
     */
    List<RecordDetail> findRecordDetailsBySellerId(Long userId);

    /**
     * 查看买家的所有订单
     * @param userId
     * @return
     */
    List<RecordDetail> findRecordDetailsByBuyerId(Long userId);

    /**
     * 查看买家未收货订单细节
     * @param userId
     * @return
     */
    List<RecordDetail> findUndeliverRecordDetailsByBuyerId(Long userId);

    /**
     * 查看买家某个状态的订单细节
     * @param userId
     * @param state
     * @return
     */
    List<RecordDetail> findRecordDetailsByBuyerIdAndState(Long userId, Short state);
}
