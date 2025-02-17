package chatdemo.message;

import chatdemo.Tools.UserInfo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ChatMessage extends Message implements Serializable {
    private String from;
    private String to;
    private int type;
    private String info;
    private List<String> friends;

    private UserInfo userInfo;
    String fontType;

    String sender;
    private String date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());

    public ChatMessage() {

    }

    public ChatMessage(String from, String to, String info, int type) {
        this.from = from;
        this.to = to;
        this.info = info;
        this.type = type;
    }
    public ChatMessage(String from, String to, String info, int type,String sender) {
        this.from = from;
        this.to = to;
        this.info = info;
        this.type = type;
        this.sender = sender;
    }


    public ChatMessage(String from, String to, String info, int type, List<String> friends) {
        this.from = from;
        this.to = to;
        this.info = info;
        this.type = type;
        this.friends = friends;
    }

    public ChatMessage(String from, String to, UserInfo userInfo,int type) {
        this.from = from;
        this.to = to;
        this.userInfo = userInfo;
        this.type = type;
    }

    public ChatMessage(String from, String to,List<String> friends, UserInfo userInfo,int type) {
        this.from = from;
        this.to = to;
        this.userInfo = userInfo;
        this.type = type;
        this.friends = friends;
    }

    public ChatMessage(String from, String to, String info, String fontType, int type) {
        this.from = from;
        this.to = to;
        this.info = info;
        this.type = type;
        this.fontType = fontType;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    public String getFrontType() {
        return fontType;
    }

    public void setFrontType(String frontType) {
        this.fontType = frontType;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}

