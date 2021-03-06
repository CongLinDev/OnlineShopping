package chd.shoppingonline.entity;
/*
 * @ClassName RecordDetail
 * @Author 从林
 * @Date 2019-04-20 21:08
 * @Description 交易记录细节
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Entity
@Table(name = "record_detail", schema = "record_detail",
        indexes = {@Index(name = "own_record_id", columnList = "own_record_id"),
        @Index(name = "commodity_id", columnList = "commodity_id")})
@Data
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
//@FilterDef(name = "onlySelled",
//        defaultCondition = "recordDetailState = '4'")
@EntityListeners(AuditingEntityListener.class)
public class RecordDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_detail_id")
    private Long recordDetailId;

    @Column(name = "own_record_id", nullable = false)
    private Long recordId;

    @Column(name = "commodity_id", nullable = false)
    private Long commodityId;

    @Min(1)
    @Column(name = "tradingVolume", nullable = false)
    private Integer tradingVolume;//交易量

    @Column(name = "express_id", columnDefinition = "varchar(13)")
    private String expressId;//快递单号

    @Column(name = "recordDetailState", columnDefinition = "smallint", nullable = false)
    private Short recordDetailState;//状态

    @Column(name = "comment", columnDefinition = "varchar(100)")
    private String comment;//评论

    @Column(name = "comment_date")
    private LocalDateTime commentDate;//评论日期

    @CreatedDate
    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;//订单细节创建日期
}
