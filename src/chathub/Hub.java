package chathub;
import java.util.ArrayList;
public class Hub {
    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<Message> messages = new ArrayList<Message>();

    private void add_user(User User){
        this.users.add(User);
    }
    private void add_message(Message msg){
        this.messages.add(msg);
    }

    public void create_user(String username, String password){
        User u = User.create_user(username,password);
        this.add_user(u);
    }

    public void create_message(User user, String message){
        //check if the user is in the users arraylist in future
        Message msg = new Message(user,message);
        this.add_message(msg);
    }

    public void print_user(){
        for (User u : this.users){
            System.out.println(u.get_username());
        }
    }

    public void print_messages(){
        for (Message m : this.messages){
            System.out.println(m.user.get_username() + " : " + m.message);
        }
    }

}
