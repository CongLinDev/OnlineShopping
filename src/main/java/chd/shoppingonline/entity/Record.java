package chd.shoppingonline.entity;

/*
 * @ClassName Record
 * @Author 从林
 * @Date 2019-03-15 15:52
 * @Description 交易记录
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "record", schema = "record")
@Data
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private Long id;

    @NotEmpty
    @Column(name = "commodity_id")
    private Long commodityId;

    @NotEmpty
    @Column(name = "seller_id")
    private Long sellerId;//卖家Id

    @NotEmpty
    @Column(name = "buyer_id")
    private Long buyerId;//买家Id

    @NotEmpty
    @Column(name = "is_finished")
    private Boolean isFinished;

    @Column(name = "express_id")
    private Long expressId;//快递单号

    @Size(min = 1)
    @Column(name = "exchange_number")
    private int exchangeNumber;//交易数量

    @NotEmpty
    @Column(name = "record_time")
    private Date recordTime;

    @Column(name = "remarks")
    private String remarks;//备注

    @Column(name = "comments")
    private String comment;//用户评论

    @Column(name = "star")
    @Size(min=1, max=5)
    private short star;//评级

    @Column(name = "name")
    private String name;//收货人

    @Column(name = "address")
    private String address;//收货地址

    @Column(name = "phone_number")
    private String phoneNumber;//手机号
}
