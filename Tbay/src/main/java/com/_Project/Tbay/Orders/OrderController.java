package com._Project.Tbay.Orders;

import com._Project.Tbay.Seller.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService service;
    @Autowired
    @Lazy
    private SellerService sellerService;

    @PostMapping("/completeOrder")
    public String completeOrder(@RequestParam("orderId") long orderId, @RequestParam("sellerId") long sellerId){
        sellerService.addCompletedOrder(sellerId, orderId);
        sellerService.removeOrder(sellerId, orderId);
        return "redirect:/seller/completedOrders/" + sellerId;
    }

}
