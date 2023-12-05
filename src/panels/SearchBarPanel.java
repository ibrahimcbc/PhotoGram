package panels;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import managers.ProgramManager;
/**
 * 
 * @author icebe
 *
 */
public class SearchBarPanel extends JPanel {
	
	JButton searchedUsername = new JButton();
	
	JLabel searchedRank = new JLabel();
	JLabel searchedUserPhoto= new JLabel();
	
	
	/**
	 * bar of user that searched
	 * @param username: the username of user in this bar
	 */
	public SearchBarPanel(String username) {
		this.setLayout(null);
		this.setBackground(Color.white);		

		searchedUserPhoto.setBounds(3, 5, 50, 50);
		searchedUserPhoto.setIcon(ProgramManager.setImageBounds(ProgramManager.userEntered(username).getProfilePhoto(), 50, 50));
		
		searchedUsername.setBounds(55, 5, 200, 25);
		searchedUsername.setForeground(Color.black);
		searchedUsername.setText("@" + username);
		searchedUsername.setBackground(this.getBackground());
		
		searchedRank.setText(ProgramManager.userEntered(username).getRank());
		searchedRank.setBounds(55,32,200,25);
		searchedRank.setBackground(this.getBackground());
		searchedRank.setForeground(Color.black);

		this.add(searchedUserPhoto);
		this.add(searchedUsername);
		this.add(searchedRank);
		/**
		 * sends user to the visitor page of user that searched
		 */
		searchedUsername.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VisitPanel visitPanel=new VisitPanel(username);
                JFrame visitPage=new JFrame();
                visitPage.setResizable(false);
                visitPage.setSize(420,550); //width-height
                visitPage.setTitle(username);
                visitPage.getContentPane().setBackground(Color.gray);
                visitPage.setLayout(null);
                visitPage.setVisible(true);
                visitPage.add(visitPanel);
                
            }
        });
		
		
	}
}
