package chd.shoppingonline.controller;
/*
 * @ClassName AccountController
 * @Author 从林
 * @Date 2019-04-01 19:29
 * @Description 账户Controller
 */

import chd.shoppingonline.entity.User;
import chd.shoppingonline.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class AccountController {
    @Autowired
    UserService userService;

    /********************************注册*********************************************/
    @PostMapping(path="/account/register")
    @ResponseBody
    public User register(@RequestParam("username")String username,
                         @RequestParam("password")String password) {
        log.info("用户"+ username +"请求注册");

        return userService.addUser(User.builder()
                .username(username)
                .password(password)
                .roles("ROLE_USER")
                .balance(Double.valueOf(0.0))
                .build());
    }

    @PostMapping(path="/account/register_json", produces = "application/json;charset=utf-8")
    @ResponseBody
    public User register(@RequestBody User user) {
        log.info("用户"+ user.getUsername() +"请求注册");

        return userService.addUser(User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles("ROLE_USER")
                .balance(Double.valueOf(0.0))
                .build());
    }
}
