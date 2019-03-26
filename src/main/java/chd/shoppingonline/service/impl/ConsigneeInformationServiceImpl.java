package chd.shoppingonline.service.impl;
/*
 * @ClassName ConsigneeInformationServiceImpl
 * @Author 从林
 * @Date 2019-03-25 18:18
 * @Description ConsigneeInformationService 实现
 */

import chd.shoppingonline.dao.ConsigneeInformationRepository;
import chd.shoppingonline.entity.ConsigneeInformation;
import chd.shoppingonline.service.ConsigneeInformationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsigneeInformationServiceImpl implements ConsigneeInformationService {
    @Autowired
    ConsigneeInformationRepository consigneeInformationRepository;

    @Override
    public ConsigneeInformation addConsigneeInformation(String consigneeName, String consigneeAddress, String consigneePhoneNumber, Long consigneeId){
        ConsigneeInformation consigneeInformation = new ConsigneeInformation();
        consigneeInformation.toBuilder()
                .consigneeAddress(consigneeAddress)
                .consigneeName(consigneeName)
                .consigneePhoneNumber(consigneePhoneNumber)
                .build();
        return addConsigneeInformation(consigneeInformation, consigneeId);
    }

    @Override
    public ConsigneeInformation addConsigneeInformation(ConsigneeInformation consigneeInformation, Long consigneeId){
        consigneeInformation.setConsignee(consigneeId);
        log.info("用户USERID="+consigneeId + " 添加收货信息 "+ consigneeInformation.toString());
        return consigneeInformationRepository.save(consigneeInformation);
    }

    @Override
    public void deleteConsigneeInformation(Long consigneeInformationId){
        log.info("删除收货信息 CONSIGNEEINFORMATIONID="+ consigneeInformationId.toString());
        consigneeInformationRepository.deleteById(consigneeInformationId);
    }
}
