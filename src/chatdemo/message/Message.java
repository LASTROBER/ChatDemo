package chatdemo.message;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {
    int type;
    private String date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());

    public Message(){

    }
    public Message(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
