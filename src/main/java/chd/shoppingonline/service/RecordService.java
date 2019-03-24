package chd.shoppingonline.service;
/*
 * @ClassName RecordService
 * @Author 从林
 * @Date 2019-03-15 16:00
 * @Description RecordService
 */

import chd.shoppingonline.entity.ConsigneeInformation;
import chd.shoppingonline.entity.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecordService {
    //生成订单
    Record addRecord(Long commodityId, Long buyerId, int num, ConsigneeInformation consigneeInformation);
    Record addRecord(Long commodityId, Long buyerId, int num,String remarks, ConsigneeInformation consigneeInformation);

    //确认订单
    void ensureRecord(Long recordId, String comment, short star);

    //查询订单
    Record findRecord(Long recordId);//通过订单ID查询
    Page<Record> findRecordOfSeller(Long sellerId, Pageable pageable);//通过商家id查询商家所有订单
    Page<Record> findRecordOfSeller(Long sellerIdint, int pageNum, int pageLimit);//按照ID逆序排列

    Page<Record> findRecordOfBuyer(Long buyerId, Pageable pageable);//通过买家id查询买家所有订单
    Page<Record> findRecordOfBuyer(Long buyerId,int pageNum, int pageLimit);//按照ID逆序排列
}
