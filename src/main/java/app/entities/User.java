package app.entities;

public class User {
    private int user_id;
    private String user_name;
    private String user_password;
    private int user_wallet;
    private String user_role;

    public User(int user_id, String user_name, String user_password, int user_wallet, String user_role) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_password = user_password;
        this.user_wallet = user_wallet;
        this.user_role = user_role;
    }

    public User(String user_name, String user_password, String user_role) {
        this.user_name = user_name;
        this.user_password = user_password;
        this.user_role = user_role;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_role() {
        return user_role;
    }

    public String getUser_password() {
        return user_password;
    }

    public int getUser_wallet() {
        return user_wallet;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public void setUser_wallet(int user_wallet) {
        this.user_wallet = user_wallet;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", user_password='" + user_password + '\'' +
                ", user_wallet=" + user_wallet +
                ", user_role='" + user_role + '\'' +
                '}';
    }
}