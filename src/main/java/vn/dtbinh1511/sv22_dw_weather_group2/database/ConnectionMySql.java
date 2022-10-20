package vn.dtbinh1511.sv22_dw_weather_group2.database;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionMySql {
    private String url;
    private String username;
    private String password;
    private String database;

    public ConnectionMySql(String database) {
        try {
            this.database = database;
            config();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    private void config() throws IOException {
        Properties prop = new Properties();
        prop.load(ConnectionMySql.class.getClassLoader().getResourceAsStream("db.properties"));
        this.username = prop.getProperty("db.username");
        this.password = prop.getProperty("db.password");
        this.url = prop.getProperty("db.url");
    }

    public Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(url.concat(database), username, password);
        if (connection != null) {
            System.out.println("Access successful " + database + " database");
        } else {
            System.out.println("Access failed " + database + " database");
        }
        return connection;
    }

}
