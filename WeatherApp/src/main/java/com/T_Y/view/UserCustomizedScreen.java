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

public class UserCustomizedScreen extends JFrame {

    public static Calendar rightNow;
    private JPanel contentPane;
    private ForcastResult result;
    private JLabel lblHeadlineText;
    private JLabel lblCurrTemperatureText;
    private BufferedImage image;
    private ImageIcon icon;
    private UserFavoritesView userFavoritesView;
    private JLabel lblTime;
    private int index=0;

    /**
     * Create the frame.
     */
    public UserCustomizedScreen(User tempUser) throws IOException {
        setVisible(true);
        rightNow = Calendar.getInstance();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel lblIcon = new JLabel("");
        setBounds(100, 100, 533, 455);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JList favoritesList = new JList();
        favoritesList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                image = null;
                icon = null;
                lblIcon.setIcon(null);
                rightNow.setTime(Calendar.getInstance().getTime());
                lblTime.setText(rightNow.getTime().toString());

                index = favoritesList.getAnchorSelectionIndex();
                //String [] resultArr={"31/05/2020","rainy","29","13"};
                //forcastResult result=new forcastResult(resultArr);

                result = new CitySearch().searchForCityResult(favoritesList.getModel().getElementAt(index).toString());
                lblHeadlineText.setText(result.getHeadline()+" Weather");
                lblCurrTemperatureText.setText(result.getMinTemperature()+"°");
                try {
                    image = ImageIO.read(new File("images\\" + result.getIconNumber() + ".png"));
                    icon = new ImageIcon(image);
                    lblIcon.setIcon(icon);
                    lblIcon.setBounds(270, 137, 100, 86);
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
        favoritesList.setBounds(0, 129, 271, 229);
        contentPane.add(favoritesList);

        lblHeadlineText = new JLabel("");
        lblHeadlineText.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblHeadlineText.setBounds(270, 186, 175, 53);
        contentPane.add(lblHeadlineText);

        JLabel lblCurrTemperature = new JLabel("Current temperature:");
        lblCurrTemperature.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
        lblCurrTemperature.setBounds(270, 224, 197, 53);
        contentPane.add(lblCurrTemperature);

        lblCurrTemperatureText = new JLabel("");
        lblCurrTemperatureText.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        lblCurrTemperatureText.setBounds(451, 226, 58, 51);
        contentPane.add(lblCurrTemperatureText);

        JButton btnEditFavorites = new JButton("Edit Favorites");
        btnEditFavorites.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {

                    new UserFavoritesView(tempUser);
                } catch (Exception e1) {
                    userFavoritesView.setVisible(false);
                   e1.printStackTrace();;
                }

            }
        });
        btnEditFavorites.setBounds(0, 81, 152, 37);
        contentPane.add(btnEditFavorites);

        JButton btnLogout = new JButton("Logout");
        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new WelcomeWindow();

            }
        });
        btnLogout.setBounds(162, 81, 152, 37);
        contentPane.add(btnLogout);

        JLabel lblWelcome = new JLabel("Welcome " + tempUser.getUsername());
        lblWelcome.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        lblWelcome.setBounds(0, 0, 304, 37);
        contentPane.add(lblWelcome);

        JButton btn = new JButton("Refresh");
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    setVisible(false);
                    dispose();
                    UserCustomizedScreen userCustomizedScreen = new UserCustomizedScreen(tempUser);
                    userCustomizedScreen.setVisible(true);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });
        btn.setBounds(110, 366, 109, 53);
        contentPane.add(btn);

        JButton btnHangouts = new JButton("Outdoor");
        btnHangouts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(index==0)
                    JOptionPane.showMessageDialog(new JFrame(), "you must select a city before you can use hangouts", "Dialog", JOptionPane.ERROR_MESSAGE);
                else
                    new HangoutDialogView(result);

            }
        });
        btnHangouts.setBounds(0, 366, 100, 53);
        contentPane.add(btnHangouts);
        lblTime = new JLabel(rightNow.getTime().toString());
        lblTime.setBounds(10, 37, 168, 37);
        getContentPane().add(lblTime);
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
