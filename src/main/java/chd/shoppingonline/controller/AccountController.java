package chd.shoppingonline.controller;
/*
 * @ClassName AccountController
 * @Author 从林
 * @Date 2019-06-10 15:11
 * @Description
 */

import chd.shoppingonline.common.role.UserRole;
import chd.shoppingonline.entity.ReturnEntity;
import chd.shoppingonline.entity.User;
import chd.shoppingonline.service.basic.CommodityService;
import chd.shoppingonline.service.basic.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private CommodityService commodityService;


    @RequestMapping("/registry/buyer")
    ReturnEntity<User> registryBuyer(@RequestBody  User user) {
        User newUser = User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .enabled(true)
                .balance(0.0)
                .roles(UserRole.ROLE_BUYER.getShortValue())
                .userId(null).build();
        try {
            newUser =  userService.addUser(newUser);//注册，可能会抛异常
        }catch(RuntimeException e){//最后细化异常
            throw new RuntimeException(e);
        }
        return ReturnEntity.<User>builder().code(true).content(newUser).build();
    }

    @RequestMapping("/registry/seller")
    ReturnEntity<User> registrySeller(@RequestBody User user) {
        User newUser = User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .enabled(true)
                .balance(0.0)
                .roles(UserRole.ROLE_SELLER.getShortValue())
                .userId(null).build();
        try {
            newUser =  userService.addUser(newUser);//注册，可能会抛异常
        }catch(RuntimeException  e){//最后细化异常
            throw new RuntimeException(e);
        }
        return ReturnEntity.<User>builder().code(true).content(newUser).build();
    }

    @RequestMapping({"/index","/account"})
    public ReturnEntity<User> account(){
        User user = userService.findUser(userService.findUser().getUserId());
        return ReturnEntity.<User>builder().code(true).content(userService.findUser()).build();
    }

    @RequestMapping("/account/amount/add")
    public Double addBalance(@RequestBody Map<String, Double> map){
        User user = userService.findUser();
        Double amount = map.get("amount");
        userService.recharge(user.getUserId() ,amount);
        user.setBalance(user.getBalance() + amount);
        return user.getBalance();
    }
}
