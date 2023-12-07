package prosper.thelast.IntegrationTest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
        basePackages = "prosper.thelast.repository")
@ComponentScan(basePackages = "prosper.thelast")
public class MongoDBSettings {
    @Bean
    public MongoTemplate mongoTemplate () {
        String MongoDBUri = "mongodb://0.0.0.0:27017/meumongo";
        return new MongoTemplate(
                new SimpleMongoClientDatabaseFactory(MongoDBUri));

    }
}
