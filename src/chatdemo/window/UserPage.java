/*
 * Created by JFormDesigner on Sat Dec 21 22:17:38 CST 2024
 */

package chatdemo.window;


import chatdemo.Tools.FileSender;
import chatdemo.Tools.FontSet;
import chatdemo.Tools.UserInfo;
import chatdemo.Tools.UserReadInfoThread;
import chatdemo.message.*;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringStack;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * @author whmfy
 */
public class UserPage extends JFrame {
    String userName1;
    Socket userSocket;
    String clientIp;
    int clientPort;
    String passwordChange;
    String passwordChangeCheck;
    String password;
    List<String> friends;
     ChatMessage chatMessage;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;
    UserInfo userInfo;
    FontSet fontSet;
    FileSendMessage fileSendMessage;
    private String date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
    ExecutorService es = Executors.newSingleThreadExecutor();
    Font PFont = new Font("宋体",Font.PLAIN,12);
    File file;
    public UserPage(String userName1, Socket userSocket, List<String> friends, String clientIp,int clientPort, ObjectOutputStream objectOutputStream, ObjectInputStream objectInputStream, UserInfo userInfo) {
        this.userName1 = userName1;
        this.userSocket = userSocket;
        this.objectOutputStream = objectOutputStream;
        this.objectInputStream = objectInputStream;
        this.friends = friends;
        this.userInfo = userInfo;
        this.clientIp = clientIp;
        this.clientPort = clientPort;
        initComponents();
        panel1.setLayout(scardLayout);
        menu3.setText(userName1);
        label5.setText(userName1);
        userName.setText(userName1+"\n"+userSocket.getPort());
        UserReadInfoThread readInfoThread = new UserReadInfoThread(objectInputStream,objectOutputStream, userName1,clientIp,clientPort,textArea10);
        es.execute(readInfoThread);
        password = textArea6.getText();
        passwordChange = textArea7.getText();
        passwordChangeCheck = textArea8.getText();
        textArea1.setText(userInfo.getUserNickName());
        textArea3.setText(userInfo.getDept());
        textArea4.setText(userInfo.getEmail());
        String[] friendsArray =  friends.toArray(new String[friends.size()]);
        friendsState.setListData(friendsArray);
    }


    public static void main(String[] args) throws SQLException, IOException {
        UserPage us = new UserPage();
        us.setVisible(true);
    }

    public UserPage(String userName1, Socket userSocket, List<String> friends, String clientIp,int clientPort,ObjectOutputStream objectOutputStream,ObjectInputStream objectInputStream) throws IOException, SQLException {
        this.userName1 = userName1;
        this.userSocket = userSocket;
        this.clientPort =clientPort;
        this.clientIp = clientIp;
        //objectInputStream = new ObjectInputStream(userSocket.getInputStream());
        //objectOutputStream = new ObjectOutputStream(userSocket.getOutputStream());
        this.objectOutputStream = objectOutputStream;
        this.objectInputStream = objectInputStream;
        this.friends = friends;
        System.out.println(userName1);
        clientPort = userSocket.getPort();
        initComponents();
        panel1.setLayout(scardLayout);
        menu3.setText(userName1);
        label5.setText(userName1);
        userName.setText(userName1+"\n"+userSocket.getPort());

        UserReadInfoThread readInfoThread = new UserReadInfoThread(objectInputStream,objectOutputStream, userName1,clientIp,clientPort,textArea10);
        es.execute(readInfoThread);
        String[] friendsArray =  friends.toArray(new String[friends.size()]);
        friendsState.setListData(friendsArray);

        password = textArea6.getText();
        passwordChange = textArea7.getText();
        passwordChangeCheck = textArea8.getText();
        System.out.println(userSocket.getPort());
    }



    public UserPage() throws SQLException, IOException {
        initComponents();
        /*String[] friendsArray =  friends.toArray(new String[friends.size()]);
        friendsState.setListData(friendsArray);*/
    }

