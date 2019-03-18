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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Page<Commodity> findCommodity(String commodityname, Pageable pageable){
        log.info("查询商品：NAME=" + commodityname);
        return commodityRepository.findAllByCommodityname(commodityname, pageable);
    }

    @Override
    public Page<Commodity> findCommodity(String commodityname, int pageNum, int pageLimit){
        Pageable pageable = PageRequest.of(pageNum, pageLimit, new Sort(Sort.Direction.DESC, "commodity_id"));
        return findCommodity(commodityname, pageable);
    }

    //获取所有用户
    @Override
    public Page<Commodity> findAllCommodity(Pageable pageable){
        log.info("查询所有商品");
        return commodityRepository.findAllCommodity(pageable);
    }

    @Override
    public Page<Commodity> findAllCommodity(int pageNum, int pageLimit){
        Pageable pageable = PageRequest.of(pageNum, pageLimit, new Sort(Sort.Direction.DESC, "commodity_id"));
        return findAllCommodity(pageable);
    }

    @Override
    @Transactional
    public Boolean transactCommodity(Long commodityId){
        Integer stock = commodityRepository.findStockById(commodityId);
        if(stock <= 0){
            log.info("商品 ID="+ commodityId.toString() + " 库存为" + stock.toString() + " ：库存不足");
            return false;
        }
        commodityRepository.updateByCommodityId(commodityId, stock - 1);
        log.info("商品 ID="+ commodityId.toString() + " 当前库存为" + stock.toString() + " ：库存充足");
        return true;
    }
}
