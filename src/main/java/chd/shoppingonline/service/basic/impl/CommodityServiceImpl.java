package chd.shoppingonline.service.basic.impl;
/*
 * @ClassName CommodityServiceImpl
 * @Author 从林
 * @Date 2019-03-15 17:19
 * @Description CommodityService实现
 */

import chd.shoppingonline.dao.CommodityRepository;
import chd.shoppingonline.entity.Commodity;
import chd.shoppingonline.entity.RecordDetail;
import chd.shoppingonline.service.basic.CommodityService;
import chd.shoppingonline.service.basic.RecordDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CommodityServiceImpl implements CommodityService {
    @Autowired
    private CommodityRepository commodityRepository;

    @Autowired
    private RecordDetailService recordDetailService;

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
    public Commodity findCommodity(Long commodityId)  throws EmptyResultDataAccessException, IllegalArgumentException{
        log.debug("查询商品：ID=" + commodityId.toString());
        //Integer volume = recordDetailService.countTradingVolume(commodityId);
        return commodityRepository.findByCommodityId(commodityId);
    }


    @Override
    public Page<Commodity> findCommodity(String search, Pageable pageable)  throws EmptyResultDataAccessException, IllegalArgumentException{
        log.debug("查询商品：SEARCH=" + search);
        return commodityRepository.findAllByCommodityName(search, pageable);
    }

    @Override
    public Page<Commodity> findCommodity(String search, int pageNum, int pageLimit) throws EmptyResultDataAccessException, IllegalArgumentException{
        Pageable pageable = PageRequest.of(pageNum, pageLimit, new Sort(Sort.Direction.DESC, "commodityId"));
        return findCommodity(search, pageable);
    }


    @Override
    public Page<Commodity> findAllCommodities(Pageable pageable) throws EmptyResultDataAccessException, IllegalArgumentException{
        log.debug("查询所有商品");
        return commodityRepository.findAll(pageable);
    }

    @Override
    public Page<Commodity> findAllCommodities(int pageNum, int pageLimit)  throws EmptyResultDataAccessException, IllegalArgumentException{
        Pageable pageable = PageRequest.of(pageNum, pageLimit, new Sort(Sort.Direction.DESC, "commodityId"));
        return findAllCommodities(pageable);
    }

    @Override
    public void updateCommodityStock(Long commodityId ,Integer decreaseStock)  throws EmptyResultDataAccessException, IllegalArgumentException{
        Commodity commodity = commodityRepository.findByCommodityId(commodityId);
        if(commodity == null || commodity.getStock() < decreaseStock)
            throw new IllegalArgumentException();
        commodityRepository.updateStockByCommodityId(commodityId, commodity.getStock(), commodity.getStock() - decreaseStock);
    }




    @Override
    public List<Commodity> findCommodity(String key, String className, Boolean asc, String orderColumn, Integer page, Integer max) {
        var order = Sort.Direction.ASC;
        if(!asc) order = Sort.Direction.DESC;

        return countRecordDetailsVolume(commodityRepository.findAllByCommodityNameAndCommodityType(key, className, PageRequest.of(page,max, new Sort(order, orderColumn))).getContent());
    }

    @Override
    public List<Commodity> findCommodity(String key, Boolean asc, String orderColumn, Integer page, Integer max)  throws EmptyResultDataAccessException, IllegalArgumentException{
        var order = Sort.Direction.ASC;
        if(!asc) order = Sort.Direction.DESC;

        return countRecordDetailsVolume(commodityRepository.findAllByCommodityName(key, PageRequest.of(page, max, new Sort(order, "commodityId"))).getContent());
    }

    @Override
    public List<Commodity> findAllCommodities(String className, Boolean asc, String orderColumn, Integer page, Integer max)  throws EmptyResultDataAccessException, IllegalArgumentException{
        var order = Sort.Direction.ASC;
        if(!asc) order = Sort.Direction.DESC;
        return countRecordDetailsVolume(commodityRepository.findAllByCommodityType(className, PageRequest.of(page, max, new Sort(order, orderColumn))).getContent());
    }

    private List<Commodity> countRecordDetailsVolume(List<Commodity> commodities){
        if(commodities == null) return  commodities;
//        List<Integer> v = commodities.parallelStream().map(Commodity::getCommodityId)
////                .map(recordDetailService::findRecordDetailByCommodityId)
////                .map(recordDetailService::countTradingVolume)
////                .collect(Collectors.toList());
        for(int i = 0; i < commodities.size(); i++){
            Commodity commodity = commodities.get(i);
            List<RecordDetail> recordDetails = recordDetailService.findRecordDetailByCommodityId(commodity.getCommodityId());
            commodity.setVolume(recordDetailService.countTradingVolume(recordDetails));
        }
        return commodities;
    }

}
