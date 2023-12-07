package prosper.thelast.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import prosper.thelast.DTO.Product.ProductDTO;
import prosper.thelast.model.Product;
import prosper.thelast.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Method to convert a ProductDTO to a Product entity
    public Product convertDTO(ProductDTO productDTO) {
        return modelMapper.map(productDTO, Product.class);
    }

    // Method to convert a Product entity to a ProductDTO
    public ProductDTO convertProduct(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }

    // Method to save a ProductDTO, converting it to a Product entity and then
    // converting the result back to a ProductDTO
    public ProductDTO saveProduct(ProductDTO productDTO) {
        Product product = convertDTO(productDTO);
        return convertProduct(productRepository.save(product));
    }

    // Method to find a Product by its ID
    public Optional<Product> findById(String id) {
        productRepository.findByName(id); // Assuming this line is meant to be executed for some reason
        return productRepository.findById(id);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<ProductDTO> searchName(String name) {
        List<Product> listProductName = productRepository.findByName(name);
        List<ProductDTO> listProductDTO = listProductName.stream()
                .map(source -> modelMapper.map(source, ProductDTO.class))
                .collect(Collectors.toList());
        return listProductDTO;
    }

    public ProductDTO updateProduct(String id, ProductDTO productDTO) {
        Product productExists = productRepository.findById(id).
                orElse(null);
        if(productExists != null){
            productExists.setName(productDTO.getName());
            productExists.setDescriptions(productDTO.getDescriptions());
            productExists.setPrice(productDTO.getPrice());

            return convertProduct(productRepository.save(productExists));
        } else {
            return null;
        }

    }

    public boolean deleteProduct(String id) {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isPresent()) {
            productRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
