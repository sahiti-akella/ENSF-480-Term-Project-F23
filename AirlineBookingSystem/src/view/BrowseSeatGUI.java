package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrowseSeatGUI {
    private JFrame frame;
    private JPanel seatPanel;
    private String selectedFlight;

    public BrowseSeatGUI(String selectedFlight) {
        this.selectedFlight = selectedFlight;
    }

    public void createUI() {
        frame = new JFrame();
        frame.setTitle("Browse Seats - " + selectedFlight);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        seatPanel = new JPanel();
        seatPanel.setLayout(new GridLayout(3, 4)); // Change the dimensions as needed

         // Create seats
         int seatNumber = 1;
         for (int row = 1; row <= 3; row++) {
             for (int col = 1; col <= 4; col++) {
             JButton seatButton = new JButton("Seat " + seatNumber);
             seatButton.addActionListener(new SeatClickListener());
 
         seatPanel.add(seatButton);
         seatNumber++;
        }
    }

        frame.add(seatPanel);
        frame.setVisible(true);
    }

    private class SeatClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton seatButton = (JButton) e.getSource();
            String seatName = seatButton.getText();
            frame.dispose(); // Close the seat selection frame
            openInsuranceSelectionFrame(seatName);
        }
    }
    
    private void openInsuranceSelectionFrame(String selectedSeat) {
        SelectInsuranceGUI insuranceGUI = new SelectInsuranceGUI(selectedSeat);
        insuranceGUI.createUI();
    }
}
