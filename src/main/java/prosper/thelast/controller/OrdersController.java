package prosper.thelast.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
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

    public OrdersController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping()
    public List<Orders> findAll() {
        return orderService.findAll();
    }

    @GetMapping("/search/{productName}")
    public ResponseEntity<List<OrdersDTO>> findOrders(@PathVariable String productName) {
        List<OrdersDTO> orders = orderService.searchName(productName);
        if (!CollectionUtils.isEmpty(orders)) {
            return ResponseEntity.ok(orders);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<OrdersDTO> createOrders(@RequestBody @Valid
                                                      OrdersDTO order) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.saveOrders(order));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdersDTO> updateOrders(@PathVariable String id,
                                                    @RequestBody OrdersDTO ordersDTO){
        OrdersDTO ordersUpdated = orderService.updateOrders
                (id, ordersDTO);
        if(ordersUpdated != null){
            return new ResponseEntity<>(ordersUpdated, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrders(@PathVariable String id){
        boolean deleted =  orderService.deleteOrders(id);
        if(deleted){
            return ResponseEntity.ok("Successfully deleted");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found, try again with another Orders!");
        }
    }

}
