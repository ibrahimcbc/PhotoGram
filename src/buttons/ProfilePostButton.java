package buttons;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import managers.ProgramManager;
import panels.ProfilePostPanel;
import panels.VisitPanel;
import photo.Photo;

public class ProfilePostButton extends JButton {
	/**
	 * the photo-buttons in profile page. whenever clicked opens a new frame with detailed post info
	 * @param username - username of the user that logged in
	 * @param photo - photo information of post
	 */
	public ProfilePostButton(String username,Photo photo) {
		ImageIcon image=new ImageIcon(photo.getAddress());
		this.setIcon(ProgramManager.setImageBounds(image,100,100));
	
	
	addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ProfilePostPanel profilePostPanel=new ProfilePostPanel(username, photo);
                JFrame frame=new JFrame(); 
                profilePostPanel.setBounds(0,0,450,500);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setPreferredSize(new Dimension(450,500));
                frame.setResizable(false);
                frame.add(profilePostPanel);
                frame.pack();
                frame.setVisible(true);
            }
        });
}
	
}
