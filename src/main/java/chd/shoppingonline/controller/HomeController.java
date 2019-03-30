package chd.shoppingonline.controller;
/*
 * @ClassName HomeController
 * @Author 从林
 * @Date 2019-03-13 22:01
 * @Description 索引Controller
 */

import chd.shoppingonline.entity.User;
import chd.shoppingonline.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@Slf4j
public class HomeController {
    @Autowired
    UserService userService;

    @GetMapping(path={"/index", "/", ""})
    public String index() {
        return "index";
    }

    //@PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping(path="account/register")
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

    @PostMapping(path="account/register_json", produces = "application/json;charset=utf-8")
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
    @PostMapping(path="account/login_json", produces = "application/json;charset=utf-8")
    @ResponseBody
    public User login(@RequestBody User user) {
        log.info("用户"+ user.getUsername() +"请求登陆");
        return user;
    }
}