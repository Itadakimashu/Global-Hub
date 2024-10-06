package chathub;

public class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String get_username(){
        return this.username;
    }

    public static User create_user(String username, String password){
        //validate username and add a profile in future
        return new User(username, password);
    }
}
