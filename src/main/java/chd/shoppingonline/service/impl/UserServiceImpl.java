package chd.shoppingonline.service.impl;
/*
 * @ClassName UserServiceImpl
 * @Author 从林
 * @Date 2019-03-15 16:35
 * @Description UserService实现
 */

import chd.shoppingonline.dao.UserRepository;
import chd.shoppingonline.entity.SystemUser;
import chd.shoppingonline.entity.User;
import chd.shoppingonline.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User addUser(User user){
        log.info("添加用户：" +  user.toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId){
        log.info("删除用户：ID=" + userId.toString());
        userRepository.deleteById(userId);
    }

    @Override
    public void deleteUser(String username){
        log.info("删除用户：USERNAME=" + username);
        userRepository.deleteByUsername(username);
    }

    //查询当前登陆用户
    @Override
    public User findUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null) return null;
        User user = (User) authentication.getPrincipal();
        log.info("查询当前用户：" + user.toString());
        return user;
    }

    @Override
    public User findUser(Long userId){
        log.info("查询用户：ID=" + userId.toString());
        return userRepository.findById(userId).get();
    }

    @Override
    public User findUser(String username){
        log.info("查询用户：USERNAME=" + username);
        return userRepository.findByUsername(username);
    }

    //获取所有用户
    @Override
    public List<User> findAllUser(){
        return userRepository.findAll();
    }

    /* 以下实现UserDetailsService接口 */
    @Override
    public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException {
        User user = findUser(username);
        if(user == null){
            throw new UsernameNotFoundException(username);
        }
        log.info("验证USER信息：" + user.toString());
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = getUserServiceAuthorities(user);
        return new SystemUser(user,
                true,
                true,
                true,
                true,
                simpleGrantedAuthorities);
    }

    private List<SimpleGrantedAuthority> getUserServiceAuthorities(User user){
        String[] authorities = user.getRoles().split(" ");//role由空格分隔
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        for (String role : authorities) {
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role));
        }
        return simpleGrantedAuthorities;
    }
}
