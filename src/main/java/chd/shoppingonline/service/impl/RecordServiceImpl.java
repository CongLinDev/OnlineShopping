package chd.shoppingonline.service.impl;
/*
 * @ClassName RecordServiceImpl
 * @Author 从林
 * @Date 2019-03-15 17:33
 * @Description RecordService实现
 */

import chd.shoppingonline.dao.RecordRepository;
import chd.shoppingonline.entity.Commodity;
import chd.shoppingonline.entity.ConsigneeInformation;
import chd.shoppingonline.entity.Record;
import chd.shoppingonline.service.CommodityService;
import chd.shoppingonline.service.RecordService;
import chd.shoppingonline.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service
@Slf4j
public class RecordServiceImpl implements RecordService {
    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private CommodityService commodityService;

    @Autowired
    private UserService userService;

    @Override
    public Record addRecord(Long commodityId, Long buyerId, int num, ConsigneeInformation consigneeInformation){
        return addRecord(commodityId, buyerId, num, null, consigneeInformation);
    }

    @Override
    @Transactional
    public Record addRecord(Long commodityId, Long buyerId, int num, String remarks, ConsigneeInformation consigneeInformation){
        Commodity commodity = commodityService.findCommodity(commodityId);
        Long sellerId = commodity.getCreatedBy();

        if(userService.payBalance(buyerId, commodity.getPrice() * num) && //买家付钱
            commodityService.transactCommodity(commodityId, num) //交易物品
        ){
            Record record = new Record();
            record.builder()
                    .commodityId(commodityId)
                    .buyerId(buyerId)
                    .sellerId(sellerId)
                    .recordTime(new Date())
                    .exchangeNumber(num)
                    .remarks(remarks)
                    .isFinished(false)
                    .name(consigneeInformation.getConsigneeName())
                    .address(consigneeInformation.getConsigneeAddress())
                    .phoneNumber(consigneeInformation.getConsigneePhoneNumber())
                    .build();
            log.info("创建订单："+ record.toString());
            return recordRepository.save(record);
        }
        log.info("创建订单失败");
        return null;
    }

    @Override
    public void ensureRecord(Long recordId, String comment, short star){
        Record record = recordRepository.findById(recordId).get();
        recordRepository.updateById(recordId, comment, star,true);
        userService.getBalance(record.getSellerId(),
                commodityService.findCommodity(record.getCommodityId()).getPrice() * record.getExchangeNumber());
        log.info("买家ID="+record.getBuyerId()+"确认订单，订单ID="+recordId);
    }

    @Override
    public Record findRecord(Long recordId){
        log.info("查询订单：ID="+recordId.toString());
        return recordRepository.findById(recordId).get();
    }

    @Override
    public Page<Record> findRecordOfSeller(Long sellerId, Pageable pageable){
        log.info("查询商家所有订单：商家ID="+sellerId.toString());
        return recordRepository.findAllBySellerId(sellerId, pageable);
    }

    @Override
    public Page<Record> findRecordOfSeller(Long sellerId,int pageNum, int pageLimit){
        Pageable pageable = PageRequest.of(pageNum, pageLimit, new Sort(Sort.Direction.DESC, "record_id"));
        return findRecordOfSeller(sellerId, pageable);
    }

    @Override
    public Page<Record> findRecordOfBuyer(Long buyerId, Pageable pageable){
        log.info("查询买家所有订单：商家ID="+buyerId.toString());
        return recordRepository.findAllByBuyerId(buyerId, pageable);
    }

    @Override
    public Page<Record> findRecordOfBuyer(Long buyerId, int pageNum, int pageLimit){
        Pageable pageable = PageRequest.of(pageNum, pageLimit, new Sort(Sort.Direction.DESC, "record_id"));
        return findRecordOfBuyer(buyerId, pageable);
    }
}
