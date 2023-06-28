package com.kevinsa.security.service.runner.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.kevinsa.security.service.service.assertCheck.AssertExecutorFactory;

@Component
public class ServerB implements ApplicationRunner {

    // 在这里编写 ServerA 的逻辑，可以使用 classB

    public void start() {
        System.out.println(ServerB.class.getName() + ":" + System.currentTimeMillis());
    }

    @Override
    public void run(ApplicationArguments args) {
        start(); // 在应用程序启动后调用 start 方法
    }
}
