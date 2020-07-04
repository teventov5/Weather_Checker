package com.shar.view;

import com.shar.model.ForcastResult;
import com.shar.model.HangoutsResult;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class HangoutDialogView extends JDialog {

    JLabel lblRatingText;
    /**
     * public static void main(String[] args) {
     * EventQueue.invokeLater(new Runnable() {
     * public void run() {
     * try {
     * hangoutDialogView dialog = new hangoutDialogView();
     * dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
     * dialog.setVisible(true);
     * } catch (Exception e) {
     * e.printStackTrace();
     * }
     * }
     * });
     * }
     */
    private HangoutsResult[] allHangouts;
    private HangoutsResult specificHangout;
    private JLabel lblHeadlineText;


    /**
     * Create the dialog.
     */
    public HangoutDialogView(ForcastResult ctRes) {
        setVisible(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 546, 369);
        getContentPane().setLayout(null);
        allHangouts = ctRes.getHangoutsResultsArr();
        JList hangoutList = new JList();
        hangoutList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                int index = hangoutList.getAnchorSelectionIndex();
                //String [] resultArr={"31/05/2020","rainy","29","13"};
                //forcastResult result=new forcastResult(resultArr);

                // result =new citySearch().searchForCityResult( hangoutList.getModel().getElementAt(index).toString());
                specificHangout = allHangouts[index];
                lblHeadlineText.setText(specificHangout.getHeadline());
                lblRatingText.setText(specificHangout.getRating());

            }
        });
        hangoutList.setModel(new AbstractListModel() {
            String[] values;

            {
                values = new String[] { "Flight delays", "Mosquito status", "Dust & Dander status", "Dog Walking Comfort", "Running", "Golf", "Tennis", "Hiking", "Bicycling", "Skateboarding", "Outdoor Concert", "Beach" +
                                                                                                                                                                                                                   " & Pool" };

            }

            public int getSize() {
                return values.length;
            }

            public Object getElementAt(int index) {
                return values[index];
            }
        });
        hangoutList.setBounds(33, 50, 114, 219);
        getContentPane().add(hangoutList);

        Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
        rigidArea.setBounds(0, 0, 530, 339);
        getContentPane().add(rigidArea);

        JLabel lblHeadline = new JLabel("Headline");
        lblHeadline.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblHeadline.setBounds(355, 11, 71, 42);
        getContentPane().add(lblHeadline);

        lblHeadlineText = new JLabel("");
        lblHeadlineText.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblHeadlineText.setBounds(272, 63, 250, 100);
        getContentPane().add(lblHeadlineText);

        JLabel lblAccuweatherLink = new JLabel("view on accuWeather");
        lblAccuweatherLink.setBounds(10, 307, 465, 14);
        getContentPane().add(lblAccuweatherLink);

        lblRatingText = new JLabel("");
        lblRatingText.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblRatingText.setBounds(340, 252, 124, 69);
        getContentPane().add(lblRatingText);

        JLabel lblRatingDesc = new JLabel("value in a 1-10 rating bar.");
        lblRatingDesc.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblRatingDesc.setBounds(272, 174, 203, 42);
        getContentPane().add(lblRatingDesc);

    }
}
