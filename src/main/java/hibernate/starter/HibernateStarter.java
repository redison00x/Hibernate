package hibernate.starter;


import org.hibernate.cfg.Configuration;

public class HibernateStarter {
    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        try (var sessionFactory = cfg.buildSessionFactory();
        var session = sessionFactory.openSession()){
            System.out.println("ok");

        }

    }
}
