package panels;

import java.awt.Color;


import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import buttons.BackButton;
import buttons.SignedUpButton;
/**
 * sign up panel for sign up page. Related button actions in DefaultFrame
 * @author icebe
 *
 */
public class SignupPanel extends JPanel {

	private JLabel usernameLabel= new JLabel();
	private JLabel nameLabel= new JLabel();
	private JLabel surnameLabel= new JLabel();
	private JLabel ageLabel= new JLabel();
	private JLabel passwordLabel= new JLabel();
	private JLabel emailLabel=new JLabel();
	private JLabel rankLabel=new JLabel();

	public JLabel errorLabel=new JLabel();

	public JTextField usernameTextField=new JTextField();
	public JTextField nameTextField=new JTextField();
	public JTextField surnameTextField=new JTextField();
	public JTextField ageTextField=new JTextField();
	public JTextField passwordTextField=new JTextField();
	public JTextField emailTextField=new JTextField();
	
	String[] rankOptions = {"free", "hobbyist", "professional"};
	public JComboBox<String> rankComboBox=new JComboBox(rankOptions);

	public SignedUpButton signedUpButton=new SignedUpButton();
	public BackButton backToLoginButton=new BackButton();

	public SignupPanel() {
		this.setLayout(null);
		this.setBackground(Color.gray);
		this.setBounds(0, 0, 420, 600);

		usernameLabel.setBounds(100, 150, 70, 20);
		usernameLabel.setText("Username:");
		usernameTextField.setBounds(200, 150, 70, 20);

		nameLabel.setBounds(100, 200,  70, 20);
		nameLabel.setText("Name:");
		nameTextField.setBounds(200, 200, 70, 20);

		surnameLabel.setBounds(100, 250,  70, 20);
		surnameLabel.setText("Surname:");
		surnameTextField.setBounds(200, 250, 70, 20);

		ageLabel.setBounds(100, 300, 70, 20);
		ageLabel.setText("Age:");
		ageTextField.setBounds(200, 300, 70, 20);

		passwordLabel.setBounds(100, 350, 70, 20);
		passwordLabel.setText("Password:");
		passwordTextField.setBounds(200, 350, 70, 20);

		emailLabel.setBounds(100, 400, 70, 20);
		emailLabel.setText("Email:");
		emailTextField.setBounds(200, 400, 70, 20);

		rankLabel.setBounds(100, 450, 70, 20);
		rankLabel.setText("Rank:");
		rankComboBox.setBounds(200, 450, 70, 20);
		
		backToLoginButton.setBounds(225, 500, 100, 20);
		backToLoginButton.setBackground(this.getBackground());
		

		errorLabel.setBounds(100,100,300 ,20 );
		errorLabel.setForeground(Color.red);

		this.add(ageLabel);
		this.add(emailLabel);
		this.add(nameLabel);
		this.add(passwordLabel);
		this.add(surnameLabel);
		this.add(usernameLabel);
		this.add(rankLabel);
		this.add(errorLabel);

		this.add(ageTextField);
		this.add(emailTextField);
		this.add(nameTextField);
		this.add(passwordTextField);
		this.add(surnameTextField);
		this.add(usernameTextField);
		this.add(rankComboBox);

		this.add(signedUpButton);
		this.add(backToLoginButton);

	}
}
