package GUI;
import javax.swing.*;

class RegisterView extends JFrame {
    public RegisterView() {
        setTitle("Register");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Add components for registration (e.g., username, password fields, register button)
        JPanel panel = new JPanel();
        panel.add(new JLabel("Username:"));
        panel.add(new JTextField(15));
        panel.add(new JLabel("Password:"));
        panel.add(new JPasswordField(15));
        panel.add(new JButton("Register"));

        add(panel);
    }
}
