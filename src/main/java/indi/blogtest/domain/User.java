package indi.blogtest.domain;

public class User {
    private String username;
    private String password;
    private String qq;
    private String email;

    public String getUsername() {
        return username;
    }

    public User() {
    }

    public User(String username, String password, String qq, String email) {
        this.username = username;
        this.password = password;
        this.qq = qq;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", qq='" + qq + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
