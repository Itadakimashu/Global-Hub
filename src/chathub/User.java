package chathub;
import java.io.Serializable;

public class User implements Serializable{
    private String username;
    private String password;
    private String email;
    private String name;
    private int age;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password,  String name, String email, int age) {
        validate_username(username);
        validate_email(email);
        validate_name(name);
        validate_age(age);
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.age = age;
    }

    //getters
    public String get_username(){
        return this.username;
    }

    public String get_email(){
        return this.email;
    }

    public String get_name(){
        return this.name;
    }

    public int get_age(){
        return this.age;
    }

    //methods
    public boolean match_password(String password){
        return this.password.equals(password);
    }

    private void validate_username(String username){
        if (username.length() < 5){
            throw new IllegalArgumentException("Username must be at least 5 characters long");
        }
        if(username.contains(" ")){
            throw new IllegalArgumentException("Username cannot contain spaces");
        }

        if (Character.isDigit(username.charAt(0))){
            throw new IllegalArgumentException("Username cannot start with a number");
        }
    }

    private void validate_email(String email){
        if (!email.contains("@")){
            throw new IllegalArgumentException("Invalid email address");
        }
        if(email.contains(" ")){
            throw new IllegalArgumentException("Email address cannot contain spaces");
        }
    }

    private void validate_name(String name){
        for (char c : name.toCharArray()) {
            if (!Character.isLetter(c) && c != ' '){
                throw new IllegalArgumentException("Name can only contain alphabets");
            }
        }
    }

    private void validate_age(int age){
        if (age <= 0){
            throw new IllegalArgumentException("Age canot be negetive or zero");
        }
        if(age < 18){
            throw new IllegalArgumentException("You must be 18 years or older to use this app");
        }
    }

}