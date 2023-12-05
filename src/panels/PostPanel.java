
package panels;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import managers.ProgramManager;
import photo.Photo;
import managers.PhotoManager;
import frames.DefaultFrame;

import java.util.Random;

public class PostPanel extends JPanel {
	
	JButton downloadButton=new JButton();	
	JButton usernameShared = new JButton();
	JButton commentsButton= new JButton();	
	JButton postPhoto=new JButton();
	JButton postLikeButton = new JButton();
	JButton postDislikeButton = new JButton();
	public JButton editPhotoButton= new JButton();
	
	JLabel rankShared = new JLabel();
	JButton postOwnerPhoto= new JButton();
	JLabel postLikeCountLabel = new JLabel();
	JLabel postDislikeCountLabel = new JLabel();
	JLabel postdescription=new JLabel();
	
	ImageIcon likeButtonImage= new ImageIcon("src\\icons\\icons8-facebook-like-60.png");
	ImageIcon dislikeButtonImage=new ImageIcon("src\\icons\\icons8-thumbs-down-60.png");
	ImageIcon downloadButtonImage=new ImageIcon("src\\icons\\icons8-download-30.png");
	ImageIcon editPhotoButtonImage=new ImageIcon("src\\icons\\icons8-edit-30.png");
	
	
	Random random=new Random();
	
	int postLikeCount= random.nextInt(100);
	int postDislikeCount= random.nextInt(20);
	int DislikeButtonPressed=0;
	int LikeButtonPressed=0;
	
	JTextField commentField= new JTextField();
	JLabel comment= new JLabel();
	JButton applyComment=new JButton();
	
