package prosper.thelast.DTO.Orders;

import org.modelmapper.ModelMapper;
import prosper.thelast.DTO.Product.ProductDTO;
import prosper.thelast.model.Orders;
import prosper.thelast.model.Product;

public class ConverterOrdersDTO {
    private ModelMapper modelMapper = new ModelMapper();
    public OrdersDTO convertDTO(Orders productName){
        return modelMapper.map(productName, OrdersDTO.class);
    }
}
