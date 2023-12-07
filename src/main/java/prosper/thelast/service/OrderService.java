package prosper.thelast.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import prosper.thelast.DTO.Orders.OrdersDTO;
import prosper.thelast.DTO.Product.ProductDTO;
import prosper.thelast.model.Orders;
import prosper.thelast.model.Product;
import prosper.thelast.repository.OrdersRepository;

@Service
public class OrderService {
    private final OrdersRepository ordersRepository;
    private ModelMapper modelMapper = new ModelMapper();

    public OrderService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public Orders convertDTO(OrdersDTO ordersDTO) {
        return modelMapper.map(ordersDTO, Orders.class);
    }

    public OrdersDTO convertOrders(Orders orders) {
        return modelMapper.map(orders, OrdersDTO.class);
    }

    public OrdersDTO saveOrders(OrdersDTO ordersDTO) {
        Orders orders = convertDTO(ordersDTO);
        return convertOrders(ordersRepository.save(orders));
    }

    public Optional<Orders> findById(String id) {
        ordersRepository.findByProductName(id);
        return ordersRepository.findById(id);
    }

    public List<Orders> findAll() {
        return ordersRepository.findAll();
    }

    public List<OrdersDTO> searchName(String productName) {
        List<Orders> listOrders = ordersRepository.findByProductName(productName);
        List<OrdersDTO> listOrdersDTO = listOrders.stream()
                .map(source -> modelMapper.map(source, OrdersDTO.class))
                .collect(Collectors.toList());
        return listOrdersDTO;
    }

    public OrdersDTO updateOrders(String id, OrdersDTO ordersDTO) {
        Orders ordersExists = ordersRepository.findById(id).
                orElse(null);
        if(ordersExists != null){
            ordersExists.setProductName(ordersDTO.getProductName());
            ordersExists.setPointSales(ordersDTO.getPointSales());
            ordersExists.setQuantity(ordersDTO.getQuantity());

            return convertOrders(ordersRepository.save(ordersExists));
        } else {
            return null;
        }

    }

    public boolean deleteOrders(String id) {
        Optional<Orders> ordersOptional = ordersRepository.findById(id);

        if (ordersOptional.isPresent()) {
            ordersRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}