package hibernateUtil;

import entity.Address;
import entity.Student;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil
{
    private static final SessionFactory sessionFactory = buildSessionFactory();
    private static StandardServiceRegistryBuilder registryBuilder;
    private static ServiceRegistry serviceRegistry;

    private static SessionFactory buildSessionFactory()
    {
        try
        {
            Configuration configuration = new Configuration()
                                                .setProperties(getProperties())
                                                .addAnnotatedClass(Student.class)
                                                .addAnnotatedClass(Address.class);

            registryBuilder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());

            serviceRegistry = registryBuilder.build();

            return configuration.buildSessionFactory(serviceRegistry);
        }
        catch (Throwable ex)
        {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    public static void shutdown()
    {
        if(sessionFactory != null) sessionFactory.close();
    }

    private static Properties getProperties()
    {
        Properties properties = new Properties();

        properties.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        properties.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/hibernate?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC");
        properties.setProperty("hibernate.connection.username", "root");
        properties.setProperty("hibernate.connection.password", "mysql?");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("current_session_context_class", "thread");
        properties.setProperty("hibernate.id.new_generator_mappings","false");
        properties.setProperty("hbm2ddl.auto", "update");

        return properties;
    }
}
