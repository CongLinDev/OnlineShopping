package chd.shoppingonline.service.basic.impl;
/*
 * @ClassName ConsigneeInformationServiceImpl
 * @Author 从林
 * @Date 2019-03-25 18:18
 * @Description ConsigneeInformationService 实现
 */

import chd.shoppingonline.dao.ConsigneeInformationRepository;
import chd.shoppingonline.entity.ConsigneeInformation;
import chd.shoppingonline.service.basic.ConsigneeInformationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsigneeInformationServiceImpl implements ConsigneeInformationService {
    @Autowired
    ConsigneeInformationRepository consigneeInformationRepository;

    @Override
    public ConsigneeInformation addConsigneeInformation(String consigneeName, String consigneeAddress, String consigneePhoneNumber){
        ConsigneeInformation consigneeInformation = ConsigneeInformation.builder()
                .consigneeAddress(consigneeAddress)
                .consigneeName(consigneeName)
                .consigneePhoneNumber(consigneePhoneNumber)
                .build();
        return addConsigneeInformation(consigneeInformation);
    }

    @Override
    public ConsigneeInformation addConsigneeInformation(ConsigneeInformation consigneeInformation){
        log.debug("用户添加收货信息 "+ consigneeInformation.toString());
        return consigneeInformationRepository.save(consigneeInformation);
    }

    @Override
    public void deleteConsigneeInformation(Long consigneeInformationId){
        log.debug("删除收货信息 ConsigneeInformationId="+ consigneeInformationId.toString());
        consigneeInformationRepository.deleteById(consigneeInformationId);
    }
}
