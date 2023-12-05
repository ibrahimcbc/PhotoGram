package buttons;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;


/**
 * takes you to the search page. Located in bottom-center of app. Action method in DefaultFrame
 * @author icebe
 *
 */
public class SearchButton extends JButton{

	public SearchButton(){
	ImageIcon searchImage= new ImageIcon("src\\icons\\icons8-search-45.png");

	Image originalSearchImage = searchImage.getImage();
	Image resizedSearchImage = originalSearchImage.getScaledInstance(50, 45, Image.SCALE_SMOOTH);
	ImageIcon resizedSearchIcon = new ImageIcon(resizedSearchImage);
	this.setBounds(140,513,140,50);
	this.setIcon(resizedSearchIcon);
	this.setBackground(Color.black);
	this.setFocusable(false);
	}
}
