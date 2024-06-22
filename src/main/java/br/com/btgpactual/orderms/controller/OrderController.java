package br.com.btgpactual.orderms.controller;

import br.com.btgpactual.orderms.dto.ApiResponse;
import br.com.btgpactual.orderms.dto.OrderResponse;
import br.com.btgpactual.orderms.dto.PaginationResponse;
import br.com.btgpactual.orderms.service.OrderService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/customers/{customerId}/orders")
    public ResponseEntity<ApiResponse<OrderResponse>> listOrders(
            @PathVariable("customerId") Long customerId,
            @RequestParam(name="page", defaultValue="0") Integer page,
            @RequestParam(name="pageSize", defaultValue="10") Integer pageSize
    ){

     var body = orderService.findAllByCustomerId(customerId, PageRequest.of(page, pageSize));

        //BigDecimal totalOrders = body.stream().map(OrderResponse::total).reduce(BigDecimal.ZERO, BigDecimal::add);

        var totalOnOrders = orderService.findTotalOnOrdersByCustomerId(customerId);

    return ResponseEntity.ok(new ApiResponse<>(
            body.getContent(),
            //totalOrders,
            Map.of("totalOnOrders", totalOnOrders),
            PaginationResponse.fromPage(body)
    ));

    }

}
