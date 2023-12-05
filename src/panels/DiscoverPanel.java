
package panels;

import java.awt.Color;
import java.awt.Component;

import managers.PhotoManager;
import managers.ProgramManager;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import panels.PostPanel;
import users.User;
import photo.Photo;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.net.ssl.KeyManagerFactorySpi;
/**
 * discover page panel. It allows the desired posts to shown and scroll up and down.
 * @author icebe
 *
 */
public class DiscoverPanel extends JPanel {
	
	JScrollPane scrollPane;
	JPanel existingPanel = new JPanel();
	

	
	public DiscoverPanel() {
		this.setBackground(Color.black);
		this.setBounds(0, -5, 420, 518);
		this.setLayout(new FlowLayout());
		existingPanel.setLayout(new BoxLayout(existingPanel, BoxLayout.Y_AXIS));
		
		JScrollPane scrollPane = new JScrollPane(existingPanel);
		scrollPane.setPreferredSize(new Dimension(420, 550));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setBackground(Color.DARK_GRAY);
        scrollPane.getVerticalScrollBar().setForeground(Color.WHITE);
         
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		this.add(scrollPane);	
		
		
	}
	/**
	 * loads the posts that going to be shown in discover page    
	 */
    public void loadPosts() {
    	for(String u:PhotoManager.getUserPhotos().keySet()) {
    		if(ProgramManager.userEntered(u).getPhotos()!=null) {
    			for(Photo photo:PhotoManager.getUserPhotos().get(u)) {
    				if(photo.getPriv().equals("open")) {
                	PostPanel postPanel= new PostPanel(photo,u);
                	postPanel.setPreferredSize(new Dimension(360, 480));
                	existingPanel.add(postPanel);
    				}
    			}
    		}
    	}
    }
    /**
     * cleans the posts on discover page (useful for algorithm)
     */
    public void cleanPosts() {
    	Component[] components= existingPanel.getComponents();
		for(Component component:components) {
			if (component instanceof PostPanel) {
			existingPanel.remove(component);
			}
		}
    }
    
}
