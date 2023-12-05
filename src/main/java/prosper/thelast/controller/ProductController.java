package prosper.thelast.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import prosper.thelast.DTO.Product.ProductDTO;
import prosper.thelast.model.Product;
import prosper.thelast.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<Product> findAll() {
        return productService.findAll();
    }
    
    @GetMapping("/find/{name}")
    public ResponseEntity<List<ProductDTO>> findProducts(@PathVariable String name) {
        List<ProductDTO> product = productService.searchName(name);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping
    public ProductDTO createProduct(@RequestBody ProductDTO product) {
        return productService.saveProduct(product);
    }


}
