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
    public ProductDTO createProduct(@RequestBody ProductDTO product) {
        messageProducer.sendMessage(String.format("Product %s registered successfully!",product.toString()));
        return productService.saveProduct(product);
    }



}
