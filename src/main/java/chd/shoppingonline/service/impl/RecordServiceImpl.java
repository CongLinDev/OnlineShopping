package chd.shoppingonline.service.impl;
/*
 * @ClassName RecordServiceImpl
 * @Author 从林
 * @Date 2019-03-15 17:33
 * @Description RecordService实现
 */

import chd.shoppingonline.dao.RecordRepository;
import chd.shoppingonline.entity.Commodity;
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
    public Record addRecord(Long commodityId, Long buyerId){
        return addRecord(commodityId, buyerId, null);
    }

    @Override
    @Transactional
    public Record addRecord(Long commodityId, Long buyerId, String remarks){
        Commodity commodity = commodityService.findCommodity(commodityId);
        Long sellerId = commodity.getCreatedBy();

        if(userService.updateUserBalance(buyerId, commodity.getPrice(), false) &&
            userService.updateUserBalance(sellerId, commodity.getPrice(),true) &&
            commodityService.transactCommodity(commodityId)
        ){
            Record record = new Record();
            record.builder()
                    .commodityId(commodityId)
                    .buyerId(buyerId)
                    .sellerId(sellerId)
                    .recordTime(new Date())
                    .remarks(remarks)
                    .build();
            log.info("创建订单："+ record.toString());
            return recordRepository.save(record);
        }
        log.info("创建订单失败");
        return null;
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
