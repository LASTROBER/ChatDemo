/*
 * Created by JFormDesigner on Fri Dec 20 00:02:11 CST 2024
 */

package chatdemo.window.LogOpen;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

/**
 * @author whmfy
 */
public class Open extends JFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Open op =new Open();
            op.setVisible(true);
        });
    }
    public Open() {
        initComponents();
    }

    private void button1MouseClicked(MouseEvent e) {
        // TODO add your code here
        SwingUtilities.invokeLater(() -> {
            Logon lo =new Logon();
            lo.setVisible(true);
            this.setVisible(false);
        });
    }

    private void button2MouseClicked(MouseEvent e) {
        // TODO add your code here
        SwingUtilities.invokeLater(() -> {
            Login li = null;
            try {
                li = new Login();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            li.setVisible(true);
            this.setVisible(false);
        });
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        this2 = new JPanel();
        button1 = new JButton();
        button2 = new JButton();
        textArea8 = new JTextArea();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== this2 ========
        {
            this2.setForeground(Color.pink);
            this2.setLayout(null);

            //---- button1 ----
            button1.setText("\u6ce8\u518c");
            button1.setFont(button1.getFont().deriveFont(button1.getFont().getSize() + 6f));
            button1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button1MouseClicked(e);
                }
            });
            this2.add(button1);
            button1.setBounds(new Rectangle(new Point(165, 130), button1.getPreferredSize()));

            //---- button2 ----
            button2.setText("\u767b\u5165");
            button2.setFont(button2.getFont().deriveFont(button2.getFont().getSize() + 6f));
            button2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button2MouseClicked(e);
                }
            });
            this2.add(button2);
            button2.setBounds(new Rectangle(new Point(165, 185), button2.getPreferredSize()));

            //---- textArea8 ----
            textArea8.setText("\u8ba1\u7b97\u673a\u7f51\u7edc\u804a\u5929\u8f6f\u4ef6");
            textArea8.setTabSize(12);
            textArea8.setFont(textArea8.getFont().deriveFont(textArea8.getFont().getSize() + 10f));
            textArea8.setEditable(false);
            textArea8.setWrapStyleWord(true);
            this2.add(textArea8);
            textArea8.setBounds(110, 75, 203, 33);
        }
        contentPane.add(this2);
        this2.setBounds(0, 0, 400, 270);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel this2;
    private JButton button1;
    private JButton button2;
    private JTextArea textArea8;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
