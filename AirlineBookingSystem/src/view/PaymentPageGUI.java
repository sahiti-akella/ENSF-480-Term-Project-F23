package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentPageGUI {
    private JFrame frame;

    public void createUI() {
        frame = new JFrame();
        frame.setTitle("Payment Page");
        JPanel panel = new JPanel();
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        JLabel promptLabel = new JLabel("Enter Payment Details:");
        promptLabel.setBounds(30, 10, 300, 40);
        panel.add(promptLabel);

        JLabel nameLabel = new JLabel("Card Holder Name:");
        nameLabel.setBounds(30, 50, 150, 25);
        panel.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(180, 50, 150, 25);
        panel.add(nameField);

        JLabel numberLabel = new JLabel("Card Number:");
        numberLabel.setBounds(30, 80, 150, 25);
        panel.add(numberLabel);

        JTextField numberField = new JTextField();
        numberField.setBounds(180, 80, 150, 25);
        panel.add(numberField);

        JLabel expiryLabel = new JLabel("Expiry Date:");
        expiryLabel.setBounds(30, 110, 150, 25);
        panel.add(expiryLabel);

        JTextField expiryField = new JTextField();
        expiryField.setBounds(180, 110, 150, 25);
        panel.add(expiryField);

        JLabel cvvLabel = new JLabel("CVV:");
        cvvLabel.setBounds(30, 140, 150, 25);
        panel.add(cvvLabel);

        JTextField cvvField = new JTextField();
        cvvField.setBounds(180, 140, 150, 25);
        panel.add(cvvField);

        JButton proceedButton = new JButton("Confirm Payment");
        proceedButton.setBounds(30, 180, 150, 40);
        proceedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform actions on payment confirmation, e.g., show confirmation message
                JOptionPane.showMessageDialog(frame, "Payment Successful!");
                frame.dispose(); // Close the payment frame

                // Open a new frame for success message
                openSuccessMessageFrame();
            }
        });
        panel.add(proceedButton);

        frame.setVisible(true);
    }

    private void openSuccessMessageFrame() {
        JFrame successFrame = new JFrame();
        successFrame.setTitle("Success!");
        JPanel successPanel = new JPanel();
        successFrame.setSize(400, 200);
        successFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        successFrame.add(successPanel);

        successPanel.setLayout(null);

        JLabel successLabel = new JLabel("Your trip has successfully been booked!");
        successLabel.setBounds(30, 10, 400, 40);
        successPanel.add(successLabel);

        JLabel emailLabel = new JLabel("Please check your email for ticket and receipt details.");
        emailLabel.setBounds(30, 50, 400, 40);
        successPanel.add(emailLabel);

        successFrame.setVisible(true);
    }
}
