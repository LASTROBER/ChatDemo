/*
 * Created by JFormDesigner on Fri Dec 20 00:01:56 CST 2024
 */

package chatdemo.window.LogOpen;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import javax.swing.*;

import chatdemo.message.ChatMessage;
import chatdemo.message.LogonMessage;
import chatdemo.message.MessageType;
import chatdemo.window.UserPage;
import chatdemo.window.UserWindow;
import com.jgoodies.forms.factories.*;

/**
 * @author whmfy
 */
public class Logon extends JFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Logon lo= null;
            lo = new Logon();
            lo.setVisible(true);
        });
    }
    public Logon() {
        initComponents();
    }

    private void button5MouseClicked(MouseEvent e) throws IOException {
        // TODO add your code here
        ChatMessage chatMessage;
        String clientIp = "127.0.0.1";
        int clientPort =5000;
        //创建Scoket对象
        Socket socket = new Socket(clientIp, clientPort);
        //创建读写字节流
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

        
        if(!clientLogonPassword.getText().equals(clientLogonPasswordCheck.getText())){
            Component jPanel = null;
            JOptionPane.showMessageDialog(jPanel, "两次密码输入不一致", "ERROR",JOptionPane.WARNING_MESSAGE);

        }
        else {
            String logonName = clientLogonName.getText();
            String logonPassworld = clientLogonPassword.getText();
            objectOutputStream.writeObject(new LogonMessage(MessageType.TYPE_LOGON_COMMIT, logonName, logonPassworld));
            Component jPanel = null;
            JOptionPane.showMessageDialog(jPanel, "注册成功", "ERROR", JOptionPane.WARNING_MESSAGE);

            SwingUtilities.invokeLater(() -> {
                UserWindow uw = new UserWindow();
                uw.setVisible(true);
            });
        }
    }

    private void button6MouseClicked(MouseEvent e) {
        // TODO add your code here
        SwingUtilities.invokeLater(() -> {
            UserPage up = null;
            try {
                up = new UserPage();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            up.setVisible(true);
        });
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
        textArea4 = new JTextArea();
        label1 = compFactory.createLabel("\u7528\u6237\u540d");
        label2 = compFactory.createLabel("\u5bc6\u7801");
        clientLogonPassword = new JPasswordField();
        label3 = compFactory.createLabel("\u786e\u8ba4\u5bc6\u7801");
        clientLogonPasswordCheck = new JPasswordField();
        button5 = new JButton();
        button6 = new JButton();
        clientLogonName = new JTextField();

        //======== this ========
        setAutoRequestFocus(false);
        setResizable(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- textArea4 ----
        textArea4.setText("\u804a\u5929\u8f6f\u4ef6\u6ce8\u518c\u754c\u9762");
        textArea4.setTabSize(12);
        textArea4.setFont(textArea4.getFont().deriveFont(textArea4.getFont().getSize() + 10f));
        textArea4.setEditable(false);
        contentPane.add(textArea4);
        textArea4.setBounds(95, 15, 203, 33);
        contentPane.add(label1);
        label1.setBounds(40, 65, 50, 20);
        contentPane.add(label2);
        label2.setBounds(45, 110, 50, 20);
        contentPane.add(clientLogonPassword);
        clientLogonPassword.setBounds(110, 110, 210, 30);
        contentPane.add(label3);
        label3.setBounds(35, 155, 65, 20);
        contentPane.add(clientLogonPasswordCheck);
        clientLogonPasswordCheck.setBounds(110, 155, 210, 30);

        //---- button5 ----
        button5.setText("LogOn");
        button5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
button5MouseClicked(e);} catch (IOException ex) {
    throw new RuntimeException(ex);
}
            }
        });
        contentPane.add(button5);
        button5.setBounds(new Rectangle(new Point(60, 210), button5.getPreferredSize()));

        //---- button6 ----
        button6.setText("Back");
        button6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button6MouseClicked(e);
            }
        });
        contentPane.add(button6);
        button6.setBounds(new Rectangle(new Point(235, 210), button6.getPreferredSize()));
        contentPane.add(clientLogonName);
        clientLogonName.setBounds(110, 65, 210, 30);

        contentPane.setPreferredSize(new Dimension(400, 300));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JTextArea textArea4;
    private JLabel label1;
    private JLabel label2;
    private JPasswordField clientLogonPassword;
    private JLabel label3;
    private JPasswordField clientLogonPasswordCheck;
    private JButton button5;
    private JButton button6;
    private JTextField clientLogonName;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
