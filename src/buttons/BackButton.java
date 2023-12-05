package buttons;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
/**
 * a parent class for the buttons which I used for going back
 * @author icebe
 *
 */
public class BackButton extends JButton {

	public BackButton() {
		ImageIcon searchImage= new ImageIcon("src\\icons\\back.png");

		Image originalImage = searchImage.getImage();
		Image resizedImage = originalImage.getScaledInstance(50, 25, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon = new ImageIcon(resizedImage);
		this.setIcon(resizedIcon);
		this.setFocusable(false);
	}
}
