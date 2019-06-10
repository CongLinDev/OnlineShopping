package chd.shoppingonline.controller;
/*
 * @ClassName ExceptionCatch
 * @Author 从林
 * @Date 2019-06-10 15:10
 * @Description
 */

import chd.shoppingonline.entity.ReturnEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionCatch {
    @ResponseBody
    @ExceptionHandler(ClassCastException.class)
    public ReturnEntity<String> requestParseException(ClassCastException e) {
        //日志记录等

        return ReturnEntity.<String>builder().code(false).message("not valid params in request").build();
    }
}
