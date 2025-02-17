package chatdemo.message;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddFriendsMessage extends Message implements Serializable {

    private int type;
    private String from;
    private String addFriend;
    private String date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());


    public AddFriendsMessage(){

    }
    public AddFriendsMessage(int type, String from, String addFriend) {
        this.type = type;
        this.from = from;
        this.addFriend = addFriend;
    }

    @Override
    public String getDate() {
        return date;
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

    public String getAddFriend() {
        return addFriend;
    }

    public void setAddFriend(String addFriend) {
        this.addFriend = addFriend;
    }
}
