package chd.shoppingonline.controller;
/*
 * @ClassName HomeController
 * @Author 从林
 * @Date 2019-03-13 22:01
 * @Description 索引Controller
 */

import chd.shoppingonline.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
public class HomeController {

    @GetMapping(path={"/index", "/", ""})
    public String index(HttpServletRequest request) {
        return request.getSession().getId();
    }

    @PostMapping(path="/account/register")
    public String register(@RequestBody User user){
        System.out.println(user.toString());
        return "done";
    }
}