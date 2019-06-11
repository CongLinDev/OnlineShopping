package chd.shoppingonline.controller;
/*
 * @ClassName SellerController
 * @Author 从林
 * @Date 2019-06-11 19:00
 * @Description
 */

import chd.shoppingonline.entity.Commodity;
import chd.shoppingonline.entity.ReturnEntity;
import chd.shoppingonline.service.basic.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SellerController {

    @Autowired
    private CommodityService commodityService;

    @RequestMapping("/seller/add")
    public ReturnEntity<Commodity> addCommodity(@RequestBody Commodity commodity){
        commodity = commodityService.addCommodity(commodity);
        return ReturnEntity.<Commodity>builder().code(true).content(commodity).build();
    }
}
