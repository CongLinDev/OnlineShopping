package chd.shoppingonline.dao;
/*
 * @ClassName ConsigneeInformationRepository
 * @Author 从林
 * @Date 2019-03-25 18:11
 * @Description 用于ConsigneeInformation与数据库的交互
 */

import chd.shoppingonline.entity.ConsigneeInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsigneeInformationRepository extends JpaRepository<ConsigneeInformation, Long> {
    List<ConsigneeInformation> findAllByConsignee(Long consigneeId);
}
