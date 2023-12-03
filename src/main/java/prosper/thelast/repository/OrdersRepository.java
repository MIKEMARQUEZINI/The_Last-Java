package prosper.thelast.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import prosper.thelast.model.Orders;

import java.util.List;

public interface OrdersRepository extends MongoRepository<Orders, String > {
    List<Orders> findbyOrders(String productName);
}
