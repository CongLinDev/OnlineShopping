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
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "record", schema = "record",
    indexes ={@Index(name = "buyer_id", columnList = "buyer_id")})
@Data
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@EntityListeners(AuditingEntityListener.class)
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private Long recordId;

    @CreatedDate
    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    @CreatedBy
    @Column(name = "buyer_id", nullable = false)
    private Long buyerId;

    @Column(name= "consignee_information_id", nullable = false)
    private Long consigneeInformationId;

//
//    @Column(name = "buyer_id", nullable = false)
//    private Long buyerId;//买家Id
//
//    @NotEmpty
//    @Column(name = "is_finished")
//    private Boolean isFinished;//订单是否完成
//
//    @Column(name = "express_id")
//    private Long expressId;//快递单号
//
//    @NotEmpty
//    @Column(name = "record_time")
//    private LocalDateTime recordTime;//生成订单时间
//
//    // 备注
//    // 备注的作用是记录是否有取消订单的情况
//    // 若存在取消订单的情况，则备注指向退款订单
//    // 若不存在，则为null
//    @Column(name = "remarks")
//    private Long remarks;
//
//    @Column(name = "comments")
//    private String comment;//用户评论
//
//    @Column(name = "star")
//    @Size(min=1, max=5)
//    private short star;//评级
//
//    @Column(name = "name")
//    private String name;//收货人
//
//    @Column(name = "address")
//    private String address;//收货地址
//
//    @Column(name = "phone_number")
//    private String phoneNumber;//手机号
}
