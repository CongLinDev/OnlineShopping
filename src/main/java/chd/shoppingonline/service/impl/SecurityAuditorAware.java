package chd.shoppingonline.service.impl;
/*
 * @ClassName SecurityAuditorAware
 * @Author 从林
 * @Date 2019-03-14 16:35
 * @Description Spring Security AuditorAware
 */

import chd.shoppingonline.entity.User;
import chd.shoppingonline.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@Slf4j
public class SecurityAuditorAware implements AuditorAware<Long> {
    @Autowired
    private UserService userService;

    @Override
    public Optional<Long> getCurrentAuditor(){
        User user = userService.findUser();//获得当前用户
        log.info("AuditorAware:" + user.toString());
        return Optional.ofNullable(user.getId());
    }
}
