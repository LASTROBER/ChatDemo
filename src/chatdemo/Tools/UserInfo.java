package chatdemo.Tools;

import java.io.Serializable;
import java.util.List;

/**
 * @author whmfy
 */
public class UserInfo implements Serializable {
    String userId;
    String userNickName;
    String gender;
    String dept;
    String email;
    String online;
    String ipaddress;
    List<String> friends;

    public UserInfo(){

    }
    public UserInfo(String userId, String userNickName, String gender, String dept, String email, String online, String ipaddress) {
        this.userId = userId;
        this.userNickName = userNickName;
        this.gender = gender;
        this.dept = dept;
        this.email = email;
        this.online = online;
        this.ipaddress = ipaddress;
    }

    public UserInfo(String userId, String userNickName,String gender, String dept, String email,String ipaddress) {
        this.userId = userId;
        this.userNickName = userNickName;
        this.gender = gender;
        this.dept = dept;
        this.email = email;
        this.ipaddress = ipaddress;
    }
    public UserInfo(String userId, String userNickName, String gender,String dept, String email,String ipaddress,List<String>friends) {
        this.userId = userId;
        this.userNickName = userNickName;
        this.gender = gender;
        this.dept = dept;
        this.email = email;
        this.ipaddress = ipaddress;
        this.friends = friends;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }
}
