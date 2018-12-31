package controller;

import entity.Address;
import entity.Student;
import hibernateUtil.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import static java.lang.System.in;
import static java.lang.System.out;

public class TestOneToOneBiDir
{
    private static SessionFactory sessionFactory;

    public static void main(String[] args)
    {
        sessionFactory = HibernateUtil.getSessionFactory();

        Session session = sessionFactory.openSession();

        try
        {
            Student student = new Student("First name", "Last name", "Major");
            Address address = new Address("avenue", 5, "street", "city", 11111);
            student.setAddress(address);

            Student s;
            Address a;

            Transaction tx = session.getTransaction();
            tx.begin();

            session.save(student);

            a = session.get(Address.class, 2);
            a.getStudent().setFirstName("new First n");

            s = session.get(Student.class, 2);
            s.getAddress().setStreet("new Street");

            out.print(s);

            a = session.get(Address.class, 8);
            session.delete(a);

            tx.commit();
        }
        catch (Exception ex)
        {
            out.print(ex.getMessage());
        }
        finally
        {
            session.close();
            HibernateUtil.shutdown();
        }
    }
}
