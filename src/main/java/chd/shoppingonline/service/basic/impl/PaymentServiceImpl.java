package chd.shoppingonline.service.basic.impl;
/*
 * @ClassName PaymentServiceImpl
 * @Author 从林
 * @Date 2019-06-03 16:08
 * @Description
 */

import chd.shoppingonline.dao.PaymentRepository;
import chd.shoppingonline.entity.Commodity;
import chd.shoppingonline.entity.Payment;
import chd.shoppingonline.entity.RecordDetail;
import chd.shoppingonline.entity.User;
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
    public Boolean pay(Long sourceUserId, Long destinationUserId, Double amount) {
        User sourceUser = userService.findUser(sourceUserId);
        Double sourceUserBalance = sourceUser.getBalance();
        if(sourceUserBalance >= amount){
            sourceUser.setBalance(sourceUserBalance - amount);
            userService.saveUser(sourceUser);

            User destinationUser = userService.findUser(destinationUserId);
            destinationUser.setBalance(destinationUser.getBalance() + amount);
            userService.saveUser(destinationUser);

            paymentRepository.save(Payment.builder().balance(amount).payerId(sourceUserId).receiverId(destinationUserId).build());
            return true;
        }
        return false;
    }

    @Override
    public Double countPrice(Long commodityId, Integer number) throws IllegalArgumentException {
        if(number <= 0) throw new IllegalArgumentException();

        Commodity commodity = commodityService.findCommodity(commodityId);
        //商品价格 * 交易量
        return commodity.getPrice() * number;
    }

    @Override
    public Double countPrice(Long recordId) throws IllegalArgumentException{
        List<RecordDetail> recordDetails = recordDetailService.findRecordDetailByRecordId(recordId);
        return countPrice(recordDetails);
    }

    @Override
    public Double countPrice(List<RecordDetail> recordDetails)throws IllegalArgumentException {
        double price = 0;
        for(RecordDetail recordDetail : recordDetails){
            price += countPrice(recordDetail.getCommodityId(),recordDetail.getTradingVolume());
        }
        return price;
    }
}
