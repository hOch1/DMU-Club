package dmu.dmuclub.common;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.BufferedReader;
import java.io.IOException;
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


    // 서버 실행시
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {

                executeSqlScript(connection, "initDB/member.sql");
                executeSqlScript(connection, "initDB/board.sql");

                insertInitialData(connection);
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 서버 종료시
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                 Statement statement = connection.createStatement()) {

                dropTables(statement, "board");
                dropTables(statement, "member");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void insertInitialData(Connection connection) throws SQLException {
        // MEMBER
        executeUpdate(connection, "insert into member(email, password, username, nickname, phone, role) value " +
                "('user@user.com', 'user123', 'user', 'user', '010-0000-0000', 'NORMAL')");
        executeUpdate(connection, "insert into member(email, password, username, nickname, phone, role) value " +
                "('admin@admin.com', 'admin123', 'admin', 'admin', '010-1111-1111', 'ADMIN')");

        // BOARD
        executeUpdate(connection, "insert into board(title, content, member_id) value " +
                "('title1', 'content1', 1)");
        executeUpdate(connection, "insert into board(title, content, member_id) value " +
                "('title2', 'content2', 1)");
    }

    private void executeSqlScript(Connection connection, String scriptPath) throws IOException, SQLException {
        try (InputStream scriptStream = getClass().getClassLoader().getResourceAsStream(scriptPath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(scriptStream))) {

            StringBuilder script = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                script.append(line).append("\n");
            }

            executeUpdate(connection, script.toString());
        }
    }

    private void executeUpdate(Connection connection, String sql) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }

    private void dropTables(Statement statement, String tableName) throws SQLException {
        statement.executeUpdate("DROP TABLE IF EXISTS " + tableName);
    }
}
