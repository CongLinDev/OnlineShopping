package chd.shoppingonline.controller;
/*
 * @ClassName CommodityDisplayController
 * @Author 从林
 * @Date 2019-06-09 17:00
 * @Description
 */

import chd.shoppingonline.common.state.RecordDetailState;
import chd.shoppingonline.entity.Commodity;
import chd.shoppingonline.entity.RecordDetail;
import chd.shoppingonline.entity.ReturnEntity;
import chd.shoppingonline.service.basic.CommodityService;
import chd.shoppingonline.service.basic.RecordDetailService;
import chd.shoppingonline.service.basic.RecordService;
import chd.shoppingonline.service.basic.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/commodity")
public class CommodityDisplayController {
    @Autowired
    private CommodityService commodityService;
    @Autowired
    private UserService userService;
    @Autowired
    private RecordDetailService recordDetailService;
    @Autowired
    private RecordService recordService;

    private Pair<String, Boolean> convertOrderType(String orderType){
        switch (orderType){
//            case "综合":
//                return Pair.of("commodityId",true);
            //case "销量":
            // new Pair<>("",true);
            case "价格从低到高":
                return Pair.of("price",true);
            case "价格从高到低":
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
        List<Commodity> commodities = commodityService.findCommodity((String)map.get("search"),
                pair.getSecond(),
                pair.getFirst(),
                (Integer)map.get("page"),
                (Integer)map.get("max"));

        return ReturnEntity.<List<Commodity>>builder().code(true).content(commodities).build();
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

    @RequestMapping("/id")
    public ReturnEntity<Commodity> searchById(@RequestBody Map<String, Long> map) {
        Commodity commodity = commodityService.findCommodity(map.get("commodityId"));
        return ReturnEntity.<Commodity>builder().code(true).content(commodity).build();
    }

    /*
    @RequestMapping("/details")
    public ReturnEntity<List<RecordDetail>> searchRecordDetialsByCommodityId(@RequestBody Map<String, Long> map){
        List<RecordDetail> recordDetails = recordDetailService.findRecordDetailByCommodityId(map.get("commodityId"));
        return ReturnEntity.<List<RecordDetail>>builder().code(true).content(recordDetails).build();
    }
    */

    static class Comment{
        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreateTime() {
            return commentDate;
        }

        public void setCreateTime(String createTime) {
            this.commentDate = createTime;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        private String commentDate;
        private String userId;

        public Comment() {
        }
    }


    //字符串（评价时间，用户号），评论内容
    @RequestMapping("/comments")
    public ReturnEntity<List<RecordDetail>> searchCommentsByCommodityId(@RequestBody Map<String, Object> map){

        //if(commodityId == null) throw new IllegalParamExcpetion();
        System.out.println(map.get("commodityId"));
        List<RecordDetail> recordDetails = recordDetailService.findRecordDetailByCommodityIdAndState(
               ((Integer)map.get("commodityId")).longValue(),
                RecordDetailState.DELIVERED.getShortValue(),
                (Integer)map.get("page"),
                (Integer)map.get("max")).getContent();
        /*
        List<Comment> comments = new ArrayList<>();
        for(RecordDetail rd : recordDetails){
            Comment comment = new Comment();
            comment.setCreateTime(rd.getCommentDate().toString());
            //System.out.println("date:"+rd.getCommentDate());
            comment.setContent(rd.getComment());
            //System.out.println("comment"+rd.getComment());
            comment.setUserId(
                    recordService.findRecord(rd.getRecordId()).getBuyerId().toString()
            );
            comments.add(comment);
        }
        return ReturnEntity.<List<Comment>>builder().code(true).content(comments).build();
        */
        return ReturnEntity.<List<RecordDetail>>builder().code(true).content(recordDetails).build();

    }
}
