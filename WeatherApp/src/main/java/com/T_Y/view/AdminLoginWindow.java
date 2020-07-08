package com.T_Y.view;

import com.T_Y.controller.UserManagement;
import com.T_Y.model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminLoginWindow extends JFrame {

    private static LoginWindow frame;
    private JPanel contentPane;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private boolean loginSucceed;

    /**
     *     public static void main(String[] args) {
     *         EventQueue.invokeLater(new Runnable() {
     *             public void run() {
     *                 try {
     *                     frame = new com.shar.view.LoginWindow();
     *                     frame.setVisible(true);
     *                 } catch (Exception e) {
     *                     e.printStackTrace();
     *                 }
     *             }
     *         });
     *     }
     */


    /**
     * Create the frame.
     */
    public AdminLoginWindow() {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 306, 369);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton button = new JButton("Submit");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usernameInput, passwordInput;
                usernameInput = usernameField.getText();
                passwordInput = passwordField.getText();
                try {
                    User tempUser = new User(usernameInput, passwordInput);
                    loginSucceed = new UserManagement().loginAdmin(tempUser);
                    if (loginSucceed) {
                        new AdminCustomizedScreen(tempUser);
                        dispose();
                    }
                } catch (Exception e1) {
                    System.out.println(e1);
                }
            }
        });
        button.setBounds(0, 253, 300, 79);
        contentPane.add(button);

        JLabel label = new JLabel("Username:");
        label.setFont(new Font("Tahoma", Font.PLAIN, 15));
        label.setBounds(10, 35, 96, 14);
        contentPane.add(label);

        usernameField = new JTextField();
        usernameField.setColumns(10);
        usernameField.setBounds(116, 32, 148, 30);
        contentPane.add(usernameField);

        JLabel label_1 = new JLabel("Password:");
        label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        label_1.setBounds(10, 80, 89, 14);
        contentPane.add(label_1);

        passwordField = new JPasswordField();
        passwordField.setColumns(10);
        passwordField.setBounds(116, 79, 148, 30);
        contentPane.add(passwordField);

        JButton btnForgotPassword = new JButton("Press if you forgot your password");
        btnForgotPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new ForgotPasswordView();
                } catch (Exception e1) {
                    System.out.println(e1);
                }
            }
        });
        btnForgotPassword.setBounds(0, 205, 300, 23);
        contentPane.add(btnForgotPassword);

        JButton btnWelcome = new JButton("Press if you want to go back to welcome screen");
        btnWelcome.setBounds(0, 156, 300, 23);
        contentPane.add(btnWelcome);
        btnWelcome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new WelcomeWindow();
                    dispose();
                } catch (Exception e1) {
                    e1.printStackTrace();

                }
            }
        });
        setLocationRelativeTo(null);
    }
}
