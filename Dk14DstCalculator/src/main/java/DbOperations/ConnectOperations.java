package DbOperations;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectOperations {
    public static void tryDbConnection(String pathToDb){
        String url = "jdbc:sqlite:" + pathToDb;
        try (Connection conn = DriverManager.getConnection(url)) {
            if(conn != null){
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getCatalogs());

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(String pathToDb) throws SQLException {
        String url = "jdbc:sqlite:" + pathToDb;
        return DriverManager.getConnection(url);
    }

    /*public static void main(String[] args) {
        tryDbConnection("C:\\Users\\safonovva\\IdeaProjects\\Dk14DstCalculator\\test.db");
    }*/
}
