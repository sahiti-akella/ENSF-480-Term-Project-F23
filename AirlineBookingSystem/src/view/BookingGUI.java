package view;

import javax.swing.*;
import java.awt.*;
import model.Booking;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BookingGUI extends JFrame {
    public BookingGUI(Booking booking) {
        setTitle("Booking");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);

        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        Font boldFont = new Font("Arial", Font.BOLD, 14);

        panel.add(createBoldLabel("Booking Details:", boldFont));
        panel.add(createLabelWithSpacing(""));
        panel.add(createLabelWithSpacing("Booking Date: " + getCurrentDate())); 
        panel.add(createLabelWithSpacing("Flight Origin: " + booking.getOrigin()));
        panel.add(createLabelWithSpacing("Flight Destination: " + booking.getDestination()));
        panel.add(createLabelWithSpacing("Selected Seat: " + booking.getSelectedSeat()));
        panel.add(createLabelWithSpacing("Seat Price: $" + booking.getSeatPrice()));
        panel.add(createLabelWithSpacing("Ticket Cancellation Insurance ($100) Selected: " + (booking.hasInsurance() ? "Yes" : "No")));
        panel.add(createLabelWithSpacing("Total Price: $" + booking.getTotalPrice()));
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

    private String getCurrentDate() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }
}
