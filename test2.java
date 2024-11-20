import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import com.jgoodies.forms.factories.*;
/*
 * Created by JFormDesigner on Wed Nov 13 23:04:46 CST 2024
 */



/**
 * @author Administrator
 */
public class test2 extends JPanel {
    public static void main(String[] args) {

            SwingUtilities.invokeLater(() -> {
                test2 t = new test2();
                t.setVisible(true);
            });

    }
    public test2() {
        initComponents();
    }

    private void button1AncestorAdded(AncestorEvent e) {
        // TODO add your code here
        System.out.println('1');
    }

    private void button2AncestorAdded(AncestorEvent e) {
        // TODO add your code here
        System.out.println('2');
    }

    private void button3AncestorAdded(AncestorEvent e) {
        // TODO add your code here
        System.out.println('3');
    }

    private void button4AncestorAdded(AncestorEvent e) {
        // TODO add your code here
        System.out.println('4');
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
        button1 = new JButton();
        button2 = new JButton();
        textArea8 = new JTextArea();
        logon = new JFrame();
        textArea4 = new JTextArea();
        clientLogonPassword = new JPasswordField();
        scrollPane3 = new JScrollPane();
        clientLogonName = new JTextArea();
        button5 = new JButton();
        button6 = new JButton();
        clientLogonPasswordCheck = new JPasswordField();
        label1 = compFactory.createLabel("\u7528\u6237\u540d");
        label2 = compFactory.createLabel("\u5bc6\u7801");
        label3 = compFactory.createLabel("\u786e\u8ba4\u5bc6\u7801");
        userwindow = new JFrame();
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItem1 = new JMenuItem();
        menuItem7 = new JMenuItem();
        menu4 = new JMenu();
        menuItem5 = new JMenuItem();
        menuItem6 = new JMenuItem();
        menuItem2 = new JMenuItem();
        button7 = new JButton();
        scrollPane4 = new JScrollPane();
        textArea6 = new JTextArea();
        scrollPane7 = new JScrollPane();
        textArea7 = new JTextArea();
        menuBar2 = new JMenuBar();
        menu3 = new JMenu();
        menuItem3 = new JMenuItem();
        menuItem4 = new JMenuItem();
        radioButton1 = new JRadioButton();
        button9 = new JButton();
        comboBox1 = new JComboBox();
        comboBox2 = new JComboBox();
        button10 = new JButton();
        button11 = new JButton();
        button12 = new JButton();
        button8 = new JButton();
        login = new JFrame();
        textArea2 = new JTextArea();
        clientLoginPassword = new JPasswordField();
        scrollPane2 = new JScrollPane();
        clientLoginName = new JTextArea();
        button3 = new JButton();
        button4 = new JButton();
        label4 = compFactory.createLabel("\u7528\u6237\u540d");
        label5 = compFactory.createLabel("\u5bc6\u7801");

        //======== this ========
        setForeground(Color.pink);
        setLayout(null);

        //---- button1 ----
        button1.setText("\u6ce8\u518c");
        button1.setFont(button1.getFont().deriveFont(button1.getFont().getSize() + 6f));
        button1.addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent e) {
                button1AncestorAdded(e);
            }
            @Override
            public void ancestorMoved(AncestorEvent e) {}
            @Override
            public void ancestorRemoved(AncestorEvent e) {}
        });
        add(button1);
        button1.setBounds(new Rectangle(new Point(165, 155), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u767b\u5165");
        button2.setFont(button2.getFont().deriveFont(button2.getFont().getSize() + 6f));
        button2.addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent e) {
                button2AncestorAdded(e);
            }
            @Override
            public void ancestorMoved(AncestorEvent e) {}
            @Override
            public void ancestorRemoved(AncestorEvent e) {}
        });
        add(button2);
        button2.setBounds(new Rectangle(new Point(165, 215), button2.getPreferredSize()));

        //---- textArea8 ----
        textArea8.setText("\u8ba1\u7b97\u673a\u7f51\u7edc\u804a\u5929\u8f6f\u4ef6");
        textArea8.setTabSize(12);
        textArea8.setFont(textArea8.getFont().deriveFont(textArea8.getFont().getSize() + 10f));
        textArea8.setEditable(false);
        add(textArea8);
        textArea8.setBounds(110, 100, 203, 33);

        setPreferredSize(new Dimension(400, 300));

        //======== logon ========
        {
            Container logonContentPane = logon.getContentPane();
            logonContentPane.setLayout(null);

            //---- textArea4 ----
            textArea4.setText("\u804a\u5929\u8f6f\u4ef6\u6ce8\u518c\u754c\u9762");
            textArea4.setTabSize(12);
            textArea4.setFont(textArea4.getFont().deriveFont(textArea4.getFont().getSize() + 10f));
            textArea4.setEditable(false);
            logonContentPane.add(textArea4);
            textArea4.setBounds(100, 35, 203, 33);
            logonContentPane.add(clientLogonPassword);
            clientLogonPassword.setBounds(100, 135, 210, 30);

            //======== scrollPane3 ========
            {
                scrollPane3.setViewportView(clientLogonName);
            }
            logonContentPane.add(scrollPane3);
            scrollPane3.setBounds(100, 100, 210, 24);

            //---- button5 ----
            button5.setText("LogOn");
            logonContentPane.add(button5);
            button5.setBounds(new Rectangle(new Point(80, 225), button5.getPreferredSize()));

            //---- button6 ----
            button6.setText("Back");
            logonContentPane.add(button6);
            button6.setBounds(new Rectangle(new Point(245, 225), button6.getPreferredSize()));
            logonContentPane.add(clientLogonPasswordCheck);
            clientLogonPasswordCheck.setBounds(100, 175, 210, 30);
            logonContentPane.add(label1);
            label1.setBounds(50, 100, 50, 20);
            logonContentPane.add(label2);
            label2.setBounds(60, 140, 50, 20);
            logonContentPane.add(label3);
            label3.setBounds(45, 180, 50, 20);

            logonContentPane.setPreferredSize(new Dimension(400, 324));
            logon.pack();
            logon.setLocationRelativeTo(logon.getOwner());
        }

        //======== userwindow ========
        {
            userwindow.setResizable(false);
            Container userwindowContentPane = userwindow.getContentPane();
            userwindowContentPane.setLayout(null);

            //======== menuBar1 ========
            {

                //======== menu1 ========
                {
                    menu1.setText("\u7528\u6237\u540d");

                    //---- menuItem1 ----
                    menuItem1.setText("\u8054\u7cfb\u4eba");
                    menu1.add(menuItem1);

                    //---- menuItem7 ----
                    menuItem7.setText("\u5386\u53f2\u8bb0\u5f55");
                    menu1.add(menuItem7);

                    //======== menu4 ========
                    {
                        menu4.setText("\u8bbe\u7f6e");

                        //---- menuItem5 ----
                        menuItem5.setText("\u4e2a\u4eba\u4fe1\u606f");
                        menu4.add(menuItem5);

                        //---- menuItem6 ----
                        menuItem6.setText("\u8d26\u53f7\u7ba1\u7406");
                        menu4.add(menuItem6);
                    }
                    menu1.add(menu4);

                    //---- menuItem2 ----
                    menuItem2.setText("\u767b\u51fa");
                    menu1.add(menuItem2);
                }
                menuBar1.add(menu1);
            }
            userwindow.setJMenuBar(menuBar1);

            //---- button7 ----
            button7.setText("\u8fd4\u56de");
            userwindowContentPane.add(button7);
            button7.setBounds(555, 460, 75, 30);

            //======== scrollPane4 ========
            {
                scrollPane4.setViewportView(textArea6);
            }
            userwindowContentPane.add(scrollPane4);
            scrollPane4.setBounds(20, 255, 615, 190);

            //======== scrollPane7 ========
            {

                //---- textArea7 ----
                textArea7.setEditable(false);
                scrollPane7.setViewportView(textArea7);
            }
            userwindowContentPane.add(scrollPane7);
            scrollPane7.setBounds(20, 20, 615, 195);

            //======== menuBar2 ========
            {

                //======== menu3 ========
                {
                    menu3.setText("\u5f55\u5c4f\u4e0e\u622a\u56fe");

                    //---- menuItem3 ----
                    menuItem3.setText("\u5f55\u5c4f");
                    menu3.add(menuItem3);

                    //---- menuItem4 ----
                    menuItem4.setText("\u622a\u56fe");
                    menu3.add(menuItem4);

                    //---- radioButton1 ----
                    radioButton1.setText("\u9690\u85cf\u7a97\u53e3");
                    menu3.add(radioButton1);
                }
                menuBar2.add(menu3);

                //---- button9 ----
                button9.setText("\u6587\u4ef6\u4f20\u8f93");
                menuBar2.add(button9);
                menuBar2.add(comboBox1);
                menuBar2.add(comboBox2);

                //---- button10 ----
                button10.setText("\u52a0\u7c97");
                menuBar2.add(button10);

                //---- button11 ----
                button11.setText("\u659c\u4f53");
                menuBar2.add(button11);

                //---- button12 ----
                button12.setText("\u4e0b\u5212\u7ebf");
                menuBar2.add(button12);
            }
            userwindowContentPane.add(menuBar2);
            menuBar2.setBounds(20, 225, 615, 20);

            //---- button8 ----
            button8.setText("\u53d1\u9001");
            userwindowContentPane.add(button8);
            button8.setBounds(460, 460, 75, 30);

            userwindowContentPane.setPreferredSize(new Dimension(670, 560));
            userwindow.pack();
            userwindow.setLocationRelativeTo(userwindow.getOwner());
        }

        //======== login ========
        {
            Container loginContentPane = login.getContentPane();
            loginContentPane.setLayout(null);

            //---- textArea2 ----
            textArea2.setText("\u804a\u5929\u8f6f\u4ef6\u767b\u5165\u754c\u9762");
            textArea2.setTabSize(12);
            textArea2.setFont(textArea2.getFont().deriveFont(textArea2.getFont().getSize() + 10f));
            textArea2.setEditable(false);
            loginContentPane.add(textArea2);
            textArea2.setBounds(105, 35, 203, 33);
            loginContentPane.add(clientLoginPassword);
            clientLoginPassword.setBounds(100, 135, 225, 30);

            //======== scrollPane2 ========
            {
                scrollPane2.setViewportView(clientLoginName);
            }
            loginContentPane.add(scrollPane2);
            scrollPane2.setBounds(105, 100, 215, 24);

            //---- button3 ----
            button3.setText("LogIn");
            button3.addAncestorListener(new AncestorListener() {
                @Override
                public void ancestorAdded(AncestorEvent e) {
                    button3AncestorAdded(e);
                }
                @Override
                public void ancestorMoved(AncestorEvent e) {}
                @Override
                public void ancestorRemoved(AncestorEvent e) {}
            });
            loginContentPane.add(button3);
            button3.setBounds(new Rectangle(new Point(80, 185), button3.getPreferredSize()));

            //---- button4 ----
            button4.setText("Back");
            button4.addAncestorListener(new AncestorListener() {
                @Override
                public void ancestorAdded(AncestorEvent e) {
                    button4AncestorAdded(e);
                }
                @Override
                public void ancestorMoved(AncestorEvent e) {}
                @Override
                public void ancestorRemoved(AncestorEvent e) {}
            });
            loginContentPane.add(button4);
            button4.setBounds(new Rectangle(new Point(245, 185), button4.getPreferredSize()));
            loginContentPane.add(label4);
            label4.setBounds(new Rectangle(new Point(50, 105), label4.getPreferredSize()));
            loginContentPane.add(label5);
            label5.setBounds(55, 140, 36, 17);

            loginContentPane.setPreferredSize(new Dimension(400, 279));
            login.pack();
            login.setLocationRelativeTo(login.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JButton button1;
    private JButton button2;
    private JTextArea textArea8;
    private JFrame logon;
    private JTextArea textArea4;
    private JPasswordField clientLogonPassword;
    private JScrollPane scrollPane3;
    private JTextArea clientLogonName;
    private JButton button5;
    private JButton button6;
    private JPasswordField clientLogonPasswordCheck;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JFrame userwindow;
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItem1;
    private JMenuItem menuItem7;
    private JMenu menu4;
    private JMenuItem menuItem5;
    private JMenuItem menuItem6;
    private JMenuItem menuItem2;
    private JButton button7;
    private JScrollPane scrollPane4;
    private JTextArea textArea6;
    private JScrollPane scrollPane7;
    private JTextArea textArea7;
    private JMenuBar menuBar2;
    private JMenu menu3;
    private JMenuItem menuItem3;
    private JMenuItem menuItem4;
    private JRadioButton radioButton1;
    private JButton button9;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton button10;
    private JButton button11;
    private JButton button12;
    private JButton button8;
    private JFrame login;
    private JTextArea textArea2;
    private JPasswordField clientLoginPassword;
    private JScrollPane scrollPane2;
    private JTextArea clientLoginName;
    private JButton button3;
    private JButton button4;
    private JLabel label4;
    private JLabel label5;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
