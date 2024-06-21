package br.com.btgpactual.orderms.dto;

import java.util.List;

public record OrderCreatedEvent(
        Long codigoPedido,
        long codigoCliente,
        List<OrderItemEvent> itens
) {
}
