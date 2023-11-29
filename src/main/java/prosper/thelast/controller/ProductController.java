package prosper.thelast.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prosper.thelast.DTO.Product.ProductDTO;
import prosper.thelast.exceptions.InvalidProductException;
import prosper.thelast.message.MessageProducer;
import prosper.thelast.model.Product;
import prosper.thelast.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private MessageProducer messageProducer;

    @Autowired
    private ProductService productService;


    public ProductController(MessageProducer messageProducer, ProductService productService) {
        this.messageProducer = messageProducer;
        this.productService = productService;
    }

    @GetMapping
    public List<Product> findAll(){
        return productService.findAll();
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO product) {
        try {
            // Validate the product data before saving
            validateProduct(product);
            // Save the validated product
            ProductDTO savedProduct = productService.saveProduct(product);
            // Return a response with the saved product
            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
        } catch (InvalidProductException e) {
            // Handle the exception and return an appropriate response
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    // Validate the product data
    private void validateProduct(ProductDTO product) throws InvalidProductException {
        // For example, check if required fields are present or if values are within acceptable ranges
        if (product.getName() == null || product.getName().isEmpty()) {
            throw new InvalidProductException("Product name cannot be empty");
        }

    }



}
