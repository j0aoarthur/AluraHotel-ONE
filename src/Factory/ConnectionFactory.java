package Factory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private DataSource dataSource;

    public ConnectionFactory() {
        Properties properties = getProperties();
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl(properties.getProperty("dataBase.url"));
        comboPooledDataSource.setUser(properties.getProperty("dataBase.user"));
        comboPooledDataSource.setPassword(properties.getProperty("dataBase.password"));

        this.dataSource = comboPooledDataSource;
    }

    public Connection recuperarConexao() {
        try {
            return this.dataSource.getConnection();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Properties getProperties(){
        Properties properties = new Properties();
        try {
            properties.load(ConnectionFactory.class.getResourceAsStream("/connection.properties"));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

}
