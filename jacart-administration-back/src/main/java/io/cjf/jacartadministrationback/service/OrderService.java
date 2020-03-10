package io.cjf.jacartadministrationback.service;

import com.github.pagehelper.Page;
import io.cjf.jacartadministrationback.dto.in.OrderSearchInDTO;
import io.cjf.jacartadministrationback.dto.out.OrderListOutDTO;
import io.cjf.jacartadministrationback.dto.out.OrderShowOutDTO;
import io.cjf.jacartadministrationback.po.Order;

public interface OrderService {

    Page<OrderListOutDTO> search(OrderSearchInDTO orderSearchInDTO, Integer pageNum);

    OrderShowOutDTO getById(Long orderId);

    void update(Order order);

}
