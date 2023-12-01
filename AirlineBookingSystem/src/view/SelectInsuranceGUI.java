package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectInsuranceGUI {
    private JFrame frame;
    private String selectedSeat;

    public SelectInsuranceGUI(String selectedSeat) {
        this.selectedSeat = selectedSeat;
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
                openPaymentPage();
            }
        });
        panel.add(yesButton);

        JButton noButton = new JButton("No");
        noButton.setBounds(120, 80, 80, 40);
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the insurance selection frame
                openPaymentPage();
            }
        });
        panel.add(noButton);

        frame.setVisible(true);
    }

    private void openPaymentPage() {
        PaymentPageGUI paymentPageGUI = new PaymentPageGUI();
        paymentPageGUI.createUI();
    }
}
