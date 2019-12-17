package xyz.gaoliqing.production;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
@EnableHystrixDashboard
@RestController
@RefreshScope
public class ProductionApplication {

    @Value("${server.version}")
    private String version;
    public static void main(String[] args) {
        SpringApplication.run(ProductionApplication.class, args);
    }

    @GetMapping("/info")
    public String getPort() {

        return "配置文件的版本是: "+ version;
    }
}
