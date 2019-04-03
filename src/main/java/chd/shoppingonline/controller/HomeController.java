package chd.shoppingonline.controller;
/*
 * @ClassName HomeController
 * @Author 从林
 * @Date 2019-03-13 22:01
 * @Description 索引Controller
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HomeController {

    @GetMapping(path={"/index", "/", ""})
    public String index() {
        return "index";
    }
}