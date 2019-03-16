package chd.shoppingonline.entity;

/*
 * @ClassName User
 * @Author 从林
 * @Date 2019-03-14 15:26
 * @Description 用户类
 */

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "user", schema = "user")
@Data
@DynamicUpdate
public class User {
    @Id//主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自动生成ID
    @Column(name = "user_id")
    private Long id;

    @Size(min = 4, max = 20, message = "用户名不得小于4个字符且超过20个字符")
    @Column(name = "username", unique = true)
    private String username;

    @Size(min=6, message="密码不得小于6个字符")
    @Column(name = "password")
    private String password;

    @Column(name = "roles")
    private String roles;

    @Column(name="balance")
    @NotEmpty
    private Double balance;

    //卖家卖的商品
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="created_by")
    @OrderBy("commodity_id DESC")//按commodity_id降序排列
    private List<Commodity> sells;

    public User(User that){
        this.id = that.getId();
        this.username = that.getUsername();
        this.password = that.getPassword();
        this.roles = that.getRoles();
        this.sells = that.getSells();
        this.balance = that.getBalance();
    }
}
