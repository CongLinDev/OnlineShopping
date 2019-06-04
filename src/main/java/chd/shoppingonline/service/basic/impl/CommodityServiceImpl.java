package chd.shoppingonline.service.basic.impl;
/*
 * @ClassName CommodityServiceImpl
 * @Author 从林
 * @Date 2019-03-15 17:19
 * @Description CommodityService实现
 */

import chd.shoppingonline.dao.CommodityRepository;
import chd.shoppingonline.entity.Commodity;
import chd.shoppingonline.service.basic.CommodityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CommodityServiceImpl implements CommodityService {
    @Autowired
    private CommodityRepository commodityRepository;

    public Commodity addCommodity(Commodity commodity){
        log.debug("添加商品：" + commodity.toString());
        return commodityRepository.save(commodity);
    }

    @Override
    public void deleteCommodity(Long commodityId){
        log.debug("删除商品：ID=" + commodityId.toString());
        commodityRepository.deleteById(commodityId);
    }

    @Override
    public Commodity findCommodity(Long commodityId){
        log.debug("查询商品：ID=" + commodityId.toString());
        return commodityRepository.findById(commodityId).orElse(null);
    }


    @Override
    public Page<Commodity> findCommodity(String search, Pageable pageable){
        log.debug("查询商品：SEARCH=" + search);
        return commodityRepository.findAllByCommodityName(search, pageable);
    }

    @Override
    public Page<Commodity> findCommodity(String search, int pageNum, int pageLimit){
        Pageable pageable = PageRequest.of(pageNum, pageLimit, new Sort(Sort.Direction.DESC, "commodity_id"));
        return findCommodity(search, pageable);
    }


    @Override
    public Page<Commodity> findAllCommodity(Pageable pageable){
        log.debug("查询所有商品");
        return commodityRepository.findAll(pageable);
    }

    @Override
    public Page<Commodity> findAllCommodity(int pageNum, int pageLimit){
        Pageable pageable = PageRequest.of(pageNum, pageLimit, new Sort(Sort.Direction.DESC, "commodity_id"));
        return findAllCommodity(pageable);
    }

    @Override
    public void updateCommodityStock(Long commodityId ,Integer decreaseStock) {
        Commodity commodity = commodityRepository.findById(commodityId).orElse(null);
        if(commodity == null || commodity.getStock() < decreaseStock)
            throw new IllegalArgumentException();
        commodityRepository.updateStockByCommodityId(commodityId, commodity.getStock(), commodity.getStock() - decreaseStock);
    }
}
