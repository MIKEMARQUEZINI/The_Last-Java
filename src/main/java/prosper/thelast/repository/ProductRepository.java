package prosper.thelast.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import prosper.thelast.model.Product;

import java.util.List;

public interface ProductRepository extends MongoRepository <Product, String> {
    List<Product> findByName(String name);
}
