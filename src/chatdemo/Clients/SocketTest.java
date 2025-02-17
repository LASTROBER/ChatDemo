package chatdemo.Clients;

import chatdemo.message.ChatMessage;
import chatdemo.message.LoginMessage;
import chatdemo.message.MessageType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String clientIp = "127.0.0.1";
        int clientPort = 5000;
        //创建Scoket对象
        Socket socket = new Socket(clientIp, clientPort);
        //创建读写字节流
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

        //用户名判断登入

        LoginMessage loginMessage = new LoginMessage(MessageType.TYPE_LOGIN, "1001", "p1001");
        objectOutputStream.writeObject(loginMessage);
        ChatMessage loginMessage2 = (ChatMessage) objectInputStream.readObject();
        System.out.println(loginMessage2.getType());
    }
}
