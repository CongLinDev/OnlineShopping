package chd.shoppingonline.entity;
/*
 * @ClassName Commodity
 * @Author 从林
 * @Date 2019-03-14 15:49
 * @Description 商品类
 */

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "commodity", schema = "commodity")
@Data
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
    private int price;//价格

    @NotEmpty
    @Column(name = "stock")
    private int stock;//库存

    @Size(min = 0, max = 200)
    @Column(name = "description")
    private String description;//产品描述

    @Column(name = "pictures")
    private String[] pictures;//产品图片，将图片上传至网络图床，数组保存url，节省服务端存储空间
}
