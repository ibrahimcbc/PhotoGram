package panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import managers.ProgramManager;
/**
 * for searching users
 * @author icebe
 *
 */
public class SearchPanel extends JPanel{
	
	JScrollPane scrollPane;
	public JTextField searchBar=new JTextField();
	public JButton searchButton=new JButton("Search");
	public JPanel existingPanel = new JPanel();
	JPanel containerPanel= new JPanel(new FlowLayout());
		
	public SearchPanel() {
		this.setLayout(null);
		this.setBackground(Color.darkGray);
		this.setBounds(0, 0, 420, 515);
		
		
		containerPanel.setBounds(30,100,360 ,400);
		containerPanel.setBackground(Color.black);
		
		existingPanel.setBackground(Color.black);
		existingPanel.setLayout(new BoxLayout(existingPanel, BoxLayout.Y_AXIS));
		
		
		searchBar.setBounds(30,15,280,60);
		searchButton.setBounds(310,15 ,80 ,60);
		
		Font font = searchBar.getFont();
		Font newFont = font.deriveFont(22f);
		searchBar.setFont(newFont);
		
		JScrollPane scrollPane = new JScrollPane(existingPanel);
		scrollPane.setPreferredSize(new Dimension(360,400 ));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setBackground(Color.DARK_GRAY);
        scrollPane.getVerticalScrollBar().setForeground(Color.WHITE);
        
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        /**
         * searches the users and list them
         */
		searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String key= searchBar.getText();
            	if(key.isEmpty()) {
            		return;
            	}else {
            		cleanSearch();
            		searchUser(key);
            		containerPanel.updateUI();
            	}
                
            }
        });
		
		containerPanel.add(scrollPane);
		this.add(containerPanel);
		this.add(searchBar);
		this.add(searchButton);
		

	}
	/**
	 * cleans the results for each search
	 */
	public void cleanSearch() {
		Component[] components= existingPanel.getComponents();
		for(Component component:components) {
			if (component instanceof JPanel) {
			existingPanel.remove(component);
			}
		}
	}
	/**
	 * searches the given word for each user. If user includes that word user will be listed
	 * @param u
	 */
	public void searchUser(String u) {
		try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\icebe\\eclipse-workspace\\PhotoCloud\\src\\users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 1) {
                    String username = parts[0];
                    if(username.contains(u)) {
                    SearchBarPanel searchBarPanel= new SearchBarPanel(username);
                    JPanel blackPanel= new JPanel();
                    blackPanel.setBackground(Color.black);
                    blackPanel.setPreferredSize(new Dimension(231,10));
                    searchBarPanel.setPreferredSize(new Dimension(231, 62));
                    existingPanel.add(searchBarPanel);
                    existingPanel.add(blackPanel);
                    }
                         
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
}
