package prosper.thelast.service;

import org.modelmapper.ModelMapper;
import prosper.thelast.DTO.Orders.OrdersDTO;
import prosper.thelast.model.Orders;
import prosper.thelast.repository.OrdersRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderService {
    private final OrdersRepository ordersRepository;
    
    private ModelMapper modelMapper = new ModelMapper();
    public OrderService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }
    
    public OrdersDTO saveOrders(OrdersDTO ordersDTO){
        Orders orders = convertDTO(ordersDTO);
        return convertOrders(ordersRepository.save(orders));
    }

    public Orders convertDTO(OrdersDTO ordersDTO) {
        return modelMapper.map(ordersDTO, Orders.class);
    }

    public OrdersDTO convertOrders (Orders orders){
        return modelMapper.map(orders, OrdersDTO.class);
    }

    public Optional<Orders> findById(String id){
        ordersRepository.findById(id);
        return ordersRepository.findById(id);
    }


    public List<Orders> findAll() {
        return ordersRepository.findAll();
    }

    public List<OrdersDTO> searchName(String productName) {
        List<Orders> listOrders = ordersRepository.findbyOrders(productName);
        List<OrdersDTO> listDTO = listOrders.stream()
                .map(source -> modelMapper.map(source, OrdersDTO.class))
                .collect(Collectors.toList());
        return listDTO;
    }
}
