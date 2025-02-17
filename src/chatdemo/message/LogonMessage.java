package chatdemo.message;

import java.io.Serializable;

public class LogonMessage extends Message implements Serializable {
    int type;
    private String userName;
    private String userPassworld;

    public LogonMessage() {

    }

    public LogonMessage(int type, String userName, String userPassworld) {
        this.type = type;
        this.userName = userName;
        this.userPassworld = userPassworld;


    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public void setType(int type) {
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassworld() {
        return userPassworld;
    }

    public void setUserPassworld(String userPassworld) {
        this.userPassworld = userPassworld;
    }
}
