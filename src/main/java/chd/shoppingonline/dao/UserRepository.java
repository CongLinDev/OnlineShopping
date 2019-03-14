package chd.shoppingonline.dao;
/*
 * @ClassName UserRepository
 * @Author 从林
 * @Date 2019-03-14 16:16
 * @Description 用于User与数据库交互
 */

import chd.shoppingonline.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
