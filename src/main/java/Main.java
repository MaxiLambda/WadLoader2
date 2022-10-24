import entities.Human;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.
                getConnection("jdbc:h2:~/wad-loader", "sa", "sa");
        // add application code here

        Human student = new Human("Ramesh2", "Fadatare", "rameshfadatare@javaguides.com");
        Human student1 = new Human("John2", "Cena", "john@javaguides.com");
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student objects
            session.persist(student);
            session.persist(student1);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
               transaction.rollback();
            }
            e.printStackTrace();
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Human> students = session.createQuery("from Human", Human.class).list();
            students.forEach(s -> System.out.println(s.getFirstName()));
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        conn.commit();
        conn.close();
    }
}
