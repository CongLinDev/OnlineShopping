package chd.shoppingonline.service.basic.impl;
/*
 * @ClassName PaymentServiceImpl
 * @Author 从林
 * @Date 2019-06-03 16:08
 * @Description
 */

import chd.shoppingonline.dao.PaymentRepository;
import chd.shoppingonline.entity.*;
import chd.shoppingonline.service.basic.CommodityService;
import chd.shoppingonline.service.basic.PaymentService;
import chd.shoppingonline.service.basic.RecordDetailService;
import chd.shoppingonline.service.basic.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private UserService userService;

    @Autowired
    private CommodityService commodityService;

    @Autowired
    private RecordDetailService recordDetailService;

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    @Transactional
    public void pay(Long sourceUserId, Long destinationUserId, Double amount) {
        User sourceUser = userService.findUser(sourceUserId);
        Double sourceUserBalance = sourceUser.getBalance();

        sourceUser.setBalance(sourceUserBalance - amount);
        userService.saveUser(sourceUser);

        User destinationUser = userService.findUser(destinationUserId);
        destinationUser.setBalance(destinationUser.getBalance() + amount);
        userService.saveUser(destinationUser);

        paymentRepository.save(Payment.builder().balance(amount).payerId(sourceUserId).receiverId(destinationUserId).build());

    }

    @Override
    public Double countPrice(Long commodityId, Integer number) throws IllegalArgumentException {
        if(number <= 0) throw new IllegalArgumentException();

        Commodity commodity = commodityService.findCommodity(commodityId);
        //商品价格 * 交易量
        return commodity.getPrice() * number;
    }

    @Override
    public Double countPrice(Long recordDetailId) throws IllegalArgumentException{
        //List<RecordDetail> recordDetails = recordDetailService.findRecordDetailByRecordId(recordId);
        RecordDetail recordDetail = recordDetailService.findRecordDetail(recordDetailId);
        return countPrice(recordDetail.getCommodityId(), recordDetail.getTradingVolume());
    }

    @Override
    public Double countPrice(List<RecordDetail> recordDetails)throws IllegalArgumentException {
        double price = 0;
        for(RecordDetail recordDetail : recordDetails){
            price += countPrice(recordDetail.getCommodityId(),recordDetail.getTradingVolume());
        }
        return price;
    }

    @Override
    @Transactional
    public void payRecord(Long recordId) {
        List<RecordDetail> recordDetails = recordDetailService.findRecordDetailByRecordId(recordId);

        for(RecordDetail recordDetail : recordDetails){
            Double price = countPrice(recordDetail.getRecordDetailId());
            Long sellerId = commodityService.findCommodity(recordDetail.getCommodityId()).getCreatedBy();
            pay(userService.findUser().getUserId(), sellerId, price);
            recordDetailService.paid(recordDetail.getRecordDetailId());
        }

    }
}
