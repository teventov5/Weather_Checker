import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class forgotPasswordView extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField usernameText;
    private boolean isRealUser=false;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            forgotPasswordView dialog = new forgotPasswordView();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public forgotPasswordView() {
        setVisible(true);
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        {
            JLabel lblUsername = new JLabel("enter username here:");
            lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
            lblUsername.setBounds(10, 39, 170, 126);
            contentPanel.add(lblUsername);
        }
        {
            usernameText = new JTextField();
            usernameText.setBounds(180, 75, 170, 59);
            contentPanel.add(usernameText);
            usernameText.setColumns(10);
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton nextButton = new JButton("Next");
                nextButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String usernameInput=usernameText.getText();
                        try{
                            User tempUser=new User(usernameInput,"1234");
                            isRealUser=new userManagement().resetUserPassword(tempUser);
                            if(isRealUser) {
                                dispose();
                                new forgotPasswordView2(tempUser);
                            }
                            else JOptionPane.showMessageDialog(new JFrame(), "No such username", "Dialog", JOptionPane.ERROR_MESSAGE);
                        }
                        catch(Exception e1){
                            System.out.println(e1);
                        }

                    }
                });
                nextButton.setActionCommand("Next");
                buttonPane.add(nextButton);
                getRootPane().setDefaultButton(nextButton);
            }
            {
                JButton cancelButton = new JButton("Cancel");
                cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        //cancel//////
                    }
                });
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
    }

}