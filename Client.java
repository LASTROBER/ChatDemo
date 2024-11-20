import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        name = scanner.nextLine();
        new Client();
    }

    private JTextArea jTextArea;
    private JScrollPane jScrollPane;
    private JPanel jPanel;
    private JTextField jTextField;
    //private JButton jButton;
    private JFrame frame;
    private BufferedWriter bw = null;
    private static String name;
    public Client() throws IOException {
        frame = new JFrame("Client");
        jTextArea = new JTextArea();
        jTextArea.setEditable(false);
        jScrollPane = new JScrollPane(jTextArea);
        jPanel = new JPanel();
        jTextField = new JTextField(10);
        jTextField.setSize(100, 30);
        JButton jButton = new JButton("Send");
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

        //发送数据

        //创建对象ServerScoket
        /* ServerSocket sc = new ServerSocket(10000);*/

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
        });
        Scanner scanner = new Scanner(System.in);

        //创建Scoket对象
        Socket socket = new Socket("127.0.0.1", 5000);

        InetAddress inetAddress = socket.getInetAddress();

        String netIp =  inetAddress.getHostAddress();
        String hostName = inetAddress.getHostName();
                //从连续通道中获取输出流
        OutputStream ops = socket.getOutputStream();

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        System.out.println("Enter Your Name:");
        String name = scanner.nextLine();

        Message message = new Message(name,null,1);

        String line = null;
        BufferedReader bfr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        bw = new BufferedWriter(new OutputStreamWriter(ops));

        /*BufferedInputStream bis = new BufferedInputStream(new FileInputStream("clientdir\\客户端文本文件.txt"));
        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
        byte[] bytes = new byte[1024];
        int len;
        while ((len = bis.read(bytes))!=-1){
            bos.write(bytes,0,len);
        }
        socket.shutdownOutput();
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line2 = br.readLine();
        System.out.println(line2);*/

        //循环读取数据，拼接到文本域中
        while ((line = bfr.readLine()) != null) {
            System.out.println(name+": ");
            jTextArea.append(hostName+": ");
            jTextArea.append(line + System.lineSeparator());
            System.out.println(name+": " + line);

        }
    }


}


