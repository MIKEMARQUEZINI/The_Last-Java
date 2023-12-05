package prosper.thelast.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import prosper.thelast.model.Orders;


public interface OrdersRepository extends MongoRepository<Orders, String > {
    List<Orders> findByProductName(String productName);
}
