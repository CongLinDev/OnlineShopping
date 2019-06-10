package chd.shoppingonline.service.feature.impl;
/*
 * @ClassName ShoppingTrolleyServiceImpl
 * @Author 从林
 * @Date 2019-06-10 22:27
 * @Description
 */

import chd.shoppingonline.entity.Commodity;
import chd.shoppingonline.service.basic.CommodityService;
import chd.shoppingonline.service.feature.ShoppingTrolleyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.Set;

public class ShoppingTrolleyServiceImpl implements ShoppingTrolleyService {

    @Autowired
    private RedisTemplate<Long, Commodity> shoppingTrolley;

    @Autowired
    private CommodityService commodityService;

    @Resource

    @Override
    public void addCommodity(Long userId, Long commodityId) {
        Commodity commodity = commodityService.findCommodity(commodityId);
        addCommodity(userId, commodity);

    }

    @Override
    public void addCommodity(Long userId, Commodity commodity) {
        shoppingTrolley.opsForSet().add(userId, commodity);
        //shoppingTrolley.expire(userId,3000L, TimeUnit.SECONDS);
    }

    @Override
    public void removeCommodity(Long userId, Long commodityId) {
        Commodity commodity = commodityService.findCommodity(commodityId);
        removeCommodity(userId, commodity);
    }

    @Override
    public void removeCommodity(Long userId, Commodity commodity) {
        shoppingTrolley.opsForSet().remove(userId, commodity);
    }


    @Override
    public Set<Commodity> getAllCommodities(Long userId) {
        return shoppingTrolley.opsForSet().members(userId);
    }
}
