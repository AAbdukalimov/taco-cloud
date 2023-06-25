package sia.tacocloud.configs;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue tacoOrderQueue() {
        return new Queue("tacocloud.order.exchange");
    }

    @Bean
    public Queue tacoQueue() {
        return new Queue("tacocloud.taco.exchange");
    }

    @Bean
    public DirectExchange tacoOrderExchange() {
        return new DirectExchange("tacocloud.order.exchange");
    }

    @Bean
    public Binding bindingTacoOrder(Queue tacoOrderQueue, DirectExchange tacoOrderExchange) {
        return BindingBuilder.bind(tacoOrderQueue).to(tacoOrderExchange).with("");
    }

    @Bean
    public Binding bindingTaco(Queue tacoQueue, DirectExchange tacoOrderExchange) {
        return BindingBuilder.bind(tacoQueue).to(tacoOrderExchange).with("");
    }

}
