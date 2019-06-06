package chd.shoppingonline.config;
/*
 * @ClassName RedisConfig
 * @Author 从林
 * @Date 2019-06-06 8:23
 * @Description
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession
public class RedisConfig {

}
