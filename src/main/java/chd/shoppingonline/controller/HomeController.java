package chd.shoppingonline.controller;
/*
 * @ClassName HomeController
 * @Author 从林
 * @Date 2019-03-13 22:01
 * @Description 索引Controller
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping(path={"/index", "/", ""})
    public String index() {
        return "index";
    }
}