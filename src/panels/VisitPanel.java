package panels;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import buttons.BackButton;
import buttons.ProfilePostButton;
import managers.ProgramManager;
import photo.Photo;
import users.User;

public class VisitPanel extends JPanel {
	
	private JPanel topProfilePanel=new JPanel();
	private JPanel bottomProfilePanel=new JPanel();
	
	public JLabel usernameLabel= new JLabel();
	public JLabel personalInfoLabel= new JLabel();
	public JLabel rankLabel=new JLabel();
	public JLabel profilPhotoLabel=new JLabel();
	/**
	 * visitor page of the user which has a given username
	 * @param username: User username
	 */
	public VisitPanel(String username) {
		User user=ProgramManager.userEntered(username);
		
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
		
		for(Photo photo:user.getPhotos()) {
			ProfilePostButton photoButton=new ProfilePostButton(username,photo);
			photoButton.setPreferredSize(new Dimension(100,100));
			photoButton.setBackground(this.getBackground());
			bottomProfilePanel.add(photoButton);

		profilPhotoLabel.setBounds(15, 15, 100, 100);
		profilPhotoLabel.setIcon(ProgramManager.setImageBounds(user.getProfilePhoto(), 100, 100));
		
		usernameLabel.setBounds(150, 15 , 150, 25);
		usernameLabel.setText(user.getUsername());
		usernameLabel.setForeground(Color.white);
		
		// name, surname, age
		personalInfoLabel.setBounds(150, 40 , 150, 25);
		personalInfoLabel.setText(user.getName()+" "+user.getSurname()+", "+user.getAge());
		personalInfoLabel.setForeground(Color.white);
		
		
		rankLabel.setBounds(150, 65 , 150, 25);
		rankLabel.setText(user.getRank());
		rankLabel.setForeground(Color.white);
		
		topProfilePanel.add(profilPhotoLabel);
		topProfilePanel.add(usernameLabel);
		topProfilePanel.add(personalInfoLabel);
		topProfilePanel.add(rankLabel);

		
		
		
		this.add(topProfilePanel);
		this.add(bottomProfilePanel);

		
	}
}}
