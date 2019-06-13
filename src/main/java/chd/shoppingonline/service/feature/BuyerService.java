package chd.shoppingonline.service.feature;
/*
 * @ClassName BuyerService
 * @Author 从林
 * @Date 2019-06-13 8:48
 * @Description
 */

public interface BuyerService {
    ///////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////
    /// 以下为订单细节状态改变函数

    /**
     * 更新为 已支付 状态
     * @param recordDetailId
     */
    void paid(Long recordDetailId);

    /**
     * 更新为 订单发货 状态
     * @param recordDetailId 订单细节id
     * @param expressId 快递单号
     */
    void shipment(Long recordDetailId, String expressId);

    /**
     * 更新为 买家收货 状态
     * 买家收货并作出评论和等级评定
     * @param recordDetailId
     */
    void deliver(Long recordDetailId);

    /**
     * 请求退货
     * @param recordDetailId
     */
    void requestReturn(Long recordDetailId);

    /**
     * 允许退货
     * @param recordDetailId
     */
    void permitReturn(Long recordDetailId);

    /**
     * 拒绝收货
     * @param recordDetailId
     */
    void rejectReturn(Long recordDetailId);

    /**
     * 评论
     * @param recordDetailId
     * @param comment
     */
    void comment(Long recordDetailId, String comment);

    /**
     * 支付订单
     * @param recordId
     * @return
     */
    void payRecord(Long recordId);
}
