package panels;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import managers.ProgramManager;

public class PhotoPanel extends JPanel{
	/**
	 * photo panel for clicked images such as profile photos and posts on discover page
	 * @param image: ImageIcon
	 */
	public PhotoPanel(ImageIcon image) {
		JLabel imageLabel=new JLabel();
		this.setSize(new Dimension(400,400));
		
		imageLabel.setIcon(ProgramManager.setImageBounds(image, 400, 400));
		this.add(imageLabel);
	}
}
