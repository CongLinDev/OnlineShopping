package chd.shoppingonline.service.basic;
/*
 * @ClassName PayService
 * @Author 从林
 * @Date 2019-04-20 21:30
 * @Description 支付服务接口
 */


public interface PaymentService {

    /**
     * 支付
     * @param sourceUserId 支付人ID
     * @param destinationUserId 收款人ID
     * @param amount 交易金额
     * @return
     */
    Boolean pay(Long sourceUserId, Long destinationUserId, Double amount);

    /**
     * 计算商品价格
     * @param commodityId 商品ID
     * @param number 商品数量
     * @return
     */
    Double countPrice(Long commodityId, Integer number);
}
