package managers;


import java.awt.Color;

import java.awt.Dimension;
import java.awt.Image;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import panels.SearchBarPanel;
import panels.SearchPanel;
import users.User;
/**
 * mostly used for algorithm related method
 * @author icebe
 *
 */
public class ProgramManager {
	private static HashMap<String, User> users=new HashMap<>();

	public ProgramManager() {
		try (BufferedReader reader = new BufferedReader(new FileReader("src\\users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 7) {
                    String username = parts[0];
                    String name = parts[1];
                    String surname = parts[2];
                    String password = parts[3];
                    String age = parts[4];
                    String email = parts[5];
                    String rank= parts[6];
                    String photoAddress= parts[7];

                    User user = new User(username, name, surname, password, age, email,rank);
                    user.setProfilePhoto(new ImageIcon(photoAddress));
                    users.put(username, user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		
		
		
	}
	/**
	 * checks for if that username already taken or not
	 * @param username: User username
	 * @return
	 */
	public boolean validateUsername(String username) {
		for(String un:users.keySet()) {
			if(un.equals(username)) {
				return false;
			}
		}
		return true;
	}
	/**
	 * adds user to related hashmap
	 * @param user: user
	 */
	public void addUser(User user) {
		users.put(user.getUsername(), user);
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("src\\users.txt", true))) {
			user.setProfilePhoto(new ImageIcon("src\\\\DefaultProfilPhoto.jpg"));
            String userLine = "\n" + user.getUsername() + "," + user.getName() + "," + user.getSurname() + "," + user.getPassword() + "," + user.getAge() + "," + user.getEmail() + "," + user.getRank() + "," + user.getProfilePhoto();
            writer.write(userLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("src\\photos.txt", true))) {
            String userLine = "\n" + user.getUsername();
            writer.write(userLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	/**
	 * for log in, checks for username and password are correct or not
	 * @param username: User username
	 * @param password: User password
	 * @return
	 */
	public boolean validateUser(String username, String password) {
		for(User u:users.values()) {
			if(u.getUsername().equals(username)) {
				if(u.getPassword().equals(password)) {
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * returns the user that which has the given username
	 * @param username: User username
	 * @return User
	 */
	public static User userEntered(String username) {
		return users.get(username);
	}
	/**
	 * for setting page, changes info of user on txt files. 
	 * @param part: related part of the txt file line
	 * @param username: User username
	 * @param newInfo: the new info for change
	 */
	public void changeInfo(int part,String username, String newInfo) {
		try (BufferedReader reader = new BufferedReader(new FileReader("src\\users.txt"))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith(username)) {
                	String[] parts= line.split(",");
                	parts[part] = newInfo;
                    line = String.join(",", parts);			
                }
                stringBuilder.append(line).append(System.lineSeparator());
            }

                try (BufferedWriter writer = new BufferedWriter(new FileWriter("src\\users.txt"))) {
                    writer.write(stringBuilder.toString());
                }
           
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	/**
	 * sets imageIcon scale for other type images such as post image, profil photo
	 * @param imageIcon : related image
	 * @param x: images new width
	 * @param y: images new height
	 * @return imageIcon
	 */
	public static ImageIcon setImageBounds(ImageIcon imageIcon, int x,int y) {
		if(imageIcon==null) {
			imageIcon= new ImageIcon("src\\DefaultProfilPhoto.jpg");
		}
		Image originalImage = imageIcon.getImage();
		Image resizedImage = originalImage.getScaledInstance(x, y, Image.SCALE_SMOOTH); 
		ImageIcon resizedIcon = new ImageIcon(resizedImage); 
		
		return resizedIcon;
	}

}
