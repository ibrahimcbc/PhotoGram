package panels;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import buttons.BackButton;
/**
 * for uploading post buttons action methods in DefaultFrame
 * @author icebe
 *
 */
public class AddPhotoPanel extends JPanel{
	
	private JLabel profilePhotoLabel=new JLabel();
	
	private JLabel privateLabel=new JLabel();

	public JLabel errorLabel=new JLabel();
	
	private JLabel descriptionLabel= new JLabel();

	public JButton setUploadPhotoButton=new JButton("Choose Photo");
	
	public JButton applyUploadPhotoButton=new JButton("Post");
	
	public JTextField descriptionField=new JTextField();
	
	String[] privateOptions = {"private", "open"};
	
	public JComboBox<String> privateComboBox=new JComboBox(privateOptions);

	public BackButton backToProfileButton=new BackButton();

	public AddPhotoPanel() {
		this.setLayout(null);
		this.setBackground(Color.darkGray);
		this.setBounds(0, 0, 420, 600);

		
		profilePhotoLabel.setBounds(100, 150, 90, 20);
		profilePhotoLabel.setText("Select Photo:");
		profilePhotoLabel.setForeground(Color.white);
		
		setUploadPhotoButton.setBounds(200, 150, 70, 20);
		setUploadPhotoButton.setBackground(Color.white);
		
		applyUploadPhotoButton.setBounds(150, 400, 70, 20);
		applyUploadPhotoButton.setBackground(Color.white);

		backToProfileButton.setBackground(this.getBackground());
		backToProfileButton.setBounds(125, 500, 100, 20);
		
		privateLabel.setBounds(100, 250, 70, 20);
		privateLabel.setText("Privacy:");
		privateLabel.setForeground(Color.white);
		privateComboBox.setBounds(200, 250, 70, 20);
		
		descriptionLabel.setBounds(100,350,70,20);
		descriptionLabel.setText("description");
		descriptionLabel.setForeground(Color.white);
		descriptionField.setBounds(200,350,70,20);
		descriptionField.setHorizontalAlignment(SwingConstants.LEFT);

		

		errorLabel.setBounds(100,450,200,20);
		errorLabel.setForeground(Color.red);


		this.add(profilePhotoLabel);

		this.add(setUploadPhotoButton);
		this.add(privateLabel);
		this.add(privateComboBox);
		
		this.add(applyUploadPhotoButton);

		this.add(backToProfileButton);
		this.add(descriptionField);
		this.add(descriptionLabel);
		this.add(errorLabel);
	}
}
