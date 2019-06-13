package chd.shoppingonline.controller;

import chd.shoppingonline.entity.ConsigneeInformation;
import chd.shoppingonline.entity.ReturnEntity;
import chd.shoppingonline.service.basic.ConsigneeInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BuyerAddressController {
    @Autowired
    ConsigneeInformationService consigneeInformationService;

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
}
