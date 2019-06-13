package chd.shoppingonline.service.feature.impl;
/*
 * @ClassName ShoppingTrolleyServiceImpl
 * @Author 从林
 * @Date 2019-06-10 22:27
 * @Description
 */

import chd.shoppingonline.entity.ShoppingTrolley;
import chd.shoppingonline.service.feature.ShoppingTrolleyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ShoppingTrolleyServiceImpl implements ShoppingTrolleyService {

    @Autowired
    private RedisTemplate<String, Object> template;

    @Autowired
    private HashOperations<String, String, String> hashOps;


    @Override
    public void add(Long userId, Long commodityId, Integer num) {
        hashOps.put(userId.toString(), commodityId.toString(), num.toString());
        log.info("用户ID=" + userId + " 添加购物车：商品ID=" + commodityId + " 数量=" + num);
    }

    @Override
    public void add(Long userId, ShoppingTrolley shoppingTrolley) {
        add(userId, shoppingTrolley.getCommodityId(), shoppingTrolley.getNumber());
    }

    @Override
    public void add(Long userId, List<ShoppingTrolley> shoppingTrolleys){
        for (ShoppingTrolley shoppingTrolley : shoppingTrolleys){
            add(userId, shoppingTrolley);
        }
    }

    @Override
    public void remove(Long userId, Long commodityId) {
        hashOps.delete(userId.toString(), commodityId.toString());
        log.info("用户ID=" + userId + " 移除购物车：商品ID=" + commodityId);
    }

    @Override
    public List<ShoppingTrolley> getAll(Long userId) {
        Map<String, String> shoppingTrolleys = hashOps.entries(userId.toString());
        log.info("用户ID=" + userId + " 获取购物车所有信息。");
        List<ShoppingTrolley> st = new ArrayList<>();
        for (var entity : shoppingTrolleys.entrySet()){
            st.add(new ShoppingTrolley( Long.valueOf(entity.getKey()),
                    Integer.valueOf(entity.getValue()) ) );
        }
        return st;
    }

}
