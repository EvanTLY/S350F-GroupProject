package s350f_groupproject;

import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

public class LoginFrame extends JFrame{
    private JTextField idField;
    private JPasswordField passwordField;
    private final JButton loginButton;
    
    public LoginFrame() {
        //GUI design
        super("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));
        
        JLabel idLabel = new JLabel("User ID: ");
        idField = new JTextField();
        JLabel passwordLabel = new JLabel("Password: ");
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = idField.getText();
                String password = new String(passwordField.getPassword());
                authenticateUser(username, password);
            }
        });
        
        add(idLabel);
        add(idField);
        add(passwordLabel);
        add(passwordField);
        add(new JLabel());
        add(loginButton);
        
        setVisible(true);
    }
    
    
    private void authenticateUser(String username, String password) {
        User user = UserDatabase.authenticateUser(username, password);
        
        if (user == null) {
            //Invalid user ID or password
            JOptionPane.showMessageDialog(null, "Invalid user ID or password!", "Login Failed", JOptionPane.ERROR_MESSAGE);
        } else {
            //Login successful
            dispose(); //Close the login frame
            
            //Turn to different frame depends on user type
            if (user instanceof Student) {
                Student student = (Student) user;
                new StudentFrame();
            } else if (user instanceof Teacher) {
                Teacher teacher = (Teacher) user;
                new TeacherFrame();
            } else if (user instanceof SystemAdmin) {
                SystemAdmin admin = (SystemAdmin) user;
                new SystemAdminFrame();
            }
        }
    }
}
 