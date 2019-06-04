package chd.shoppingonline.dao;
/*
 * @ClassName UserRepository
 * @Author 从林
 * @Date 2019-03-14 16:16
 * @Description 用于User与数据库交互
 */

import chd.shoppingonline.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findAll(Pageable pageable);

    Optional<User> findByUsername(String username);
    void deleteByUsername(String username);

    @Transactional
    @Modifying
    @Query(value = "update User user set user.balance = :balance where user.id = :userId")
    void updateBalanceByUserId(@Param("userId")Long userId, @Param("balance") double balance);

    @Modifying
    @Query(value = "update User user set user.enabled = :enabled where user.id = :userId")
    void updateUserState(@Param("userId")Long userId, @Param("enabled") Boolean enabled);
}
