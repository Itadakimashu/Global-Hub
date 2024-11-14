import chathub.*;
import GUI.*;

public class App {
    public static String state = "login";
    public static void main(String[] args) {
        Hub GlobalHub = Hub.load_chats();

        Gui gui = new Gui();

        User currentUser = null;

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
