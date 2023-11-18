package dmu.dmuclub.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCTemplate {

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:mysql://localhost:3306/DMUCLUB";
        String user = "root";
        String password = "admin";

        Connection con = null;

        try{
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e){
            e.printStackTrace();
        }

        return con;
    }
}
