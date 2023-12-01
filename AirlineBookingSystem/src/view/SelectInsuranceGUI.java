package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.Receipt;
import model.Seat;

public class SelectInsuranceGUI {
    private JFrame frame;
    private String selectedSeat;
    private FlightSystem sys;

    public SelectInsuranceGUI(String selectedSeat) {
        this.selectedSeat = selectedSeat;
        this.sys = FlightSystem.getInstance(); 
    }

    public void createUI() {
        frame = new JFrame();
        frame.setTitle("Insurance Selection");
        JPanel panel = new JPanel();
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        JLabel seatLabel = new JLabel("Selected Seat: " + selectedSeat);
        seatLabel.setBounds(30, 10, 500, 40);
        seatLabel.setFont(new Font("Arial", Font.BOLD, 15));
        panel.add(seatLabel);

        JLabel promptLabel = new JLabel("Would you like to Opt-In for Ticket Cancellation Insurance? (+$100)");
        promptLabel.setBounds(30, 40, 500, 40);
        panel.add(promptLabel);

        JButton yesButton = new JButton("Yes");
        yesButton.setBounds(30, 80, 80, 40);
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the insurance selection frame
                openPaymentPage(true);
            }
        });
        panel.add(yesButton);

        JButton noButton = new JButton("No");
        noButton.setBounds(120, 80, 80, 40);
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the insurance selection frame
                openPaymentPage(false);
            }
        });
        panel.add(noButton);

        frame.setVisible(true);
    }

    private void openPaymentPage(boolean hasInsurance) {
        double seatPrice = getSeatPrice(selectedSeat); 
        Receipt receipt = new Receipt(selectedSeat, hasInsurance, seatPrice);
        PaymentPageGUI paymentPageGUI = new PaymentPageGUI(receipt);
        paymentPageGUI.createUI();
    }

    private double getSeatPrice(String selectedSeat) {
        ArrayList<Seat> seatsList = sys.getSeatList();
    
        // Extract the numeric part from the selectedSeat string
        String numericPart = selectedSeat.replaceAll("\\D+", "");
    
        for (Seat seat : seatsList) {
            if (seat.getSeatID() == Integer.parseInt(numericPart)) {
                return seat.getPrice();
            }
        }
    
        System.out.println("ERROR: Seat ID " + numericPart + " not found in seat list");
        return 0.0; 
    }
    
    
}
