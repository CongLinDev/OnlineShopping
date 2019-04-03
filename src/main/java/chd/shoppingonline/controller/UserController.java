package chd.shoppingonline.controller;
/*
 * @ClassName UserController
 * @Author 从林
 * @Date 2019-04-01 19:32
 * @Description 用户Controller
 */

import chd.shoppingonline.entity.User;
import chd.shoppingonline.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(path="/user")
    @ResponseBody
    public User user(){
        return userService.findUser();
    }
}
