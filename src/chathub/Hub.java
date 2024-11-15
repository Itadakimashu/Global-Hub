package chathub;

import java.util.ArrayList;
import java.io.*;

public class Hub implements Serializable{
    public static String databaseFile = "data.bin";
    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<Message> messages = new ArrayList<Message>();

    //getters
    public ArrayList<Message> get_messages(){
        return this.messages;
    }

    public ArrayList<User> get_users(){
        return this.users;
    }


    //methods
    private void add_user(User User){
        this.users.add(User);
    }
    private void add_message(Message msg){
        this.messages.add(msg);
    }
    
    public void create_user(String username, String password){
        User u = User.create_user(username,password);
        this.add_user(u);
        this.save();
    }

    public void create_message(User user, String message){
        //check if the user is in the users arraylist in future
        Message msg = new Message(user,message);
        this.add_message(msg);
        this.save();
    }

    public User authenticate(String username, String password){
        for (User u : this.users){
            if (u.get_username().equals(username) && u.check_password(password)){
                return u;
            }
        }
        return null;
    }

    public void save(){
        try {
            FileOutputStream fos = new FileOutputStream(databaseFile,false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
            fos.close();
            System.out.println("Chats saved");
        }

        catch (IOException e) {
            System.out.println("Error saving chats");
            e.printStackTrace();

        }
    }

    public void load() {
        try {
            FileInputStream fis = new FileInputStream(databaseFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Hub hub = (Hub) ois.readObject();
            ois.close();
            fis.close();
            if (hub != null) {
                this.users = hub.get_users();
                this.messages = hub.get_messages();
            }
            // System.out.println("Chats loaded");
        } 
        catch (IOException | ClassNotFoundException e) {

            //create the file if the file does not exist
            if (e instanceof FileNotFoundException) {
                try {
                    File file = new File(databaseFile);
                    file.createNewFile();
                    System.out.println("Database file created");
                } catch (IOException ioException) {
                    System.out.println("Error creating database file");
                    ioException.printStackTrace();
                }   
            }
            else if (e instanceof EOFException) {
                System.out.println("Database file is empty, no data to load");
            } 
            else {
                System.out.println("Error loading chats");
                e.printStackTrace();
            }
        }
    }

    //for testing - delete later
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
