package panels;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import buttons.LoginButton;
import buttons.SignupButton;


/**
 * login page components
 * @author icebe
 *
 */
public class LoginPanel extends JPanel{

	private JLabel usernameLabel= new JLabel();
	private JLabel passwordLabel= new JLabel();

	public JTextField usernameTextField=new JTextField();
	public JTextField passwordTextField=new JTextField();

	public LoginButton loginButton;
	public SignupButton signupButton;
	
	public JLabel loginErrorLabel=new JLabel(); 

	public LoginPanel() {
		this.setLayout(null);
		this.setBackground(Color.lightGray);
		this.setBounds(0, 0, 420, 600);
		
		loginButton=new LoginButton();
		signupButton=new SignupButton();
		
		loginErrorLabel.setBounds(100,375,200,25);
		loginErrorLabel.setBackground(Color.red);

		usernameLabel.setBounds(100, 200, 70, 20);
		usernameLabel.setText("Username:");

		passwordLabel.setBounds(100, 225, 70, 20);
		passwordLabel.setText("Password:");

		usernameTextField.setBounds(170, 200, 70, 20);
		passwordTextField.setBounds(170, 225, 70, 20);


		this.add(usernameLabel);
		this.add(passwordLabel);
		this.add(usernameTextField);
		this.add(passwordTextField);
		this.add(loginButton);
		this.add(signupButton);
		this.add(loginErrorLabel);
	}
}