	/**
	 * post panel for the posts on discover page
	 * @param photo: post photo (related photo infos)
	 * @param username: username of the user who shared the post
	 */
	public PostPanel(Photo photo,String username) {
		this.setLayout(null);
		this.setBackground(Color.black);
		
		ImageIcon image= new ImageIcon(photo.getAddress());
		File imageFile= new File(photo.getAddress());
		
		postdescription.setBounds(15,430 ,200,30);
		postdescription.setText(username+": "+photo.getdescription());
		postdescription.setForeground(Color.white);
		
		downloadButton.setBounds(300,15 ,30 ,30);
		downloadButton.setIcon(ProgramManager.setImageBounds(downloadButtonImage, 30, 30));
		downloadButton.setBackground(this.getBackground());
		
		editPhotoButton.setBounds(340,15 ,30 ,30);
		editPhotoButton.setIcon(ProgramManager.setImageBounds(editPhotoButtonImage, 30, 30));
		editPhotoButton.setBackground(this.getBackground());
		
		postLikeButton.setIcon(ProgramManager.setImageBounds(likeButtonImage, 40, 30));
		postDislikeButton.setIcon(ProgramManager.setImageBounds(dislikeButtonImage, 40, 30));
		
		postOwnerPhoto.setBounds(15, 15, 30, 30);
		postOwnerPhoto.setIcon(ProgramManager.setImageBounds(ProgramManager.userEntered(username).getProfilePhoto(), 30, 30));
		
		usernameShared.setBounds(50, 15, 100, 15);
		usernameShared.setFocusable(false);
		usernameShared.setForeground(Color.white);
		usernameShared.setText(username);
		usernameShared.setBackground(this.getBackground());
		
		rankShared.setText(ProgramManager.userEntered(username).getRank());
		rankShared.setBounds(50,30,70,15);
		rankShared.setBackground(this.getBackground());
		rankShared.setForeground(Color.white);
		
		postPhoto.setBounds(60, 50, 320, 320 );
		postPhoto.setIcon(ProgramManager.setImageBounds(image, 320, 320));
		
		postLikeButton.setBounds(15,396,40,30);
		postLikeButton.setForeground(Color.white);
		postLikeButton.setBackground(this.getBackground());
		postLikeButton.setFocusable(false);
		
		postLikeCountLabel.setText(String.valueOf(postLikeCount));
		postLikeCountLabel.setBounds(60,396 ,40, 30);
		postLikeCountLabel.setBackground(this.getBackground());
		postLikeCountLabel.setForeground(Color.white);
		postLikeCountLabel.setHorizontalTextPosition(JButton.CENTER);

		postDislikeButton.setBounds(105,396,40,30);
		postDislikeButton.setForeground(Color.white);
		postDislikeButton.setBackground(this.getBackground());	
		postDislikeButton.setFocusable(false);
		
		postDislikeCountLabel.setText(String.valueOf(postDislikeCount));
		postDislikeCountLabel.setBounds(150,396,40,30);
		postDislikeCountLabel.setBackground(this.getBackground());
		postDislikeCountLabel.setForeground(Color.white);
        postDislikeCountLabel.setHorizontalTextPosition(JButton.CENTER);
		
		commentsButton.setBounds(250, 396, 150, 25);
		commentsButton.setText("Comments");
		commentsButton.setForeground(Color.white);
		commentsButton.setBackground(this.getBackground());
		commentsButton.setFocusable(false);
		
		commentField.setBounds(15,430 ,200,30);
		applyComment.setBounds(220,430 , 100, 30);
		applyComment.setBackground(this.getBackground());
		applyComment.setForeground(Color.white);
		applyComment.setText("Comment");
		applyComment.setFocusable(false);
		
		comment.setBounds(15,460,300,30);
		comment.setBackground(this.getBackground());
		comment.setForeground(Color.white);
		commentField.setVisible(false);
		applyComment.setVisible(false);
		comment.setVisible(false);
		
		/**
		 * downloads the post on downloadedPhotos folder in src
		 */
		downloadButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // Handle download button click
	            	try {
	                    URI imageUri = new File(photo.getAddress()).toURI();
	                    URL imageUrl = imageUri.toURL();
	                    String fileName = imageFile.getName();
	                    
	                    String savePath = "src\\downloadedPhotos\\" + fileName;

	                    try (InputStream in = new BufferedInputStream(imageUrl.openStream());
	                            FileOutputStream fileOutputStream = new FileOutputStream(savePath)) {
	                        byte[] dataBuffer = new byte[1024];
	                        int bytesRead;
	                        while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
	                            fileOutputStream.write(dataBuffer, 0, bytesRead);
	                        }

	                        SwingUtilities.invokeLater(() -> {
	                            JOptionPane.showMessageDialog(null, "Image downloaded successfully!");
	                        });
	                    }
	                } catch (IOException ex) {
	                	 SwingUtilities.invokeLater(() -> {
	                         JOptionPane.showMessageDialog(null, "Error downloading image: " + ex.getMessage(),
	                                 "Download Error", JOptionPane.ERROR_MESSAGE);
	                     });
	            }
	        }});
		/**
		 * opens PhotoPanel for the user profile photo
		 */
		postOwnerPhoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame=new JFrame();
                PhotoPanel panel=new PhotoPanel(ProgramManager.userEntered(username).getProfilePhoto());
                frame.setResizable(false);
                frame.setSize(400,400);
                frame.getContentPane().setBackground(Color.gray);
            	frame.setLayout(null);
            	frame.setVisible(true);
                frame.add(panel);
                frame.setVisible(true);
            }
        });
		/**
		 * for writing comment on post		
		 */
		applyComment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comment.setText("You: "+commentField.getText());
                commentField.setVisible(false);
                applyComment.setVisible(false);
                comment.setVisible(true);
            }
        });
		/**
		 * opens comment section
		 */
		commentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commentsButton.setVisible(false);
                commentField.setVisible(true);
                applyComment.setVisible(true);
            }
        });
		/**
		 * increase dislike count
		 */
		postDislikeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(DislikeButtonPressed % 2==0) {
            		postDislikeCount++;
            		postDislikeCountLabel.setText(String.valueOf(postDislikeCount));
            	}else {
            		postDislikeCount--;
            		postDislikeCountLabel.setText(String.valueOf(postDislikeCount));
            	}
            	DislikeButtonPressed++;
            }
        });
		/**
		 * increase like count
		 */
		postLikeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(LikeButtonPressed % 2==0) {
            		postLikeCount++;
            		postLikeCountLabel.setText(String.valueOf(postLikeCount));
            		
            	}else {
            		postLikeCount--;
            		postLikeCountLabel.setText(String.valueOf(postLikeCount));
            	}
            	LikeButtonPressed++;
            }
        });
		/**
		 * opens PhotoPanel for the post image
		 */
		postPhoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	JFrame frame=new JFrame();
                PhotoPanel panel=new PhotoPanel(image);
                frame.setResizable(false);
                frame.setSize(400,400);
                frame.getContentPane().setBackground(Color.gray);
            	frame.setLayout(null);
            	frame.setVisible(true);
                frame.add(panel);
                frame.setVisible(true);
            }
        });
		/**
		 * sends user to the visitor page of user that shared post
		 */
		usernameShared.addActionListener(new ActionListener() {
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
		/**
		 * for editing the post image
		 */
		editPhotoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	EditPanel editPanel=new EditPanel(DefaultFrame.username,image);
            	JFrame editPage= new JFrame();
            	editPage.setResizable(false);
            	editPage.setSize(1000,700);
            	editPage.setTitle("Edit Photo");
            	editPage.getContentPane().setBackground(Color.gray);
            	editPage.setLayout(null);
            	editPage.setVisible(true);
            	editPage.add(editPanel);
            	
            	
            }
        });
	
		
		
		this.add(commentsButton);
		this.add(postDislikeButton);
		this.add(postLikeButton);
		this.add(postOwnerPhoto);
		this.add(usernameShared);
		this.add(rankShared);
		this.add(postPhoto);
		this.add(postdescription);
		this.add(postDislikeCountLabel);
		this.add(postLikeCountLabel);
		this.add(applyComment);
		this.add(comment);
		this.add(commentField);
		this.add(downloadButton);
		this.add(editPhotoButton);
		
		
	}

}


