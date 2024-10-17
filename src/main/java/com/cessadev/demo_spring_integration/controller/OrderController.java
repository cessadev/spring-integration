package com.cessadev.demo_spring_integration.controller;

import com.cessadev.demo_spring_integration.model.OrderModel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final MessageChannel inputChannel;

    public OrderController(MessageChannel inputChannel) {
        this.inputChannel = inputChannel;
    }

    @PostMapping
    public String placeOrder(@RequestBody OrderModel order) {
        inputChannel.send(MessageBuilder.withPayload(order).build());
        return "Pedido enviado.";
    }
}
