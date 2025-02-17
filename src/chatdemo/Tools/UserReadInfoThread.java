package chatdemo.Tools;

import chatdemo.message.ChatMessage;
import chatdemo.message.MessageType;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * @author whmfy
 */
public class UserReadInfoThread implements Runnable{
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    //标记
    private boolean flag = true;

    String clientIp;
    int clientPort;
    String name;
    Component jPanel = null;
    JTextArea chatReceive;
    FontSet fontSet;
    public UserReadInfoThread(ObjectInputStream oIn,ObjectOutputStream op, String name, String clientIp, int clientPort, JTextArea chatReceive) {
        this.objectInputStream = oIn;
        this.objectOutputStream = op;
        this.name = name;
        this.clientIp = clientIp;
        this.clientPort = clientPort;
        this.chatReceive = chatReceive;
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
                    //  System.out.println("---单传--->from:" + chatMessage.getFrom() + "   info:" + chatMessage.getInfo() + "   time:" + chatMessage.getDate());
                    //JOptionPane.showMessageDialog(jPanel, "用户"+chatMessage.getFrom()+"发来消息:"+chatMessage.getInfo(), "message", JOptionPane.INFORMATION_MESSAGE);

                    String fontType = chatMessage.getFrontType();
                    Font font = new Font(fontType,Font.PLAIN,12);
                    chatReceive.setText(chatReceive.getText()+"\n"+"用户"+chatMessage.getFrom()+"发来消息:"+chatMessage.getInfo()+"   "+chatMessage.getDate()+"\n");
                } else if (chatMessage.getType() == MessageType.TYPE_SEND_MASS) {
                    //        System.out.println("---群发--->from:" + chatMessage.getFrom() + "   info:" + chatMessage.getInfo() + "   time:" + chatMessage.getDate());
                    JOptionPane.showMessageDialog(jPanel, "用户"+chatMessage.getFrom()+"发来群发消息"+chatMessage.getInfo(), "message", JOptionPane.INFORMATION_MESSAGE);
                } else if (chatMessage.getType() == MessageType.TYPE_LGOOUTCHECK) {
                    JOptionPane.showMessageDialog(jPanel, "GOOD BYE~~~", "message", JOptionPane.INFORMATION_MESSAGE);
                    //System.out.println("from server: " + chatMessage.getInfo());
                    System.exit(0);
                } else if (chatMessage.getType() == MessageType.TYPE_ADDFRIENDS_COMMIT && chatMessage.getTo().equals(name)) {
                    //System.out.println(String.format("您收到了一个来自用户：%S 的好友请求：", chatMessage.getFrom()) + "   info   " + chatMessage.getInfo());
                    int n = JOptionPane.showConfirmDialog(null,"用户"+chatMessage.getFrom(),"Title",JOptionPane.YES_NO_OPTION);
                    if (n == 0) {
                        objectOutputStream.writeObject(new ChatMessage(name, chatMessage.getFrom(), "COMMIT", MessageType.TYPE_ADDFRIENDS_COMMIT));
                    } else  {
                        objectOutputStream.writeObject(new ChatMessage(name, chatMessage.getFrom(), "REFUSED", MessageType.TYPE_ADDFRIENDS_REFUSED));
                    }

                } else if (chatMessage.getType() == MessageType.TYPE_SEND_FILES) {
                    System.out.println("getSender:"+chatMessage.getSender());
                    String[] parts = chatMessage.getSender().split(";");
                    String sendIp = parts[0];
                    int sendPort = Integer.parseInt(parts[1]);
                    String fileName = parts[2];
                    System.out.println("sendIp:"+sendIp+"  sendPort"+sendPort+" fileName"+fileName);
                    int n = JOptionPane.showConfirmDialog(null,"您收到了来自用户"  + chatMessage.getFrom() + "的文件传输申请 文件信息："+chatMessage.getInfo(),"Title",JOptionPane.YES_NO_OPTION);
                    if (n ==0){
                        //同意，即通过UDP向文件发送方发起连接
                        int port = getAccessPort();
                        System.out.println("getAccessPort"+port);
                        objectOutputStream.writeObject(new ChatMessage(name, chatMessage.getFrom(), clientIp + ";" + port+";"+fileName, MessageType.TYPE_SEND_FILES_COMMIT));

                        String filePath = JOptionPane.showInputDialog(null,"请输入您的文件保存地址\n","Title",JOptionPane.PLAIN_MESSAGE);

                        FileSender fileSender = new FileSender(sendIp, port);
                        fileSender.catchFile(filePath);
                    }


                } else if (chatMessage.getType() == MessageType.TYPE_SEND_FILES_COMMIT) {
                   /* String nameAndPath = JOptionPane.showInputDialog(null,"请输入您的文件保存\n","Title",JOptionPane.PLAIN_MESSAGE);
                    ChatMessage readIp = (ChatMessage) objectInputStream.readObject();
                    String ipPortStr = readIp.getInfo();
                    String[] parts = ipPortStr.split(";");
                    String iP = parts[0];
                    int port = Integer.parseInt(parts[1]);
                    FileSender fileSender = new FileSender();
                    fileSender.sendFile(iP, port, nameAndPath);*/
                } else if (chatMessage.getType() == MessageType.TYPE_SEND_FILES_COMMIT_NEXT) {
                    String receiveInfo = chatMessage.getInfo();
                    System.out.println("receiveInfo:"+receiveInfo);
                    String[] parts = receiveInfo.split(";");
                    String receiveIp = parts[0];
                    int receivePort = Integer.parseInt(parts[1]);
                    String fileSendPath = parts[2];
                    FileSender fileSender = new FileSender();
                    System.out.println("receivePort"+receivePort);
                    fileSender.sendFile(receiveIp, receivePort, fileSendPath);


                } else if (chatMessage.getType() == MessageType.TYPE_CHANGE_PASSWORD_COMMIT) {
                    JOptionPane.showMessageDialog(jPanel, "修改密码成功", "message", JOptionPane.INFORMATION_MESSAGE);
                }
                else if (chatMessage.getType() == MessageType.TYPE_CHANGE_PASSWORD_FAIL) {
                    JOptionPane.showMessageDialog(jPanel, "修改密码失败", "message", JOptionPane.INFORMATION_MESSAGE);

                }

            }


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public int getAccessPort() throws IOException {
        ServerSocket socket = new ServerSocket(0);
        int localPort = socket.getLocalPort();
        socket.close();
        return localPort;
    }
}