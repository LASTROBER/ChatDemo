package chatdemo;

import java.io.*;

import chatdemo.Tools.UserInfo;
import chatdemo.message.*;

import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Serializable {

    public static void main(String[] args) throws IOException, SQLException {
        try (ServerSocket sc = new ServerSocket(5000)) {
            Vector<UserThread> vector = new Vector<>();
            //固定大小的线程池，用来处理不同客户端
            ExecutorService es = Executors.newFixedThreadPool(5);
            System.out.println("-----服务器启动-----");
            while (true) {
                //死循环不断接受客户端传来的socket
                System.out.println("服务器等待......");
                Socket socket = sc.accept();
                UserThread u = new UserThread(socket, vector);
                es.execute(u);
            }
        }
    }
}

class UserThread implements Runnable {
    private String name;
    private final Socket socket;
    private final Vector<UserThread> vector;

    private Scanner scanner;

    //用于接收客户端传来的消息
    private ObjectInputStream objectInputStream;

    //用于给客户端传递消息
    private ObjectOutputStream objectOutputStream;

    ChatDemoMysql c = new ChatDemoMysql();


    //在线程池中添加线程，即对每个相同的用户分配同一个线程，从而实现服务器识别不同客户端
    public UserThread(Socket socket, Vector<UserThread> vector) throws SQLException {
        this.socket = socket;
        this.vector = vector;
        //添加线程
        vector.add(this);

    }

