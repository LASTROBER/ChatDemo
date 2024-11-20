import jdk.nashorn.internal.runtime.regexp.joni.ast.StringNode;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        name = scanner.nextLine();
        new Server();
        new test2();
    }

    private JTextArea jTextArea;
    private JScrollPane jScrollPane;
    private JPanel jPanel;
    private JTextField jTextField;
    //private JButton jButton;
    private JFrame frame;
    private BufferedWriter bw = null;
    private static String name;

    public Server() throws IOException {
        frame = new JFrame("Server");
        jTextArea = new JTextArea();
        jTextArea.setEditable(false);
        jScrollPane = new JScrollPane(jTextArea);
        jPanel = new JPanel();
        jTextField = new JTextField(10);
        jTextField.setSize(100, 30);
        JButton jButton = new JButton("Send");
        jButton.setSize(40, 30);
        JButton jButton1 = new JButton("选择文件");

        jPanel.add(jTextField);
        jPanel.add(jButton);
        jPanel.add(jButton1);

        frame.add(jScrollPane, BorderLayout.CENTER);
        frame.add(jPanel, BorderLayout.SOUTH);
        frame.setSize(400, 500);
        frame.setLocation(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        //接收数据--    创建一个客户端套接字

        //按钮实现发送数据
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("buttton click");
                String text = jTextField.getText();
                //jTextArea.append(text);
                try {
                    bw.write(text);
                    bw.newLine();
                    bw.flush();
                    jTextField.setText("");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

        });
        ServerSocket sc = new ServerSocket(5000);
        while (true) {
            Socket socket = sc.accept();
            InetAddress inetAddress = socket.getInetAddress();
            String hostName = inetAddress.getHostName();


       /* //按钮实现发送文件
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                FileSystemView fsv = FileSystemView.getFileSystemView();

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(fsv.getHomeDirectory());
                fileChooser.setDialogTitle("请选择要上传的文件...");
                fileChooser.setApproveButtonText("确定");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

                int result = fileChooser.showOpenDialog(null);

                if (JFileChooser.APPROVE_OPTION == result) {
                    String path = fileChooser.getSelectedFile().getPath();
                    System.out.println("path: " + path);
                }
            }
        });*/

        //监听客户端连接

       /* BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("serverdir\\客户端文本文件.txt"));
        byte[] bytes = new byte[1024];
        int len;
        while ((len = bis.read(bytes)) != -1) {
            bos.write(bytes, 0, len);
        }

        BufferedWriter bw2 = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bw2.write("上传成功");
        bw2.newLine();
        bw2.flush();*/


        //从连续通道中获取输入流读取数据--一行一行读取——》bufferreader
        String line = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        System.out.println(name + ": ");
        while ((line = br.readLine()) != null) {
            //将读取的数据拼接到文本域上
            jTextArea.append(hostName + ": ");
            jTextArea.append(line + System.lineSeparator());
            System.out.println(name + ": " + line);
        }

    }
    }

}


class UserThread implements Runnable {
    private String Name;
    private Socket socket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    private boolean flag;

    public UserThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            while (flag) {
                Message message = (Message) objectInputStream.readObject();
                int type = message.getType();
                String to = message.getTo();
                String from = message.getFrom();
                objectOutputStream.writeObject(message);
            }


        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);


        }
    }
}

