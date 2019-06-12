package chd.shoppingonline.dao;
/*
 * @ClassName UserRepository
 * @Author 从林
 * @Date 2019-03-14 16:16
 * @Description 用于User与数据库交互
 */

import chd.shoppingonline.entity.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(Long userId) throws EmptyResultDataAccessException, IllegalArgumentException;

    User findByUsername(String username) throws EmptyResultDataAccessException, IllegalArgumentException;

    void deleteByUsername(String username);

    @Transactional
    @Modifying
    @Query(value = "update User user set user.balance = :balance where user.id = :userId")
    void updateBalanceByUserId(@Param("userId")Long userId, @Param("balance") double balance);

    @Modifying
    @Query(value = "update User user set user.enabled = :enabled where user.id = :userId")
    @Transactional
    void updateUserState(@Param("userId")Long userId, @Param("enabled") Boolean enabled);

    @Modifying
    @Query("update User user set  user.balance = user.balance + ?2 where user.userId = ?1")
    @Transactional
    void rechargeBalance(Long userId, Double amount);
}
