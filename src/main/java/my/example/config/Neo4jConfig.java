package my.example.config;


import my.example.domain.Item;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.neo4j.ogm.session.event.Event;
import org.neo4j.ogm.session.event.EventListenerAdapter;
import org.springframework.context.annotation.*;
import org.springframework.data.neo4j.repository.config.EnableExperimentalNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Calendar;
import java.util.UUID;

@Configuration
@ComponentScan(value = "my.example")
@EnableExperimentalNeo4jRepositories(basePackages = "my.example.repository")
@EnableTransactionManagement

public class Neo4jConfig {

    @Bean
    public org.neo4j.ogm.config.Configuration getConfiguration() {
        org.neo4j.ogm.config.Configuration config = new org.neo4j.ogm.config.Configuration();

        config
                .driverConfiguration()
                .setDriverClassName("org.neo4j.ogm.drivers.http.driver.HttpDriver")
                .setURI("http://hobby-ldoabpkfbjnmgbkepeckgknl.dbs.graphenedb.com:24789/")
                .setCredentials("backup", "eRgcVp0J9PqKGrMkB2mw");

        return config;
    }

    @Bean
    public Neo4jTransactionManager transactionManager() throws Exception {
        return new Neo4jTransactionManager(sessionFactory());
    }

    @Bean
    public SessionFactory sessionFactory() {
        return new SessionFactory(getConfiguration(), "my.example.domain") {

            @Override
            public Session openSession() {
                Session session = super.openSession();
                session.register(new EventListenerAdapter() {
                    @Override
                    public void onPreSave(Event event) {
                        Object eventObject = event.getObject();
                        ((Item) eventObject).setId(UUID.randomUUID().toString());

                    }
                });


                return session;
            }
        };
    }

    @Bean
    @Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Session getSession() throws Exception {
        return sessionFactory().openSession();
    }


}
