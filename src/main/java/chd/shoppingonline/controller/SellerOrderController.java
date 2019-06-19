package chd.shoppingonline.controller;
/*
 * @ClassName SellerOrderController
 * @Author 从林
 * @Date 2019-06-11 19:00
 * @Description
 */

import chd.shoppingonline.common.state.RecordDetailState;
import chd.shoppingonline.entity.*;
import chd.shoppingonline.service.basic.*;
import chd.shoppingonline.service.feature.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class SellerOrderController {
    @Autowired
    private RecordDetailService recordDetailService;
    @Autowired
    private CommodityService commodityService;
    @Autowired
    private UserService userService;
    @Autowired
    private RecordService recordService;
    @Autowired
    private ConsigneeInformationService consigneeInformationService;
    @Autowired
    private BuyerService buyerService;

/*
    @RequestMapping("/seller/order/all")
    public ReturnEntity<List<RecordDetail>> getOrderItems(){
        List<RecordDetail> orderItems = recordService.findRecordDetailsBySellerId(
                userService.findUser().getUserId());
        return ReturnEntity.<List<RecordDetail>>builder().code(true).content(orderItems).build();
    }

    static class Address{
        public Address() {
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        String address;
    }

    @RequestMapping("/seller/order/address")
    public ReturnEntity<Address> getAddressInfoById(@RequestBody Map<String, Long> map){
        Record record = recordService.findRecord(map.get("recordId"));
        ConsigneeInformation consigneeInformation =
                consigneeInformationService.findConsigneeInformation(record.getConsigneeInformationId());
        Address address = new Address();
        address.setAddress(consigneeInformation.getConsigneeName()+","+
            consigneeInformation.getConsigneeAddress()+","+consigneeInformation.getConsigneePhoneNumber());
        return ReturnEntity.<Address>builder().code(true).content(address).build();
    }

*/
    /*

    @RequestMapping("/seller/order/undelivered")
    public ReturnEntity<List<RecordDetail>> getOrderItemsUndelivered(){
        List<RecordDetail> orderItems = commodityService.findRecordDetailByUserID(
                userService.findUser().getUserId(),RecordDetailState.PREPARE_SHIPMENT.getShortValue());
        return ReturnEntity.<List<RecordDetail>>builder().code(true).content(orderItems).build();
    }

    @RequestMapping("/seller/order/delivered")
    public ReturnEntity<List<RecordDetail>> getOrderItemsDelivered(){
        List<RecordDetail> orderItems = commodityService.findRecordDetailByUserID(
                userService.findUser().getUserId(),RecordDetailState.SHIPMENT.getShortValue());
        return ReturnEntity.<List<RecordDetail>>builder().code(true).content(orderItems).build();
    }


    @RequestMapping("/seller/order/received")
    public ReturnEntity<List<RecordDetail>> getOrderItemsReceived(){
        List<RecordDetail> orderItems = commodityService.findRecordDetailByUserID(
                userService.findUser().getUserId(),RecordDetailState.DELIVERED.getShortValue());
        return ReturnEntity.<List<RecordDetail>>builder().code(true).content(orderItems).build();
    }

    @RequestMapping("/seller/order/returned")
    public ReturnEntity<List<RecordDetail>> getOrderItemsReturned(){
        List<RecordDetail> orderItems = commodityService.findRecordDetailByUserID(
                userService.findUser().getUserId(),RecordDetailState.RETURNED.getShortValue());
        return ReturnEntity.<List<RecordDetail>>builder().code(true).content(orderItems).build();
    }

*/

    static class OrderItemOfSellerView{


        public OrderItemOfSellerView() {
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

    @RequestMapping("/seller/order/all")
    public ReturnEntity<List<OrderItemOfSellerView>> getOrderItems(){
        List<RecordDetail> orderItems = recordService.findRecordDetailsBySellerId(
                userService.findUser().getUserId());
        List<OrderItemOfSellerView> items = new ArrayList<OrderItemOfSellerView>();
        //查询并填入商品信息
        //转化并填入地址信息
        //转换并填入状态信息
        //填入其他信息
        for(RecordDetail orderItem: orderItems){
            OrderItemOfSellerView item = new OrderItemOfSellerView();
            Commodity commodity = commodityService.findCommodity(orderItem.getCommodityId());
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

        return ReturnEntity.<List<OrderItemOfSellerView>>builder().code(true).content(items).build();
    }


    @RequestMapping("/seller/order/deliver")
    public void setOrderItemDelivered(@RequestBody @Valid RecordDetail recordDetail){
        recordDetailService.updateRecordDetailExpressId(recordDetail.getRecordDetailId(),recordDetail.getExpressId());
        recordDetailService.updateRecordDetailState(recordDetail.getRecordDetailId(), RecordDetailState.PREPARE_SHIPMENT.getShortValue(),RecordDetailState.SHIPMENT.getShortValue());
    }

    @RequestMapping("/sell/order/return")
    public void acceptReturnRequest(@RequestBody Map<String, Long> map){
        buyerService.permitReturn(map.get("recordDetailId"));
    }

    @RequestMapping("/sell/order/returned/refuse")
    public void refuseReturnRequest(@RequestBody Map<String, Long> map){
        buyerService.rejectReturn(map.get("recordDetailId"));
    }
}