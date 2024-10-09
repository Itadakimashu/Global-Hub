package chathub;

public class Message {
    public User user;
    public String message;
    public Message(User user, String message) {
        this.user = user;
        this.message = message;
    }
}
