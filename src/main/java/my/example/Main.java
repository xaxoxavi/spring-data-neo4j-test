package my.example;

import my.example.config.Neo4jConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Xavi Torrens
 */
public class Main {

    public static void main(String[] args){
        ApplicationContext ctx =
                new AnnotationConfigApplicationContext(Neo4jConfig.class);

        Test test = ctx.getBean(Test.class);
        test.doTest();

    }
}
