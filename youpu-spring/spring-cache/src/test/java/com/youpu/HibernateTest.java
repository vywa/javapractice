package com.youpu;

import com.youpu.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class HibernateTest extends SpringTestCase{

    @Test
    public void get(){

        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        User user = new User();
        user.setUsername("baoan");
        user.setPassword("111111");
        try{
            session.save(user);
            tx.commit();

        }catch (Exception ex){
            tx.rollback();
        }

    }
}