    @Override
    public void run() {

        try {
            //将输入输出流封装到socket中
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            while (true) {

                //这里获取到Message信息，下面获得Message中的属性（包含【发送方：from；接收方：to；信息：info】）
                ChatMessage chatMessage = new ChatMessage();
                LoginMessage loginMessage = new LoginMessage();
                LogoutMessage logoutMessage = new LogoutMessage();
                AddFriendsMessage addFriendsMessage = new AddFriendsMessage();
                LogonMessage logonMessage = new LogonMessage();
                FileSendMessage fileSendMessage = new FileSendMessage();
                //以下为接收信息部分
                //区分读出的不同消息【聊天，登入，登出】
                Message m = (Message) objectInputStream.readObject();
                if (m.getType() == MessageType.TYPE_SEND || m.getType() == MessageType.TYPE_ADDFRIENDS_COMMIT || m.getType() == MessageType.TYPE_ADDFRIENDS_REFUSED) {
                    // private String from;private String to;private int type;private String info;
                    chatMessage = (ChatMessage) m;
                    System.out.println("<MessageType.TYPE_SEND> type:" + chatMessage.getType() + "    from:" + chatMessage.getFrom() + "  to:" + chatMessage.getTo()
                            + "  info:" + chatMessage.getInfo() + "   time:" + chatMessage.getDate());
                } else if (m.getType() == MessageType.TYPE_LOGIN) {
                    // type, String clientNameCheck,String clientPassworldCheck
                    loginMessage = (LoginMessage) m;
                    name = loginMessage.getClientNameCheck();
                    System.out.println("<MessageType.TYPE_LOGIN> type:" + loginMessage.getType() + "   clientNameCheck:" + loginMessage.getClientNameCheck()
                            + "   clientPassworldCheck:" + loginMessage.getClientPassworldCheck());
                } else if (m.getType() == MessageType.TYPE_LOGOUT) {
                    //private int type;
                    logoutMessage = (LogoutMessage) m;
                    System.out.println("<MessageType.TYPE_LOGOUT> type:" + logoutMessage.getType());
                } else if (m.getType() == MessageType.TYPE_SEND_MASS) {
                    chatMessage = (ChatMessage) m;
                    System.out.println("<MessageType.TYPE_SEND_MASS> type:" + chatMessage.getType() + "    from:" + chatMessage.getFrom() + "  to:" + chatMessage.getTo()
                            + "  info:" + chatMessage.getInfo() + "   time:" + chatMessage.getDate());

                } else if (m.getType() == MessageType.TYPE_ADDFRIENDS) {
                    addFriendsMessage = (AddFriendsMessage) m;
                    System.out.println("<MessageType.TYPE_ADDFRIENDS> type" + addFriendsMessage.getType() + "   from" +
                            addFriendsMessage.getFrom() + "   friendsToAdd:" + addFriendsMessage.getAddFriend());
                } else if (m.getType() == MessageType.TYPE_LOGON_COMMIT) {
                    logonMessage = (LogonMessage) m;
                    System.out.println("<MessageType.TYPE_LOGON> type:" + logonMessage.getType() + "   userName:"
                            + logonMessage.getUserName() + "   userPassworld:" + logonMessage.getUserPassworld());
                } else if (m.getType() == MessageType.TYPE_SEND_FILES) {
                    fileSendMessage = (FileSendMessage) m;
                    System.out.println("<MessageType.TYPE_SEND_FILES> type:" + fileSendMessage.getType() + "    from:" + fileSendMessage.getFrom()
                            + "  to:" + fileSendMessage.getTo() + "   fileName:" + fileSendMessage.getFileName() + "   fileSize:" + fileSendMessage.getFileSize());
                } else if (m.getType() == MessageType.TYPE_SEND_FILES_COMMIT) {
                    chatMessage = (ChatMessage) m;
                    System.out.println("<MessageType.TYPE_SEND_FILES_COMMIT> type:" + chatMessage.getType() + "    from:" + chatMessage.getFrom() + "  to:" + chatMessage.getTo()
                            + "  info:" + chatMessage.getInfo() + "   time:" + chatMessage.getDate());
                } else if (m.getType() == MessageType.TYPE_WINDOW_SHAKE) {
                    chatMessage = (ChatMessage) m;
                    System.out.println("<MessageType.TYPE_WINDOW_SHAKE> type:" + chatMessage.getType() + "    from:" + chatMessage.getFrom() + "  to:" + chatMessage.getTo()
                            + "  info:" + chatMessage.getInfo() + "   time:" + chatMessage.getDate());
                } else if (m.getType() == MessageType.TYPE_GET_FRIENDS) {
                    chatMessage = (ChatMessage) m;
                    System.out.println("<MessageType.TYPE_GET_FRIENDS> type:" + chatMessage.getType() + "    from:" + chatMessage.getFrom() + "  to:" + chatMessage.getTo()
                            + "  info:" + chatMessage.getInfo() + "   time:" + chatMessage.getDate());

                } else if (m.getType() == MessageType.TYPE_CHANGE_PASSWORD) {
                    chatMessage = (ChatMessage) m;
                    System.out.println("<MessageType.TYPE_CHANGE_PASSWORD> type:" + chatMessage.getType() + "    from:" + chatMessage.getFrom() + "  to:" + chatMessage.getTo()
                            + "  info:" + chatMessage.getInfo() + "   time:" + chatMessage.getDate());

                }else if(m.getType() == MessageType.TYPE_CHANGE_USER_INFO){
                    chatMessage = (ChatMessage) m;
                    System.out.println("<MessageType.TYPE_CHANGE_USER_INFO> type:" + chatMessage.getType() + "    from:" + chatMessage.getFrom() + "  to:" + chatMessage.getTo()
                            + "  info:" + chatMessage.getInfo() + "   time:" + chatMessage.getDate());

                }else if(m.getType() == MessageType.TYPE_GET_USER_INFO) {
                    chatMessage = (ChatMessage) m;
                    System.out.println("<MessageType.TYPE_GET_USER_INFO> type:" + chatMessage.getType() + "    from:" + chatMessage.getFrom() + "  to:" + chatMessage.getTo()
                            + "  info:" + chatMessage.getInfo() + "   time:" + chatMessage.getDate());
                } else if (m.getType() == MessageType.TYPE_SEND_FILES_COMMIT_NEXT) {
                    chatMessage = (ChatMessage) m;
                    System.out.println("<MessageType.TYPE_SEND_FILES_COMMIT_NEXT> type:" + chatMessage.getType() + "    from:" + chatMessage.getFrom() + "  to:" + chatMessage.getTo()
                            + "  info:" + chatMessage.getInfo() + "   time:" + chatMessage.getDate());
                }


                //以下为处理信息部分
                //登入请求
                if (m.getType() == MessageType.TYPE_LOGIN) {
                    name = loginMessage.getClientNameCheck();

                    String passworldCheck = loginMessage.getClientPassworldCheck();
                    //从数据库中比对登入用户名是否存在，不存在返回不存在用户，存在则比对密码，否则返回密码错误，正确后准许登入
                    try (ResultSet rs = c.mysqlSelectUser("user_p", name)) {
                        //用户不在数据库中就创建注册任务
                        if (rs == null) {
                            objectOutputStream.writeObject(new ChatMessage("server", name, "User NOT FOUND!,Do you want to Create An Account? ", MessageType.TYPE_LOGON));
                        } else if (passworldCheck.equals(rs.getString(2)) && name.equals(rs.getString(1))) {
                            String userId = rs.getString(1);
                            String passworld = rs.getString(2);
                            c.mysqlUpdateUser("user", "online", "1", userId);
                            List<String> friends = c.getUserFriends(name);
                            System.out.println(name);
                            ResultSet userInfo = c.mysqlSelectUser("user",name);
                            System.out.println(userInfo.toString());
                            UserInfo info= getUserInfo(userId, userInfo);
                            info.setFriends(friends);

                            UserThread ut;
                            for (UserThread userThread : vector) {
                                ut = userThread;
                                if (ut.name.equals(name)) {
                                    System.out.println(ut.name);
                                    ut.objectOutputStream.writeObject(new ChatMessage("server", ut.name,friends, info, MessageType.TYPE_LGOINCHECK));
                                }
                            }

                   //         objectOutputStream.writeObject(new ChatMessage("server", name, "welcome!", MessageType.TYPE_LGOINCHECK,friends));




                        } else {
                            chatMessage.setInfo("Name or Passworld ERROR! ");
                            objectOutputStream.writeObject(chatMessage);
                        }
                    }
                    //创建用户请求
                } else if (m.getType() == MessageType.TYPE_LOGON_COMMIT) {
                    c.mysqlAddUser("user_p", logonMessage.getUserName(), logonMessage.getUserPassworld());
                    c.mysqlAddUserInfo(logonMessage.getUserName(), logonMessage.getUserName());
                    objectOutputStream.writeObject(new ChatMessage("server", name, "CREATE SUCCESS", MessageType.TYPE_SEND));
                    //创建成功后需要为用户分配文件夹，服务器先指定好目录，然后根据用户标识创建文件夹，创建成功则登入任务完成
                }
                //发送消息请求
                else if (m.getType() == MessageType.TYPE_SEND) {
                    UserThread ut;
                    for (UserThread userThread : vector) {
                        ut = userThread;
                        if (chatMessage.getTo().equals(ut.name) && ut != this) {
                            ut.objectOutputStream.writeObject(chatMessage);
                        }
                    }
                }

                //群发请求
                else if (m.getType() == MessageType.TYPE_SEND_MASS) {
                    List<String> friends = c.getUserFriends(name);
                    UserThread ut;
                    for (UserThread userThread : vector) {
                        ut = userThread;
                        int friendsnum = friends.size();
                        for (int i = 0; i < friendsnum; i++) {
                            ut.objectOutputStream.writeObject(new ChatMessage(chatMessage.getFrom(), friends.get(i), chatMessage.getInfo(), chatMessage.getType()));
                        }
                    }
                }

                //登出请求
                else if (m.getType() == MessageType.TYPE_LOGOUT) {
                    c.mysqlUpdateUser("user","Online","0", chatMessage.getFrom());
                }
                //添加好友请求
                else if (m.getType() == MessageType.TYPE_ADDFRIENDS) {
                    UserThread ut;
                    for (UserThread userThread : vector) {
                        ut = userThread;
                        System.out.println(addFriendsMessage.getAddFriend());
                        if (ut.name.equals(addFriendsMessage.getAddFriend()) && ut != this) {
                            System.out.println(ut.name);
                            ut.objectOutputStream.writeObject(new ChatMessage(addFriendsMessage.getFrom(), addFriendsMessage.getAddFriend(), "friends requestion from " + addFriendsMessage.getFrom(), MessageType.TYPE_ADDFRIENDS_COMMIT));
                        }
                    }
                }
                //确认添加好友
                else if (m.getType() == MessageType.TYPE_ADDFRIENDS_COMMIT) {
                    UserThread ut;
                    for (UserThread userThread : vector) {
                        ut = userThread;
                        if (addFriendsMessage.getAddFriend().equals(ut.name) && ut != this) {
                            c.mysqlAddUserFriends(addFriendsMessage.getFrom(), addFriendsMessage.getAddFriend(), null);
                            ut.objectOutputStream.writeObject(new ChatMessage(chatMessage.getFrom(), chatMessage.getTo(), "COMMIT", 1));
                        }
                    } //拒绝添加好友
                } else if (m.getType() == MessageType.TYPE_ADDFRIENDS_REFUSED) {
                    objectOutputStream.writeObject(new ChatMessage(chatMessage.getFrom(), chatMessage.getTo(), "REFUSED", 1));

                } else if (m.getType() == MessageType.TYPE_SEND_FILES) {
                   //这两个是空的。。。

                    String sendIp = fileSendMessage.getClientIp();
                    int sendPort = fileSendMessage.getClientPort();
                    System.out.println("sendIp:"+sendIp+"sendPort"+sendPort);
                    String fileName = fileSendMessage.getFileName();
                    UserThread ut;
                    for (UserThread userThread : vector) {
                        ut = userThread;
                        if (fileSendMessage.getTo().equals(ut.name) && ut != this) {
                            ut.objectOutputStream.writeObject(new ChatMessage(fileSendMessage.getFrom(), fileSendMessage.getTo(), "fileName:" + fileSendMessage.getFileName() + "   fileSize:" + fileSendMessage.getFileSize(), MessageType.TYPE_SEND_FILES,sendIp+";"+sendPort+";"+fileName));
                        }
                    }
                } else if (m.getType() == MessageType.TYPE_SEND_FILES_COMMIT) {
                    UserThread ut;
                    for (UserThread userThread : vector) {
                        ut = userThread;
                        if (chatMessage.getTo().equals(ut.name)) {
                            ut.objectOutputStream.writeObject(new ChatMessage("server", chatMessage.getTo(), chatMessage.getInfo(), MessageType.TYPE_SEND_FILES_COMMIT_NEXT));
                        }
                    }
                }
                else if (m.getType() == MessageType.TYPE_WINDOW_SHAKE) {
                    UserThread ut;
                    for (UserThread userThread : vector) {
                        ut = userThread;
                        if (chatMessage.getTo().equals(ut.name)) {
                            ut.objectOutputStream.writeObject(new ChatMessage("server", chatMessage.getTo(), chatMessage.getInfo(), MessageType.TYPE_WINDOW_SHAKE));
                        }
                    }
                } else if (m.getType()==MessageType.TYPE_GET_FRIENDS) {
                    UserThread ut;
                    for (UserThread userThread : vector) {
                        ut = userThread;
                        if (name.equals(ut.name)) {
                            List<String> friends = c.getUserFriends(name);
                            ut.objectOutputStream.writeObject(new GetFriendsMessage("server", friends, chatMessage.getFrom(), MessageType.TYPE_SEND));
                        }
                    }

                } else if (m.getType() == MessageType.TYPE_CHANGE_PASSWORD) {
                    String userName = chatMessage.getFrom();
                    String[] parts = chatMessage.getInfo().split(";");
                    System.out.println(Arrays.toString(parts));
                    String password = parts[0];
                    String passwordCheck = parts[1];
                    ResultSet rs =  c.mysqlSelectUser("user_p",userName);
                    if (password.equals(rs.getString(2)) && name.equals(rs.getString(1))){
                       c.mysqlUpdateUser("user_p","Passworld",passwordCheck,userName);
                        objectOutputStream.writeObject(new ChatMessage("server",chatMessage.getFrom(),"CHANGE_PASSWORD_COMMIT", MessageType.TYPE_CHANGE_PASSWORD_COMMIT));
                    }else {
                        objectOutputStream.writeObject(new ChatMessage("server",chatMessage.getFrom(),"CHANGE_PASSWORD_COMMIT", MessageType.TYPE_CHANGE_PASSWORD_FAIL));

                    }

                } else if (m.getType() == MessageType.TYPE_CHANGE_USER_INFO) {
                    String userName = chatMessage.getFrom();
                    /*String[] parts = chatMessage.getInfo().split(";");
                        String nickName=parts[0];
                        String sex=parts[1];
                        String dept=parts[2];
                        String email=parts[3];
                        c.mysqlChangeUserInfo(userName,"Name",nickName);
                        c.mysqlChangeUserInfo(userName,"Gender",sex);
                        c.mysqlChangeUserInfo(userName,"Dept",dept);
                        c.mysqlChangeUserInfo(userName,"Email",email);
                        */
                    UserInfo userInfo = chatMessage.getUserInfo();
                    String nickName = userInfo.getUserNickName();
                    String gender = userInfo.getGender();
                    String dept = userInfo.getDept();
                    String email = userInfo.getEmail();

                    c.mysqlChangeUserInfo(userName,"Name",nickName);
                    c.mysqlChangeUserInfo(userName,"Gender",gender);
                    c.mysqlChangeUserInfo(userName,"Dept",dept);
                    c.mysqlChangeUserInfo(userName,"Email",email);

                        objectOutputStream.writeObject(new ChatMessage("server",chatMessage.getFrom(),userInfo,MessageType.TYPE_SET_USER_INFO));
                }else if (m.getType() == MessageType.TYPE_GET_USER_INFO) {
                     ResultSet userInfo = c.mysqlSelectUser("user",chatMessage.getFrom());
                     String userId = chatMessage.getFrom();
                    UserInfo info = getUserInfo(userId, userInfo);
                    objectOutputStream.writeObject(new ChatMessage("server", chatMessage.getFrom(),info,MessageType.TYPE_GET_USER_INFO));
                }

            }

        } catch (IOException | ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private UserInfo getUserInfo(String userId, ResultSet userInfo) throws SQLException {
        String userNickName= userInfo.getString(2);
        String gender=userInfo.getString(2);
        String dept=userInfo.getString(4);
        String email=userInfo.getString(5);
        String ipaddress=userInfo.getString(6);
        return new UserInfo(userId,userNickName,gender,dept,email,ipaddress);
    }
}