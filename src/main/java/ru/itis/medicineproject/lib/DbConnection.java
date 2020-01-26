package ru.itis.medicineproject.lib;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {

    private Connection connection;

    private void setupConnection() throws IOException, ClassNotFoundException, SQLException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("app.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        String url = properties.getProperty("db.url");
        String password = properties.getProperty("db.password");
        String login = properties.getProperty("db.user");
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(url, login, password);
    }

    public DbConnection() {
        try {
            this.setupConnection();
        } catch (SQLException e) {
            System.out.println("Can`t get connection");
            throw new IllegalArgumentException(e);
        } catch (ClassNotFoundException e) {
            System.out.println("Can`t find driver class");
            throw new IllegalArgumentException(e);
        } catch (FileNotFoundException e) {
            System.out.println("Can`t find properties file");
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            System.out.println("Properties load exception");
            throw new IllegalArgumentException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

}
