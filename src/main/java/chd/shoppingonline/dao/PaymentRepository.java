package chd.shoppingonline.dao;
/*
 * @ClassName PaymentRepository
 * @Author 从林
 * @Date 2019-06-03 14:04
 * @Description
 */

import chd.shoppingonline.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
