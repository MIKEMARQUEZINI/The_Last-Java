package prosper.thelast.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import prosper.thelast.DTO.Product.ProductDTO;
import prosper.thelast.model.Product;
import prosper.thelast.service.ProductService;

import java.util.List;
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

    @GetMapping("/search/{name}")
    public ResponseEntity<List<ProductDTO>> findProducts(@PathVariable String name) {
        List<ProductDTO> product = productService.searchName(name);
        if (!CollectionUtils.isEmpty(product)) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody @Valid
                                                        ProductDTO product) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.saveProduct(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable String id,
                                                    @RequestBody ProductDTO productDTO){
        ProductDTO updatedProduct = productService.updateProduct
                (id, productDTO);
        if(updatedProduct != null){
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletedProduct(@PathVariable String id){
        boolean deleted =  productService.deleteProduct(id);
        if(deleted){
            return ResponseEntity.ok("Successfully deleted");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found, try again with another Product!");
        }
    }


}
