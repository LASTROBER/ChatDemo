package chatdemo.Clients;

import java.io.*;

import chatdemo.Tools.FileSender;
import chatdemo.message.*;

import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {

    static String name;
    static String clientPassWorldCheck;
    static String clientNameCheck;

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Client client = new Client();

    }

    /**
     * 查看一个字符串是否可以转换为数字
     *
     * @param str 字符串
     * @return true 可以; false 不可以
     */
    public static boolean isStr2Num(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public Client() throws IOException, ClassNotFoundException {

        System.out.println("-----客户端启动-----");
        ExecutorService es = Executors.newSingleThreadExecutor();

        String clientIp = "127.0.0.1";
        int clientPort = 5000;

        //创建Scoket对象
        Socket socket = new Socket(clientIp, clientPort);


        //创建读写字节流
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());


        //接收服务器发送来的消息
        // objectInputStream.readObject();
        ChatMessage chatMessage;

        Scanner scanner = new Scanner(System.in);

        FileSender fileSender;
        try {
            System.out.println("服务器连接成功");
            while (true) {
                System.out.println("请输入业务：[1]登入；[2]注册");
                int type = scanner.nextInt();
                if (type == 1) {
                    //登入功能
                    while (true) {
                        System.out.println("输入你的名字：");
                        scanner.nextLine();
                        name = scanner.nextLine();
                        System.out.println("输入你的密码：");
                        clientPassWorldCheck = scanner.nextLine();
                        scanner.nextLine();
                        clientNameCheck = name;
                        //向服务器发送登入消息
                        LoginMessage loginMessage = new LoginMessage(MessageType.TYPE_LOGIN, clientNameCheck, clientPassWorldCheck);
                        objectOutputStream.writeObject(loginMessage);
                        chatMessage = (ChatMessage) objectInputStream.readObject();
                        if (chatMessage.getType() == MessageType.TYPE_LGOINCHECK) {
                            System.out.println(chatMessage.getInfo());
                            break;
                        }
                        System.out.println("用户错误无法请求");

                    }
                    break;
                } else if (type == 2) {
                    //注册功能
                    while (true) {
                        System.out.println("请输入登入账号");
                        String userId = scanner.nextLine();
                        scanner.nextLine();
                        System.out.println("请输入登入密码");
                        String userPassworld = scanner.nextLine();
                        scanner.nextLine();
                        System.out.println("请再次输入登入密码");
                        String userPassWorldCheck = scanner.nextLine();
                        scanner.nextLine();
                        if (userPassWorldCheck.equals(userPassWorldCheck)) {
                            objectOutputStream.writeObject(new LogonMessage(MessageType.TYPE_LOGON_COMMIT, userId, userPassworld));
                            break;
                        } else {
                            System.out.println("两次密码输入不一致！");
                        }

                    }
                } else {
                    System.out.println("输入错误");

                }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //打印服务器返回的信息+当前客户端的名字


        ReadInfoThread readInfoThread = new ReadInfoThread(objectInputStream, name, clientIp, clientPort);


        while (true) {
            es.execute(readInfoThread);
            scanner.nextLine();
            System.out.println("Enter type:");

            int type;
            String typeCatch = scanner.nextLine();
            if (isStr2Num(typeCatch)) {
                type = Integer.parseInt(typeCatch);

                //发送群发消息
                if (type == MessageType.TYPE_SEND_MASS) {
                    System.out.println("Enter Your Info:");
                    String info = scanner.nextLine();
                    scanner.nextLine();
                    objectOutputStream.writeObject(new ChatMessage(name, null, info, MessageType.TYPE_SEND_MASS));
                    continue;
                }
                //发送登出消息
                if (type == MessageType.TYPE_LOGOUT) {
                    objectOutputStream.writeObject(new LogoutMessage(MessageType.TYPE_LOGOUT, name));

                }
                //发送单个消息
                if (type == MessageType.TYPE_SEND) {
                    System.out.println("Enter Your Info:");
                    String info = scanner.nextLine();
                    scanner.nextLine();
                    System.out.println("Enter Your To:");
                    String to = scanner.nextLine();
                    chatMessage = new ChatMessage(null, null, name, type);
                    chatMessage.setInfo(info);
                    chatMessage.setTo(to);
                    objectOutputStream.writeObject(chatMessage);
                }
                if (type == MessageType.TYPE_ADDFRIENDS) {
                    System.out.println("Enter the name of your friends");
                    String friend = scanner.nextLine();
                    scanner.nextLine();
                    objectOutputStream.writeObject(new AddFriendsMessage(type, name, friend));
                }
                if (type == MessageType.TYPE_SEND_FILES) {
                    System.out.println("who do you want to send for");
                    //后期改为用户输入文件路径，然后自动解析文件名和文件大小
                    String to = scanner.nextLine();
                    System.out.println("your file name:");
                    String fileName = scanner.nextLine();
                    System.out.println("your file size:");
                    String fileSize = scanner.nextLine();
                    //FileSender fileSender = new FileSender(nameAndPath);
                    objectOutputStream.writeObject(new FileSendMessage(name, to, MessageType.TYPE_SEND_FILES, fileName, fileSize));
                }
                int choose = scanner.nextInt();
                if (choose == 1) {
                } else {
                    break;
                }
            }
        }
    }
}

class ReadInfoThread implements Runnable {
    Scanner scanner = new Scanner(System.in);
    //输入流 用来读操作
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    //标记
    private boolean flag = true;

    String clientIp;
    int clientPort;
    String name;

    public ReadInfoThread(ObjectInputStream oIn, String name, String clientIp, int clientPort) {
        this.objectInputStream = oIn;
        this.name = name;
        this.clientIp = clientIp;
        this.clientPort = clientPort;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }


    @Override
    public void run() {

        try {
            //循环 不断读取消息
            while (flag) {
                //读取信息
                ChatMessage chatMessage = (ChatMessage) objectInputStream.readObject();
                if (chatMessage.getType() == MessageType.TYPE_SEND && chatMessage.getTo().equals(name)) {
                    //输出用户名+内容
                    System.out.println("---单传--->from:" + chatMessage.getFrom() + "   info:" + chatMessage.getInfo() + "   time:" + chatMessage.getDate());
                } else if (chatMessage.getType() == MessageType.TYPE_SEND_MASS) {
                    System.out.println("---群发--->from:" + chatMessage.getFrom() + "   info:" + chatMessage.getInfo() + "   time:" + chatMessage.getDate());
                } else if (chatMessage.getType() == MessageType.TYPE_LGOOUTCHECK) {
                    System.out.println("from server: " + chatMessage.getInfo());
                    System.exit(0);
                } else if (chatMessage.getType() == MessageType.TYPE_ADDFRIENDS_COMMIT && chatMessage.getTo().equals(name)) {
                    System.out.println(String.format("您收到了一个来自用户：%S 的好友请求：", chatMessage.getFrom()) + "   info   " + chatMessage.getInfo());
                    while (true) {
                        System.out.println("您同意吗？YES:请输入[Y];NO:请输入[N]");
                        String choose = scanner.nextLine();
                        if ("Y".equalsIgnoreCase(choose)) {
                            objectOutputStream.writeObject(new ChatMessage(name, chatMessage.getFrom(), "COMMIT", MessageType.TYPE_ADDFRIENDS_COMMIT));
                            break;
                        } else if ("N".equalsIgnoreCase(choose)) {
                            objectOutputStream.writeObject(new ChatMessage(name, chatMessage.getFrom(), "REFUSED", MessageType.TYPE_ADDFRIENDS_REFUSED));
                            break;
                        } else {
                            System.out.println("输入格式错误！");
                        }
                    }
                } else if (chatMessage.getType() == MessageType.TYPE_SEND_FILES) {
                    System.out.println("您收到了来自用户 " + chatMessage.getFrom() + " 的文件传输申请");
                    System.out.println("文件信息：" + chatMessage.getInfo());
                    System.out.println("您是否要接收该文件？YES:请输入[Y];NO:请输入[N]");
                    System.out.println(chatMessage.getInfo());

                        //String choose = scanner.nextLine();

                            objectOutputStream.writeObject(new ChatMessage(name, chatMessage.getFrom(), clientIp + ";" + clientPort, MessageType.TYPE_SEND_FILES_COMMIT));
                            System.out.println("请输入文件存储路径");
                            String path = scanner.nextLine();
                            FileSender fileSender = new FileSender(clientIp, clientPort);
                            fileSender.catchFile(path);
                            break;
                     /*   } else if ("N".equals(choose)) {
                            objectOutputStream.writeObject(new ChatMessage(name, chatMessage.getFrom(), "user" + name + "REFUSE", MessageType.TYPE_SEND));
                            break;
                        } else {
                            System.out.println("输入格式错误！");
                        */


                } else if (chatMessage.getType() == MessageType.TYPE_SEND_FILES_COMMIT) {
                    System.out.println("Enter the name of your file NameAndpath");
                    String nameAndPath = scanner.nextLine();
                    ChatMessage readIp = (ChatMessage) objectInputStream.readObject();
                    String ipPortStr = readIp.getInfo();
                    String[] parts = ipPortStr.split(";");
                    String iP = parts[0];
                    int port = Integer.parseInt(parts[1]);
                    FileSender fileSender = new FileSender();
                    fileSender.sendFile(iP, port, nameAndPath);
                }

            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}

