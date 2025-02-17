/*
 * Created by JFormDesigner on Fri Dec 20 00:02:16 CST 2024
 */

package chatdemo.window;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author whmfy
 */
public class UserWindow extends JFrame {
    public UserWindow() {
        initComponents();
    }

    private void menuItem6MouseClicked(MouseEvent e) {
        // TODO add your code here
        SwingUtilities.invokeLater(() -> {
            ManageUser mu =new ManageUser();
            mu.setVisible(true);
        });



    }

    private void menuItem1MouseClicked(MouseEvent e) {
        // TODO add your code here
        SwingUtilities.invokeLater(() -> {
            ManageUser mu =new ManageUser();
            mu.setVisible(true);
        });

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel1 = new JPanel();
        label1 = new JLabel();
        textField1 = new JTextField();
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
        button8 = new JButton();
        button7 = new JButton();

        //======== this ========
        setResizable(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setLayout(null);

            //---- label1 ----
            label1.setText("\u53d1\u9001\u7ed9\u8c01");
            panel1.add(label1);
            label1.setBounds(30, 40, 75, 25);
            panel1.add(textField1);
            textField1.setBounds(115, 35, 80, 25);

            //======== scrollPane7 ========
            {

                //---- textArea7 ----
                textArea7.setEditable(false);
                scrollPane7.setViewportView(textArea7);
            }
            panel1.add(scrollPane7);
            scrollPane7.setBounds(20, 70, 615, 165);

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
                button16.setText("\u7a97\u4f53\u6296\u52a8");
                button16.setActionCommand("\u6296\u52a8");
                menuBar3.add(button16);
            }
            panel1.add(menuBar3);
            menuBar3.setBounds(0, 250, 673, menuBar3.getPreferredSize().height);

            //======== scrollPane4 ========
            {
                scrollPane4.setViewportView(textArea6);
            }
            panel1.add(scrollPane4);
            scrollPane4.setBounds(20, 305, 615, 155);

            //---- button8 ----
            button8.setText("\u53d1\u9001");
            panel1.add(button8);
            button8.setBounds(460, 465, 75, 30);

            //---- button7 ----
            button7.setText("\u8fd4\u56de");
            panel1.add(button7);
            button7.setBounds(550, 465, 75, 30);

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
        contentPane.add(panel1);
        panel1.setBounds(15, -15, 675, 525);

        contentPane.setPreferredSize(new Dimension(675, 525));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel panel1;
    private JLabel label1;
    private JTextField textField1;
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
    private JButton button8;
    private JButton button7;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
