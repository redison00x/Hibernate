package hibernate.starter;


import hibernate.starter.converter.BirthdayConverter;
import hibernate.starter.entity.Birthday;
import hibernate.starter.entity.Role;
import hibernate.starter.entity.User;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

public class HibernateStarter {
    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        cfg.addAttributeConverter(new BirthdayConverter() , true);
  //      cfg.addAnnotatedClass(User.class);

        try (var sessionFactory = cfg.buildSessionFactory();
        var session = sessionFactory.openSession()){
            session.beginTransaction();

            User user = User.builder()
                            .username("admin@mail.ru")
                            .firstname("admin")
                            .lastname("adminov")
                            .birthDate(new Birthday(LocalDate.of(2000, 4, 3)))
                            .role(Role.ADMIN)
                    .build();

            session.save(user);
            session.update(user);
            session.saveOrUpdate(user);
            session.getTransaction().commit();
        }
    }
}