    CardLayout scardLayout = new CardLayout();
    private void menuItem7(ActionEvent e) {
        // TODO add your code here
        scardLayout.show(panel1,"card1");
    }

    private void menuItem8(ActionEvent e) {
        // TODO add your code here
        scardLayout.show(panel1,"card2");
    }

    private void menuItem9(ActionEvent e) {
        // TODO add your code here
        scardLayout.show(panel1,"card3");
    }

    private void menuItem1(ActionEvent e) {
        // TODO add your code here
        scardLayout.show(panel1,"card4");
    }

    private void menuItem5(ActionEvent e) {
        // TODO add your code here
        scardLayout.show(panel1,"card5");
    }
    private void menuItem6(ActionEvent e) {
        // TODO add your code here
        scardLayout.show(panel1,"card6");
    }


    private void button5MouseClicked(MouseEvent e) throws IOException {
        // TODO add your code here
        UserInfo userInfo = new UserInfo();
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);
        String gender;
        if(radioButton1.isSelected()){
            gender = "1";
        }
        else {
            gender = "0";
        }
        userInfo.setUserId(userName1);
        userInfo.setGender(gender);
        userInfo.setDept(textArea3.getText());
        userInfo.setEmail(textArea4.getText());


        chatMessage = new ChatMessage(userName1,"servere",userInfo,MessageType.TYPE_CHANGE_USER_INFO);
        objectOutputStream.writeObject(chatMessage);
    }

    private void button6MouseClicked(MouseEvent e) throws IOException, ClassNotFoundException {
        // TODO add your code here
        //objectInputStream = new ObjectInputStream(userSocket.getInputStream());
       // objectOutputStream=new ObjectOutputStream(userSocket.getOutputStream());
        password = textArea6.getText();
        passwordChange = textArea7.getText();
        passwordChangeCheck = textArea8.getText();
        if(!passwordChange.equals(passwordChangeCheck)){
            JOptionPane.showMessageDialog(null, "两次输入不一致", "ERROR", JOptionPane.WARNING_MESSAGE);
        }else {
            System.out.println("check1");
            chatMessage = new ChatMessage(userName1, "server", password + ";" + passwordChange, MessageType.TYPE_CHANGE_PASSWORD);
            objectOutputStream.writeObject(chatMessage);
            System.out.println("check2");
            objectInputStream = new ObjectInputStream(userSocket.getInputStream());
            chatMessage = (ChatMessage) objectInputStream.readObject();

        }
    }

    //发送消息按钮
    private void button8MouseClicked(MouseEvent e) throws IOException {
        // TODO add your code here


        Graphics graphics = getGraphics();
        Graphics2D graphics2D = (Graphics2D)graphics;
        Font font = new Font(fontSet.getFontType(),Font.PLAIN,12);
        graphics2D.setFont(font);
        graphics2D.drawString(fontSet.getMessage(),10,10);


        String sendTo = textField1.getText();
        String info = textArea11.getText();


         textArea10.setText(textArea10.getText()+"\n"+"用户"+userName1+"发来消息:"+info+"   "+date+"\n");

        System.out.println("check3");
        objectOutputStream.writeObject(new ChatMessage(userName1,sendTo,info,fontSet.getFontType(),MessageType.TYPE_SEND));
        textArea11.setText("");
    }


    //截图按钮
    private void menuItem11(ActionEvent e) throws AWTException, IOException, InterruptedException {
        // TODO add your code here
        // 创建一个Robot对象

        if(radioButton2.isSelected()){
         this.setVisible(false);
            TimeUnit.SECONDS.sleep(1);
        }
        Robot robot = new Robot();
// 获取屏幕的大小
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
// 创建一个Rectangle对象，用于指定截屏的区域
        Rectangle rectangle = new Rectangle(dimension);
// 捕获屏幕上的内容
        BufferedImage bufferedImage = robot.createScreenCapture(rectangle);
// 将捕获的内容保存到文件
        String screenShotPathName = "screenshot.jpg";

        if (new File("screenshot.jpg").exists()){
            int i = 1;
            while (new File(String.format("screenshot(%d).jpg",i)).exists()) {
                i++;
            }
            screenShotPathName = String.format("screenshot(%d).jpg",i);
        }else {
            ImageIO.write(bufferedImage, "jpg", new File(screenShotPathName));
        }
        TimeUnit.SECONDS.sleep(2);
        this.setVisible(true);
        System.out.println(screenShotPathName);
        ImageIO.write(bufferedImage, "jpg", new File(screenShotPathName));

        JFrame frame=new JFrame("截图");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        ImageIcon img= new ImageIcon(screenShotPathName);
        //这是背景图片

        JLabel imgLabel =new JLabel(img);
        //将背景图放在标签里。

        frame.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
        //注意这里是关键，将背景标签添加到jfram的LayeredPane面板里。

        imgLabel.setBounds(0,0,img.getIconWidth(), img.getIconHeight());
        //设置背景标签的位置

        Container cp=frame.getContentPane();

        ((JPanel)cp).setOpaque(false);
        //注意这里，将内容面板设为透明。这样LayeredPane面板中的背景才能显示出来。

        frame.setSize(500,300);
        //frame.setVisible(true);

        frame.setVisible(true);

    }

    //退出按钮
    private void button1MouseClicked(MouseEvent e) throws IOException {
        // TODO add your code here
        objectOutputStream.writeObject(new ChatMessage(userName1,"server","LOGOUT", MessageType.TYPE_LOGOUT));
        System.exit(0);

    }

    //发送文件
    private void button13MouseClicked(MouseEvent e) throws IOException {
        // TODO add your code here
        System.out.println("check  "+clientIp+clientPort);
        String sendFile = JOptionPane.showInputDialog(null,"要发送文件给谁？","Title",JOptionPane.PLAIN_MESSAGE);
        String fileNameAndPath = JOptionPane.showInputDialog(null,"文件名称？","Title",JOptionPane.PLAIN_MESSAGE);
        file = new File(fileNameAndPath);
        objectOutputStream.writeObject(new FileSendMessage(userName1,sendFile,MessageType.TYPE_SEND_FILES,fileNameAndPath,clientIp,clientPort));

    }


    private void button2MouseClicked(MouseEvent e) {
        // TODO add your code here
        String message = textArea11.getText();
        fontSet = new FontSet("宋体",message,textArea11);
        fontSet = new FontSet("宋体",message,textArea10);
    }

    private void button3MouseClicked(MouseEvent e) {
        // TODO add your code here
        String message = textArea11.getText();
        fontSet = new FontSet("黑体",message,textArea11);
        fontSet = new FontSet("黑体",message,textArea10);
    }

    private void button4MouseClicked(MouseEvent e) {
        // TODO add your code here
        String message = textArea11.getText();
        fontSet = new FontSet("楷体",message,textArea11);
        fontSet = new FontSet("楷体",message,textArea10);

    }



    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        menuBar1 = new JMenuBar();
        usermenu = new JMenu();
        menu3 = new JMenu();
        Information = new JMenuItem();
        Manage = new JMenuItem();
        Main = new JMenuItem();
        Friends = new JMenuItem();
        Chat = new JMenuItem();
        menuItem6 = new JMenuItem();
        panel1 = new JPanel();
        InfomationPage = new JPanel();
        label1 = new JLabel();
        scrollPane1 = new JScrollPane();
        textArea1 = new JTextArea();
        label2 = new JLabel();
        label3 = new JLabel();
        scrollPane3 = new JScrollPane();
        textArea3 = new JTextArea();
        label4 = new JLabel();
        scrollPane4 = new JScrollPane();
        textArea4 = new JTextArea();
        button5 = new JButton();
        radioButton1 = new JRadioButton();
        radioButton3 = new JRadioButton();
        ManagePage = new JPanel();
        label6 = new JLabel();
        label7 = new JLabel();
        scrollPane6 = new JScrollPane();
        textArea6 = new JTextArea();
        label8 = new JLabel();
        scrollPane7 = new JScrollPane();
        textArea7 = new JTextArea();
        label9 = new JLabel();
        scrollPane8 = new JScrollPane();
        textArea8 = new JTextArea();
        button6 = new JButton();
        MainPage = new JPanel();
        label5 = new JLabel();
        label12 = new JLabel();
        label13 = new JLabel();
        FriendsPage = new JPanel();
        userName = new JTextField();
        scrollPane10 = new JScrollPane();
        friendsState = new JList();
        label10 = new JLabel();
        ChatPage = new JPanel();
        label11 = new JLabel();
        textField1 = new JTextField();
        scrollPane11 = new JScrollPane();
        textArea10 = new JTextArea();
        menuBar3 = new JMenuBar();
        menu5 = new JMenu();
        menuItem10 = new JMenuItem();
        menuItem11 = new JMenuItem();
        radioButton2 = new JRadioButton();
        button13 = new JButton();
        menu1 = new JMenu();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        comboBox4 = new JComboBox();
        button14 = new JButton();
        button15 = new JButton();
        button16 = new JButton();
        scrollPane12 = new JScrollPane();
        textArea11 = new JTextArea();
        button8 = new JButton();
        button7 = new JButton();
        panel2 = new JPanel();
        label14 = new JLabel();
        button1 = new JButton();

        //======== this ========
        setResizable(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== menuBar1 ========
        {

            //======== usermenu ========
            {
                usermenu.setText("\u8bbe\u7f6e");
                usermenu.setFont(new Font("\u5e7c\u5706", Font.BOLD, 20));

                //======== menu3 ========
                {
                    menu3.setText("\u7528\u6237\u540d");
                    menu3.setFont(new Font("\u534e\u6587\u5b8b\u4f53", Font.PLAIN, 16));

                    //---- Information ----
                    Information.setText("\u4e2a\u4eba\u4fe1\u606f");
                    Information.setFont(new Font("\u5e7c\u5706", Font.PLAIN, 14));
                    Information.addActionListener(e -> menuItem7(e));
                    menu3.add(Information);

                    //---- Manage ----
                    Manage.setText("\u8d26\u53f7\u7ba1\u7406");
                    Manage.setFont(new Font("\u5e7c\u5706", Font.PLAIN, 14));
                    Manage.addActionListener(e -> menuItem8(e));
                    menu3.add(Manage);
                }
                usermenu.add(menu3);

                //---- Main ----
                Main.setText("\u4e3b\u754c\u9762");
                Main.setFont(new Font("\u534e\u6587\u5b8b\u4f53", Font.PLAIN, 16));
                Main.addActionListener(e -> menuItem9(e));
                usermenu.add(Main);

                //---- Friends ----
                Friends.setText("\u8054\u7cfb\u4eba");
                Friends.setFont(new Font("\u534e\u6587\u5b8b\u4f53", Font.PLAIN, 16));
                Friends.addActionListener(e -> menuItem1(e));
                usermenu.add(Friends);

                //---- Chat ----
                Chat.setText("\u804a\u5929\u7a97\u53e3");
                Chat.setActionCommand("\u804a\u5929\u7a97\u53e3");
                Chat.setFont(new Font("\u534e\u6587\u5b8b\u4f53", Font.PLAIN, 16));
                Chat.addActionListener(e -> menuItem5(e));
                usermenu.add(Chat);

                //---- menuItem6 ----
                menuItem6.setText("\u767b\u51fa");
                menuItem6.setFont(new Font("\u534e\u6587\u5b8b\u4f53", Font.PLAIN, 16));
                menuItem6.addActionListener(e -> menuItem6(e));
                usermenu.add(menuItem6);
            }
            menuBar1.add(usermenu);
        }
        setJMenuBar(menuBar1);

        //======== panel1 ========
        {
            panel1.setLayout(scardLayout);

            //======== InfomationPage ========
            {
                InfomationPage.setLayout(null);

                //---- label1 ----
                label1.setText("\u6635\u79f0");
                label1.setFont(new Font("\u5e7c\u5706", Font.PLAIN, 18));
                InfomationPage.add(label1);
                label1.setBounds(110, 90, 55, label1.getPreferredSize().height);

                //======== scrollPane1 ========
                {

                    //---- textArea1 ----
                    textArea1.setFont(new Font("\u5e7c\u5706", Font.BOLD, 16));
                    scrollPane1.setViewportView(textArea1);
                }
                InfomationPage.add(scrollPane1);
                scrollPane1.setBounds(210, 85, 209, scrollPane1.getPreferredSize().height);

                //---- label2 ----
                label2.setText("\u6027\u522b");
                label2.setFont(new Font("\u5e7c\u5706", Font.PLAIN, 18));
                InfomationPage.add(label2);
                label2.setBounds(110, 130, 55, label2.getPreferredSize().height);

                //---- label3 ----
                label3.setText("\u51fa\u751f\u65e5\u671f");
                label3.setFont(new Font("\u5e7c\u5706", Font.PLAIN, 18));
                InfomationPage.add(label3);
                label3.setBounds(110, 170, 95, 20);

                //======== scrollPane3 ========
                {

                    //---- textArea3 ----
                    textArea3.setFont(new Font("\u5e7c\u5706", Font.PLAIN, 16));
                    scrollPane3.setViewportView(textArea3);
                }
                InfomationPage.add(scrollPane3);
                scrollPane3.setBounds(210, 170, 209, scrollPane3.getPreferredSize().height);

                //---- label4 ----
                label4.setText("\u90ae\u7bb1");
                label4.setFont(new Font("\u5e7c\u5706", Font.PLAIN, 18));
                InfomationPage.add(label4);
                label4.setBounds(110, 215, 55, label4.getPreferredSize().height);

                //======== scrollPane4 ========
                {

                    //---- textArea4 ----
                    textArea4.setFont(new Font("\u5e7c\u5706", Font.PLAIN, 16));
                    scrollPane4.setViewportView(textArea4);
                }
                InfomationPage.add(scrollPane4);
                scrollPane4.setBounds(210, 210, 209, scrollPane4.getPreferredSize().height);

                //---- button5 ----
                button5.setText("\u63d0\u4ea4\u4fee\u6539");
                button5.setFont(new Font("\u5e7c\u5706", Font.BOLD, 16));
                button5.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        try {
button5MouseClicked(e);} catch (IOException ex) {
    throw new RuntimeException(ex);
}
                    }
                });
                InfomationPage.add(button5);
                button5.setBounds(new Rectangle(new Point(460, 150), button5.getPreferredSize()));

                //---- radioButton1 ----
                radioButton1.setText("\u7537");
                radioButton1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                InfomationPage.add(radioButton1);
                radioButton1.setBounds(210, 130, 70, 26);

                //---- radioButton3 ----
                radioButton3.setText("\u5973");
                radioButton3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                InfomationPage.add(radioButton3);
                radioButton3.setBounds(270, 130, 60, 26);
            }
            panel1.add(InfomationPage, "card1");

            //======== ManagePage ========
            {
                ManagePage.setLayout(null);

                //---- label6 ----
                label6.setText("\u5bc6\u7801\u4fee\u6539");
                label6.setFont(new Font("\u5e7c\u5706", Font.PLAIN, 36));
                ManagePage.add(label6);
                label6.setBounds(255, 15, 225, 110);

                //---- label7 ----
                label7.setText("\u539f\u5bc6\u7801");
                label7.setFont(new Font("\u5e7c\u5706", Font.PLAIN, 18));
                ManagePage.add(label7);
                label7.setBounds(125, 140, 75, label7.getPreferredSize().height);

                //======== scrollPane6 ========
                {
                    scrollPane6.setViewportView(textArea6);
                }
                ManagePage.add(scrollPane6);
                scrollPane6.setBounds(250, 140, 317, scrollPane6.getPreferredSize().height);

                //---- label8 ----
                label8.setText("\u65b0\u5bc6\u7801");
                label8.setFont(new Font("\u5e7c\u5706", Font.PLAIN, 18));
                ManagePage.add(label8);
                label8.setBounds(125, 195, 75, label8.getPreferredSize().height);

                //======== scrollPane7 ========
                {
                    scrollPane7.setViewportView(textArea7);
                }
                ManagePage.add(scrollPane7);
                scrollPane7.setBounds(250, 190, 317, scrollPane7.getPreferredSize().height);

                //---- label9 ----
                label9.setText("\u786e\u8ba4\u5bc6\u7801");
                label9.setFont(new Font("\u5e7c\u5706", Font.PLAIN, 18));
                ManagePage.add(label9);
                label9.setBounds(new Rectangle(new Point(115, 245), label9.getPreferredSize()));

                //======== scrollPane8 ========
                {
                    scrollPane8.setViewportView(textArea8);
                }
                ManagePage.add(scrollPane8);
                scrollPane8.setBounds(250, 245, 317, scrollPane8.getPreferredSize().height);

                //---- button6 ----
                button6.setText("\u786e\u8ba4\u4fee\u6539");
                button6.setFont(new Font("\u5e7c\u5706", Font.PLAIN, 18));
                button6.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        try {
button6MouseClicked(e);} catch (IOException ex) {
    throw new RuntimeException(ex);
} catch (ClassNotFoundException ex) {
    throw new RuntimeException(ex);
}
                    }
                });
                ManagePage.add(button6);
                button6.setBounds(460, 305, 128, button6.getPreferredSize().height);
            }
            panel1.add(ManagePage, "card2");

            //======== MainPage ========
            {
                MainPage.setLayout(null);

                //---- label5 ----
                label5.setText("test");
                label5.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 30));
                MainPage.add(label5);
                label5.setBounds(225, 140, 120, 100);

                //---- label12 ----
                label12.setText("\u7528\u6237");
                label12.setFont(new Font("\u534e\u6587\u7425\u73c0", Font.ITALIC, 30));
                MainPage.add(label12);
                label12.setBounds(400, 150, 150, 80);

                //---- label13 ----
                label13.setText("\u6b22\u8fce\u4f7f\u7528\u8f6f\u4ef6");
                label13.setFont(new Font("\u534e\u6587\u7425\u73c0", Font.PLAIN, 48));
                MainPage.add(label13);
                label13.setBounds(190, 230, 350, 100);
            }
            panel1.add(MainPage, "card3");

            //======== FriendsPage ========
            {
                FriendsPage.setLayout(null);

                //---- userName ----
                userName.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
                userName.setFocusable(false);
                userName.setEditable(false);
                userName.setEnabled(false);
                userName.setFont(new Font("\u534e\u6587\u7425\u73c0", Font.PLAIN, 48));
                FriendsPage.add(userName);
                userName.setBounds(380, 205, 265, 45);

                //======== scrollPane10 ========
                {

                    //---- friendsState ----
                    friendsState.setEnabled(false);
                    friendsState.setMinimumSize(null);
                    friendsState.setMaximumSize(null);
                    friendsState.setFont(new Font("\u5e7c\u5706", Font.BOLD, 20));
                    scrollPane10.setViewportView(friendsState);
                }
                FriendsPage.add(scrollPane10);
                scrollPane10.setBounds(90, 120, 235, 265);

                //---- label10 ----
                label10.setText("\u597d\u53cb\u72b6\u6001");
                label10.setFont(new Font("\u5e7c\u5706", Font.BOLD, 28));
                FriendsPage.add(label10);
                label10.setBounds(125, 65, 165, 30);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < FriendsPage.getComponentCount(); i++) {
                        Rectangle bounds = FriendsPage.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = FriendsPage.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    FriendsPage.setMinimumSize(preferredSize);
                    FriendsPage.setPreferredSize(preferredSize);
                }
            }
            panel1.add(FriendsPage, "card4");

            //======== ChatPage ========
            {
                ChatPage.setLayout(null);

                //---- label11 ----
                label11.setText("\u53d1\u9001\u7ed9\u8c01");
                label11.setFont(new Font("\u5e7c\u5706", Font.BOLD, 16));
                ChatPage.add(label11);
                label11.setBounds(55, 30, 75, 25);
                ChatPage.add(textField1);
                textField1.setBounds(150, 30, 80, 25);

                //======== scrollPane11 ========
                {

                    //---- textArea10 ----
                    textArea10.setEditable(false);
                    scrollPane11.setViewportView(textArea10);
                }
                ChatPage.add(scrollPane11);
                scrollPane11.setBounds(70, 60, 615, 165);

                //======== menuBar3 ========
                {

                    //======== menu5 ========
                    {
                        menu5.setText("\u5f55\u5c4f\u4e0e\u622a\u56fe");
                        menu5.setFont(new Font("\u5e7c\u5706", Font.PLAIN, 16));

                        //---- menuItem10 ----
                        menuItem10.setText("\u5f55\u5c4f");
                        menuItem10.setFont(new Font("\u5e7c\u5706", Font.PLAIN, 14));
                        menu5.add(menuItem10);

                        //---- menuItem11 ----
                        menuItem11.setText("\u622a\u56fe");
                        menuItem11.setFont(new Font("\u5e7c\u5706", Font.PLAIN, 14));
                        menuItem11.addActionListener(e -> {try {
menuItem11(e);} catch (AWTException ex) {
    throw new RuntimeException(ex);
} catch (IOException ex) {
    throw new RuntimeException(ex);
} catch (InterruptedException ex) {
    throw new RuntimeException(ex);
}});
                        menu5.add(menuItem11);

                        //---- radioButton2 ----
                        radioButton2.setText("\u9690\u85cf\u7a97\u53e3");
                        radioButton2.setFont(new Font("\u5e7c\u5706", Font.PLAIN, 14));
                        menu5.add(radioButton2);
                    }
                    menuBar3.add(menu5);

                    //---- button13 ----
                    button13.setText("\u6587\u4ef6\u4f20\u8f93");
                    button13.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            try {
button13MouseClicked(e);} catch (IOException ex) {
    throw new RuntimeException(ex);
}
                        }
                    });
                    menuBar3.add(button13);

                    //======== menu1 ========
                    {
                        menu1.setText("\u5b57\u4f53\u9009\u62e9");

                        //---- button2 ----
                        button2.setText("\u9ed1\u4f53");
                        button2.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                button2MouseClicked(e);
                            }
                        });
                        menu1.add(button2);

                        //---- button3 ----
                        button3.setText("\u5b8b\u4f53");
                        button3.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                button3MouseClicked(e);
                            }
                        });
                        menu1.add(button3);

                        //---- button4 ----
                        button4.setText("\u6977\u4f53");
                        button4.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                button4MouseClicked(e);
                            }
                        });
                        menu1.add(button4);
                    }
                    menuBar3.add(menu1);
                    menuBar3.add(comboBox4);

                    //---- button14 ----
                    button14.setText("\u52a0\u7c97");
                    menuBar3.add(button14);

                    //---- button15 ----
                    button15.setText("\u659c\u4f53");
                    menuBar3.add(button15);

                    //---- button16 ----
                    button16.setText("\u7a97\u4f53\u6296\u52a8");
                    button16.setActionCommand("\u6296\u52a8");
                    menuBar3.add(button16);
                }
                ChatPage.add(menuBar3);
                menuBar3.setBounds(45, 250, 673, menuBar3.getPreferredSize().height);

                //======== scrollPane12 ========
                {
                    scrollPane12.setViewportView(textArea11);
                }
                ChatPage.add(scrollPane12);
                scrollPane12.setBounds(70, 300, 615, 155);

                //---- button8 ----
                button8.setText("\u53d1\u9001");
                button8.setFont(new Font("\u5e7c\u5706", Font.BOLD, 16));
                button8.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        try {
button8MouseClicked(e);} catch (IOException ex) {
    throw new RuntimeException(ex);
}
                    }
                });
                ChatPage.add(button8);
                button8.setBounds(505, 480, 75, 30);

                //---- button7 ----
                button7.setText("\u8fd4\u56de");
                button7.setFont(new Font("\u5e7c\u5706", Font.BOLD, 16));
                ChatPage.add(button7);
                button7.setBounds(610, 480, 75, 30);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < ChatPage.getComponentCount(); i++) {
                        Rectangle bounds = ChatPage.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = ChatPage.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    ChatPage.setMinimumSize(preferredSize);
                    ChatPage.setPreferredSize(preferredSize);
                }
            }
            panel1.add(ChatPage, "card5");

            //======== panel2 ========
            {
                panel2.setLayout(null);

                //---- label14 ----
                label14.setText("\u60a8\u662f\u5426\u8981\u9000\u51fa......");
                label14.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 48));
                panel2.add(label14);
                label14.setBounds(100, 110, 395, 115);

                //---- button1 ----
                button1.setText("\u786e\u5b9a");
                button1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 22));
                button1.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        try {
button1MouseClicked(e);} catch (IOException ex) {
    throw new RuntimeException(ex);
}
                    }
                });
                panel2.add(button1);
                button1.setBounds(280, 320, 130, button1.getPreferredSize().height);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < panel2.getComponentCount(); i++) {
                        Rectangle bounds = panel2.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panel2.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panel2.setMinimumSize(preferredSize);
                    panel2.setPreferredSize(preferredSize);
                }
            }
            panel1.add(panel2, "card6");
        }
        contentPane.add(panel1);
        panel1.setBounds(0, 0, 765, 535);

        contentPane.setPreferredSize(new Dimension(770, 585));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JMenuBar menuBar1;
    private JMenu usermenu;
    private JMenu menu3;
    private JMenuItem Information;
    private JMenuItem Manage;
    private JMenuItem Main;
    private JMenuItem Friends;
    private JMenuItem Chat;
    private JMenuItem menuItem6;
    private JPanel panel1;
    private JPanel InfomationPage;
    private JLabel label1;
    private JScrollPane scrollPane1;
    private JTextArea textArea1;
    private JLabel label2;
    private JLabel label3;
    private JScrollPane scrollPane3;
    private JTextArea textArea3;
    private JLabel label4;
    private JScrollPane scrollPane4;
    private JTextArea textArea4;
    private JButton button5;
    private JRadioButton radioButton1;
    private JRadioButton radioButton3;
    private JPanel ManagePage;
    private JLabel label6;
    private JLabel label7;
    private JScrollPane scrollPane6;
    private JTextArea textArea6;
    private JLabel label8;
    private JScrollPane scrollPane7;
    private JTextArea textArea7;
    private JLabel label9;
    private JScrollPane scrollPane8;
    private JTextArea textArea8;
    private JButton button6;
    private JPanel MainPage;
    private JLabel label5;
    private JLabel label12;
    private JLabel label13;
    private JPanel FriendsPage;
    private JTextField userName;
    private JScrollPane scrollPane10;
    private JList friendsState;
    private JLabel label10;
    private JPanel ChatPage;
    private JLabel label11;
    private JTextField textField1;
    private JScrollPane scrollPane11;
    private JTextArea textArea10;
    private JMenuBar menuBar3;
    private JMenu menu5;
    private JMenuItem menuItem10;
    private JMenuItem menuItem11;
    private JRadioButton radioButton2;
    private JButton button13;
    private JMenu menu1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JComboBox comboBox4;
    private JButton button14;
    private JButton button15;
    private JButton button16;
    private JScrollPane scrollPane12;
    private JTextArea textArea11;
    private JButton button8;
    private JButton button7;
    private JPanel panel2;
    private JLabel label14;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
