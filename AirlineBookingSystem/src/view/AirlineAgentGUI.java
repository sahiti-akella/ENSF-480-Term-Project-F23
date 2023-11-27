package view;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class AirlineAgentGUI implements ActionListener {
	public static void main(String[] args) {
        AirlineAgentGUI gui = new AirlineAgentGUI();
        gui.createUI();
    }

    public void createUI() {
        JFrame frame = new JFrame();
        frame.setTitle("Airline Agent Welcome Page");
        JPanel panel = new JPanel();
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        JLabel welcomeLabel = new JLabel("Welcome!");
        welcomeLabel.setBounds(30, 10, 200, 40);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 15));
        panel.add(welcomeLabel);

        JLabel promptLabel = new JLabel("What would you like to do?");
        promptLabel.setBounds(30, 40, 200, 40);
        panel.add(promptLabel);

        String[] options = {"Select an action..", "Browse Passenger List", };
        JComboBox<String> dropdown = new JComboBox<>(options);
        dropdown.setBounds(30, 80, 250, 25);
        dropdown.addActionListener(this); 
        panel.add(dropdown);

        frame.setVisible(true);
    }

	@Override
    public void actionPerformed(ActionEvent e) {
        JComboBox<String> source = (JComboBox<String>) e.getSource();

        if (source.getSelectedIndex() == 1) { 
            openBrowsePassengerListFrame();
        }
    }

    private void openBrowsePassengerListFrame() {
         JFrame frame = new JFrame();
        frame.setTitle("Browse Passenger List");
        JPanel panel = new JPanel();
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        JLabel welcomeLabel = new JLabel("Browse Passenger List");
        welcomeLabel.setBounds(30, 10, 200, 40);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 15));
        panel.add(welcomeLabel);

        JLabel promptLabel = new JLabel("Please Select the Desired Flight");
        promptLabel.setBounds(30, 40, 200, 40);
        panel.add(promptLabel);

        String[] options = {"Select flight..", "1", "2", "3", };
        JComboBox<String> dropdown = new JComboBox<>(options);
        dropdown.setBounds(30, 80, 250, 25);
        dropdown.addActionListener(this); 
        panel.add(dropdown);

        frame.setVisible(true);
    }
}
