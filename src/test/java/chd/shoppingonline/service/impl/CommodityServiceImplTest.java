package chd.shoppingonline.service.impl;

import chd.shoppingonline.service.basic.CommodityService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CommodityServiceImplTest {

    @Autowired
    private CommodityService commodityService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addCommodity() {

    }

    @Test
    public void deleteCommodity() {
    }

    @Test
    public void findCommodity() {
    }
}