package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrowseSeatGUI {
    private JFrame frame;
    private JPanel seatPanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BrowseSeatGUI seatGUI = new BrowseSeatGUI();
            seatGUI.createUI();
        });
    }

    public void createUI() {
        frame = new JFrame();
        frame.setTitle("Browse Seats");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        seatPanel = new JPanel();
        seatPanel.setLayout(new GridLayout(5, 5)); // Change the dimensions as needed

        // Create seats
        for (int row = 1; row <= 4; row++) {
            for (int col = 1; col <= 4; col++) {
                JButton seatButton = new JButton("Seat " + row + "-" + col);
                seatButton.addActionListener(new SeatClickListener());
         
                seatPanel.add(seatButton);
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
            JOptionPane.showMessageDialog(frame, "You selected " + seatName);
        }
    }
}
