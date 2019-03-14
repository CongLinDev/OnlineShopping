package chd.shoppingonline.service;
/*
 * @ClassName UserService
 * @Author 从林
 * @Date 2019-03-14 16:21
 * @Description 用户业务接口
 */

import chd.shoppingonline.entity.User;

import java.util.List;

public interface UserService {
    //添加用户
    User addUser(User user);

    //删除用户
    User deleteUser(Long userId);
    User deleteUser(String username);

    //查询用户
    User findUser();//查询当前登陆用户
    User findUser(Long userId);
    User findUser(String username);

    //获取所有用户
    List<User> findAllUser();
}
