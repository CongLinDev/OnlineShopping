package chd.shoppingonline.entity;
/*
 * @ClassName ReturnEntity
 * @Author 从林
 * @Date 2019-06-09 16:56
 * @Description
 */

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ReturnEntity<T> {
    private T content;
    private Boolean code;
    private String message;
}
