package hibernate.starter.util;

import hibernate.starter.converter.BirthdayConverter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Util {
    public static SessionFactory buildSessionFactory() {
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        config.addAttributeConverter(new BirthdayConverter());
        return config.buildSessionFactory();
    }
}
