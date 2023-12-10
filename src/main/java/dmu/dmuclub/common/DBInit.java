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
import java.util.Arrays;
import java.util.List;

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
                executeSqlScript(connection, "initDB/report.sql");
                executeSqlScript(connection, "initDB/friend.sql");
                executeSqlScript(connection, "initDB/chat.sql");
                executeSqlScript(connection, "initDB/chatlog.sql");

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
                dropTables(statement, "report");
                dropTables(statement, "friend");
                dropTables(statement, "chatlog");
                dropTables(statement, "chat");
                dropTables(statement, "member");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void insertInitialData(Connection connection) throws SQLException {
        executeUpdate(connection, "insert into member(email, password, username, nickname, phone, role) values " +
                "('admin@admin.com', 'admin', 'admin', 'admin', '010-1111-1111', 'ADMIN')");

        for (int i = 0; i < 30; i++) {
            String email = "user" + i + "@user.com";
            String password = "1234";
            String username = "user" + i;
            String nickname = "user" + i;
            String role = "NORMAL";
            String phone;

            if (i < 10)
                phone = "010-0000-000"+i;
            else
                phone = "010-0000-00"+i;

            List<String> mbtiList = Arrays.asList("ISTJ", "ISFJ", "ESTJ", "ESFJ", "ISTP", "ISFP", "ESTP", "ESFP",
                    "INFJ", "INTJ", "ENFJ", "ENTJ", "INTP", "ISFP", "ENFP", "ESFP");
            String mbti = mbtiList.get(i % 16);

            String sql = "insert into member(email, password, username, nickname, phone, mbti, role) value " +
                    "('" + email + "', '" + password + "', '" + username + "', '" + nickname + "', '" + phone + "', '" + mbti + "', '" + role + "')";
            executeUpdate(connection, sql);
        }


            // BOARD
        executeUpdate(connection, "insert into board(title, content, member_id) value " +
                "('title1', 'content1', 1)");
        executeUpdate(connection, "insert into board(title, content, member_id) value " +
                "('title2', 'content2', 1)");

        // REPORT
        executeUpdate(connection, "insert into report(title, content, solve, member_id) value "+
                "('title1', 'content1', 0, 1)");
        executeUpdate(connection, "insert into report(title, content, solve, member_id) value "+
                "('title2', 'content2', 1, 1)");

        // FRIEND
        executeUpdate(connection, "insert into friend(member1_id, member2_id) value "+
                "(2,3)");
        executeUpdate(connection, "insert into friend(member1_id, member2_id) value "+
                "(3,2)");
        executeUpdate(connection, "insert into friend(member1_id, member2_id) value "+
                "(2,4)");
        executeUpdate(connection, "insert into friend(member1_id, member2_id) value "+
                "(4,2)");
        executeUpdate(connection, "insert into friend(member1_id, member2_id) value "+
                "(2,5)");
        executeUpdate(connection, "insert into friend(member1_id, member2_id) value "+
                "(5,2)");

        executeUpdate(connection, "insert into chat(from_member, to_member) value "+
                "(2,4)");
        executeUpdate(connection, "insert into chat(from_member, to_member) value "+
                "(4,2)");

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
        System.out.println(tableName+" drop 완료");
    }
}
