package com.shar.view;

import com.shar.controller.UserManagement;
import com.shar.model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationWindow extends JFrame {

    private static RegistrationWindow frame;
    private JPanel contentPane;
    private JTextField usernameField;
    private JTextField passwordField;
    private boolean loginSucceed;
    private JTextField secretQuestionField;
    private JTextField secretAnswerField;


    /**
     * Create the frame.
     */
    public RegistrationWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 390, 443);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnSubmit = new JButton("Submit");
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usernameInput, passwordInput, secretQuestionInput, secretAnswerInput;
                usernameInput = usernameField.getText();
                passwordInput = passwordField.getText();
                secretQuestionInput = secretQuestionField.getText();
                secretAnswerInput = secretAnswerField.getText();
                try {
                    User tempUser = new User(usernameInput, passwordInput, secretQuestionInput, secretAnswerInput);
                    new UserManagement().registerUser(tempUser);


                } catch (Exception e1) {
                    System.out.println(e1);
                }
            }
        });
        btnSubmit.setBounds(0, 304, 376, 107);
        contentPane.add(btnSubmit);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblUsername.setBounds(10, 14, 96, 14);
        contentPane.add(lblUsername);

        usernameField = new JTextField();
        usernameField.setColumns(10);
        usernameField.setBounds(160, 14, 148, 20);
        contentPane.add(usernameField);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblPassword.setBounds(10, 54, 89, 14);
        contentPane.add(lblPassword);

        passwordField = new JTextField();
        passwordField.setColumns(10);
        passwordField.setBounds(160, 56, 148, 20);
        contentPane.add(passwordField);

        JButton btnForgotPassword = new JButton("Press if you forgot your password");
        btnForgotPassword.setBounds(59, 207, 254, 23);
        contentPane.add(btnForgotPassword);

        secretQuestionField = new JTextField();
        secretQuestionField.setColumns(10);
        secretQuestionField.setBounds(160, 103, 148, 20);
        contentPane.add(secretQuestionField);

        JLabel lblSecretQuestion = new JLabel("Secret Question:");
        lblSecretQuestion.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblSecretQuestion.setBounds(10, 101, 118, 14);
        contentPane.add(lblSecretQuestion);

        secretAnswerField = new JTextField();
        secretAnswerField.setColumns(10);
        secretAnswerField.setBounds(160, 141, 148, 20);
        contentPane.add(secretAnswerField);

        JLabel lblSecretAnswer = new JLabel("Secret Answer:");
        lblSecretAnswer.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblSecretAnswer.setBounds(10, 142, 118, 14);
        contentPane.add(lblSecretAnswer);
    }
}
