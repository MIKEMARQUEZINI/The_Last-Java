package prosper.thelast.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import prosper.thelast.DTO.Orders.OrdersDTO;
import prosper.thelast.model.Orders;
import prosper.thelast.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {

    @Autowired
    private OrderService orderService;

    public OrdersController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping()
    public List<Orders> findAll() {
        return orderService.findAll();
    }

    @GetMapping("/find/{productName}")
    public ResponseEntity<List<OrdersDTO>> findOrders(@PathVariable String productName) {
        List<OrdersDTO> orders = orderService.searchName(productName);
        if (orders != null) {
            return ResponseEntity.ok(orders);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public OrdersDTO createOrders(@RequestBody OrdersDTO order) {
        return orderService.saveOrders(order);
    }

}
