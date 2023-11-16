package dmu.dmuclub.common;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@WebListener
public class DBInit implements ServletContextListener {
    private static final String URL = "jdbc:mysql://localhost:3306/DMUCLUB";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            // JDBC 드라이버 로딩
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 데이터베이스 연결
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {

                Statement statement = connection.createStatement();
                InputStream member = getClass().getClassLoader().getResourceAsStream("initDB/member.sql");
                InputStream board = getClass().getClassLoader().getResourceAsStream("initDB/board.sql");
                BufferedReader memberReader = new BufferedReader(new InputStreamReader(member));
                BufferedReader boardReader = new BufferedReader(new InputStreamReader(board));
                StringBuilder memberScript = new StringBuilder();
                StringBuilder boardScript = new StringBuilder();


                String memberLine;
                String boardLine;

                while ((memberLine = memberReader.readLine()) != null) {
                    memberScript.append(memberLine).append("\n");
                }

                while ((boardLine = boardReader.readLine()) != null) {
                    boardScript.append(boardLine).append("\n");
                }


                statement.executeUpdate(memberScript.toString());
                statement.executeUpdate(boardScript.toString());

                statement.execute("insert into member(email, password, username, nickname, phone, role) value ('user@user.com', 'user123', 'user', 'user', '010-0000-0000', 'NORMAL');");
                statement.execute("insert into member(email, password, username, nickname, phone, role) value ('admin@admin.com', 'admin123', 'admin', 'admin', '010-1111-1111', 'ADMIN');");

            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to initialize the database.");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load MySQL JDBC Driver.", e);
        }
    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                 Statement statement = connection.createStatement()) {

                statement.executeUpdate("DROP TABLE IF EXISTS board");
                statement.executeUpdate("DROP TABLE IF EXISTS member");

            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to drop the database.", e);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load MySQL JDBC Driver.", e);
        }
    }
}
