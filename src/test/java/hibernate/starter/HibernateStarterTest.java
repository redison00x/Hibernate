package hibernate.starter;

import hibernate.starter.entity.Birthday;
import hibernate.starter.entity.User;
import org.junit.jupiter.api.Test;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;


class HibernateStarterTest {
/*
    @Test
    public void testHibernateApi() throws SQLException, IllegalAccessException {
        var user= User.builder()
                .username("admin1@mail.ru")
                .firstname("admin")
                .lastname("adminov")
                .birthDate(new Birthday(LocalDate.of(2000, 4, 3)))
                .build();

        var sql = """
                insert into 
                %s
                (%s)
                values
                (%s)
                """;

        var tableName = Optional.ofNullable(user.getClass().getAnnotation(Table.class))
                .map(table -> table.schema() + "." + table.name())
                .orElse(user.getClass().getName());

        Field[] fields = user.getClass().getDeclaredFields();

        var fieldNames = Arrays.stream(fields)
                .map(field -> Optional.ofNullable(field.getAnnotation(Column.class))
                        .map(Column::name)
                        .orElse(field.getName())
                ).collect(Collectors.joining(", "));

        var columnValues = Arrays.stream(fields)
                .map(field -> "?")
                .collect(Collectors.joining(", "));

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres", "1212");
        PreparedStatement preparedStatement = connection
                .prepareStatement(sql.formatted(tableName, fieldNames, columnValues));

        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);  //разрешение для приват полей
            preparedStatement.setObject(i + 1, fields[i].get(user));
        }


        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();

        /*
    }

}