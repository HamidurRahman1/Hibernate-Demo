package controller;

import entity.mapping.oneToOneUni.Student;
import hibernateUtil.HibernateUtil;
import org.hibernate.Session;

import java.util.List;
import static java.lang.System.out;

public class TestStudent
{
    public static void main(String[] args)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Student> students;

        try
        {
            session.beginTransaction();

            students = session.createQuery("from Student").getResultList();
            displayStudent(students);

            session.getTransaction().commit();

        }
        catch(Exception ex)
        {
            out.println(ex.getMessage());
        }
        finally
        {
            session.close();
            HibernateUtil.shutdown();
        }
    }

    private static void displayStudent(List<Student> students)
    {
        for(Student student : students)
        {
            out.println(student);
        }
    }


}
