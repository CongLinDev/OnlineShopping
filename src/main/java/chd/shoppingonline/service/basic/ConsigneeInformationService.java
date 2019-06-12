package chd.shoppingonline.service.basic;
/*
 * @ClassName ConsigneeInformationService
 * @Author 从林
 * @Date 2019-03-24 14:10
 * @Description 收货信息服务接口
 */

import chd.shoppingonline.entity.ConsigneeInformation;

import java.util.List;

public interface ConsigneeInformationService {
    /**
     * 添加收货信息
     * @param consigneeName 收货人姓名
     * @param consigneeAddress 收货人地址
     * @param consigneePhoneNumber 收货人手机号
     * @return
     */
    ConsigneeInformation addConsigneeInformation(String consigneeName, String consigneeAddress, String consigneePhoneNumber);

    /**
     * 添加收货信息
     * @param consigneeInformation 收货信息
     * @return
     */
    ConsigneeInformation addConsigneeInformation(ConsigneeInformation consigneeInformation);

    /**
     * 通过收货信息id查询
     * @param consigneeInformationId
     * @return
     */
    ConsigneeInformation findConsigneeInformation(Long consigneeInformationId);

    /**
     * 删除收货信息
     * @param consigneeInformationId 收货信息ID
     */
    void deleteConsigneeInformation(Long consigneeInformationId);

    /**
     * 获取可见的收货信息
     * @return
     */
    List<ConsigneeInformation> getVisibleConsigneeInformations();

    /**
     * 更新收货地址
     * 将原来的收货地址置为不可见
     * 返回一个新的收货地址
     * @param consigneeInformation
     * @return
     */
    ConsigneeInformation updateConsigneeInformation(ConsigneeInformation consigneeInformation);

}
