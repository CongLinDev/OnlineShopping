package chd.shoppingonline.controller;
/*
 * @ClassName BuyerOrderController
 * @Author 从林
 * @Date 2019-06-12 10:56
 * @Description
 */

import chd.shoppingonline.common.state.RecordDetailState;
import chd.shoppingonline.entity.*;
import chd.shoppingonline.service.basic.*;
import chd.shoppingonline.service.feature.BuyerService;
import chd.shoppingonline.service.feature.ShoppingTrolleyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;




@RestController
public class BuyerOrderController {
    @Autowired
    ConsigneeInformationService consigneeInformationService;
    @Autowired
    UserService userService;
    @Autowired
    private RecordDetailService recordDetailService;
    @Autowired
    private BuyerService buyerService;
    @Autowired
    private RecordService recordService;
    @Autowired
    private CommodityService commodityService;
    @Autowired
    private ShoppingTrolleyService shoppingTrolleyService;

    static class Order {
        private Long consigneeInformationId;
        private List<RecordDetail> recordDetails;

        public Order() {
        }

        public Long getConsigneeInformationId() {
            return consigneeInformationId;
        }

        public void setConsigneeInformationId(Long consigneeInformationId) {
            this.consigneeInformationId = consigneeInformationId;
        }

        public List<RecordDetail> getRecordDetails() {
            return recordDetails;
        }

        public void setRecordDetails(List<RecordDetail> recordDetails) {
            this.recordDetails = recordDetails;
        }
    }

    //下单和支付
    @RequestMapping("/buyer/order/add")
    public ReturnEntity<Record> makeOrder(@RequestBody @Valid Order order){
        Record record = recordService.addRecord(
               order.getConsigneeInformationId(),
                order.getRecordDetails());
        buyerService.payRecord(record.getRecordId());

        /*
        //移出购物车
        for(RecordDetail rd: order.getRecordDetails()){
            shoppingTrolleyService.remove(record.getBuyerId(),rd.getCommodityId());
        }*/
        return ReturnEntity.<Record>builder().code(true).content(record).build();
    }

    @RequestMapping("buyer/order/all")
    public ReturnEntity<List<RecordDetail>> getAllOrderItems(){
        List<RecordDetail> orderItems = recordService.findRecordDetailsByBuyerId(
                userService.findUser().getUserId());
        return ReturnEntity.<List<RecordDetail>>builder().code(true).content(orderItems).build();
    }


    static class OrderItemOfUserView{


        public OrderItemOfUserView() {
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        private String address;//地址信息
        private LocalDateTime createTime;
        private Integer tradingVolume;//交易量
        private String expressId;//快递单号

        public LocalDateTime getCreateTime() {
            return createTime;
        }

        public void setCreateTime(LocalDateTime createTime) {
            this.createTime = createTime;
        }

        public Integer getTradingVolume() {
            return tradingVolume;
        }

        public void setTradingVolume(Integer tradingVolume) {
            this.tradingVolume = tradingVolume;
        }

        public String getExpressId() {
            return expressId;
        }

        public void setExpressId(String expressId) {
            this.expressId = expressId;
        }

        public String getRecordDetailState() {
            return recordDetailState;
        }

        public void setRecordDetailState(String recordDetailState) {
            this.recordDetailState = recordDetailState;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        private String recordDetailState;//状态
        private String commodityName;
        private Double price;

        public Long getRecordDetailId() {
            return recordDetailId;
        }

        public void setRecordDetailId(Long recordDetailId) {
            this.recordDetailId = recordDetailId;
        }

        private Long recordDetailId;

    }

    private List<OrderItemOfUserView> packageInfo(List<RecordDetail> orderItems){
        List<OrderItemOfUserView> items = new ArrayList<OrderItemOfUserView>();
        //查询并填入商品信息
        //转化并填入地址信息
        //转换并填入状态信息
        //填入其他信息
        for(RecordDetail orderItem: orderItems){
            OrderItemOfUserView item = new OrderItemOfUserView();
            Commodity commodity = commodityService.findCommodity(orderItem.getCommodityId());
            if(commodity == null) System.out.println("Oooops");
            item.setCommodityName(commodity.getCommodityName());
            item.setPrice(commodity.getPrice());

            Record record = recordService.findRecord(orderItem.getRecordId());
            ConsigneeInformation consigneeInformation =
                    consigneeInformationService.findConsigneeInformation(record.getConsigneeInformationId());
            item.setAddress(consigneeInformation.getConsigneeName()+","+
                    consigneeInformation.getConsigneeAddress()+","+consigneeInformation.getConsigneePhoneNumber());

            String orderState = RecordDetailState.getStringValue(orderItem.getRecordDetailState());
            item.setRecordDetailState(orderState);
            item.setCreateTime(orderItem.getCreateTime());
            item.setExpressId(orderItem.getExpressId());
            item.setTradingVolume(orderItem.getTradingVolume());
            item.setRecordDetailId(orderItem.getRecordDetailId());

            items.add(item);
        }

        return items;
    }

    //获取正在进行的订单
    @RequestMapping("/buyer/order/unreceived")
    public ReturnEntity<List<OrderItemOfUserView>> getOrderItemsUndelivered(){
        List<RecordDetail> orderItems = recordService.findUndeliverRecordDetailsByBuyerId(
                userService.findUser().getUserId());
        if (orderItems == null) System.out.println("Ooops!");
        return ReturnEntity.<List<OrderItemOfUserView>>builder().code(true).content(packageInfo(orderItems)).build();
    }

    //获取已收货的订单
    @RequestMapping("/buyer/order/received")
    public ReturnEntity<List<OrderItemOfUserView>> getOrderItemDelivered(){
        List<RecordDetail> recordDetails =
                recordService.findRecordDetailsByBuyerIdAndState(userService.findUser().getUserId(),RecordDetailState.DELIVERED.getShortValue());
        if (recordDetails == null) System.out.println("Ooops");
        return ReturnEntity.<List<OrderItemOfUserView>>builder().code(true).content(packageInfo(recordDetails)).build();
    }

    //发布评论
    @RequestMapping("/buyer/order/comment")
    public void makeComment(@RequestBody @Valid RecordDetail recordDetail){
        buyerService.comment(recordDetail.getRecordDetailId(), recordDetail.getComment());
    }


    //请求退货
    @RequestMapping("/buyer/order/return")
    public void returnGoods(@RequestBody @Valid RecordDetail recordDetail){
        buyerService.requestReturn(recordDetail.getRecordDetailId());
    }

    //确认收货
    @RequestMapping("/buyer/order/receive")
    public void receiveGoods(@RequestBody @Valid RecordDetail recordDetail){
        buyerService.deliver(recordDetail.getRecordDetailId());
    }
}
