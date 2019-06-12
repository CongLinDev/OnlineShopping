package chd.shoppingonline.controller;
/*
 * @ClassName BuyerController
 * @Author 从林
 * @Date 2019-06-12 10:56
 * @Description
 */

import chd.shoppingonline.common.state.RecordDetailState;
import chd.shoppingonline.entity.*;
import chd.shoppingonline.service.basic.*;
import chd.shoppingonline.service.feature.ShoppingTrolleyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


class Order {
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

@RestController
public class BuyerController {
    @Autowired
    ConsigneeInformationService consigneeInformationService;
    //@Autowired
    //ShoppingTrolleyService shoppingTrolleyService;
    @Autowired
    UserService userService;
    @Autowired
    private RecordDetailService recordDetailService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private RecordService recordService;
    @Autowired
    private CommodityService commodityService;


    @RequestMapping("/buyer/address/get")
    public ReturnEntity<List<ConsigneeInformation>> getConsigneeInformation(){
        List<ConsigneeInformation> consigneeInformations =
                consigneeInformationService.getVisibleConsigneeInformations();
        return ReturnEntity.<List<ConsigneeInformation>>builder().code(true).content(consigneeInformations).build();
    }

    @RequestMapping("/buyer/address/add")
    public ReturnEntity<ConsigneeInformation> addConsigneeInformation(
            @RequestBody ConsigneeInformation consigneeInformation){
        consigneeInformation = consigneeInformationService.addConsigneeInformation(consigneeInformation);
        return ReturnEntity.<ConsigneeInformation>builder().code(true).content(consigneeInformation).build();
    }

    @RequestMapping("/buyer/address/delete")
    public ReturnEntity<String> deleteConsigneeInformation(@RequestBody ConsigneeInformation consigneeInformation){
        consigneeInformationService.deleteConsigneeInformation(consigneeInformation.getConsigneeInformationId());
        return ReturnEntity.<String>builder().code(true).build();
    }

    @RequestMapping("/buyer/address/update")
    public ReturnEntity<ConsigneeInformation> updateConsigneeInformation(
            @RequestBody ConsigneeInformation consigneeInformation){
        consigneeInformation = consigneeInformationService.updateConsigneeInformation(consigneeInformation);
        return ReturnEntity.<ConsigneeInformation>builder().code(true).content(consigneeInformation).build();
    }

    @RequestMapping("/buyer/order/add")
    public ReturnEntity<Record> makeOrder(@RequestBody Order order){
        Record record = recordService.addRecord(
               order.getConsigneeInformationId(),
                order.getRecordDetails());
        paymentService.payRecord(record.getRecordId());
        return ReturnEntity.<Record>builder().code(true).content(record).build();
    }

    @RequestMapping("buyer/order/all")
    public ReturnEntity<List<RecordDetail>> getAllOrderItems(){
        List<RecordDetail> orderItems = recordService.findRecordDetailsByBuyerId(
                userService.findUser().getUserId());
        return ReturnEntity.<List<RecordDetail>>builder().code(true).content(orderItems).build();
    }

    @RequestMapping("/buyer/order/return")
    public void returnGoods(@RequestBody RecordDetail recordDetail){
        recordDetailService.returned(recordDetail.getRecordDetailId());
    }

    @RequestMapping("/buyer/order/receive")
    public void receiveGoods(@RequestBody RecordDetail recordDetail){
        recordDetailService.deliver(recordDetail.getRecordDetailId(),recordDetail.getStar(),recordDetail.getComment());
    }

    //@RequestMapping("buyer/order/undelivered")
    public ReturnEntity<List<RecordDetail>> getOrderItemsUndelivered(){
        List<RecordDetail> orderItems = commodityService.findRecordDetailByUserID(
                userService.findUser().getUserId(), RecordDetailState.PREPARE_SHIPMENT.getShortValue());
        return ReturnEntity.<List<RecordDetail>>builder().code(true).content(orderItems).build();
    }

}
