package frames;
import java.awt.Color;



import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import buttons.DiscoverButton;
import buttons.ProfileButton;
import buttons.ProfilePostButton;
import buttons.SearchButton;
import managers.PhotoManager;
import managers.ProgramManager;
import panels.AddPhotoPanel;
import panels.DiscoverPanel;
import panels.LoginPanel;
import panels.PostPanel;
import panels.ProfilePanel;
import panels.ProfilePostPanel;
import panels.SearchPanel;
import panels.SettingsPanel;
import panels.SignupPanel;
import panels.VisitPanel;
import photo.Photo;
import panels.EditPanel;
import users.FreeUser;
import users.HobbyistUser;
import users.ProfessionalUser;
import users.User;

/**
 * main frame. includes most of buttons action methods.
 * @author icebe
 * 
 */
public class DefaultFrame extends JFrame {
	
	public static String username;
	
	private ProgramManager programManager=new ProgramManager();
	private PhotoManager photoManager=new PhotoManager();
	
	private DiscoverPanel discoverPanel= new DiscoverPanel();
	private SearchPanel searchPanel=new SearchPanel();
	private ProfilePanel profilePanel=new ProfilePanel();
	private LoginPanel loginPanel=new LoginPanel();
	private SignupPanel signupPanel=new SignupPanel();
	private SettingsPanel settingsPanel=new SettingsPanel();
	private AddPhotoPanel addPhotoPanel=new AddPhotoPanel();
	
	private DiscoverButton discoverButton=new DiscoverButton();
	private SearchButton searchButton=new SearchButton();
	private ProfileButton profileButton=new ProfileButton();
	
	private File settingsSelectedImageFile;
	private File uploadSelectedImageFile;
	

