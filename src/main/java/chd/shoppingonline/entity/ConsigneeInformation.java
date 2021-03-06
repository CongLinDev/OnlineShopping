package chd.shoppingonline.entity;
/*
 * @ClassName ConsigneeInformation
 * @Author 从林
 * @Date 2019-03-24 13:42
 * @Description 用户收货信息
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "consignee_information", schema = "consignee_information")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@EntityListeners(AuditingEntityListener.class)
public class ConsigneeInformation {

    @Id//主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自动生成ID
    @Column(name = "consignee_information_id")
    private Long consigneeInformationId;//收货信息id

    @CreatedBy
    @Column(
            name = "consignee_id",
            nullable = false,
            updatable = false
    )
    private Long consigneeId;

    @Size(max=10)
    @Column(name = "consignee_name", columnDefinition = "varchar(10)", nullable = false)
    private String consigneeName;//收货人

    @Column(name = "consignee_address", columnDefinition = "varchar(50)", nullable = false)
    private String consigneeAddress;//收货地址

    @Column(name = "consignee_phone_number", columnDefinition = "char(13)", nullable = false)
    private String consigneePhoneNumber;//手机号

    @Column(name = "valid")
    private Boolean valid;//有效性
}
