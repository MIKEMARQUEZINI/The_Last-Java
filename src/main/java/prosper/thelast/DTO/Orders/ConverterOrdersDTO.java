package prosper.thelast.DTO.Orders;

import org.modelmapper.ModelMapper;
import prosper.thelast.model.Orders;

public class ConverterOrdersDTO {
    private ModelMapper modelMapper = new ModelMapper();
    public OrdersDTO convertDTO(Orders productName){
        return modelMapper.map(productName, OrdersDTO.class);
    }
}