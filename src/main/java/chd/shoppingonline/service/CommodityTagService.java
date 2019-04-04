package chd.shoppingonline.service;
/*
 * @ClassName CommodityTagService
 * @Author 从林
 * @Date 2019-04-04 20:49
 * @Description CommodityTag服务
 */


import chd.shoppingonline.entity.CommodityTag;

import java.util.Set;

public interface CommodityTagService {

    CommodityTag addCommodityTag(String tag);

    void deleteCommodityTag(Long id);

    Set<CommodityTag> findAllCommodityTag();
}
