package view;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class LoginGUI implements ActionListener {
	JTextField userText;
	JPasswordField passwordText;
	JLabel success;
	
	public static void main(String[] args) {
        LoginGUI gui = new LoginGUI();
        gui.createUI();
    }
	
	public void openUserGui(String type) {
		// Valid credentials, check account type
        if (type.equals("admin")) {
            // Open System Admin GUI
            new SystemAdminGUI().createUI();
        } else if (type.equals("customer")) {
            // Open Customer GUI
            new CustomerGUI().createUI();
        } else if (type.equals("airline-agent")){
            // Open Airline Agent GUI
        	new AirlineAgentGUI().createUI();
        } else if (type.equals("flight-attendant")){
        	// Open Flight Attendant GUI
        	new FlightAttendantGUI().createUI();
        } else if (type.equals("tourism-agent")){
            // Open Tourism Agent GUI
            new TourismAgentGUI().createUI();
        } Else {
        	success.setText("Unknown acount type");
        }
	}

    public void createUI() {
        JFrame frame = new JFrame();
        frame.setTitle("Flight Reservation Application");
        JPanel login = new JPanel();
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(login);

        login.setLayout(null);

        JLabel userLabel = new JLabel("UserName:");
        userLabel.setBounds(20, 20, 80, 25);
        login.add(userLabel);

        userText = new JTextField();
        userText.setBounds(100, 20, 165, 25);
        login.add(userText);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(20, 60, 80, 25);
        login.add(passwordLabel);

        passwordText = new JPasswordField();
        passwordText.setBounds(100, 60, 165, 25);
        login.add(passwordText);

        JButton logButton = new JButton("Login");
        logButton.setBounds(20, 100, 80, 25);
        logButton.addActionListener(this);
        login.add(logButton);

        success = new JLabel("");
        success.setBounds(20, 120, 300, 25);
        login.add(success);

        frame.setVisible(true);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Login")) {
			String username = userText.getText();
            String password = String.valueOf(passwordText.getPassword());
         // Database Connection Details
            String url = "jdbc:mysql://localhost/FRWA";
            String dbUsername = "root";
            String dbPassword = "password";

            try {
               
                Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);

                PreparedStatement credentials = connection.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
                credentials.setString(1, username);
                credentials.setString(2, password);

                ResultSet resultSet = credentials.executeQuery();

                if (resultSet.next()) {
                    // Valid credentials
                    success.setText("Login Successful");
                    Timer timer = new Timer(2000, new ActionListener() {
                    	@Override
                    	public void actionPerformed(ActionEvent e) {
                    		String accountType = resultSet.getString("AccountType");

                            openUserGui(accountType);
                    	}
                    });
                    timer.setRepeats(false); // Set to run only once
                    timer.start();
                } else {
                    // Invalid credentials
                    success.setText("Login Failed");
                }

                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
