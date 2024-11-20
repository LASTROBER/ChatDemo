
import java.sql.*;

 class sqlserver {

    public static void main(String[] args)

    {

        String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

        String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=TestDB;encrypt=true;trustServerCertificate=true";

        String userName = "sa";

        String userPwd = "9999";

        Connection dbConn = null;

        try

        {

            Class.forName(driverName);

            dbConn = DriverManager.getConnection(dbURL, userName, userPwd);

            System.out.println("连接数据库成功");

        }

        catch (Exception e)

        {

            e.printStackTrace();

            System.out.print("连接失败");

        }

    }

}
