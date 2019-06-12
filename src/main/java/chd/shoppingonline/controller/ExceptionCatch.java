package chd.shoppingonline.controller;
/*
 * @ClassName ExceptionCatch
 * @Author 从林
 * @Date 2019-06-10 15:10
 * @Description
 */

import chd.shoppingonline.entity.ReturnEntity;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ExceptionCatch {
    @ResponseBody
    @ExceptionHandler(ClassCastException.class)
    public ReturnEntity<String> requestParseException(ClassCastException e) {
        //日志记录等

        return ReturnEntity.<String>builder().code(false).message("invalid params in request").build();
    }

    @ResponseBody
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ReturnEntity<String> sqlParseException(DataIntegrityViolationException e) {
        //实体完整性异常等

        return ReturnEntity.<String>builder().code(false).message("Data Integrity Violation Exception").build();
    }

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    public ReturnEntity<String> sqlParseException(ConstraintViolationException e) {
        //实体完整性异常等
        return ReturnEntity.<String>builder().code(false).message("余额不足").build();
    }

    @ResponseBody
    @ExceptionHandler(TransactionSystemException.class)
    public ReturnEntity<String> sqlParseException(TransactionSystemException e) {
        //实体完整性异常等
        return ReturnEntity.<String>builder().code(false).message(e.getMessage()).build();
    }


}
