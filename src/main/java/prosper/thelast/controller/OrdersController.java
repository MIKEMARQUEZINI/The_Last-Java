package prosper.thelast.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prosper.thelast.DTO.Orders.OrdersDTO;
import prosper.thelast.model.Orders;
import prosper.thelast.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {

    @Autowired
    private OrderService orderService;

    public OrdersController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping()
    public List<Orders> findAll(){
        return orderService.findAll();
    }

    @GetMapping("/find/{name}")
    public ResponseEntity<List<OrdersDTO>> findOrders(@PathVariable String productName){
        List<OrdersDTO> ordersDTOS = orderService.searchName(productName);
        if(ordersDTOS != null){
            return ResponseEntity.ok(ordersDTOS);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public OrdersDTO createOrders(@RequestBody OrdersDTO order){
        return orderService.saveOrders(order);
    }

}
