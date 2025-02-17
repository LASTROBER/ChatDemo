package chatdemo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChatDemoMysql { // 创建ChatDemo数据库对象
    Connection con; // 声明Connection对象
    public static String user;
    public static String password;
    Statement statement = null;
    ResultSet resultSet = null;

    public ChatDemoMysql() throws SQLException {
        try { // 加载数据库驱动类
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("数据库驱动加载成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        user = "root";//数据库登录名
        password = "9999";//密码
        try { // 通过访问数据库的URL获取数据库连接对象
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatdemo?useUnicode=true&characterEncoding=gbk", user, password);
            System.out.println("数据库连接成功");
            System.out.println("~~~~~~~~~~");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    //SELECT * from `user` where UserId = '1001'
    public ResultSet mysqlSelectUser(String tableName, String userId) throws SQLException {
        String sql = String.format("select * from %s where UserId = %s ", tableName, userId);
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        //resultSet.next();
        if (resultSet.next()) {
            return resultSet;
        } else {
            return null;
        }
    }
    public ResultSet mysqlSelectUser(String columnName,String tableName, String userId) throws SQLException {
        String sql = String.format("select %s from %s where UserId = %s ",columnName,tableName, userId);
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        //resultSet.next();
        if (resultSet.next()) {
            return resultSet;
        } else {
            return null;
        }
    }

    public void mysqlAddUser(String tableName, String userId, String userPassworld) throws SQLException {
        String sql = String.format("insert into user_p (UserId,Passworld) values('%s','%s')", userId, userPassworld);
        statement = con.createStatement();
        int count = statement.executeUpdate(sql);
        if (count > 0) {
            System.out.println("执行成功");
        } else {
            System.out.println("执行失败");
        }
    }

    public ResultSet mysqlUpdateUser(String tableName, String columnName, String values, String userId) throws SQLException {
        String sql = String.format("update %s set %s = '%s' where UserId = %s", tableName, columnName, values, userId);
        System.out.println(sql);
        statement = con.createStatement();
        int count = statement.executeUpdate(sql);
        if (count > 0) {
            System.out.println("执行成功");
            return resultSet;
        } else {
            System.out.println("执行失败");
            return null;
        }
    }


    public List<String> getUserFriends(String userId) throws SQLException {
        List<String> friends = new ArrayList<String>();
        String sql = String.format("select * from friendsinfo where UserId = %s",userId);
        CallableStatement cstmt = con.prepareCall(sql);
        ResultSet rs = cstmt.executeQuery();
        boolean flag = cstmt.execute();
        while (flag) {
            rs = cstmt.getResultSet();
            // 内循环获取每个结果集的记录
            while (rs.next()) {
                String r = rs.getString(2);
                friends.add(r);
            }
            break;
        }
        flag = cstmt.getMoreResults();
        return friends;
    }
    public void mysqlAddUserFriends(String userId, String userFriendsId,String discription) throws SQLException {
        String sql = String.format("insert into friendsinfo values(%s,%s,%s)",userId, userFriendsId,discription);
        statement = con.createStatement();
        int count = statement.executeUpdate(sql);
        if (count > 0) {
            System.out.println("执行成功");
        } else {
            System.out.println("执行失败");
        }
    }


 /*   `UserId` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `Name` varchar(20) NOT NULL,
  `Gender` char(1) DEFAULT NULL,
  `Dept` varchar(100) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Online` char(1) DEFAULT NULL,
  `IPaddress` varchar(50) DEFAULT NULL,*/

    public void mysqlAddUserInfo(String userId,String name) throws SQLException {
    String sql = String.format("insert into user (UserId,Name,Online) values(%s,%s,0)",userId,name);
    statement = con.createStatement();
    int count = statement.executeUpdate(sql);{
        if(count > 0){
            System.out.println("执行成功");
        }
        else {
            System.out.println("执行失败");
        }
        }
    }

    public void mysqlMessageSave(int MsgId,String From,String To,String Msg) throws SQLException {
        String sql = String.format("insert into offline_text ('MsgId','From','To','Msg') values(%s,%s,%s,%s)",MsgId, From,To,Msg);
        statement = con.createStatement();
        int count = statement.executeUpdate(sql);
        if (count > 0) {
            System.out.println("执行成功");
        } else {
            System.out.println("执行失败");
        }
    }
    public void mysqlChangeUserInfo(String userId,String columnName,String value) throws SQLException {
        String sql = String.format("update user set %s = '%s' where UserId = %s",columnName,value,userId);
        statement = con.createStatement();
        int count = statement.executeUpdate(sql);
        if (count > 0) {
            System.out.println("执行成功");
        } else {
            System.out.println("执行失败");
        }
    }


    public static void main(String[] args) throws SQLException {
        ChatDemoMysql c = new ChatDemoMysql();
        c.mysqlUpdateUser("user_p","Passworld","aa","1001");
    }


}
