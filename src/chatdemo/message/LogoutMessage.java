package chatdemo.message;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogoutMessage extends Message implements Serializable {

    private int type;
    private String userId;

    private String date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());

    public LogoutMessage(){

    }

    public LogoutMessage(int type, String userId) {
        this.type = type;
        this.userId = userId;
    }


    @Override
    public int getType() {
        return type;
    }

    @Override
    public void setType(int type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}


