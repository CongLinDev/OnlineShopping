package chd.shoppingonline.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

/*
 * @ClassName Record
 * @Author 从林
 * @Date 2019-03-15 15:52
 * @Description 交易记录
 */

@Entity
@Table(name = "record", schema = "record")
@Data
@NoArgsConstructor
@Builder
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
    private Long buyerId;

    @NotEmpty
    @Column(name = "record_time")
    private Date recordTime;

    @Column(name = "remarks")
    private String remarks;
}
