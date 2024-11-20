import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.jgoodies.forms.factories.*;
/*
 * Created by JFormDesigner on Tue Nov 19 19:50:58 CST 2024
 */



/**
 * @author whmfy
 */
public class Logon extends JFrame {
   /* public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Logon lo = new Logon();
            lo.setVisible(true);
        });

    }*/
    public Logon() {
        initComponents();
    }

    private void button6MouseClicked(MouseEvent e) {
        // TODO add your code here
        dispose();
    }

    private void button5MouseClicked(MouseEvent e) {
        // TODO add your code here
        SwingUtilities.invokeLater(() -> {
            UserWindow uw = new UserWindow();
            uw.setVisible(true);
        });

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
        textArea4 = new JTextArea();
        label1 = compFactory.createLabel("\u7528\u6237\u540d");
        scrollPane3 = new JScrollPane();
        clientLogonName = new JTextArea();
        label2 = compFactory.createLabel("\u5bc6\u7801");
        clientLogonPassword = new JPasswordField();
        label3 = compFactory.createLabel("\u786e\u8ba4\u5bc6\u7801");
        clientLogonPasswordCheck = new JPasswordField();
        button5 = new JButton();
        button6 = new JButton();

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

        //======== scrollPane3 ========
        {
            scrollPane3.setViewportView(clientLogonName);
        }
        contentPane.add(scrollPane3);
        scrollPane3.setBounds(110, 70, 210, 19);
        contentPane.add(label2);
        label2.setBounds(45, 110, 50, 20);
        contentPane.add(clientLogonPassword);
        clientLogonPassword.setBounds(110, 115, 210, 20);
        contentPane.add(label3);
        label3.setBounds(35, 155, 65, 20);
        contentPane.add(clientLogonPasswordCheck);
        clientLogonPasswordCheck.setBounds(110, 160, 210, 20);

        //---- button5 ----
        button5.setText("LogOn");
        button5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button5MouseClicked(e);
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

        contentPane.setPreferredSize(new Dimension(400, 300));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JTextArea textArea4;
    private JLabel label1;
    private JScrollPane scrollPane3;
    private JTextArea clientLogonName;
    private JLabel label2;
    private JPasswordField clientLogonPassword;
    private JLabel label3;
    private JPasswordField clientLogonPasswordCheck;
    private JButton button5;
    private JButton button6;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
