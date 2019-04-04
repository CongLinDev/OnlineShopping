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

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "consignee_information", schema = "consignee_information",
        indexes = {@Index(name = "consignee_information_id", columnList = "consignee_information_id")})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ConsigneeInformation {
    private static final String PHONE_NUMBER_REG = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$";

    @Id//主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自动生成ID
    @Column(name = "consignee_information_id")
    private Long id;//收货信息id

    @CreatedBy
    @Column(
            name = "consignee",
            nullable = false,
            updatable = false
    )
    private Long consignee;

    @Size(max=10)
    @Column(name = "consignee_name")
    private String consigneeName;//收货人

    @Size(min=5)
    @Column(name = "consignee_address")
    private String consigneeAddress;//收货地址

    @Pattern(regexp = PHONE_NUMBER_REG)
    @Column(name = "consignee_phone_number")
    private String consigneePhoneNumber;//手机号
}
