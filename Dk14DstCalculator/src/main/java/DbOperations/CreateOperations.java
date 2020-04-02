package DbOperations;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateOperations {

    public static void createTable(Connection conn){
        try(conn; Statement stmt = conn.createStatement()){
            String sql = "CREATE TABLE if not exists 'users' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'var1' INT, 'var2' INT, 'var3' INT, 'result' INT);";//создавать необходимую таблицу
            stmt.execute(sql);                                                                                                    //возможно, стоит изменить на preparedStatement
                                                                                                                                  //и название таблицы передавать как аргумент функции
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

/*    public static void main(String[] args){

        try {
            createTable(ConnectOperations.getConnection("C:\\Users\\safonovva\\IdeaProjects\\Dk14DstCalculator\\test.db"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }*/
}
