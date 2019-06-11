package chd.shoppingonline.service.basic.impl;
/*
 * @ClassName UserServiceImpl
 * @Author 从林
 * @Date 2019-03-15 16:35
 * @Description UserService实现
 */

import chd.shoppingonline.common.role.UserRole;
import chd.shoppingonline.dao.UserRepository;
import chd.shoppingonline.entity.SystemUser;
import chd.shoppingonline.entity.User;
import chd.shoppingonline.service.basic.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;



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
    public User saveUser(User user) {
        log.info("保存用户：" + user.toString());
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
        if(authentication == null)
            throw new NoSuchElementException();

        User user = ((SystemUser)(authentication.getPrincipal())).getUser();
        //User user = User.builder().username(details.getUsername()).password(details.getPassword()).enabled(details.isEnabled()).build();
        log.info("查询当前用户：" + user.toString());
        return user;
    }

    @Override
    public User findUser(Long userId)  throws EmptyResultDataAccessException, IllegalArgumentException{
        log.info("查询用户：ID=" + userId.toString());
        return userRepository.findByUserId(userId);
    }

    @Override
    public User findUser(String username) throws EmptyResultDataAccessException, IllegalArgumentException {
        log.info("查询用户：USERNAME=" + username);
        return  userRepository.findByUsername(username);
    }

    //获取所有用户
    @Override
    public Page<User> findAllUser(Pageable pageable) throws EmptyResultDataAccessException, IllegalArgumentException{
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<User> findAllUser(int pageNum, int pageLimit){
        Pageable pageable = PageRequest.of(pageNum, pageLimit, new Sort(Sort.Direction.ASC, "user_id"));
        return findAllUser(pageable);
    }

    @Override
    public void disableUser(Long userId){
        userRepository.updateUserState(userId, false);
    }

    @Override
    public void enableUser(Long userId){
        userRepository.updateUserState(userId, true);
    }


    /**********************以下实现UserDetailsService接口****************/
    @Override
    public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException {
        User user = findUser(username);
        if(user == null){
            log.error("加载用户失败");
            throw new UsernameNotFoundException(username + "not found");
        }
        System.out.println("验证USER信息：" + user.toString());
        log.info("验证USER信息：" + user.toString());
        return SystemUser.builder()
                .user(user)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .authorities(UserRole.getAuthorities(user.getRoles()))
                .build();
    }
}
