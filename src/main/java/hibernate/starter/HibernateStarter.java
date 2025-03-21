package hibernate.starter;


import hibernate.starter.converter.BirthdayConverter;
import hibernate.starter.entity.Birthday;
import hibernate.starter.entity.Role;
import hibernate.starter.entity.User;
import hibernate.starter.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

public class HibernateStarter {

    private static final Logger log = LoggerFactory.getLogger(HibernateStarter.class);

    public static void main(String[] args) {

        User user = User.builder()
                .username("admin@mail.ru")
                .firstname("admin")
                .lastname("adminov")
                .birthDate(new Birthday(LocalDate.of(2000, 4, 3)))
                .role(Role.ADMIN)
                .build();
        log.info("User object in transient state: {}", user);

        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        cfg.addAttributeConverter(new BirthdayConverter() , true);


        try (SessionFactory sessionFactory = Util.buildSessionFactory()){
            try (Session session1 = sessionFactory.openSession()) {
                session1.beginTransaction();

                //session1.saveOrUpdate(user);

                user.setFirstname("Pol");
                log.warn("User firstname is changed: {}", user);
                System.out.println(session1.isDirty());
                session1.get(User.class, "admin@mail.ru");
                System.out.println(user);
                log.debug("User: {}, session: {}", user, session1);
                session1.getTransaction().commit();


            }
        } catch (Exception e){
            log.error("Exception occurred: {}", user);
        }
    }
}

