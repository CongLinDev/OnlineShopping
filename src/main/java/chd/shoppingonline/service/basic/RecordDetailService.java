package chd.shoppingonline.service.basic;
/*
 * @ClassName RecordDetailService
 * @Author 从林
 * @Date 2019-04-20 21:40
 * @Description 订单细节服务接口
 */

import chd.shoppingonline.entity.RecordDetail;
import org.springframework.data.domain.Page;

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
     * 查询某个状态的订单细节
     * @param commodityId
     * @param state
     * @return
     */
    List<RecordDetail> findRecordDetailsByCommodityIdAndState(Long commodityId, Short state);

    /**
     * 通过商品id查询细节
     * @param commodityId
     * @param state 状态
     * @param page 页号
     * @param max 页内最大数
     * @return
     */
    Page<RecordDetail> findRecordDetailByCommodityIdAndState(Long commodityId, Short state, int page, int max);


    /**
     * 通过订单id查找订单细节
     * @param recordId
     * @return
     */
    List<RecordDetail> findRecordDetailByRecordId(Long recordId);


    /**
     * 更新快递单号
     * @param recordDetailId
     * @param expressId
     */
    void updateRecordDetailExpressId(Long recordDetailId, String expressId);

    /**
     * 更新订单状态
     * @param recordDetailId
     * @param currentState 当前状态
     * @param expectState 预期状态
     */
    void updateRecordDetailState(Long recordDetailId, Short currentState, Short expectState);

    /**
     * 更新订单评论
     * @param recordDetailId
     * @param comment
     */
    void updateRecordDetailComment(Long recordDetailId, String comment);

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
