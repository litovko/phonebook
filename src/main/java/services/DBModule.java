package services;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.text.html.parser.Entity;
import java.util.HashMap;
import java.util.Map;

/**
 * (c) Roman Gordeev
 * <p/>
 * 2014 июн 18
 */
public class DBModule extends AbstractModule
{
    @Override
    protected void configure()
    {
        bind(StorageService.class).to(HibernateStorageService.class);
    }

    @Provides
    @Singleton
    public EntityManagerFactory getFactory()
    {
        Map<String, String> props = new HashMap<>();

        props.put("hibernate.connection.driver_class", "org.postgresql.Driver");
        props.put("hibernate.connection.url", "jdbc:postgresql://localhost/phonebook1");
        props.put("hibernate.connection.username", "postgres");
        props.put("hibernate.connection.password", "123456");
        props.put("hibernate.connection.pool_size", "1");
        props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
        props.put("hibernate.hbm2ddl.auto", "update");

        return Persistence.createEntityManagerFactory("db-manager", props);

    }

    @Provides
    public EntityManager getManager(EntityManagerFactory managerFactory)
    {
        return managerFactory.createEntityManager();
    }

}
