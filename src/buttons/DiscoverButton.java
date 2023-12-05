package buttons;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
/**
 * takes you to the discover page. Located in bottom-left of app. Action method in DefaultFrame
 * @author icebe
 *
 */
public class DiscoverButton extends JButton {

	public DiscoverButton() {

		ImageIcon discoverImage= new ImageIcon("src\\icons\\icons8-home-45.png");
		Image originalDiscoverImage = discoverImage.getImage();
		Image resizedDiscoverImage = originalDiscoverImage.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
		ImageIcon resizedDiscoverIcon = new ImageIcon(resizedDiscoverImage);
		this.setBounds(0,513,140,50);
		this.setIcon(resizedDiscoverIcon);
		this.setBackground(Color.black);
		this.setFocusable(false);

	}

}
