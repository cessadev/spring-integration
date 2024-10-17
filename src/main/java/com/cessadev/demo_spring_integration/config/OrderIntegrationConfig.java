package com.cessadev.demo_spring_integration.config;

import com.cessadev.demo_spring_integration.model.OrderModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.router.RecipientListRouter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

@Configuration
public class OrderIntegrationConfig {

    // Canal de entrada para recibir pedidos
    @Bean
    public MessageChannel inputChannel() {
        return new DirectChannel();
    }

    // Canal para pedidos después de la validación
    @Bean
    public MessageChannel validatedOrdersChannel() {
        return new DirectChannel();
    }

    // Canal de pedidos válidos
    @Bean
    public MessageChannel validOrdersChannel() {
        return new DirectChannel();
    }

    // Canal de pedidos inválidos
    @Bean
    public MessageChannel invalidOrdersChannel() {
        return new DirectChannel();
    }

    // Enrutador para pedidos válidos e inválidos
    @Bean
    @ServiceActivator(inputChannel = "validatedOrdersChannel")
    public RecipientListRouter router() {
        RecipientListRouter router = new RecipientListRouter();
        router.addRecipient("validOrdersChannel", this::isValidOrder);
        router.addRecipient("invalidOrdersChannel", this::isInvalidOrder);
        return router;
    }

    // Verifica si el pedido es válido
    private boolean isValidOrder(Message<?> message) {
        OrderModel order = (OrderModel) message.getPayload();
        return order.isValid();
    }

    // Verifica si el pedido es inválido
    private boolean isInvalidOrder(Message<?> message) {
        OrderModel order = (OrderModel) message.getPayload();
        return !order.isValid();
    }
}
