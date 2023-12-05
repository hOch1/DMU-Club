package dmu.dmuclub.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCTemplate {

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/DMUCLUB";
            String user = "root";
            String password = "admin";

            Connection con = DriverManager.getConnection(url, user, password);

            return con;
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void close(PreparedStatement statement, Connection con) throws SQLException {
        statement.close();
        con.close();
    }
}
