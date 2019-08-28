
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class DBConnection {
    protected static Connection initDatabase() throws ClassNotFoundException, SQLException{
        String dbDriver="com.mysql.jdbc.Driver";
        Class.forName(dbDriver);
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/servletDB", "root", "");
        return connection;
    }
}
