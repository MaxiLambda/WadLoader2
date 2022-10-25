import model.Wad;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.nio.file.Path;
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

        Wad wad1 = new Wad(Path.of("wads/monsters/creeper.pk3"));
        Wad wad2 = new Wad(Path.of("wads/weapons/weapons.pk3"));
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student objects
            session.merge(wad1);
            session.merge(wad2);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
               transaction.rollback();
            }
            e.printStackTrace();
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Wad> students = session.createQuery("from Wad", Wad.class).list();
            students.forEach(w -> System.out.println(w.getPath()));
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        conn.commit();
        conn.close();
    }
}