	public DefaultFrame() {
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(420,600); //width-height
		this.setTitle("PhotoGram");
		//this.getContentPane().setBackground(new Color(150,100,100));
		this.setLayout(null);
		this.setVisible(true);
	
		
		loginPanel.setVisible(true);
		searchPanel.setVisible(false);
		signupPanel.setVisible(false);
		profilePanel.setVisible(false);
		discoverPanel.setVisible(false);
		settingsPanel.setVisible(false);
		addPhotoPanel.setVisible(false);

		discoverButton.setVisible(false);
		searchButton.setVisible(false);
		profileButton.setVisible(false);
		
		//login page buttons

		/**
		 * sends you to sign up page
		 */
		loginPanel.signupButton.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
            	loginPanel.setVisible(false);
            	loginPanel.loginErrorLabel.setVisible(false);
            	signupPanel.setVisible(true);
            }
        });
		
		/**
		 * if infos are appropriate allows you log in
		 */
		loginPanel.loginButton.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
				username=loginPanel.usernameTextField.getText();
				String password=loginPanel.passwordTextField.getText();
				if(programManager.validateUser(username, password)) {
	            	discoverPanel.setVisible(true);
	            	discoverButton.setVisible(true);
	            	searchButton.setVisible(true);
	            	profileButton.setVisible(true);
	            	loginPanel.setVisible(false);
	            	loginPanel.loginErrorLabel.setVisible(false);
	            	discoverPanel.loadPosts();
	            	
				}else {
					loginPanel.loginErrorLabel.setText("Username or password is wrong.");
					loginPanel.loginErrorLabel.setVisible(true);
					
				}

            }
        });
		
		//signup page buttons

		/**
		 * allows you to go back login page
		 */
		signupPanel.backToLoginButton.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
            	loginPanel.setVisible(true);
            	signupPanel.setVisible(false);
            }
        });

		/**
		 * creates new user if infos are appropriate
		 */
		signupPanel.signedUpButton.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
				String username= signupPanel.usernameTextField.getText();
				if(programManager.validateUsername(username)) {
					String name= signupPanel.nameTextField.getText();
					String surname= signupPanel.surnameTextField.getText();
					String age= signupPanel.ageTextField.getText();
					String email= signupPanel.emailTextField.getText();
					String password= signupPanel.passwordTextField.getText();
					String rank= (String) signupPanel.rankComboBox.getSelectedItem();
					if (name.isEmpty() || surname.isEmpty() || age.isEmpty() || email.isEmpty() || password.isEmpty()) {
					    // all fields are required
					     signupPanel.errorLabel.setText("Please enter all fields with proper values");
					} else if (!age.matches("\\d+")) {
					    // age should be a number
						signupPanel.errorLabel.setText("Age should be a number");
					} else if (!email.contains("@")) {
					    //  email should contain "@"
						signupPanel.errorLabel.setText("Please enter a proper email address");
					} else if (password.length() < 3 || password.length() > 8) {
					    //  password should be between 2 and 8 characters
						signupPanel.errorLabel.setText("Your password should be between 2-8 characters");
					}else {
						if(rank.equals("free")) {
							programManager.addUser(new FreeUser(username,name,surname,password,age,email,rank));
						}else if(rank.equals("hobbyist")) {
							programManager.addUser(new HobbyistUser(username,name,surname,password,age,email,rank));
						}else if(rank.equals("professional")) {
							programManager.addUser(new ProfessionalUser(username,name,surname,password,age,email,rank));
						}
		            	signupPanel.setVisible(false);
		            	loginPanel.setVisible(true);
					}


				}else {
					signupPanel.errorLabel.setText("Username already taken.");
				}

            }
        });
		
		// menu bar buttons

		/**
		 * allows you to go discover page
		 */
		discoverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	discoverPanel.setVisible(true);
                profilePanel.setVisible(false);
                searchPanel.setVisible(false); 
            }
        });

		/**
		 * allows you to go search page
		 */
		searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchPanel.setVisible(true);
                profilePanel.setVisible(false);
                discoverPanel.setVisible(false);
                
            }
        });

		/**
		 * allows you to go profile page
		 */
		profileButton.addActionListener(new ActionListener() {
			private boolean isFirstTime = true;
            @Override
            public void actionPerformed(ActionEvent e) {
            	discoverPanel.updateUI();
                profilePanel.setVisible(true);
                searchPanel.setVisible(false);
                discoverPanel.setVisible(false);
                String usernameText= "@" + programManager.userEntered(username).getUsername();
                String infoText= programManager.userEntered(username).getName() + " " + programManager.userEntered(username).getSurname() + ", " + programManager.userEntered(username).getAge();
                String rankText= programManager.userEntered(username).getRank();
                if (isFirstTime) {
                    profilePanel.userPhotos(programManager.userEntered(username).getUsername());
                    isFirstTime = false; 
                }
                profilePanel.usernameLabel.setText(usernameText);
                profilePanel.personalInfoLabel.setText(infoText);
                profilePanel.rankLabel.setText(rankText);
                profilePanel.profilPhotoLabel.setIcon(programManager.setImageBounds(programManager.userEntered(username).getProfilePhoto(), 100, 100));
                
            }
        });
		
		// profile page buttons
		
		/**
		 * allows user to go settings page
		 */
		profilePanel.settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	username=loginPanel.usernameTextField.getText();
				String password=loginPanel.passwordTextField.getText();
				
	            profilePanel.setVisible(false);
	            discoverButton.setVisible(false);
	            searchButton.setVisible(false);
	            profileButton.setVisible(false);
	            settingsPanel.setVisible(true);
	            settingsPanel.usernameShowLabel.setText(username);
	            settingsPanel.currentEmailLabel.setText("Current Email:   "+ ProgramManager.userEntered(username).getEmail());
	            settingsPanel.currentPasswordLabel.setText("Current Password:   "+ProgramManager.userEntered(username).getPassword());
            }
        });
		
		/**
		 * allow user to go upload post page
		 */
		profilePanel.uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
	            profilePanel.setVisible(false);
	            discoverButton.setVisible(false);
	            searchButton.setVisible(false);
	            profileButton.setVisible(false);
	            addPhotoPanel.setVisible(true);
	            
            }
        });
		
		// settings page buttons
		
		/**
		 * allows user to go back profile page
		 */
		settingsPanel.backToProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                profilePanel.setVisible(true);
                settingsPanel.setVisible(false);
	            discoverButton.setVisible(true);
	            searchButton.setVisible(true);
	            profileButton.setVisible(true);
                String usernameText= "@" + programManager.userEntered(username).getUsername();
                String infoText= programManager.userEntered(username).getName() + " " + programManager.userEntered(username).getSurname() + ", " + programManager.userEntered(username).getAge();
                String rankText= programManager.userEntered(username).getRank();              
                ImageIcon profilePhotoIcon= programManager.setImageBounds(programManager.userEntered(username).getProfilePhoto(), 100,100);
                profilePanel.usernameLabel.setText(usernameText);
                profilePanel.personalInfoLabel.setText(infoText);
                profilePanel.rankLabel.setText(rankText);
                profilePanel.profilPhotoLabel.setIcon(profilePhotoIcon);
            	
            }
        });
		
		/**
		 * allows the user to select the desired photo
		 */
		settingsPanel.setProfilePhotoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "jpeg", "png", "gif");
            	JFileChooser fileChooser = new JFileChooser();
            	fileChooser.setFileFilter(filter);
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    settingsSelectedImageFile = fileChooser.getSelectedFile();
                                  
                }
            }
        });
		
		/**
		 * sets users name with newName and save it
		 */
		settingsPanel.applyNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(settingsPanel.setNameTextField.getText().length()>0 && settingsPanel.setNameTextField.getText().length()<=10) {
            		String newName= settingsPanel.setNameTextField.getText();
                	username=loginPanel.usernameTextField.getText();
            		programManager.userEntered(username).setName(newName);				  		
            		programManager.changeInfo(1, username, newName);
            	}else {
            		settingsPanel.errorLabel.setText("Please enter a valid name under 10 characters.");
            	}          	
            }
        });
		/**
		 * sets users surname with newSurname and save it
		 */
		settingsPanel.applySurnameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(settingsPanel.setSurnameTextField.getText().length()>0 && settingsPanel.setSurnameTextField.getText().length()<=10) {
            		String newSurname= settingsPanel.setSurnameTextField.getText();
                	username=loginPanel.usernameTextField.getText();
            		programManager.userEntered(username).setSurname(newSurname);
            		programManager.changeInfo(2, username, newSurname);            		
            	}
            	else {
            		settingsPanel.errorLabel.setText("Please enter a valid surname under 10 characters.");
            	}
            }
        });
		/**
		 * sets users age with newAge and save it
		 */
		settingsPanel.applyAgeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(settingsPanel.setAgeTextField.getText().length()>0 && settingsPanel.setAgeTextField.getText().matches("\\d+")) {
            		String newAge= settingsPanel.setAgeTextField.getText();
                	username=loginPanel.usernameTextField.getText();
            		programManager.userEntered(username).setAge(newAge);				    		
            		programManager.changeInfo(4, username, newAge);
            	} else {
            		settingsPanel.errorLabel.setText("Please enter a valid age.");
            	}
            }
        });
		/**
		 * sets users password with newPassword and save it
		 */
		settingsPanel.applyPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(settingsPanel.setPasswordTextField.getText().length()>2 && settingsPanel.setPasswordTextField.getText().length()<=8) {
            		String newPassword= settingsPanel.setPasswordTextField.getText();
                	username=loginPanel.usernameTextField.getText();
            		programManager.userEntered(username).setName(newPassword);				    		
            		programManager.changeInfo(3, username, newPassword);
            		settingsPanel.currentPasswordLabel.setText("Current Password:   "+ newPassword);
            	}else {
            		settingsPanel.errorLabel.setText("Please enter a valid password between 2-8 characters.");
            	}
            }
        });
		/**
		 * sets users email with newEmail and save it
		 */
		
		settingsPanel.applyEmailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(settingsPanel.setEmailTextField.getText().length()>3 && settingsPanel.setEmailTextField.getText().contains("@")) {
            		String newEmail= settingsPanel.setEmailTextField.getText();
                	username=loginPanel.usernameTextField.getText();
            		programManager.userEntered(username).setName(newEmail);		
            		programManager.changeInfo(5, username, newEmail);
            		settingsPanel.currentEmailLabel.setText("Current Email:   "+ newEmail);
            	}else {
            		settingsPanel.errorLabel.setText("Email should contain @ and more than 3 characters.");
            	} 
            }
        });
		
		/**
		 * sets users rank with newRank and save it
		 */
		settingsPanel.applyRankButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String newRank= (String) settingsPanel.rankComboBox.getSelectedItem();
            	username=loginPanel.usernameTextField.getText();
            	programManager.userEntered(username).setRank(newRank);
            	programManager.changeInfo(6, username, newRank);
            }
        });
		/**
		 * sets users profile photo with new profile photo and save it
		 */
		settingsPanel.applyProfilePhotoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(settingsSelectedImageFile != null) {
            		username=loginPanel.usernameTextField.getText();
            		Image image = null;
					try {
						image = ImageIO.read(settingsSelectedImageFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
            	    ImageIcon imageIcon = new ImageIcon(image);
            		programManager.userEntered(username).setProfilePhoto(imageIcon);
            		programManager.changeInfo(7, username, settingsSelectedImageFile.getAbsolutePath());
            	}else {
            		settingsPanel.errorLabel.setText("Please select an image.");
            	}
            }
        });
		
		// upload photo page buttons
				
		
		/**
		 * uploads the post if it meets the conditions
		 */
		addPhotoPanel.applyUploadPhotoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(uploadSelectedImageFile != null) {
            		username=loginPanel.usernameTextField.getText();
            		String privOrPublic;
            		if(addPhotoPanel.descriptionField.getText().length()!=0) {
            			String description=addPhotoPanel.descriptionField.getText();
            			
            			if(addPhotoPanel.privateComboBox.getSelectedItem().equals("open")){
            				privOrPublic="open";
            			}else {
            				privOrPublic="private";
            			}
            			
                	    photoManager.addUserPhoto(username,uploadSelectedImageFile,privOrPublic,description);
                	    PhotoManager.photoReader();
                	    profilePanel.cleanProfile();
                	    profilePanel.userPhotos(username);
                	    discoverPanel.cleanPosts();            	    
                	    discoverPanel.loadPosts();
            			}else {
            			addPhotoPanel.errorLabel.setText("Please enter a description");
            		}
            	}else {
            		addPhotoPanel.errorLabel.setText("Please select an image");
            	}
            }
        });
		
		/**
		 * allows the user select desired photo for post
		 */
		addPhotoPanel.setUploadPhotoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "jpeg", "png", "gif");
            	JFileChooser fileChooser = new JFileChooser();
            	fileChooser.setFileFilter(filter);
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    uploadSelectedImageFile = fileChooser.getSelectedFile();
                    
                    
                }
            }
        });
		/**
		 * allows user to go back profile page
		 */
		addPhotoPanel.backToProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                profilePanel.setVisible(true);
                addPhotoPanel.setVisible(false);
	            discoverButton.setVisible(true);
	            searchButton.setVisible(true);
	            profileButton.setVisible(true);
                String usernameText= "@" + programManager.userEntered(username).getUsername();
                String infoText= programManager.userEntered(username).getName() + " " + programManager.userEntered(username).getSurname() + ", " + programManager.userEntered(username).getAge();
                String rankText= programManager.userEntered(username).getRank();
                ImageIcon profilePhotoIcon= programManager.setImageBounds(programManager.userEntered(username).getProfilePhoto(), 100,100);
                profilePanel.usernameLabel.setText(usernameText);
                profilePanel.personalInfoLabel.setText(infoText);
                profilePanel.rankLabel.setText(rankText);
                profilePanel.profilPhotoLabel.setIcon(profilePhotoIcon);
            }
        });
		
		
		

		this.add(discoverButton);
		this.add(profileButton);
		this.add(searchButton);
		this.add(profilePanel);
		this.add(discoverPanel);
		this.add(searchPanel);
		this.add(loginPanel);
		this.add(signupPanel);
		this.add(settingsPanel);
		this.add(addPhotoPanel);
	}
	

}