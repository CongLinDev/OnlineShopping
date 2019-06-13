package chd.shoppingonline.controller;
/*
 * @ClassName ExceptionCatch
 * @Author 从林
 * @Date 2019-06-10 15:10
 * @Description
 */

import chd.shoppingonline.controller.exception.IllegalParamExcpetion;
import chd.shoppingonline.entity.ReturnEntity;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
        //"invalid params in request"
        return ReturnEntity.<String>builder().code(false).message(e.getMessage()).build();
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
        return ReturnEntity.<String>builder().code(false).message("TransactionSystemException").build();
    }

    @ResponseBody
    @ExceptionHandler(IllegalParamExcpetion.class)
    public ReturnEntity<String> sqlParseException(IllegalParamExcpetion e) {
        return ReturnEntity.<String>builder().code(false).message("请求参数错误，请检查参数").build();
    }

    @ResponseBody
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ReturnEntity<String> sqlParseException(EmptyResultDataAccessException e) {
        return ReturnEntity.<String>builder().code(false).message("查询结果为空，数据库内部数据出错").build();
    }
}
