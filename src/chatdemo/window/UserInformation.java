/*
 * Created by JFormDesigner on Sat Dec 21 16:51:10 CST 2024
 */

package chatdemo.window;

import chatdemo.ChatDemoMysql;
import chatdemo.message.ChatMessage;

import java.awt.*;
import java.awt.event.*;
import java.net.Socket;
import java.sql.SQLException;
import javax.swing.*;

/**
 * @author whmfy
 */
public class UserInformation  {

    String clientName;
    Socket socket;
    String clientIp;

    String clientNickName;
    String birthday;
    String mail;
    
    ChatDemoMysql c = new ChatDemoMysql();
    
UserInformation(String clientName, Socket socket, String clientIp) throws SQLException {
    this.clientIp =clientName;
    this.clientName = clientName;
    this.socket =socket;
}

private void button1MouseClicked(MouseEvent e) throws SQLException {
    // TODO add your code here
    c.mysqlChangeUserInfo("Name",textArea1.getText(),clientName);
    textArea1.setText(textArea1.getText());
}

private void button2MouseClicked(MouseEvent e) throws SQLException {
    // TODO add your code here
    c.mysqlChangeUserInfo("Gender",textArea2.getText(),clientName);
    textArea1.setText(textArea1.getText());
}

private void button3MouseClicked(MouseEvent e) throws SQLException {
    // TODO add your code here
    c.mysqlChangeUserInfo("Dept",textArea3.getText(),clientName);
    textArea1.setText(textArea1.getText());
}

private void button4MouseClicked(MouseEvent e) throws SQLException {
    // TODO add your code here
    c.mysqlChangeUserInfo("Email",textArea4.getText(),clientName);
    textArea1.setText(textArea1.getText());
}

private void button5MouseClicked(MouseEvent e) {
    // TODO add your code here
}

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        InformationChange = new JFrame();
        panel1 = new JPanel();
        scrollPane2 = new JScrollPane();
        textArea2 = new JTextArea();
        label1 = new JLabel();
        scrollPane1 = new JScrollPane();
        textArea1 = new JTextArea();
        button1 = new JButton();
        label2 = new JLabel();
        button2 = new JButton();
        label3 = new JLabel();
        scrollPane3 = new JScrollPane();
        textArea3 = new JTextArea();
        button3 = new JButton();
        label4 = new JLabel();
        scrollPane4 = new JScrollPane();
        textArea4 = new JTextArea();
        button4 = new JButton();
        label5 = new JLabel();
        scrollPane5 = new JScrollPane();
        textArea5 = new JTextArea();
        button5 = new JButton();

