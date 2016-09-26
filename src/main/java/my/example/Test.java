package my.example;

import my.example.domain.Item;
import my.example.repository.ExampleRepository;
import org.neo4j.ogm.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author Xavi Torrens
 */
@Component
public class Test {

    @Autowired
    private ExampleRepository repository;

    @Autowired
    @Qualifier("getSession")
    private Session sesiones;

    public void doTest(){
        Item item = new Item();
        item.setContent("Content");
        sesiones.save(item);
    }

}
