package chd.shoppingonline.service.basic.impl;
/*
 * @ClassName ConsigneeInformationServiceImpl
 * @Author 从林
 * @Date 2019-03-25 18:18
 * @Description ConsigneeInformationService 实现
 */

import chd.shoppingonline.dao.ConsigneeInformationRepository;
import chd.shoppingonline.entity.ConsigneeInformation;
import chd.shoppingonline.entity.User;
import chd.shoppingonline.service.basic.ConsigneeInformationService;
import chd.shoppingonline.service.basic.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ConsigneeInformationServiceImpl implements ConsigneeInformationService {
    @Autowired
    private ConsigneeInformationRepository consigneeInformationRepository;

    @Autowired
    private UserService userService;

    @Override
    public ConsigneeInformation addConsigneeInformation(String consigneeName, String consigneeAddress, String consigneePhoneNumber){
        ConsigneeInformation consigneeInformation = ConsigneeInformation.builder()
                .consigneeAddress(consigneeAddress)
                .consigneeName(consigneeName)
                .consigneePhoneNumber(consigneePhoneNumber)
                .valid(true)
                .build();
        return addConsigneeInformation(consigneeInformation);
    }

    @Override
    public ConsigneeInformation addConsigneeInformation(ConsigneeInformation consigneeInformation){
        consigneeInformation.setValid(true);
        log.info("用户添加收货信息 "+ consigneeInformation.toString());
        return consigneeInformationRepository.save(consigneeInformation);
    }

    @Override
    public ConsigneeInformation findConsigneeInformation(Long consigneeInformationId) throws EmptyResultDataAccessException, IllegalArgumentException {
        return consigneeInformationRepository.findByConsigneeInformationId(consigneeInformationId);
    }

    @Override
    public void deleteConsigneeInformation(Long consigneeInformationId){
        log.info("删除收货信息 ConsigneeInformationId="+ consigneeInformationId.toString());
        consigneeInformationRepository.updateInvalidConsigneeInformation(consigneeInformationId);
    }

    @Override
    public List<ConsigneeInformation> getVisibleConsigneeInformations() {
        User user = userService.findUser();
        log.info("查询收货地址, userID="+user.getUserId());
        return consigneeInformationRepository.findAllByConsigneeIdAndValidIsTrue(user.getUserId());
    }

    @Override
    public ConsigneeInformation updateConsigneeInformation(ConsigneeInformation consigneeInformation) {
        consigneeInformationRepository.updateInvalidConsigneeInformation(consigneeInformation.getConsigneeInformationId());
        log.info("收货地址ID=" + consigneeInformation.getConsigneeInformationId() + " 设为无效");
        return consigneeInformationRepository.save(
                consigneeInformation.toBuilder()
                        .valid(true)
                        .consigneeInformationId(null).build());

    }
}
