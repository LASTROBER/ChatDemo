package chatdemo.Tools;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

//实现两端P2P通信

/**
 * @author whmfy
 */
public class FileSender {
    private String thisIp;
    private int thisPort=6000;

    public FileSender() {

    }

    public FileSender(String thisIp, int thisPort) throws IOException {
        this.thisIp = thisIp;
        this.thisPort = thisPort;
    }


    //sendFile中，函数调用者作为客户端向服务器发送文件
    public void sendFile(String toIp, int toPort, String fileNameAndPath) throws IOException {
        Socket client = new Socket(toIp, toPort);
        //找到文件并创建java文件对象
        File file = new File(fileNameAndPath);
        if (file.exists()) {
            //将文件传输到文件流
            FileInputStream fileInputStream = new FileInputStream(file);
            DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());
            dataOutputStream.writeUTF(file.getName());
            dataOutputStream.flush();
            dataOutputStream.writeLong(file.length());
            dataOutputStream.flush();
            System.out.println("开始传输");
            byte[] bytes = new byte[1024];
            int length;
            while ((length = fileInputStream.read(bytes, 0, bytes.length)) != -1) {
                dataOutputStream.write(bytes, 0, length);
                dataOutputStream.flush();

            }
            System.out.println("传输成功");
            fileInputStream.close();
            dataOutputStream.close();
        }
    }

    //catchFile中，函数调用者作为服务器接收客户端发送来的文件
    public void catchFile(String filePath) throws IOException {
        ServerSocket server = new ServerSocket(thisPort);
        Socket socket = server.accept();
        System.out.println("P2P连接成功");
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        String fileName = dataInputStream.readUTF();
        long fileSize = dataInputStream.readLong();

        File directory = new File(filePath);
        //如果所给路径不存在就创建文件夹
        if (!directory.exists()) {
            directory.mkdir();
        }
        //
        File file = new File(directory.getAbsolutePath() + File.separatorChar + fileName);
        file.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = dataInputStream.read(bytes, 0, bytes.length)) != -1) {
            fileOutputStream.write(bytes, 0, length);
            fileOutputStream.flush();
        }
        fileOutputStream.close();
        socket.close();
    }

    public String getThisIp() {
        return thisIp;
    }

    public void setThisIp(String thisIp) {
        this.thisIp = thisIp;
    }

    public int getThisPort() {
        return thisPort;
    }

    public void setThisPort(int thisPort) {
        this.thisPort = thisPort;
    }

}

