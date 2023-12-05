package buttons;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
/**
 * takes you to the profile page. Located in bottom-right of app. Action method in DefaultFrame
 * @author icebe
 *
 */
public class ProfileButton extends JButton{

	public ProfileButton(){
	ImageIcon profileImage= new ImageIcon("src\\icons\\icons8-user-45.png");

	Image originalProfileImage = profileImage.getImage();
	Image resizedProfileImage = originalProfileImage.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
	ImageIcon resizedProfileIcon = new ImageIcon(resizedProfileImage);
	this.setBounds(280,513,140,50);
	this.setIcon(resizedProfileIcon);
	this.setBackground(Color.black);
	this.setFocusable(false);
	}



}
