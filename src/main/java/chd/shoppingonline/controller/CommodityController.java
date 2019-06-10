package chd.shoppingonline.controller;
/*
 * @ClassName CommodityController
 * @Author 从林
 * @Date 2019-06-09 17:00
 * @Description
 */

import chd.shoppingonline.entity.Commodity;
import chd.shoppingonline.entity.RecordDetail;
import chd.shoppingonline.entity.ReturnEntity;
import chd.shoppingonline.service.basic.CommodityService;
import chd.shoppingonline.service.basic.RecordDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/commodity")
public class CommodityController {
    @Autowired
    private CommodityService commodityService;

    @Autowired
    private RecordDetailService recordDetailService;

    private Pair<String, Boolean> convertOrderType(String orderType){
        switch (orderType){
//            case "综合":
//                return Pair.of("commodityId",true);
            //case "销量":
            // new Pair<>("",true);
            case "价格":
                return Pair.of("price",true);
            case "价格2":
                return Pair.of("price",false);
            default:
                return Pair.of("commodityId",true);
        }
    }

    @RequestMapping("/search")
    /*
    public ReturnEntity<List<Commodity>> search(@RequestBody QureyRequest query){
        Pair<String, Boolean> pair = convertOrderType(query.getOrderType());
        List<Commodity> comodities = commodityService.findCommodity(query.getSearch(),
                pair.getSecond(),
                pair.getFirst(),
                query.getPage(),
                query.getMax());
        return ReturnEntity.<List<Commodity>>builder().code(true).content(comodities).build();
    }*/
    public ReturnEntity<List<Commodity>> search(@RequestBody Map<String, Object> map){
        Pair<String, Boolean> pair = convertOrderType((String)map.get("orderType"));
        List<Commodity> comodities = commodityService.findCommodity((String)map.get("search"),
                pair.getSecond(),
                pair.getFirst(),
                (Integer)map.get("page"),
                (Integer)map.get("max"));

        return ReturnEntity.<List<Commodity>>builder().code(true).content(comodities).build();
    }

    @RequestMapping("/class/search")
    public ReturnEntity<List<Commodity>> searchForClass(@RequestBody Map<String, Object> map){
        Pair<String, Boolean> pair = convertOrderType((String)map.get("orderType"));
        List<Commodity> comodities = commodityService.findCommodity((String)map.get("search"),
                (String)map.get("className"),
                pair.getSecond(),
                pair.getFirst(),
                (Integer)map.get("page"),
                (Integer)map.get("max"));
        return ReturnEntity.<List<Commodity>>builder().code(true).content(comodities).build();
    }

    @RequestMapping("/class/all")
    public ReturnEntity<List<Commodity>> searchAll(@RequestBody Map<String, Object> map){
        Pair<String, Boolean> pair = convertOrderType((String)map.get("orderType"));
        List<Commodity> comodities = commodityService.findAllCommodities((String)map.get("className"),
                pair.getSecond(),
                pair.getFirst(),
                (Integer)map.get("page"),
                (Integer)map.get("max"));
        return ReturnEntity.<List<Commodity>>builder().code(true).content(comodities).build();
    }

    @RequestMapping("/details")
    public ReturnEntity<List<RecordDetail>> getRecordDetailsOf(@RequestBody Long commodityId){
        List<RecordDetail> recordDetails = recordDetailService.findRecordDetailsByCommodityId(commodityId);
        return ReturnEntity.<List<RecordDetail>>builder().code(true).content(recordDetails).build();
    }
}
