package com.T_Y.view;

import com.T_Y.controller.CitySearch;
import com.T_Y.model.ForcastResult;
import com.T_Y.model.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

public class AdminCustomizedScreen extends JFrame {

    public static Calendar rightNow;
    private JPanel contentPane;
    private ForcastResult result;
    private JLabel lblHeadlineText;
    private JLabel lblCurrTemperatureText;
    private BufferedImage image;
    private ImageIcon icon;
    private UserFavoritesView userFavoritesView;
    private JLabel lblTime;

    /**
     * Create the frame.
     */
    public AdminCustomizedScreen(User tempUser) throws IOException {
        rightNow = Calendar.getInstance();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel lblIcon = new JLabel("");
        setBounds(100, 100, 799, 596);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
        rigidArea.setBounds(0, 0, 785, 559);
        contentPane.add(rigidArea);

        JList favoritesList = new JList();
        favoritesList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                image = null;
                icon = null;
                lblIcon.setIcon(null);
                rightNow.setTime(Calendar.getInstance().getTime());
                lblTime.setText(rightNow.getTime().toString());

                int index = favoritesList.getAnchorSelectionIndex();
                //String [] resultArr={"31/05/2020","rainy","29","13"};
                //forcastResult result=new forcastResult(resultArr);

                result = new CitySearch().searchForCityResult(favoritesList.getModel().getElementAt(index).toString());
                lblHeadlineText.setText(result.getHeadline());
                lblCurrTemperatureText.setText(result.getMinTemperature());
                try {
                    image = ImageIO.read(new File("images\\" + result.getIconNumber() + ".png"));
                    icon = new ImageIcon(image);
                    lblIcon.setIcon(icon);
                    lblIcon.setBounds(545, 137, 100, 86);
                    contentPane.add(lblIcon);
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }
        });
        favoritesList.setModel(new AbstractListModel() {
            String[] values;

            {
                values = tempUser.getFavoritesArr();
            }

            public int getSize() {
                return values.length;
            }

            public Object getElementAt(int index) {
                return values[index];
            }
        });
        favoritesList.setFont(new Font("Tahoma", Font.PLAIN, 27));
        favoritesList.setBounds(10, 297, 271, 229);
        contentPane.add(favoritesList);

        JLabel lblHeadline = new JLabel("Headline:");
        lblHeadline.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblHeadline.setBounds(509, 11, 152, 66);
        contentPane.add(lblHeadline);

        lblHeadlineText = new JLabel("");
        lblHeadlineText.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblHeadlineText.setBounds(427, 68, 340, 152);
        contentPane.add(lblHeadlineText);

        JLabel lblCurrTemperature = new JLabel("Current temperature");
        lblCurrTemperature.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblCurrTemperature.setBounds(463, 297, 295, 95);
        contentPane.add(lblCurrTemperature);

        lblCurrTemperatureText = new JLabel("");
        lblCurrTemperatureText.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblCurrTemperatureText.setBounds(545, 427, 109, 66);
        contentPane.add(lblCurrTemperatureText);

        JButton btnEditFavorites = new JButton("Edit Favorites");
        btnEditFavorites.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {

                    new UserFavoritesView(tempUser);
                } catch (Exception e1) {
                    userFavoritesView.setVisible(false);
                    System.out.println(e1);
                }

            }
        });
        btnEditFavorites.setBounds(10, 175, 152, 75);
        contentPane.add(btnEditFavorites);

        JButton btnLogout = new JButton("Logout");
        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new WelcomeWindow();

            }
        });
        btnLogout.setBounds(217, 175, 152, 75);
        contentPane.add(btnLogout);

        JLabel lblWelcome = new JLabel("Welcome " + tempUser.getUsername());
        lblWelcome.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        lblWelcome.setBounds(10, 30, 271, 66);
        contentPane.add(lblWelcome);

        JButton btn = new JButton("Refresh");
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    setVisible(false);
                    dispose();
                    AdminCustomizedScreen userCustomizedScreen = new AdminCustomizedScreen(tempUser);
                    userCustomizedScreen.setVisible(true);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });
        btn.setBounds(291, 324, 109, 53);
        contentPane.add(btn);

        JButton btnHangouts = new JButton("Hangouts possibilities");
        btnHangouts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                HangoutDialogView hangoutDialogView = new HangoutDialogView(result);
            }
        });
        btnHangouts.setBounds(291, 388, 182, 53);
        contentPane.add(btnHangouts);
        lblTime = new JLabel(rightNow.getTime().toString());
        lblTime.setBounds(10, 40, 370, 140);
        getContentPane().add(lblTime);

        JButton btnUserManagment = new JButton("User Management");
        btnUserManagment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AdminUserManagement adminUserManagement = new AdminUserManagement();
                adminUserManagement.setVisible(true);

            }
        });
        btnUserManagment.setBounds(291, 451, 152, 75);
        contentPane.add(btnUserManagment);
        setLocationRelativeTo(null);
        contentPane.setVisible(true);


    }

    public ForcastResult getListForcastResult() {
        return result;
    }

    public void setListForcastResult(ForcastResult listForcastResult) {
        this.result = listForcastResult;
    }
}
