package prosper.thelast.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import prosper.thelast.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByName(String name);
    Product findOneProductByName(String nome);

}
