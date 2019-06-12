package chd.shoppingonline.service.basic;
/*
 * @ClassName RecordDetailService
 * @Author 从林
 * @Date 2019-04-20 21:40
 * @Description 订单细节服务接口
 */

import chd.shoppingonline.entity.RecordDetail;

import java.util.List;

public interface RecordDetailService {

    /**
     * 添加交易记录细节
     * @param recordDetail
     * @return
     */
    RecordDetail addRecordDetail(RecordDetail recordDetail);

    /**
     * 查找交易记录细节
     * @param recordDetailId 交易记录细节ID
     * @return
     */
    RecordDetail findRecordDetail(Long recordDetailId);

    /**
     * 通过商品id查询细节
     * @param commodityId
     * @return
     */
    List<RecordDetail> findRecordDetailByCommodityId(Long commodityId);

    /**
     * 查询某个商品下的所有评论
     * @param commodityId
     * @return
     */
    List<RecordDetail> findCommentsByCommodityId(Long commodityId);

    /**
     * 通过商品id查看指定状态订单细节
     * @param commodityId
     * @param state
     * @return
     */
    List<RecordDetail> findRecordDetailsByCommodityIdAndState(Long commodityId, Short state);



    /**
     * 通过订单id查找订单细节
     * @param recordId
     * @return
     */
    List<RecordDetail> findRecordDetailByRecordId(Long recordId);





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
     * @param star
     * @param comment
     */
    void deliver(Long recordDetailId, Short star, String comment);

    /**
     * 更新为 买家退货 状态
     * @param recordDetailId
     */
    void returned(Long recordDetailId);

    /**
     * 统计交易量
     * @param commodityId
     * @return
     */
    Integer countTradingVolume (Long commodityId);


    /**
     * 计算商品交易量
     * @param recordDetails
     * @return
     */
    Integer countTradingVolume(List<RecordDetail> recordDetails);
}
