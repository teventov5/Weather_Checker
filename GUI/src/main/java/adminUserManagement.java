import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class adminUserManagement extends JFrame {

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    adminUserManagement frame = new adminUserManagement();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public adminUserManagement() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 187, 426);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnUpdateUser = new JButton("Update user");
        btnUpdateUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //update user////
            }
        });
        btnUpdateUser.setBounds(10, 155, 112, 62);
        contentPane.add(btnUpdateUser);

        JButton btnViewUsers = new JButton("View users");
        btnViewUsers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    JOptionPane.showMessageDialog(new JFrame(), "Users Table available in CLI after pressing OK", "Dialog", JOptionPane.PLAIN_MESSAGE);
                    new usersDB().showUsersTable();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        btnViewUsers.setBounds(10, 45, 112, 62);
        contentPane.add(btnViewUsers);

        JButton btnDeleteUser = new JButton("Delete user");
        btnDeleteUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               deleteUserFromDbView deleteUserFromDbView= new deleteUserFromDbView();

            }
        });
        btnDeleteUser.setBounds(10, 276, 112, 62);
        contentPane.add(btnDeleteUser);
    }
}
