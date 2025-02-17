import java.sql.*;;
public class SqlServer {
    public static void main(String[] args) {
        String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        //定义数据库用户
        String userName = "sa";
        //定义数据库密码
        String userPwd = "9999";
        //定义数据库连接对象
        Connection dbConn = null;
        try {
            //1.加载及注册驱动1
            Class.forName(driverName);
            //2.定义数据库连接字符串
            String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=manage;sslProtocol=TLSv1";
            //3.数据库连接
            dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
            System.out.println("连接数据库成功");
            dbConn.close();
        }catch (SQLException e) {      //数据库的处理异常的方法
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (dbConn!=null){
                try {
                    dbConn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}