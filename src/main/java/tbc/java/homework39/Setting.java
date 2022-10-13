package tbc.java.homework39;

public class Setting {
    String userName;
    String password;
    String personsAdd;
    String [] ip;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPersonsAdd() {
        return personsAdd;
    }

    public void setPersonsAdd(String personsAdd) {
        this.personsAdd = personsAdd;
    }

    public String [] getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip.split(";");
    }
}
