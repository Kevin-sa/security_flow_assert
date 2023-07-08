package com.kevinsa.security.service.runner;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kevinsa.security.service.service.operator.impl.EquOperatorServiceImpl;
import com.kevinsa.security.service.service.operator.impl.LtOperatorServiceImpl;
import com.kevinsa.security.service.service.operator.impl.MtOperatorServiceImpl;
import com.kevinsa.security.service.service.operator.impl.NeqOperatorServiceImpl;

@SpringBootTest(classes = TaskApplication.class)
@RunWith(SpringRunner.class)
public class OperatorServiceTest {

    @Autowired
    private NeqOperatorServiceImpl neqOperatorService;

    @Autowired
    private EquOperatorServiceImpl equOperatorService;

    @Autowired
    private LtOperatorServiceImpl ltOperatorService;

    @Autowired
    private MtOperatorServiceImpl mtOperatorService;

    @Test
    public void neqOperatorServiceTest() {
        boolean result = neqOperatorService.execute(1, 1);
        Assert.assertFalse(result);
    }


    @Test
    public void eqOperatorServiceTest() {
        boolean result = equOperatorService.execute(1, 1);
        Assert.assertTrue(result);
    }

    @Test
    public void ltOperatorServiceTest() {
        boolean result = ltOperatorService.execute(1, 2);
        Assert.assertTrue(result);
    }

    @Test
    public void mtOperatorServiceTest() {
        boolean result = mtOperatorService.execute(2, 1);
        Assert.assertTrue(result);
    }

}
