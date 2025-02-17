/*
 * Created by JFormDesigner on Fri Dec 20 00:00:16 CST 2024
 */

package chatdemo.window;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;

import chatdemo.Tools.UserInfo;
import chatdemo.message.*;
import chatdemo.window.UserPage;
import com.jgoodies.forms.factories.*;

/**
 * @author whmfy
 */
public class Login extends JFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Login li = null;
            try {
                li = new Login();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            li.setVisible(true);

        });
    }

    public Login() throws IOException {
        initComponents();
    }

    private void button3MouseClicked(MouseEvent e) throws IOException, ClassNotFoundException {
        // TODO add your code here
        ChatMessage chatMessage;
        String clientName = clientLoginName.getText();
        String clientPassworld = clientLoginPassword.getText();


        //会有空值异常

        String clientIp = "127.0.0.1";
        int clientPort = 5000;
        //创建Scoket对象
        Socket socket = new Socket(clientIp, clientPort);
        //创建读写字节流
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

        //用户名判断登入
        LoginMessage loginMessage = new LoginMessage(MessageType.TYPE_LOGIN, clientName, clientPassworld);
        objectOutputStream.writeObject(loginMessage);
        chatMessage = (ChatMessage) objectInputStream.readObject();

        //获取朋友
        List<String> friends = chatMessage.getFriends();
        System.out.println(friends);
        UserInfo userInfo = chatMessage.getUserInfo();
        System.out.println(userInfo.getFriends());
        System.out.println("nickName:"+userInfo.getUserNickName()+" userId:"+userInfo.getUserId());
        if (chatMessage.getType() == MessageType.TYPE_LGOINCHECK) {
            System.out.println(chatMessage.getInfo());
            SwingUtilities.invokeLater(() -> {
                UserPage up = null;

                up = new UserPage(clientName, socket,friends,clientIp,clientPort,objectOutputStream,objectInputStream,userInfo);
                System.out.println(clientName+clientIp);
                up.setVisible(true);
               this.setVisible(false);
            });
        } else {
            Component jPanel = null;
            JOptionPane.showMessageDialog(jPanel, "用户名或密码错误", "ERROR", JOptionPane.WARNING_MESSAGE);
        }


    }

    private void button4MouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void initComponents() throws IOException {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
        label4 = compFactory.createLabel("\u7528\u6237\u540d");
        label5 = compFactory.createLabel("\u5bc6\u7801");
        clientLoginPassword = new JPasswordField();
        button3 = new JButton();
        button4 = new JButton();
        clientLoginName = new JTextField();
        label1 = new JLabel();

        //======== this ========
        setResizable(false);
        setAutoRequestFocus(false);
        setAlwaysOnTop(true);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label4 ----
        label4.setFont(new Font("\u5b8b\u4f53", Font.BOLD, 16));
        contentPane.add(label4);
        label4.setBounds(new Rectangle(new Point(45, 105), label4.getPreferredSize()));

        //---- label5 ----
        label5.setFont(new Font("\u5b8b\u4f53", Font.BOLD, 16));
        contentPane.add(label5);
        label5.setBounds(45, 145, 36, 17);
        contentPane.add(clientLoginPassword);
        clientLoginPassword.setBounds(110, 145, 215, 25);

        //---- button3 ----
        button3.setText("LogIn");
        button3.setFont(new Font("Microsoft Tai Le", Font.BOLD, 14));
        button3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
button3MouseClicked(e);} catch (IOException ex) {
    throw new RuntimeException(ex);
} catch (ClassNotFoundException ex) {
    throw new RuntimeException(ex);
}
            }
        });
        contentPane.add(button3);
        button3.setBounds(new Rectangle(new Point(85, 200), button3.getPreferredSize()));

        //---- button4 ----
        button4.setText("Back");
        button4.setFont(new Font("Microsoft Tai Le", Font.BOLD, 14));
        button4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button4MouseClicked(e);
            }
        });
        contentPane.add(button4);
        button4.setBounds(new Rectangle(new Point(230, 200), button4.getPreferredSize()));
        contentPane.add(clientLoginName);
        clientLoginName.setBounds(110, 105, 215, clientLoginName.getPreferredSize().height);

        //---- label1 ----
        label1.setText("\u804a\u5929\u8f6f\u4ef6\u767b\u5165\u754c\u9762");
        label1.setFont(new Font("\u7b49\u7ebf", Font.BOLD, 30));
        contentPane.add(label1);
        label1.setBounds(85, 15, 320, 100);

        contentPane.setPreferredSize(new Dimension(400, 300));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on


    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label4;
    private JLabel label5;
    private JPasswordField clientLoginPassword;
    private JButton button3;
    private JButton button4;
    private JTextField clientLoginName;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
