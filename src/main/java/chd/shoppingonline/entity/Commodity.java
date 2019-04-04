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
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "commodity", schema = "commodity",
        indexes = {
                @Index(name = "commodityname", columnList = "commodityname"),
                @Index(name= "created_by", columnList = "created_by"),
                @Index(name = "commodity_id", columnList = "commodity_id")})
@Data
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Commodity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commodity_id")
    private Long id;

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
    private Date time;//上架时间

    @Size(max=20, min=4)
    @Column(name = "commodityname")
    private String commodityname;//产品名

    @NotEmpty
    @Column(name = "price")
    private Double price;//价格

    @NotEmpty
    @Column(name = "stock")
    private Integer stock;//库存

    @Size(min = 0, max = 200)
    @Column(name = "description")
    private String description;//产品描述

    @Column(name = "pictures")
    private String pictures;//产品图片，将图片上传至网络图床，数组保存url，节省服务端存储空间\

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="commodity_id")
    @OrderBy("record_id DESC")//按record_id降序排列
    private List<Record> records;

    /**
     * 标签
     * 关系维护端
     * 关系维护端删除时，如果中间表存在些纪录的关联信息，则会删除该关联信息
     */
    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable (//关联表
            name = "commodity_tag_relation" , //关联表名
            inverseJoinColumns = @JoinColumn (name = "commodity_tag_id" ),//被维护端外键
            joinColumns = @JoinColumn (name = "commodity_id" ))//维护端外键
    private Set<CommodityTag> tags;

    /**
     * 购物车
     * 关系维护端
     * 关系维护端删除时，如果中间表存在些纪录的关联信息，则会删除该关联信息
     */
    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable (//关联表
            name = "shopping_trolley" , //关联表名
            inverseJoinColumns = @JoinColumn (name = "user_id" ),//被维护端外键
            joinColumns = @JoinColumn (name = "commodity_id" ))//维护端外键
    private Set<User> shoppingTrolley;

    public List<String> getComments(){
        return records.stream()
                .filter(record -> record.getIsFinished())
                .map(record -> record.getComment())
                .collect(Collectors.toList());
    }
}
