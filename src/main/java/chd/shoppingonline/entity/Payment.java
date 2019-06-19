package chd.shoppingonline.entity;
/*
 * @ClassName Payment
 * @Author 从林
 * @Date 2019-05-01 10:02
 * @Description 支付
 */

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@Table(name = "payment", schema = "payment",
        indexes = {
                @Index(name = "receiver", columnList = "receiver"),
                @Index(name = "payer", columnList = "payer")
        })
@EntityListeners(AuditingEntityListener.class)
public class Payment {

    @Id//主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自动生成ID
    @Column(name = "payment_id")
    private Long paymentId;

    @Column(name = "balance", nullable = false)
    private Double balance;//交易金额

    @Column(name= "receiver", nullable = false)
    private Long receiverId;

    @Column(name="payer", nullable = false)
    private Long payerId;

    @CreatedDate
    @Column(name="create_time", nullable = false)
    private LocalDateTime createTime;
}
