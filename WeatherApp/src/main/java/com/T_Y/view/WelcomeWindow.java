package com.T_Y.view;

import com.T_Y.controller.CityCodeHushMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WelcomeWindow {

    private JFrame frame;
    private JLabel lblInfo;
    private WelcomeWindow window;

    /**
     * Create the application.
     */
    public WelcomeWindow() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                CityCodeHushMap.getInstance().stop();
            }
        });

        JButton btnLogin = new JButton("User Login");
        btnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lblInfo.setText(convertToMultiline("Use this button to go to User login page"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lblInfo.setText("");
            }

            public void mouseClicked(MouseEvent e1) {
                LoginWindow LoginWindow = new LoginWindow();
                LoginWindow.setVisible(true);
                frame.setVisible(false);
            }

        });
        btnLogin.setBounds(53, 177, 124, 23);
        frame.getContentPane().add(btnLogin);
        JButton btnAdminLogin = new JButton("Admin Login");
        btnAdminLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                AdminLoginWindow AdminLoginWindow = new AdminLoginWindow();
                frame.setVisible(false);
            }
        });
        btnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lblInfo.setText(convertToMultiline("Use this button to go to Admin login page"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lblInfo.setText("");
            }

            public void mouseClicked(MouseEvent e1) {
                LoginWindow LoginWindow = new LoginWindow();
                frame.setVisible(false);
            }

        });

        btnAdminLogin.setBounds(130, 229, 168, 23);
        frame.getContentPane().add(btnAdminLogin);
        JButton btnRegister = new JButton("Register");

        btnRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lblInfo.setText(convertToMultiline("Use this button to go to registration page"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lblInfo.setText("");
            }

            public void mouseClicked(MouseEvent e1) {
                RegistrationWindow registrationWindow = new RegistrationWindow();
                registrationWindow.setVisible(true);
                frame.setVisible(false);
            }

        });
        btnRegister.setBounds(243, 177, 138, 23);
        frame.getContentPane().add(btnRegister);

        lblInfo = new JLabel("");
        lblInfo.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblInfo.setBounds(53, 11, 343, 123);
        frame.getContentPane().add(lblInfo);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    /**
     * public static void main(String[] args) {
     * EventQueue.invokeLater(new Runnable() {
     * public void run() {
     * try {
     * window = new com.shar.view.WelcomeWindow();
     * window.frame.setVisible(true);
     * } catch (Exception e) {
     * e.printStackTrace();
     * }
     * }
     * });
     * }
     */
    public static String convertToMultiline(String orig) {
        return "<html>" + orig.replaceAll("\n", "<br>");
    }
}


