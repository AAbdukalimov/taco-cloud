package sia.tacocloud;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import sia.tacocloud.configs.OrderProperties;

@EnableConfigurationProperties(OrderProperties.class)
@SpringBootApplication
@EnableRabbit
public class TacoCloudApplication {

    public static void main(String[] args) {

       SpringApplication.run(TacoCloudApplication.class, args);
    }

}
