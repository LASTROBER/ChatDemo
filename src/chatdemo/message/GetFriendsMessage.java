package chatdemo.message;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GetFriendsMessage extends Message implements Serializable {
    private String date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
    List<String> Friends = new ArrayList<>();
    private int type;
    private String from;

    public GetFriendsMessage(){

    }
    public GetFriendsMessage(String date, List<String> friends,  String from,int type) {
        this.date = date;
        Friends = friends;
        this.type = type;
        this.from = from;
    }

    @Override
    public String getDate() {
        return date;
    }

    @Override
    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getFriends() {
        return Friends;
    }

    public void setFriends(List<String> friends) {
        Friends = friends;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public void setType(int type) {
        this.type = type;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
