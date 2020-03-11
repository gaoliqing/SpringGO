package xyz.gaoliqing.configserver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
class ConfigServerApplicationTests {


    @Test
    void contextLoads() {

        SnowFlake snowFlake = new SnowFlake(0, 0);
        long nextId = snowFlake.nextId();
        System.out.println(nextId);
    }

}
