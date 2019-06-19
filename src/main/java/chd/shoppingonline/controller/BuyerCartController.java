package chd.shoppingonline.controller;

import chd.shoppingonline.entity.Commodity;
import chd.shoppingonline.entity.ReturnEntity;
import chd.shoppingonline.entity.ShoppingTrolley;
import chd.shoppingonline.service.basic.CommodityService;
import chd.shoppingonline.service.basic.UserService;
import chd.shoppingonline.service.feature.ShoppingTrolleyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/buyer")
public class BuyerCartController {
    @Autowired
    ShoppingTrolleyService shoppingTrolleyService;
    @Autowired
    UserService userService;
    @Autowired
    CommodityService commodityService;

//    @RequestMapping({"/cart/add","/cart/update"})
//    public void addToCart(@RequestBody Map<String, Object> map) {
//        shoppingTrolleyService.add(userService.findUser().getUserId(),(Long)map.get("commodityId")),(Integer)map.get("number")));
//    }

//    @RequestMapping({"/cart/delete"})
//    public void removeFromCart(@RequestBody Map<String, String> map) {
//        shoppingTrolleyService.remove(userService.findUser().getUserId(),Long.valueOf(map.get("commodityId")));
//    }


    @RequestMapping({"/cart/add","/cart/update"})
    public void addToCart(@RequestBody @Valid ShoppingTrolley shoppingTrolley) {
        //System.out.println(shoppingTrolley);
        shoppingTrolleyService.add(userService.findUser().getUserId(),shoppingTrolley);
    }

    @RequestMapping({"/cart/delete"})
    public void removeFromCart(@RequestBody Map<String, Long> map) {
        //System.out.println(map.get("commodityId"));
        shoppingTrolleyService.remove(userService.findUser().getUserId(),map.get("commodityId"));
    }

    static class Cart{
        int num;

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getCommodityname() {
            return commodityname;
        }

        public void setCommodityname(String commodityname) {
            this.commodityname = commodityname;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public long getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(long commodityId) {
            this.commodityId = commodityId;
        }

        String commodityname;
        double price;
        long commodityId;

        public Cart() {
        }
    }
    //商品名称，商品价格，商品数量
    @RequestMapping("/cart/all")
    public ReturnEntity<List<Cart>> getAllOfCart(){
        List<ShoppingTrolley> shoppingTrolleys = shoppingTrolleyService.getAll(userService.findUser().getUserId());
        List<Cart> carts = new ArrayList<>();
        for(ShoppingTrolley st : shoppingTrolleys){
            Cart cart = new Cart();
            cart.setCommodityId(st.getCommodityId());
            Commodity commodity = commodityService.findCommodity(st.getCommodityId());
            cart.setCommodityname(commodity.getCommodityName());
            cart.setPrice(commodity.getPrice());
            cart.setNum(st.getNumber());
            carts.add(cart);
        }
        return ReturnEntity.<List<Cart>>builder().code(true).content(carts).build();
    }
}
