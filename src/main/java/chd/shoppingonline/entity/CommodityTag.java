package chd.shoppingonline.entity;
/*
 * @ClassName CommodityTag
 * @Author 从林
 * @Date 2019-04-04 20:35
 * @Description 商品标签
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "commodity_tag", schema = "commodity_tag",
        indexes = {@Index(name = "commodity_tag_id", columnList = "commodity_tag_id")})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CommodityTag {
    @Id//主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自动生成ID
    @Column(name = "commodity_tag_id")
    private Long id;//标签id

    @Column(name="tag", unique = true)
    private String tag;

    /**
     * 购物车
     * 关系被维护端
     * 关系被维护端删除时，如果中间表存在些纪录的关联信息，则会删除失败
     */
    @ManyToMany(
            cascade = CascadeType.REFRESH,
            mappedBy = "tags",//通过维护端的属性关联
            fetch = FetchType.LAZY)
    private Set<Commodity> commodities;
}
