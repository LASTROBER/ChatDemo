import java.awt.*;
import javax.swing.*;
/*
 * Created by JFormDesigner on Tue Nov 19 19:53:40 CST 2024
 */



/**
 * @author whmfy
 */
public class UserWindow extends JFrame {
    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserWindow uw = new UserWindow();
            uw.setVisible(true);
        });
    }*/
    public UserWindow() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItem1 = new JMenuItem();
        menuItem7 = new JMenuItem();
        menu4 = new JMenu();
        menuItem5 = new JMenuItem();
        menuItem6 = new JMenuItem();
        menuItem2 = new JMenuItem();
        scrollPane7 = new JScrollPane();
        textArea7 = new JTextArea();
        menuBar3 = new JMenuBar();
        menu5 = new JMenu();
        menuItem8 = new JMenuItem();
        menuItem9 = new JMenuItem();
        radioButton2 = new JRadioButton();
        button13 = new JButton();
        comboBox3 = new JComboBox();
        comboBox4 = new JComboBox();
        button14 = new JButton();
        button15 = new JButton();
        button16 = new JButton();
        scrollPane4 = new JScrollPane();
        textArea6 = new JTextArea();
        button7 = new JButton();
        button8 = new JButton();

        //======== this ========
        setResizable(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

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
        setJMenuBar(menuBar1);

        //======== scrollPane7 ========
        {

            //---- textArea7 ----
            textArea7.setEditable(false);
            scrollPane7.setViewportView(textArea7);
        }
        contentPane.add(scrollPane7);
        scrollPane7.setBounds(30, 40, 615, 165);

        //======== menuBar3 ========
        {

            //======== menu5 ========
            {
                menu5.setText("\u5f55\u5c4f\u4e0e\u622a\u56fe");

                //---- menuItem8 ----
                menuItem8.setText("\u5f55\u5c4f");
                menu5.add(menuItem8);

                //---- menuItem9 ----
                menuItem9.setText("\u622a\u56fe");
                menu5.add(menuItem9);

                //---- radioButton2 ----
                radioButton2.setText("\u9690\u85cf\u7a97\u53e3");
                menu5.add(radioButton2);
            }
            menuBar3.add(menu5);

            //---- button13 ----
            button13.setText("\u6587\u4ef6\u4f20\u8f93");
            menuBar3.add(button13);
            menuBar3.add(comboBox3);
            menuBar3.add(comboBox4);

            //---- button14 ----
            button14.setText("\u52a0\u7c97");
            menuBar3.add(button14);

            //---- button15 ----
            button15.setText("\u659c\u4f53");
            menuBar3.add(button15);

            //---- button16 ----
            button16.setText("\u4e0b\u5212\u7ebf");
            menuBar3.add(button16);
        }
        contentPane.add(menuBar3);
        menuBar3.setBounds(30, 220, 620, 25);

        //======== scrollPane4 ========
        {
            scrollPane4.setViewportView(textArea6);
        }
        contentPane.add(scrollPane4);
        scrollPane4.setBounds(30, 260, 615, 155);

        //---- button7 ----
        button7.setText("\u8fd4\u56de");
        contentPane.add(button7);
        button7.setBounds(560, 425, 75, 30);

        //---- button8 ----
        button8.setText("\u53d1\u9001");
        contentPane.add(button8);
        button8.setBounds(475, 425, 75, 30);

        contentPane.setPreferredSize(new Dimension(675, 525));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItem1;
    private JMenuItem menuItem7;
    private JMenu menu4;
    private JMenuItem menuItem5;
    private JMenuItem menuItem6;
    private JMenuItem menuItem2;
    private JScrollPane scrollPane7;
    private JTextArea textArea7;
    private JMenuBar menuBar3;
    private JMenu menu5;
    private JMenuItem menuItem8;
    private JMenuItem menuItem9;
    private JRadioButton radioButton2;
    private JButton button13;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JButton button14;
    private JButton button15;
    private JButton button16;
    private JScrollPane scrollPane4;
    private JTextArea textArea6;
    private JButton button7;
    private JButton button8;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
