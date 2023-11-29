package view;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Properties;

public class LoginGUI implements ActionListener {
	JTextField userText;
	JPasswordField passwordText;
	JLabel success;
    int UserID = 0;
    String UserName = null;
    String UserPassword = null;
    String FirstName= null;
    String LastName = null;
    String Address = null;
    String Email = null; 
    String AccountType = null;
    boolean isRegistered = false; 
    String CreditCardNumber = null;
	
	public static void main(String[] args) {
        LoginGUI gui = new LoginGUI();
        gui.createUI();
    }
	
	public void openUserGui(int UserID, String UserName, String UserPassword, String FirstName, String LastName, String Address, String Email, String AccountType, boolean isRegistered, String CreditCardNumber) {
		// Valid credentials, check account type
        if (AccountType.equals("admin")) {
            // Open System Admin GUI
            new SystemAdminGUI().initialize();
        } else if (AccountType.equals("customer")) {
            // Open Customer GUI
            new CustomerGUI().createCustomer(UserID, UserName, UserPassword, FirstName, LastName, Email, Address, isRegistered);
        } else if (AccountType.equals("airline-agent")){
            // Open Airline Agent GUI
        	new AirlineAgentGUI().createUI();
        } else if (AccountType.equals("flight-attendant")){
        	// Open Flight Attendant GUI
        	new FlightAttendantGUI().createUI();
        } else if (AccountType.equals("tourism-agent")){
            // Open Tourism Agent GUI
            new TourismAgentGUI().createUI();
        } else {
        	success.setText("Unknown account type");
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
        
            // Load database properties
            Properties properties = DBUtils.loadProperties("AirlineBookingSystem/config/database.properties");
            if (properties == null) {
                // Handle the error appropriately
                return;
            }

            String url = properties.getProperty("db.url");
            String dbUsername = properties.getProperty("db.username");
            String dbPassword = properties.getProperty("db.password");


            try {
               
                Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);

                PreparedStatement credentials = connection.prepareStatement("SELECT * FROM users WHERE username=? AND userpassword=?");
                credentials.setString(1, username);
                credentials.setString(2, password);

                ResultSet resultSet = credentials.executeQuery();

                if (resultSet.next()) {
                    // Valid credentials
                    UserID = resultSet.getInt("UserID");
                    UserName = resultSet.getString("UserName");
                    UserPassword = resultSet.getString("UserPassword");
                    FirstName= resultSet.getString("FirstName");
                    LastName = resultSet.getString("LastName");
                    Address = resultSet.getString("Address");
                    Email = resultSet.getString("Email"); 
                    AccountType = resultSet.getString("AccountType");
                    isRegistered = resultSet.getBoolean("isRegistered");
                    CreditCardNumber = resultSet.getString("CreditCardNumber");
                    success.setText("Login Successful");
                    Timer timer = new Timer(2000, new ActionListener() {
                    	@Override
                    	public void actionPerformed(ActionEvent e) {
                    		try {
                                openUserGui(UserID, UserName, UserPassword, FirstName, LastName, Address, Email, AccountType, isRegistered, CreditCardNumber);
                            } catch (SQLException ex) {
                                ex.printStackTrace(); // handle the exception appropriately
                            }
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
