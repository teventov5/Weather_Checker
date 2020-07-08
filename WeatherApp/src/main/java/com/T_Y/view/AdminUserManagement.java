package com.T_Y.view;

import com.T_Y.model.UsersDB;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AdminUserManagement extends JFrame {

    private JPanel contentPane;

    /**
     * Create the frame.
     */
    public AdminUserManagement() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 157, 249);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnViewUsers = new JButton("View users");
        btnViewUsers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    JOptionPane.showMessageDialog(new JFrame(), "Users Table available in CLI after pressing OK", "Dialog", JOptionPane.PLAIN_MESSAGE);
                    new UsersDB().showUsersTable();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        btnViewUsers.setBounds(26, 28, 112, 62);
        contentPane.add(btnViewUsers);

        JButton btnDeleteUser = new JButton("Delete user");
        btnDeleteUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DeleteUserFromDbView deleteUserFromDbView = new DeleteUserFromDbView();
            }
        });
        btnDeleteUser.setBounds(26, 125, 112, 62);
        contentPane.add(btnDeleteUser);
        setLocationRelativeTo(null);
    }

}
