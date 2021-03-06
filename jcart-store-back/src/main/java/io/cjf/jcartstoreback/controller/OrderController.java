package io.cjf.jcartstoreback.controller;

import io.cjf.jcartstoreback.dto.in.OrderCheckoutInDTO;
import io.cjf.jcartstoreback.dto.out.OrderListOutDTO;
import io.cjf.jcartstoreback.dto.out.OrderShowOutDTO;
import io.cjf.jcartstoreback.dto.out.PageOutDTO;
import io.cjf.jcartstoreback.dto.out.ProductShowOutDTO;
import io.cjf.jcartstoreback.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/checkout")
    public Integer checkout(@RequestBody OrderCheckoutInDTO orderCheckoutInDTO,
                            @RequestAttribute Integer customerId){

        return null;
    }

    @GetMapping("/getList")
    public PageOutDTO<OrderListOutDTO> getList(@RequestAttribute Integer customerId){
        return null;
    }

    @GetMapping("/getById")
    public OrderShowOutDTO getById(@RequestParam Long orderId){
        return null;
    }
}
