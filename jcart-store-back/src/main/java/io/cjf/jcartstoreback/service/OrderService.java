package io.cjf.jcartstoreback.service;

import io.cjf.jcartstoreback.dto.in.OrderCheckoutInDTO;

public interface OrderService {
    Long   create(OrderCheckoutInDTO orderCheckoutInDTO0,Integer customerId);
}
