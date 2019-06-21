package chd.shoppingonline.entity;
/*
 * @ClassName Commodity
 * @Author 从林
 * @Date 2019-03-14 15:49
 * @Description 商品类
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Entity
@Table(name = "commodity", schema = "commodity",
        indexes = {
                @Index(name = "commodityName", columnList = "commodity_name")
        })
@Check(constraints="stock >= 0 and price >= 0")
@Data
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@EntityListeners(AuditingEntityListener.class)
public class Commodity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commodity_id")
    private Long commodityId;//商品 id

    @CreatedBy
    @Column(
            name = "created_by",
            nullable = false,
            updatable = false
    )
    private Long createdBy;//商家id

    @CreatedDate
    @Column(
            name = "created_date",
            nullable = false,
            updatable = false
    )
    private LocalDateTime time;//上架时间

    //@Size(max=20, min=4)
    @Column(name = "commodity_name", columnDefinition="varchar(10)", nullable = false)
    private String commodityName;//产品名

    //@NotEmpty
    @Column(name = "price",nullable = false, columnDefinition = "double")
    @Min(0)
    private Double price;//单价

    //@NotEmpty

    @Column(name = "stock",nullable = false, columnDefinition = "int")
    @Min(0)
    private Integer stock;//库存

    @Column(name = "description", columnDefinition="varchar(512)")
    private String description;//产品描述

    @Column(name = "pictures", columnDefinition="varchar(255)")
    private String pictures;//产品图片，将图片上传至网络图床，保存url，节省服务端存储空间

    @Column(name="commodity_state", columnDefinition = "smallint", nullable = false)
    private Short commodityState;

    @Column(name="commodity_type", columnDefinition = "varchar(10)")
    private String commodityType;


 //   @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name="commodity_id")
//    @LazyCollection(LazyCollectionOption.EXTRA)
//    @Filter(name = "onlySelled")
//    @JsonIgnore
//    private List<RecordDetail> recordDetails;//销量

    @Transient
    private int volume;

    //@Transient
//    public Integer getVolume(){
//        return volume;
//    }

  //  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name="commodity_id")
//    @OrderBy("record_id DESC")//按record_id降序排列
//    private List<Record> records;

//    /**
//     * 标签
//     * 关系维护端
//     * 关系维护端删除时，如果中间表存在些纪录的关联信息，则会删除该关联信息
//     */
//    @ManyToMany(cascade = CascadeType.REFRESH)
//    @JoinTable (//关联表
//            name = "commodity_tag_relation" , //关联表名
//            inverseJoinColumns = @JoinColumn (name = "commodity_tag_id" ),//被维护端外键
//            joinColumns = @JoinColumn (name = "commodity_id" ))//维护端外键
//    private Set<CommodityTag> tags;
//
//    /**
//     * 购物车
//     * 关系维护端
//     * 关系维护端删除时，如果中间表存在些纪录的关联信息，则会删除该关联信息
//     */
//    @ManyToMany(cascade = CascadeType.REFRESH)
//    @JoinTable (//关联表
//            name = "shopping_trolley" , //关联表名
//            inverseJoinColumns = @JoinColumn (name = "user_id" ),//被维护端外键
//            joinColumns = @JoinColumn (name = "commodity_id" ))//维护端外键
//    private Set<User> shoppingTrolley;
}
