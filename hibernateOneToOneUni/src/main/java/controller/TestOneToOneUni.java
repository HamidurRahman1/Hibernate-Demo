package controller;

import entity.Address;
import entity.Student;
import hibernateUtil.HibernateUtil;
import org.hibernate.Session;

import static java.lang.System.in;
import static java.lang.System.out;

public class TestOneToOneUni
{
    public static void main(String[] args)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try
        {
            Student s1 = new Student("Test", "Test", "Test");
            Address address = new Address("a", 12, "c", "d", 11111);
            s1.setAddress(address);
            Student s2;

            session.beginTransaction();

            session.save(s1);
            out.println(s1);

            s2 = session.get(Student.class, s1.getId());
            session.delete(s2);

            session.getTransaction().commit();
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
