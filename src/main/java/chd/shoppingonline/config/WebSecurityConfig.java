package chd.shoppingonline.config;
/*
 * @ClassName Commodity
 * @Author 从林
 * @Date 2019-03-14 16:49
 * @Description Security配置
 */

import chd.shoppingonline.common.role.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity //注解开启Spring Security的功能
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    @Qualifier("userServiceImpl")
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //加入数据库验证类，下面的语句实际上在验证链中加入了一个DaoAuthenticationProvider
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/registry/**", "/logout_success").permitAll()
                .antMatchers("/seller/**").hasRole(UserRole.ROLE_SELLER.getTrimStringValue())
                .antMatchers("/buyer/**", "/account/**").hasAnyRole(UserRole.ROLE_BUYER.getTrimStringValue(), UserRole.ROLE_SELLER.getTrimStringValue())
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successForwardUrl("/index")
                .permitAll()
                .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/logout_success")
                //.logoutSuccessUrl("/index")
                .permitAll()
            .and()
                .csrf().disable();
    }
}