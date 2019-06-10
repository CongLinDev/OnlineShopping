package chd.shoppingonline.controller;
/*
 * @ClassName RegistryController
 * @Author 从林
 * @Date 2019-06-10 15:11
 * @Description
 */

import chd.shoppingonline.common.role.UserRole;
import chd.shoppingonline.entity.ReturnEntity;
import chd.shoppingonline.entity.User;
import chd.shoppingonline.service.basic.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistryController {

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/registry/user")
    ReturnEntity<User> registryBuyer(@RequestBody  User user) {
    /*
    ReturnEntity<User> registry(@RequestBody Map<String, Object> map){
    //String username,String password, String role
     */
        //User newUser = null;
        User newUser = User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .enabled(true)
                .balance(0.0)
                .roles(UserRole.ROLE_BUYER.getShortValue())
                .userId(null).build();
        //user.toBuilder().enabled(true).balance(0.0).roles(UserRole.ROLE_BUYER.getShortValue()).userId(null).build();
        try {
            newUser =  userService.addUser(newUser);//注册，可能会抛异常
        }catch(RuntimeException e){//最后细化异常
            throw new RuntimeException(e);
        }
        return ReturnEntity.<User>builder().code(true).content(newUser).build();
    }

    @RequestMapping("/registry/seller")
    ReturnEntity<User> registrySeller(@RequestBody User user) {
    /*
    ReturnEntity<User> registry(@RequestBody Map<String, Object> map){
    //String username,String password, String role
     */
        //User newUser = null;
        User newUser = User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .enabled(true)
                .balance(0.0)
                .roles(UserRole.ROLE_SELLER.getShortValue())
                .userId(null).build();
        try {
            newUser =  userService.addUser(newUser);//注册，可能会抛异常
        }catch(RuntimeException e){//最后细化异常
            throw new RuntimeException(e);
        }
        return ReturnEntity.<User>builder().code(true).content(newUser).build();
    }

}
