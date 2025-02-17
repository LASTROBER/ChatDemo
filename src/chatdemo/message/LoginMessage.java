package chatdemo.message;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginMessage extends Message implements Serializable {

    private int type;
    private String clientPassworldCheck;
    private String clientNameCheck;


    private String date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());

    public LoginMessage() {

    }

    public LoginMessage(int type, String clientNameCheck,String clientPassworldCheck) {
        this.type = type;
        this.clientNameCheck = clientNameCheck;
        this.clientPassworldCheck = clientPassworldCheck;

    }


    @Override
    public int getType() {
        return type;
    }

    @Override
    public void setType(int type) {
        this.type = type;
    }


    @Override
    public String getDate() {
        return date;
    }

    @Override
    public void setDate(String date) {
        this.date = date;
    }

    public String getClientPassworldCheck() {
        return clientPassworldCheck;
    }

    public void setClientPassworldCheck(String clientPassworldCheck) {
        this.clientPassworldCheck = clientPassworldCheck;
    }

    public String getClientNameCheck() {
        return clientNameCheck;
    }

    public void setClientNameCheck(String clientNameCheck) {
        this.clientNameCheck = clientNameCheck;
    }
}

