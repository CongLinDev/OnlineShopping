package chd.shoppingonline.service;
/*
 * @ClassName UserService
 * @Author 从林
 * @Date 2019-03-14 16:21
 * @Description 用户业务接口
 */

import chd.shoppingonline.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    //添加用户
    User addUser(User user);

    //删除用户
    void deleteUser(Long userId);
    void deleteUser(String username);

    //查询用户
    User findUser();//查询当前登陆用户
    User findUser(Long userId);
    User findUser(String username);

    //获取所有用户
    //List<User> findAllUser();//弃用
    Page<User> findAllUser(Pageable pageable);
    Page<User> findAllUser(int pageNum, int pageLimit);//按照序号顺序排列，页号从1开始

    /*******************以下方法不建议Controller层调用**************************************************************/
    //更新用户账户余额
    Boolean payBalance(Long userId, Double transactionBalance);//买家付钱
    Boolean getBalance(Long userId, Double transactionBalance);//卖家收钱

    Boolean updateUserBalance(Long userId, Double transactionBalance, Boolean isSeller);
}
