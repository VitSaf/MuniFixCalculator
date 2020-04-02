package DbOperations;

import Frames.Calculator1Frame;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertOperations {

    public static boolean insertIntoTable(Connection conn, Calculator1Frame calculator1Frame){
        String sql = "INSERT INTO 'calc1' ('var1', 'var2' ,'var3','result') VALUES (?,?,?,?);";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setDouble(1,calculator1Frame.getVar1());
            preparedStatement.setDouble(2,calculator1Frame.getVar2());
            preparedStatement.setDouble(3,calculator1Frame.getVar3());
            preparedStatement.setDouble(4,calculator1Frame.getResult());

            return preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
    public static boolean insertIntoTable(Connection conn, Calculator2Frame calculator2Frame){
        String sql = "INSERT INTO 'calc2 ('var1', 'var2', 'var3' , 'result') VALUES (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setDouble(1, calculator2Frame.getVar1());
            preparedStatement.setDouble(2, calculator2Frame.getVar2());
            preparedStatement.setDouble(3, calculator2Frame.getVar3());
            preparedStatement.setDouble(4, calculator2Frame.getAddMaslo());
            return preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }*/


}
