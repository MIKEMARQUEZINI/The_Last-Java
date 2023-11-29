package prosper.thelast.DTO.Product;

import org.modelmapper.ModelMapper;
import prosper.thelast.model.Product;

public class ConverterProductDTO {
    private ModelMapper modelMapper = new ModelMapper();
    public ProductDTO convertDTO(Product product){
        return modelMapper.map(product, ProductDTO.class);
    }
}
