/*
 * Created by JFormDesigner on Sat Dec 21 10:44:44 CST 2024
 */

package chatdemo.window;

import java.awt.*;
import javax.swing.*;

import chatdemo.Tools.ShakeSender;

/**
 * @author whmfy
 */
public class ManageUser extends JPanel {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        SwingUtilities.invokeLater(() -> {
            ManageUser lo =new ManageUser();
            jFrame.add(lo);
            ShakeSender s = new ShakeSender(jFrame);
            lo.setVisible(true);
            s.start();
        });
    }
    public ManageUser() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        frame1 = new JFrame();
        panel1 = new JPanel();
        label2 = new JLabel();
        label3 = new JLabel();
        scrollPane3 = new JScrollPane();
        textArea3 = new JTextArea();
        label4 = new JLabel();
        scrollPane4 = new JScrollPane();
        textArea4 = new JTextArea();
        label5 = new JLabel();
        scrollPane5 = new JScrollPane();
        textArea5 = new JTextArea();
        button2 = new JButton();

        //======== frame1 ========
        {
            frame1.setVisible(true);
            Container frame1ContentPane = frame1.getContentPane();
            frame1ContentPane.setLayout(null);

            //======== panel1 ========
            {
                panel1.setLayout(null);

                //---- label2 ----
                label2.setText("\u5bc6\u7801\u4fee\u6539");
                panel1.add(label2);
                label2.setBounds(205, 55, 80, label2.getPreferredSize().height);

                //---- label3 ----
                label3.setText("\u539f\u5bc6\u7801");
                panel1.add(label3);
                label3.setBounds(45, 110, 55, label3.getPreferredSize().height);

                //======== scrollPane3 ========
                {
                    scrollPane3.setViewportView(textArea3);
                }
                panel1.add(scrollPane3);
                scrollPane3.setBounds(120, 105, 284, scrollPane3.getPreferredSize().height);

                //---- label4 ----
                label4.setText("\u65b0\u5bc6\u7801");
                panel1.add(label4);
                label4.setBounds(45, 165, 55, label4.getPreferredSize().height);

                //======== scrollPane4 ========
                {
                    scrollPane4.setViewportView(textArea4);
                }
                panel1.add(scrollPane4);
                scrollPane4.setBounds(120, 160, 284, scrollPane4.getPreferredSize().height);

                //---- label5 ----
                label5.setText("\u786e\u8ba4\u5bc6\u7801");
                panel1.add(label5);
                label5.setBounds(new Rectangle(new Point(40, 225), label5.getPreferredSize()));

                //======== scrollPane5 ========
                {
                    scrollPane5.setViewportView(textArea5);
                }
                panel1.add(scrollPane5);
                scrollPane5.setBounds(125, 220, 284, scrollPane5.getPreferredSize().height);

                //---- button2 ----
                button2.setText("\u786e\u8ba4\u4fee\u6539");
                panel1.add(button2);
                button2.setBounds(185, 260, 121, 27);

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
            frame1ContentPane.add(panel1);
            panel1.setBounds(0, -15, 535, 335);

            frame1ContentPane.setPreferredSize(new Dimension(540, 335));
            frame1.pack();
            frame1.setLocationRelativeTo(frame1.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JFrame frame1;
    private JPanel panel1;
    private JLabel label2;
    private JLabel label3;
    private JScrollPane scrollPane3;
    private JTextArea textArea3;
    private JLabel label4;
    private JScrollPane scrollPane4;
    private JTextArea textArea4;
    private JLabel label5;
    private JScrollPane scrollPane5;
    private JTextArea textArea5;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
