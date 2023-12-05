package panels;

import java.awt.Color;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import buttons.BackButton;
import buttons.SignedUpButton;
/**
 * for setting user information. Button action methods in DefaultFrame
 * @author icebe
 *
 */
public class SettingsPanel extends JPanel {
	private JLabel usernameLabel= new JLabel();
	private JLabel nameLabel= new JLabel();
	private JLabel surnameLabel= new JLabel();
	private JLabel ageLabel= new JLabel();
	private JLabel passwordLabel= new JLabel();
	private JLabel emailLabel=new JLabel();
	private JLabel rankLabel=new JLabel();
	private JLabel profilePhotoLabel=new JLabel();
	
	public JLabel currentEmailLabel=new JLabel();
	public JLabel currentPasswordLabel=new JLabel();
	public JLabel errorLabel=new JLabel(); //TODO TODO TODO
	public JLabel usernameShowLabel= new JLabel();

	public JTextField setNameTextField=new JTextField();
	public JTextField setSurnameTextField=new JTextField();
	public JTextField setAgeTextField=new JTextField();
	public JTextField setPasswordTextField=new JTextField();
	public JTextField setEmailTextField=new JTextField();
	public JButton setProfilePhotoButton=new JButton("Choose Photo");
	
	public JButton applyNameButton=new JButton("apply");
	public JButton applySurnameButton=new JButton("apply");
	public JButton applyAgeButton=new JButton("apply");
	public JButton applyEmailButton=new JButton("apply");
	public JButton applyPasswordButton=new JButton("apply");
	public JButton applyProfilePhotoButton=new JButton("apply");
	public JButton applyRankButton=new JButton("apply");
	
	
	String[] rankOptions = {"free", "hobbyist", "professional"};
	public JComboBox<String> rankComboBox=new JComboBox(rankOptions);

	public BackButton backToProfileButton=new BackButton();

	public SettingsPanel() {
		this.setLayout(null);
		this.setBackground(Color.darkGray);
		this.setBounds(0, 0, 420, 600);
		
		usernameLabel.setBounds(100, 60, 70, 20);
		usernameLabel.setText("Username:");
		usernameLabel.setForeground(Color.white);
		usernameShowLabel.setBounds(195,60,70,20);
		usernameShowLabel.setForeground(Color.white);
		
		currentEmailLabel.setBounds(100,80,200,20);
		currentEmailLabel.setForeground(Color.white);
		
		currentPasswordLabel.setBounds(100,100,200,20);
		currentPasswordLabel.setForeground(Color.white);
		
		profilePhotoLabel.setBounds(100, 150, 70, 20);
		profilePhotoLabel.setText("Profile Photo:");
		profilePhotoLabel.setForeground(Color.white);
		setProfilePhotoButton.setBounds(200, 150, 70, 20);
		setProfilePhotoButton.setBackground(Color.white);
		applyProfilePhotoButton.setBounds(300, 150, 70, 20);
		applyProfilePhotoButton.setBackground(Color.white);

		nameLabel.setBounds(100, 200,  70, 20);
		nameLabel.setText("Name:");
		nameLabel.setForeground(Color.white);
		setNameTextField.setBounds(200, 200, 70, 20);
		applyNameButton.setBounds(300, 200, 70, 20);
		applyNameButton.setBackground(Color.white);

		surnameLabel.setBounds(100, 250,  70, 20);
		surnameLabel.setText("Surname:");
		surnameLabel.setForeground(Color.white);
		setSurnameTextField.setBounds(200, 250, 70, 20);
		applySurnameButton.setBounds(300, 250, 70, 20);
		applySurnameButton.setBackground(Color.white);

		ageLabel.setBounds(100, 300, 70, 20);
		ageLabel.setText("Age:");
		ageLabel.setForeground(Color.white);
		setAgeTextField.setBounds(200, 300, 70, 20);
		applyAgeButton.setBounds(300, 300, 70, 20);
		applyAgeButton.setBackground(Color.white);

		passwordLabel.setBounds(100, 350, 70, 20);
		passwordLabel.setText("Password:");
		passwordLabel.setForeground(Color.white);
		setPasswordTextField.setBounds(200, 350, 70, 20);
		applyPasswordButton.setBounds(300, 350, 70, 20);
		applyPasswordButton.setBackground(Color.white);

		emailLabel.setBounds(100, 400, 70, 20);
		emailLabel.setText("Email:");
		emailLabel.setForeground(Color.white);
		setEmailTextField.setBounds(200, 400, 70, 20);
		applyEmailButton.setBounds(300, 400, 70, 20);
		applyEmailButton.setBackground(Color.white);

		rankLabel.setBounds(100, 450, 70, 20);
		rankLabel.setText("Rank:");
		rankLabel.setForeground(Color.white);
		rankComboBox.setBounds(200, 450, 70, 20);
		applyRankButton.setBounds(300, 450, 70, 20);
		applyRankButton.setBackground(Color.white);
		
		backToProfileButton.setBackground(this.getBackground());
		backToProfileButton.setBounds(125, 500, 100, 20);
		

		errorLabel.setBounds(20,480 ,370 ,20);
		errorLabel.setForeground(Color.red);

		this.add(ageLabel);
		this.add(emailLabel);
		this.add(nameLabel);
		this.add(passwordLabel);
		this.add(surnameLabel);
		this.add(usernameLabel);
		this.add(rankLabel);
		this.add(profilePhotoLabel);
		this.add(usernameShowLabel);
		this.add(currentEmailLabel);
		this.add(currentPasswordLabel);
		this.add(errorLabel);

		this.add(setAgeTextField);
		this.add(setEmailTextField);
		this.add(setNameTextField);
		this.add(setPasswordTextField);
		this.add(setSurnameTextField);
		this.add(setProfilePhotoButton);
		this.add(rankComboBox);
		
		this.add(applyAgeButton);
		this.add(applyEmailButton);
		this.add(applyNameButton);
		this.add(applyPasswordButton);
		this.add(applyProfilePhotoButton);
		this.add(applyRankButton);
		this.add(applySurnameButton);
		this.add(backToProfileButton);

	}
}
