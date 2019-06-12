package chd.shoppingonline.service.basic;
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
    /**
     * 添加用户
     * @param user
     * @return
     */
    User addUser(User user);

    /**
     * 保存用户
     * @param user
     * @return
     */
    User saveUser(User user);

    /**
     * 删除用户（通过用户ID）
     * @param userId 用户ID
     */
    void deleteUser(Long userId);

    /**
     * 删除用户（通过用户名）
     * @param username 用户名
     * @return
     */
    void deleteUser(String username);

    /**
     * 查询当前登陆用户
     * @return
     */
    User findUser();

    /**
     * 查询用户（通过用户ID）
     * @param userId 用户ID
     * @return
     */
    User findUser(Long userId);

    /**
     * 查询用户（通过用户名）
     * @param username 用户名
     * @return
     */
    User findUser(String username);


    /**
     * 获取所有用户
     * @param pageable 页信息
     */
    Page<User> findAllUser(Pageable pageable);

    /**
     * 获取所有用户
     * @param pageNum 页号
     * @param pageLimit 页内信息数量
     */
    Page<User> findAllUser(int pageNum, int pageLimit);

    /**
     * 禁用用户
     * @param userId 用户ID
     */
    void disableUser(Long userId);

    /**
     * 激活用户
     * @param userId 用户ID
     */
    void enableUser(Long userId);

    /**
     * 充值
     * @param userId
     * @param amount
     */
    void recharge(Long userId, Double amount);
}
