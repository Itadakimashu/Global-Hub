import chathub.*;
public class App {
    public static void main(String[] args) throws Exception {
        Hub GlobalHub = new Hub();
        GlobalHub.create_user("Fardin","1231");
        GlobalHub.create_user("Nayer","1231");
        User u1 = new User("fardin","1234");
        User u2 = new User("nayer","1234");
        User u3 = new User("raiyan","1234");
        GlobalHub.create_message(u2,"hello Fardin");
        GlobalHub.create_message(u1,"hey Nayer!");

        GlobalHub.print_messages();

    }
}
