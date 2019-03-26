package chd.shoppingonline.service;
/*
 * @ClassName ConsigneeInformationService
 * @Author 从林
 * @Date 2019-03-24 14:10
 * @Description 收货信息服务接口
 */

import chd.shoppingonline.entity.ConsigneeInformation;

public interface ConsigneeInformationService {
    //增加收货信息
    ConsigneeInformation addConsigneeInformation(String consigneeName, String consigneeAddress, String consigneePhoneNumber, Long consigneeId);
    ConsigneeInformation addConsigneeInformation(ConsigneeInformation consigneeInformation, Long consigneeId);

    //删除收货信息
    void deleteConsigneeInformation(Long consigneeInformationId);

}
