package panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import buttons.ProfilePostButton;
import managers.PhotoManager;
import managers.ProgramManager;
import photo.Photo;
import users.User;
import frames.DefaultFrame;
/**
 * profile panel for profile page. has 2 main section one of topProfilePanel the other bottomProfilePanel.
 * topProfilePanel includes user information, settings and upload post buttons
 * bottomProfilePanel includes posts that user has made 
 * @author icebe
 *
 */
public class ProfilePanel extends JPanel {
	
	private JPanel topProfilePanel=new JPanel();
	private JPanel bottomProfilePanel=new JPanel();
	
	public JLabel usernameLabel= new JLabel();
	public JLabel personalInfoLabel= new JLabel();
	public JLabel bioLabel= new JLabel();
	public JLabel rankLabel=new JLabel();
	public JLabel profilPhotoLabel=new JLabel();
	
	public JButton settingsButton= new JButton();
	public JButton uploadButton=new JButton();
	
	public ProfilePanel() {
		this.setLayout(null);
		this.setBackground(Color.darkGray);
		this.setBounds(0, 0, 420, 515);

		topProfilePanel.setLayout(null);
		topProfilePanel.setBackground(Color.black);
		topProfilePanel.setBounds(0, 0, 420, 150);
		
		bottomProfilePanel.setLayout(new GridLayout(0,3,5,5));
		bottomProfilePanel.setBackground(Color.black);
		bottomProfilePanel.setPreferredSize(new Dimension(390,315));
		bottomProfilePanel.setBounds(10, 175, 390, 330);		
				
		ImageIcon settingImage= new ImageIcon("src\\icons\\icons8-settings-60.png");

		Image originalSettingImage = settingImage.getImage();
		Image resizedSettingImage = originalSettingImage.getScaledInstance(60, 60, Image.SCALE_SMOOTH); //TODO TODO
		ImageIcon resizedSettingIcon = new ImageIcon(resizedSettingImage);
		settingsButton.setBounds(360, 0, 60, 60); //TODO TODO
		settingsButton.setIcon(resizedSettingIcon);
		settingsButton.setFocusable(false);
		settingsButton.setFocusPainted(false);
		settingsButton.setBackground(Color.black);
		
		ImageIcon uploadImage= new ImageIcon("src\\icons\\icons8-upload-60.png");
		
		Image originalUploadImage = uploadImage.getImage();
		Image resizedUploadImage = originalUploadImage.getScaledInstance(60, 60, Image.SCALE_SMOOTH);  //TODO TODO
		ImageIcon resizedUploadIcon = new ImageIcon(resizedUploadImage);
		uploadButton.setBounds(300, 0, 60, 60);  //TODO TODO
		uploadButton.setIcon(resizedUploadIcon);
		uploadButton.setFocusable(false);
		uploadButton.setBackground(Color.black);
		

		profilPhotoLabel.setBounds(15, 15, 100, 100); 
		
		usernameLabel.setBounds(150, 15 , 150, 25);
		usernameLabel.setForeground(Color.white);
		
		// name, surname, age
		personalInfoLabel.setBounds(150, 40 , 150, 25);
		personalInfoLabel.setForeground(Color.white);
		
		
		rankLabel.setBounds(150, 65 , 150, 25);
		rankLabel.setForeground(Color.white);
		
		bioLabel.setBounds(150, 90 , 150, 25);
		bioLabel.setForeground(Color.white);
		
		
		topProfilePanel.add(profilPhotoLabel);
		topProfilePanel.add(usernameLabel);
		topProfilePanel.add(uploadButton);
		topProfilePanel.add(settingsButton);
		topProfilePanel.add(personalInfoLabel);
		topProfilePanel.add(bioLabel);
		topProfilePanel.add(rankLabel);
		
		this.add(topProfilePanel);
		this.add(bottomProfilePanel);

		

	}
	/**
	 * loads the user photos for bottomProfilePanel
	 * @param username: User username
	 */
	public void userPhotos(String username) {
		User user=ProgramManager.userEntered(username);
		if(user.getPhotos()!=null) {
		for(Photo photo:user.getPhotos()) {		
			ProfilePostButton photoButton=new ProfilePostButton(username,photo);
			photoButton.setPreferredSize(new Dimension(100,100));
			photoButton.setBackground(this.getBackground());
			bottomProfilePanel.add(photoButton);
		}}
	}
	/**
	 * deletes the posts that user has made (useful for algorithm)
	 */
	public void cleanProfile() {
		Component[] components= bottomProfilePanel.getComponents();
		for(Component component:components) {
			bottomProfilePanel.remove(component);
		}
	}

}
