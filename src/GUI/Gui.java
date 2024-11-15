package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import chathub.*;

public class Gui{
    public User user;
    public User login_user(Hub hub) {
        user = null;
        JDialog loginDialog = new JDialog((Frame) null, "Global Hub | Login", true);
        loginDialog.setSize(300, 200);
        loginDialog.setLocationRelativeTo(null);
        loginDialog.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel usernameLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        loginDialog.add(usernameLabel, gbc);

        JTextField usernameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        loginDialog.add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        loginDialog.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        loginDialog.add(passwordField, gbc);

        JButton loginButton = new JButton("Login");
        gbc.gridx = 1;
        gbc.gridy = 2;
        loginDialog.add(loginButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle login action
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();
                user = hub.authenticate(username, new String(password));
                if (user != null) {
                    System.out.println("Login successful");
                    loginDialog.setVisible(false);
                } else {
                    System.out.println("Login failed");
                    JOptionPane.showMessageDialog(loginDialog, "Invalid username or password. Please try again.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
                // Perform login logic here
            }
        });

        close_window_listener(loginDialog);

        loginDialog.setVisible(true);
        return user;
    }


    public User hub_view(Hub hub, User currentUser) {
        user = currentUser;
        JDialog hubDialog = new JDialog((Frame) null, "Global Hub | Welcome " + user.get_username(), true);
        hubDialog.setSize(400, 300);
        hubDialog.setLocationRelativeTo(null);

        // Initialize components
        JTextArea messageArea = new JTextArea();
        messageArea.setEditable(false);
        JTextField inputField = new JTextField(20);
        JButton sendButton = new JButton("Send");

        JButton logoutButton = new JButton("Logout");


    
        for (Message m : hub.get_messages()) {
            String username = m.user.get_username().equals(currentUser.get_username()) ? "You: " : m.user.get_username();
            messageArea.append(username + " : " + m.message + "\n");
            messageArea.append("\n");            
        }

        messageArea.setCaretPosition(messageArea.getDocument().getLength()); // Scroll to the bottom
        messageArea.setLineWrap(true); //wraps message to next line if they exceed width of text area

        //a thread to continuously update the message area
        new Thread(() -> {
            int lastMessageCount = hub.get_messages().size();
            while (true) {
                hub.load();
                int currentMessageCount = hub.get_messages().size();
                
                if (currentMessageCount > lastMessageCount) {
                    for (int i = lastMessageCount; i < currentMessageCount; i++) {
                        Message m = hub.get_messages().get(i);
                        messageArea.append(m.user.get_username() + " : " + m.message + "\n");
                        messageArea.append("\n");
                    }
                    lastMessageCount = currentMessageCount;
                    messageArea.setCaretPosition(messageArea.getDocument().getLength()); // Scroll to the bottom
                }

                try {
                    Thread.sleep(1000); // Update every second
                } 
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        
        
        
        // Add action listener to the send button
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = inputField.getText();
                if (!message.isEmpty()) {
                    hub.create_message(user, message);
                    inputField.setText("");
                }
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform logout actions here
                System.out.println("User logged out");
                user = null;
                hubDialog.dispose(); // Close the dialog
            }
        });

        
        // Layout components
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new JScrollPane(messageArea), BorderLayout.CENTER);
        
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.add(inputField);
        inputPanel.add(sendButton);
        
        panel.add(inputPanel, BorderLayout.SOUTH);

        inputPanel.add(logoutButton); // Add the logout button to the input panel

        close_window_listener(hubDialog);

        hubDialog.add(panel);
        hubDialog.setVisible(true);
        return user;
        
    }
    
    private void close_window_listener(JDialog jdiag){
        jdiag.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("closed the window");
                System.exit(0);
            }
        });
    }
}