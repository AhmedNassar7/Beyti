package com.beyti.app;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginFrame extends JFrame {
    private JButton loginButton;
    private JTextField usernameTextField;
    private JPasswordField PasswordTextField;
    private JPanel mainPanel;

    public LoginFrame(Connection conn, String title) {
        super(title);
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(1000, 720));
        pack();
        setVisible(true);
        loginButton.addActionListener(actionEvent -> {
            String username = usernameTextField.getText();
            String password = new String(PasswordTextField.getPassword());
            try {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Employee WHERE username = '" + username + "'");
                if (rs.next()) {
                    if (rs.getString("password").equals(password)) {
                        JFrame frame = new MainFrame("Main");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Wrong username or password!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Wrong username or password!");
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
