import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import com.jgoodies.forms.factories.*;
/*
 * Created by JFormDesigner on Tue Nov 19 19:42:53 CST 2024
 */



/**
 * @author whmfy
 */
public class Login extends JFrame {
    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Login li = new Login();
            li.setVisible(true);
        });

    }*/
    public Login() {
        initComponents();
    }

    private void button3AncestorAdded(AncestorEvent e) {
        // TODO add your code here
    }

    private void button4AncestorAdded(AncestorEvent e) {
        // TODO add your code here


    }

    private void button4MouseClicked(MouseEvent e) {
        // TODO add your code here
        dispose();
    }

    private void button3MouseClicked(MouseEvent e) {
        // TODO add your code here
        SwingUtilities.invokeLater(() -> {
            UserWindow uw = new UserWindow();
            uw.setVisible(true);
        });
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
        textArea2 = new JTextArea();
        label4 = compFactory.createLabel("\u7528\u6237\u540d");
        label5 = compFactory.createLabel("\u5bc6\u7801");
        scrollPane2 = new JScrollPane();
        clientLoginName = new JTextArea();
        clientLoginPassword = new JPasswordField();
        button3 = new JButton();
        button4 = new JButton();

        //======== this ========
        setResizable(false);
        setAutoRequestFocus(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- textArea2 ----
        textArea2.setText("\u804a\u5929\u8f6f\u4ef6\u767b\u5165\u754c\u9762");
        textArea2.setTabSize(12);
        textArea2.setFont(textArea2.getFont().deriveFont(textArea2.getFont().getSize() + 10f));
        textArea2.setEditable(false);
        textArea2.setBackground(Color.white);
        contentPane.add(textArea2);
        textArea2.setBounds(95, 25, 203, 33);
        contentPane.add(label4);
        label4.setBounds(new Rectangle(new Point(45, 105), label4.getPreferredSize()));
        contentPane.add(label5);
        label5.setBounds(45, 145, 36, 17);

        //======== scrollPane2 ========
        {
            scrollPane2.setViewportView(clientLoginName);
        }
        contentPane.add(scrollPane2);
        scrollPane2.setBounds(110, 100, 215, 24);
        contentPane.add(clientLoginPassword);
        clientLoginPassword.setBounds(110, 145, 215, 25);

        //---- button3 ----
        button3.setText("LogIn");
        button3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button3MouseClicked(e);
            }
        });
        contentPane.add(button3);
        button3.setBounds(new Rectangle(new Point(85, 200), button3.getPreferredSize()));

        //---- button4 ----
        button4.setText("Back");
        button4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button4MouseClicked(e);
            }
        });
        contentPane.add(button4);
        button4.setBounds(new Rectangle(new Point(230, 200), button4.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(400, 300));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JTextArea textArea2;
    private JLabel label4;
    private JLabel label5;
    private JScrollPane scrollPane2;
    private JTextArea clientLoginName;
    private JPasswordField clientLoginPassword;
    private JButton button3;
    private JButton button4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
