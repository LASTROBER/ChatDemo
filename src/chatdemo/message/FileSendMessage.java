package chatdemo.message;

import java.io.Serializable;

public class FileSendMessage extends Message implements Serializable {
    private String from;
    private String to;
    private int type;
    private String fileName;
    private String fileSize;
    String clientIp;
    int clientPort;

    public FileSendMessage(){

    }
    public FileSendMessage(String from, String to, int type, String fileName, String fileSize) {
        this.from = from;
        this.to = to;
        this.type = type;
        this.fileName = fileName;
        this.fileSize = fileSize;
    }
    public FileSendMessage(String from, String to, int type, String fileName,String clientIp,int clientPort) {
        this.from = from;
        this.to = to;
        this.type = type;
        this.fileName = fileName;
        this.clientIp = clientIp;
        this.clientPort = clientPort;
    }
    public FileSendMessage(String from, String to, int type) {
        this.from = from;
        this.to = to;
        this.type = type;

    }



    public String getFrom() {
        return from;
    }


    public void setFrom(String from) {
        this.from = from;
    }


    public String getTo() {
        return to;
    }


    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public void setType(int type) {
        this.type = type;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public int getClientPort() {
        return clientPort;
    }

    public void setClientPort(int clientPort) {
        this.clientPort = clientPort;
    }
}