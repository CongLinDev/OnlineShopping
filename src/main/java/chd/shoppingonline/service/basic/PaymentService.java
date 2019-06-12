package chd.shoppingonline.service.basic;
/*
 * @ClassName PayService
 * @Author 从林
 * @Date 2019-04-20 21:30
 * @Description 支付服务接口
 */

import chd.shoppingonline.entity.RecordDetail;

import java.util.List;

public interface PaymentService {

    /**
     * 支付
     * @param sourceUserId 支付人ID
     * @param destinationUserId 收款人ID
     * @param amount 交易金额
     */
    void pay(Long sourceUserId, Long destinationUserId, Double amount);

    /**
     * 计算单个商品价格
     * @param commodityId 商品ID
     * @param number 商品数量
     * @return
     */
    Double countPrice(Long commodityId, Integer number);

    /**
     * 通过订单id查询订单的金额
     * 请确保订单已经存入数据库
     * @param recordDetailId
     * @return
     */
    Double countPrice(Long recordDetailId);

    /**
     * 通过订单细节计算订单的金额
     * @param recordDetails
     * @return
     */
    Double countPrice(List<RecordDetail> recordDetails);

    /**
     * 支付订单
     * @param recordId
     * @return
     */
    void payRecord(Long recordId);
}
