package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Payment;

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

        JLabel firstNameLabel = new JLabel("Card Holder First Name:");
        firstNameLabel.setBounds(30, 50, 200, 25);
        panel.add(firstNameLabel);

        JTextField firstNameField = new JTextField();
        firstNameField .setBounds(180, 50, 150, 25);
        panel.add(firstNameField );

        JLabel lastNameLabel = new JLabel("Card Holder Last Name:");
        lastNameLabel.setBounds(30, 80, 150, 25);
        panel.add(lastNameLabel);

        JTextField lastNameField = new JTextField();
        lastNameField .setBounds(180, 80, 150, 25);
        panel.add(lastNameField );

        JLabel numberLabel = new JLabel("Card Number:");
        numberLabel.setBounds(30, 110, 150, 25);
        panel.add(numberLabel);

        JTextField numberField = new JTextField();
        numberField.setBounds(180, 110,150, 25);
        panel.add(numberField);

        JLabel expiryLabel = new JLabel("Expiry Date:");
        expiryLabel.setBounds(30, 140, 200, 25);
        panel.add(expiryLabel);

        JTextField expiryField = new JTextField();
        expiryField.setBounds(180, 140, 150, 25);
        panel.add(expiryField);

        JLabel cvvLabel = new JLabel("CVV:");
        cvvLabel.setBounds(30, 170, 150, 25);
        panel.add(cvvLabel);

        JTextField cvvField = new JTextField();
        cvvField.setBounds(180, 170, 150, 25);
        panel.add(cvvField);

        JButton proceedButton = new JButton("Confirm Payment");
        proceedButton.setBounds(100, 210, 150, 40);
        proceedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get entered information
                String cardHolderFirstName = firstNameField.getText();
                String cardHolderLastName = lastNameField.getText();
                String cardNumber = numberField.getText();
                String expiryDate = expiryField.getText();
                String cvvString = cvvField.getText();
        
                // Check if any of the required fields are empty
                if (cardHolderFirstName.isEmpty() || cardHolderLastName.isEmpty() ||
                    cardNumber.isEmpty() || expiryDate.isEmpty() || cvvString.isEmpty()) {
                    // Show an error message for incomplete form
                    JOptionPane.showMessageDialog(frame, "Please complete the payment form.");
                    return; // Stop processing further
                }
        
                int cvv;
                try {
                    cvv = Integer.parseInt(cvvString);
                } catch (NumberFormatException ex) {
                    // Show error message for invalid CVV format
                    JOptionPane.showMessageDialog(frame, "Invalid CVV format. Please enter a 3-digit CVV.");
                    return; // Stop processing further
                }
        
                // Create Payment object
                Payment payment = new Payment(cardHolderFirstName, cardHolderLastName, cardNumber, expiryDate, cvv);
        
                // Check if payment is valid
                if (payment.isValid()) {
                    // Perform actions on payment confirmation, e.g., show confirmation message
                    JOptionPane.showMessageDialog(frame, "Payment Successful!");
                    frame.dispose(); // Close the payment frame
        
                    // Open a new frame for success message
                    openSuccessMessageFrame();
                } else {
                    // Show an error message if the payment is not valid
                    JOptionPane.showMessageDialog(frame, "Invalid payment information. Please check and try again.\n" +
                            "Please enter a valid expiry date in the format dd/mm/yyyy, a 16-digit Card Number, and a 3-digit CVV.");
                }
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
