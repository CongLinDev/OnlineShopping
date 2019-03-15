package chd.shoppingonline.service.impl;
/*
 * @ClassName CommodityServiceImpl
 * @Author 从林
 * @Date 2019-03-15 17:19
 * @Description CommodityService实现
 */

import chd.shoppingonline.dao.CommodityRepository;
import chd.shoppingonline.entity.Commodity;
import chd.shoppingonline.service.CommodityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CommodityServiceImpl implements CommodityService {
    @Autowired
    private CommodityRepository commodityRepository;

    public Commodity addCommodity(Commodity commodity){
        log.info("添加商品：" + commodity.toString());
        return commodityRepository.save(commodity);
    }

    @Override
    public void deleteCommodity(Long commodityId){
        log.info("删除商品：ID=" + commodityId.toString());
        commodityRepository.deleteById(commodityId);
    }

    @Override
    public Commodity findCommodity(Long commodityId){
        log.info("查询商品：ID=" + commodityId.toString());
        return commodityRepository.findById(commodityId).get();
    }

    @Override
    public List<Commodity> findCommodity(String commodityname){
        log.info("查询商品：NAME=" + commodityname);
        return commodityRepository.findAllByCommodityname(commodityname);
    }

    @Override
    public List<Commodity> findAllCommodity(){
        log.info("查询所有商品");
        return commodityRepository.findAll();
    }
}
