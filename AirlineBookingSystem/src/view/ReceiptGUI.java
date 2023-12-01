package view;

import javax.swing.*;
import java.awt.*;
import model.Receipt;

public class ReceiptGUI extends JFrame {
    public ReceiptGUI(Receipt receipt) {
        setTitle("Receipt");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);

        // Set an empty border to add space around the panel
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        Font boldFont = new Font("Arial", Font.BOLD, 14);

        panel.add(createBoldLabel("Receipt:", boldFont));
        panel.add(createLabelWithSpacing(""));
        panel.add(createLabelWithSpacing("Selected Seat: " + receipt.getSelectedSeat()));
        panel.add(createLabelWithSpacing("Seat Price: $" + receipt.getSeatPrice()));
        panel.add(createLabelWithSpacing("Ticket Cancellation Insurance ($100) Selected: " + (receipt.hasInsurance() ? "Yes" : "No")));
        panel.add(createLabelWithSpacing("Total Price: $" + receipt.getTotalPrice()));
    }

    private JLabel createBoldLabel(String text, Font font) {
        JLabel label = new JLabel("<html><b>" + text + "</b></html>");
        label.setFont(font);
        return label;
    }

    private JLabel createLabelWithSpacing(String text) {
        JLabel label = new JLabel(text);
        label.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        return label;
    }
}
