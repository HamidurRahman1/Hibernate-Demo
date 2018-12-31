package hibernateUtil;

import entity.mapping.oneToOneUni.Instructor;
import entity.mapping.oneToOneUni.InstructorDetail;
import entity.model.employee.Employee;
import entity.model.student.Student;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class HibernateUtil
{
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory()
    {
        try
        {
            return new Configuration()
                    .addProperties(createProperties())
                    .addAnnotatedClass(Student.class)
                    .buildSessionFactory();
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

    private static Properties createProperties()
    {
        Properties properties = new Properties();
        properties.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        properties.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/hibernate?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC");
        properties.setProperty("hibernate.connection.username", "root");
        properties.setProperty("hibernate.connection.password", "pass");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("current_session_context_class", "thread");
        properties.setProperty("hibernate.id.new_generator_mappings","false");
        properties.setProperty("hbm2ddl.auto", "update");
        return properties;
    }
}
