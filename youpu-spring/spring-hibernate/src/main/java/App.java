import com.youpu.domain.Address;
import com.youpu.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.net.SocketPermission;
import java.util.Date;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-hibernate.xml");

        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();

        Address address1 = new Address();
        address1.setProvince("陕西省");
        address1.setCity("西安市");
        Address address2 = new Address();
        address2.setProvince("河南省");
        address2.setCity("郑州市");

        User user = new User();
        user.setUsername("baoan");
        user.setPassword("111111");
        User user2 = new User();
        user2.setUsername("amin");
        user2.setPassword("123456");

        user2.getAddress().add(address1);
        user2.getAddress().add(address2);

        try {
            session.save(user2);
            tx.commit();
        }catch (Exception ex){
            tx.rollback();
        }
    }
}
