package com.cessadev.demo_spring_integration.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderModel {

    private String id;
    private String product;
    private int quantity;
    private boolean valid;
    private String errorMessage;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderModel order)) return false;
        return quantity == order.quantity && valid == order.valid && Objects.equals(id, order.id) && Objects.equals(product, order.product) && Objects.equals(errorMessage, order.errorMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, quantity, valid, errorMessage);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", product='" + product + '\'' +
                ", quantity=" + quantity +
                ", valid=" + valid +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
