package chd.shoppingonline.controller;

import chd.shoppingonline.common.state.RecordDetailState;
import chd.shoppingonline.entity.Commodity;
import chd.shoppingonline.entity.RecordDetail;
import chd.shoppingonline.entity.ReturnEntity;
import chd.shoppingonline.service.basic.CommodityService;
import chd.shoppingonline.service.basic.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SellerCommodityController {
    @Autowired
    private UserService userService;
    @Autowired
    private CommodityService commodityService;

    @RequestMapping("/seller/commodity/add")
    public ReturnEntity<Commodity> addCommodity(@RequestBody Commodity commodity){

        commodity = commodityService.addCommodity(commodity);
        return ReturnEntity.<Commodity>builder().code(true).content(commodity).build();
    }

    @RequestMapping("/seller/commodity/delete")
    public ReturnEntity<Commodity> deleteCommodity(@RequestBody Commodity commodity){
        commodityService.deleteCommodity(commodity.getCommodityId(), userService.findUser().getUserId());
        return ReturnEntity.<Commodity>builder().code(true).content(commodity).build();
    }

    @RequestMapping("/seller/commodity/get")
    public ReturnEntity<List<Commodity>> getCommodity(){
        List<Commodity> commodities = commodityService.findCommodityByUserID(userService.findUser().getUserId());
        return ReturnEntity.<List<Commodity>>builder().code(true).content(commodities).build();
    }


    @RequestMapping("/seller/commodity/update")
    public ReturnEntity<Commodity> updateCommodity(@RequestBody Commodity commodity){
        //commodityService.deleteCommodity(commodity.getCommodityId());
        //commodity = commodityService.addCommodity(commodity);
        commodityService.updateCommodity(commodity);
        return ReturnEntity.<Commodity>builder().code(true).content(commodity).build();
    }

}
