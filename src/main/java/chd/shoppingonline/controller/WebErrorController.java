package chd.shoppingonline.controller;
/*
 * @ClassName WebErrorController
 * @Author 从林
 * @Date 2019-03-18 8:49
 * @Description 处理错误
 */

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WebErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, ModelMap map){
        //TODO: 处理错误
//        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
//        map.addAttribute("statusCode", statusCode);
        return "error";
    }

    @Override
    public String getErrorPath(){
        return "/error";
    }
}