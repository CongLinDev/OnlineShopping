package chd.shoppingonline.controller;
/*
 * @ClassName UserController
 * @Author 从林
 * @Date 2019-04-01 19:32
 * @Description 用户Controller
 */

import chd.shoppingonline.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {


    @PostMapping(path="/greeting")
    public String user(@RequestBody User user){
        System.out.println(user.toString());
        return "done";
    }
}