        //======== InformationChange ========
        {
            InformationChange.setResizable(false);
            InformationChange.setVisible(true);
            Container InformationChangeContentPane = InformationChange.getContentPane();
            InformationChangeContentPane.setLayout(null);

            //======== panel1 ========
            {
                panel1.setLayout(null);

                //======== scrollPane2 ========
                {
                    scrollPane2.setViewportView(textArea2);
                }
                panel1.add(scrollPane2);
                scrollPane2.setBounds(145, 105, 209, scrollPane2.getPreferredSize().height);

                //---- label1 ----
                label1.setText("\u6635\u79f0");
                panel1.add(label1);
                label1.setBounds(30, 65, 55, label1.getPreferredSize().height);

                //======== scrollPane1 ========
                {
                    scrollPane1.setViewportView(textArea1);
                }
                panel1.add(scrollPane1);
                scrollPane1.setBounds(145, 60, 209, scrollPane1.getPreferredSize().height);

                //---- button1 ----
                button1.setText("\u63d0\u4ea4\u4fee\u6539");
                button1.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        try {
button1MouseClicked(e);} catch (SQLException ex) {
    throw new RuntimeException(ex);
}
                    }
                });
                panel1.add(button1);
                button1.setBounds(new Rectangle(new Point(415, 55), button1.getPreferredSize()));

                //---- label2 ----
                label2.setText("\u6027\u522b");
                panel1.add(label2);
                label2.setBounds(30, 105, 55, label2.getPreferredSize().height);

                //---- button2 ----
                button2.setText("\u63d0\u4ea4\u4fee\u6539");
                button2.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        try {
button2MouseClicked(e);} catch (SQLException ex) {
    throw new RuntimeException(ex);
}
                    }
                });
                panel1.add(button2);
                button2.setBounds(new Rectangle(new Point(415, 95), button2.getPreferredSize()));

                //---- label3 ----
                label3.setText("Dept");
                panel1.add(label3);
                label3.setBounds(30, 145, 55, label3.getPreferredSize().height);

                //======== scrollPane3 ========
                {
                    scrollPane3.setViewportView(textArea3);
                }
                panel1.add(scrollPane3);
                scrollPane3.setBounds(145, 145, 209, scrollPane3.getPreferredSize().height);

                //---- button3 ----
                button3.setText("\u63d0\u4ea4\u4fee\u6539");
                button3.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        try {
button3MouseClicked(e);} catch (SQLException ex) {
    throw new RuntimeException(ex);
}
                    }
                });
                panel1.add(button3);
                button3.setBounds(new Rectangle(new Point(415, 135), button3.getPreferredSize()));

                //---- label4 ----
                label4.setText("\u90ae\u7bb1");
                panel1.add(label4);
                label4.setBounds(30, 185, 55, label4.getPreferredSize().height);

                //======== scrollPane4 ========
                {
                    scrollPane4.setViewportView(textArea4);
                }
                panel1.add(scrollPane4);
                scrollPane4.setBounds(145, 185, 209, scrollPane4.getPreferredSize().height);

                //---- button4 ----
                button4.setText("\u63d0\u4ea4\u4fee\u6539");
                button4.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        try {
button4MouseClicked(e);} catch (SQLException ex) {
    throw new RuntimeException(ex);
}
                    }
                });
                panel1.add(button4);
                button4.setBounds(new Rectangle(new Point(415, 175), button4.getPreferredSize()));

                //---- label5 ----
                label5.setText("IPaddress");
                panel1.add(label5);
                label5.setBounds(new Rectangle(new Point(30, 225), label5.getPreferredSize()));

                //======== scrollPane5 ========
                {
                    scrollPane5.setViewportView(textArea5);
                }
                panel1.add(scrollPane5);
                scrollPane5.setBounds(145, 225, 209, scrollPane5.getPreferredSize().height);

                //---- button5 ----
                button5.setText("\u63d0\u4ea4\u4fee\u6539");
                button5.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button5MouseClicked(e);
                    }
                });
                panel1.add(button5);
                button5.setBounds(new Rectangle(new Point(415, 220), button5.getPreferredSize()));

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < panel1.getComponentCount(); i++) {
                        Rectangle bounds = panel1.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panel1.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panel1.setMinimumSize(preferredSize);
                    panel1.setPreferredSize(preferredSize);
                }
            }
            InformationChangeContentPane.add(panel1);
            panel1.setBounds(0, -15, 520, 310);

            InformationChangeContentPane.setPreferredSize(new Dimension(520, 310));
            InformationChange.pack();
            InformationChange.setLocationRelativeTo(InformationChange.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private static JFrame InformationChange;
    private static JPanel panel1;
    private JScrollPane scrollPane2;
    private JTextArea textArea2;
    private JLabel label1;
    private JScrollPane scrollPane1;
    private JTextArea textArea1;
    private JButton button1;
    private JLabel label2;
    private JButton button2;
    private JLabel label3;
    private JScrollPane scrollPane3;
    private JTextArea textArea3;
    private JButton button3;
    private JLabel label4;
    private JScrollPane scrollPane4;
    private JTextArea textArea4;
    private JButton button4;
    private JLabel label5;
    private JScrollPane scrollPane5;
    private JTextArea textArea5;
    private JButton button5;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

   /* public static JPanel userInformationView(){
        InformationChange = UserPage2.returnJFrame();
        InformationChange.add(panel1);
        return panel1;
    }*/
}
