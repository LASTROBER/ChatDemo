    package chatdemo;
    
    import java.io.*;
    
    import chatdemo.message.LoginMessage;
    import chatdemo.message.ChatMessage;
    import chatdemo.message.LogoutMessage;
    import chatdemo.message.MessageType;
    import java.net.Socket;
    import java.util.Scanner;
    import java.util.concurrent.ExecutorService;
    import java.util.concurrent.Executors;
    
    public class Client3 {
    
        static String name;
        static String clientPassWorldCheck;
        static String clientNameCheck;
    
    
        public static void main(String[] args) throws IOException, ClassNotFoundException {
    
            Client client = new Client();
    
        }
    
        public Client3() throws IOException {
    
    
            ExecutorService es = Executors.newSingleThreadExecutor();
            //创建Scoket对象
            Socket socket = new Socket("127.0.0.1", 5000);
    
    
            //创建读写字节流
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
    
    
    
            //接收服务器发送来的消息
            ChatMessage chatMessage;
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.println("服务器连接成功");
    
                //登入功能
                while (true){
                    System.out.println("输入你的名字：");
                    name = scanner.nextLine();
                    clientNameCheck = name;
    
                    System.out.println("输入你的密码：");
                    clientPassWorldCheck = scanner.nextLine();
                    //向服务器发送登入消息
                    LoginMessage loginMessage = new LoginMessage(MessageType.TYPE_LOGIN, clientNameCheck, clientPassWorldCheck);
                    objectOutputStream.writeObject(loginMessage);
    
                    chatMessage = (ChatMessage) objectInputStream.readObject();
                    if (chatMessage.getType()==MessageType.TYPE_LGOINCHECK){
                        break;
                    }
                    System.out.println("用户错误无法请求");
    
    
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            //打印服务器返回的信息+当前客户端的名字
            System.out.println(chatMessage.getInfo());
    
    
            ReadInfoThread readInfoThread = new ReadInfoThread(objectInputStream, name);
    
    
            while (true) {
                es.execute(readInfoThread);
                scanner.nextLine();
                System.out.println("Enter type:");
                int type = scanner.nextInt();
                scanner.nextLine();
    
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
                    objectOutputStream.writeObject(new LogoutMessage(MessageType.TYPE_LOGOUT,name));
    
                }
                //发送单个消息
                if (type == MessageType.TYPE_SEND) {
                    System.out.println("Enter Your Info:");
                    String info = scanner.nextLine();
                    scanner.nextLine();
                    System.out.println("Enter Your To:");
                    String to = scanner.nextLine();
                    chatMessage = new ChatMessage(name, null, null, type);
                    chatMessage.setInfo(info);
                    chatMessage.setTo(to);
                    objectOutputStream.writeObject(chatMessage);
                }
            }
    
        }
    }
    
    class ReadInfoThread3 implements Runnable {
    //输入流 用来读操作
    private ObjectInputStream objectInputStream;
    //标记
    private boolean flag = true;
    
        String name;
    
        public ReadInfoThread3(ObjectInputStream oIn, String name) {
            this.objectInputStream = oIn;
            this.name = name;
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
                        System.out.println("---转发--->from:" + chatMessage.getFrom() + "   info:" + chatMessage.getInfo() + "   time:" + chatMessage.getDate());
                    }
                    if(chatMessage.getType() == MessageType.TYPE_LOGOUT){
                        System.out.println("from server: "+chatMessage.getInfo());
                    }
    
                }
    
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
    
        }
    
    }


