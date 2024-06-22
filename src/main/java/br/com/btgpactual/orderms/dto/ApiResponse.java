package br.com.btgpactual.orderms.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public record ApiResponse<T>(List<T> data,
                            //BigDecimal totalOrders,
                             Map<String, Object> summary,
                             PaginationResponse pagination) {
}
