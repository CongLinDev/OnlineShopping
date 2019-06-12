package chd.shoppingonline.service.feature.impl;
/*
 * @ClassName ShoppingTrolleyServiceImpl
 * @Author 从林
 * @Date 2019-06-10 22:27
 * @Description
 */

import chd.shoppingonline.entity.Commodity;
import chd.shoppingonline.entity.ShoppingTrolley;
import chd.shoppingonline.service.feature.ShoppingTrolleyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ShoppingTrolleyServiceImpl implements ShoppingTrolleyService {
//
//    @Autowired
//    private RedisTemplate<Long, ShoppingTrolley> shoppingTrolleyHolder;
//
//    @Override
//    public void add(Long userId, Long commodityId, Integer num) {
//        shoppingTrolleyHolder.opsForHash().put(userId, commodityId, num);
//    }
//
//    @Override
//    public void add(Long userId, ShoppingTrolley shoppingTrolley) {
//        shoppingTrolleyHolder.opsForHash().put(userId, shoppingTrolley.getCommodityId(), shoppingTrolley.getNumber());
//    }
//
//    @Override
//    public void add(Long userId, List<ShoppingTrolley> shoppingTrolleys){
//        for (ShoppingTrolley shoppingTrolley : shoppingTrolleys){
//            add(userId, shoppingTrolley);
//        }
//    }
//
//    @Override
//    public void removeCommodity(Long userId, Long commodityId) {
//        shoppingTrolleyHolder.opsForHash().delete(userId, commodityId);
//    }
//
//    @Override
//    public Set<Long> getAllCommoditiyIds(Long userId) {
//        Map<Long, Integer> shoppingTrolleys = shoppingTrolleyHolder.opsForHash().entries(userId);
//    }
//
//    @Override
//    public Set<Commodity> getAllCommodities(Long userId) {
//        return getAllCommoditiyIds(userId).parallelStream().map(commodityService::findCommodity).collect(Collectors.toSet());
//    }
}
