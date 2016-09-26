package my.example.repository;

import my.example.domain.Item;
import org.springframework.data.neo4j.repository.Neo4jRepository;

/**
 * @author Xavi Torrens
 */
public interface ExampleRepository extends Neo4jRepository<Item> {

}
