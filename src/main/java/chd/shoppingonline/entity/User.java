package chd.shoppingonline.entity;

/*
 * @ClassName User
 * @Author 从林
 * @Date 2019-03-14 15:26
 * @Description 用户类
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "user", schema = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Check(constraints="balance>=0")
public class User implements Serializable {

    private static final long serialVersionUID = 3184902925414737238L;
    @Id//主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自动生成ID
    @Column(name = "user_id")
    private Long userId;

    //@Size(min = 4, max = 20, message = "用户名不得小于4个字符且超过20个字符")
    @NotBlank
    @Column(name = "username", unique = true, columnDefinition="varchar(10)", nullable = false)
    private String username;

    @Column(name = "password",columnDefinition="char(60)", nullable = false)
    @NotBlank
    private String password;

    @Column(name = "roles", columnDefinition = "smallint unsigned", nullable = false)
    private Short roles;

    @Min(0)
    @Column(name="balance", nullable = false, columnDefinition = "double")
    private Double balance;

    @Column(name = "enabled", nullable = false)
    @NotNull
    private boolean enabled;//账户是否可用, true 为可用

//    //卖家卖的商品
//    @JsonIgnore
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name="created_by")
//    @OrderBy("commodity_id DESC")//按commodity_id降序排列
//    private List<Commodity> sells;

//    @JsonIgnore
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name="consignee")
//    @OrderBy("consignee_information_id DESC")//按consignee_information_id降序排列
//    private List<ConsigneeInformation> consigneeInformation;//收货人信息

//    @JsonIgnore
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name="buyer_id")
//    @OrderBy("record_id DESC")
//    private List<Record> hasBuyed;

    /**
     * 购物车
     * 关系被维护端
     * 关系被维护端删除时，如果中间表存在些纪录的关联信息，则会删除失败
     */
//    @ManyToMany(
//            cascade = CascadeType.REFRESH,
//            mappedBy = "shoppingTrolley",//通过维护端的属性关联
//            fetch = FetchType.LAZY)
//    @JsonIgnore
//    private Set<Commodity> shoppingTrolley;


}
