package com.cessadev.demo_spring_integration.service;

import com.cessadev.demo_spring_integration.model.OrderModel;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    // Valida el pedido y lo envía al canal correspondiente
    @ServiceActivator(inputChannel = "inputChannel", outputChannel = "validatedOrdersChannel")
    public OrderModel validateOrder(OrderModel order) {
        if (order.getQuantity() <= 0) {
            order.setValid(false);
            order.setErrorMessage("Cantidad no puede ser menor o igual a 0");
        } else {
            order.setValid(true);
        }
        return order;
    }

    // Procesa los pedidos válidos
    @ServiceActivator(inputChannel = "validOrdersChannel")
    public void processValidOrder(OrderModel order) {
        System.out.println("Pedido válido procesado: " + order);
    }

    // Maneja los pedidos inválidos
    @ServiceActivator(inputChannel = "invalidOrdersChannel")
    public void handleInvalidOrder(OrderModel order) {
        System.out.println("Pedido inválido: " + order.getErrorMessage());
    }
}
