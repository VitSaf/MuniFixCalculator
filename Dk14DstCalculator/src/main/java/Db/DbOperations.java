package Db;

import java.sql.*;

public class DbOperations {

    private static final String DB_PATH = "C:\\Users\\bobca\\OneDrive\\Документы\\ПЭК.ПИ\\IdeaProjects\\Dk14DstCalculator\\DSSK_DB.s3db";

    public static void tryDbConnection(String pathToDb){
        String url = "jdbc:sqlite:" + pathToDb;
        try (Connection conn = DriverManager.getConnection(url)) {
            if(conn != null){
                Statement stmt = conn.createStatement();
                //System.out.println(stmt.execute("SELECT * FROM DSSK_Logging"));
                //stmt.execute("insert into DSSK_Logging (muni_was, muni_now) values ('1', '1')");
                ResultSet set = stmt.executeQuery("SELECT * FROM DSSK_Logging");
                System.out.println(set.getFloat("muni_now") + " " + " " + set.getFloat("muni_was") + " " + set.getString("date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:sqlite:" + DB_PATH;
        return DriverManager.getConnection(url);
    }

    //public static boolean insert(Connection con ,float muniWas, float muniNow, float muniBatarei, float masloWas, float masloNow, float ro, float suhoyOstatok, float addMaslo, float addPolimer) throws SQLException {
    public static boolean insert(Connection con, Record r) throws SQLException{
        PreparedStatement pstmt = con.prepareStatement("insert into DSSK_Logging (muni_was, muni_now, muni_batarei, maslo_was, maslo_now, ro, suhoy_ostatok, add_maslo, add_polimer) " +
                "                                           values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
        pstmt.setFloat(1, r.getMuniWas());
        pstmt.setFloat(2, r.getMuniNow());
        pstmt.setFloat(3, r.getMuniBatarei());
        pstmt.setFloat(4, r.getMasloWas());
        pstmt.setFloat(5, r.getMasloNow());
        pstmt.setFloat(6, r.getRo());
        pstmt.setFloat(7, r.getSuhoyOstatok());
        pstmt.setFloat(8, r.getAddMaslo());
        pstmt.setFloat(9, r.getAddPolimer());
        return pstmt.execute();
    }

    public static Record[] selectAll(Connection con) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet set = stmt.executeQuery("SELECT * FROM DSSK_Logging");
        Record[] records = new Record[DbOperations.getCountOfRowsInDb(con)];
        for(int i = 0; i < records.length; i++)
            records[i] = new Record(set.getInt("id"), set.getString("date"), set.getFloat("muni_was"), set.getFloat("muni_now"), set.getFloat("muni_batarei"), set.getFloat(" maslo_was"),
                    set.getFloat("maslo_now"), set.getFloat("ro"), set.getFloat("suhoy_ostatok"), set.getFloat("add_maslo"), set.getFloat("add_polimer"));

        return records;
    }

    public static int getCountOfRowsInDb(Connection con) throws SQLException {
        Statement stmt = con.createStatement();
        return stmt.executeQuery("SELECT COUNT(*) FROM DSSK_Logging").getInt(1);
    }

    public static void main(String[] args) {
        tryDbConnection(DB_PATH);
        try {
            System.out.println(getCountOfRowsInDb(getConnection()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
