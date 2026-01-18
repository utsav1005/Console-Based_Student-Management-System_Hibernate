package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
        private static final SessionFactory factory = new Configuration()
                                .addAnnotatedClass(org.example.Student.class)
                                .configure("hibernate.cfg.xml").buildSessionFactory();


        //Creates A Session
        public static SessionFactory getSessionFactory() {return factory;}

}
