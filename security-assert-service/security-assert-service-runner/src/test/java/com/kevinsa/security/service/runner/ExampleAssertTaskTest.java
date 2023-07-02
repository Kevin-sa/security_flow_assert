package com.kevinsa.security.service.runner;

import com.kevinsa.security.service.service.task.ExampleAssertTaskServer;
import com.kevinsa.security.service.utils.DataUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest(classes = TaskApplication.class)
@RunWith(SpringRunner.class)
public class ExampleAssertTaskTest {

    @Autowired
    private ExampleAssertTaskServer exampleAssertTaskServer;

    @Autowired
    private DataUtils dataUtils;

    @Test
    public void exampleTaskServiceTest() throws InterruptedException {
        Runnable runnable = exampleAssertTaskServer.getRunnableExec("kwai-shop");
        runnable.run();
    }

}
