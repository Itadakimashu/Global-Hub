import chathub.*;
import GUI.*;

public class App {
    public static String state = "welcome";
    public static void main(String[] args) {
        Hub GlobalHub = new Hub();
        Gui gui = new Gui();

        while(true){
            switch (state) {
                case "welcome":
                    state = gui.welcome_view(GlobalHub);
                    break;

                case "login":
                    state = gui.login_user(GlobalHub);
                    break;
                
                case "hub":
                    state = gui.hub_view(GlobalHub);
                    break;
                case "register":
                    state = gui.register_view(GlobalHub);
                    break;

                case "userlist":
                    gui.userlist_view(GlobalHub);
                    state = "welcome";
                    break;
            
                default:
                    break;
            }
        }


    }

}
