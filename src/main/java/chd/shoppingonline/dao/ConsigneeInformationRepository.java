package chd.shoppingonline.dao;
/*
 * @ClassName ConsigneeInformationRepository
 * @Author 从林
 * @Date 2019-03-25 18:11
 * @Description 用于ConsigneeInformation与数据库的交互
 */

import chd.shoppingonline.entity.ConsigneeInformation;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ConsigneeInformationRepository extends JpaRepository<ConsigneeInformation, Long> {
    List<ConsigneeInformation> findAllByConsigneeIdAndValidIsTrue(Long consigneeId) throws EmptyResultDataAccessException, IllegalArgumentException;

    @Modifying
    @Transactional
    @Query("update ConsigneeInformation c set c.valid = false where c.consigneeInformationId = ?1")
    void updateInvalidConsigneeInformation(Long consigneeInformationId);

    ConsigneeInformation findByConsigneeInformationId(Long consigneeAddressId)throws EmptyResultDataAccessException, IllegalArgumentException;

}
