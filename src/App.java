import chathub.*;
import GUI.*;
public class App {
    public static String state = "hub";
    public static void main(String[] args) throws Exception {
        Hub GlobalHub = new Hub();
        GlobalHub.create_user("Fardin","1234");
        GlobalHub.create_user("Nayer","1234");
        User u1 = new User("Fardin","1234");
        User u2 = new User("Nayer","1234");
        User u3 = new User("raiyan","1234");
        GlobalHub.create_message(u2,"Bhi java bujhi na-_-");
        GlobalHub.create_message(u1,"Java Ki khawar jinish?");
        // for(int i = 0; i  < 10; i++){
        //     GlobalHub.create_message(u2,"hello Fardin");
        //     GlobalHub.create_message(u1,"hey Nayer!");
        // }

        // GlobalHub.print_messages();
        Gui gui = new Gui();

        User currentUser = u1;

        while(true){
            switch (state) {
                case "login":
                    currentUser = gui.login_user(GlobalHub);
                    if(currentUser != null){
                        state = "hub";
                    }
                    break;
                
                case "hub":
                    currentUser = gui.hub_view(GlobalHub,currentUser);
                    if(currentUser == null){
                        state = "login";
                    }
                    break;
            
                default:
                    break;
            }
        }


    }
}
