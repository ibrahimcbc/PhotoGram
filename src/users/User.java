package users;

import java.io.BufferedReader;


import managers.PhotoManager;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import photo.Photo;
import javax.swing.ImageIcon;


public class User {
	String username;
	String password;
	String name;
	String surname;
	String age;
	String email;
	String rank;
	ImageIcon profilePhoto; 
	ArrayList<Photo> photos;

	 /**
	  *  User class for each user
	  * @param username: User username
	  * @param name: User name
	  * @param surname: User surname
	  * @param password: User password
	  * @param age: User age
	  * @param email: User email
	  * @param rank: User rank
	  */
	public User(String username, String name, String surname, String password, String age, String email,String rank) {
		this.username=username;
		this.name=name;
		this.surname=surname;
		this.password=password;
		this.age=age;
		this.email=email;
		this.rank=rank;
		photos= PhotoManager.getUserPhotos().get(username);
	}
	
	//getter and setters
	
	public ArrayList<Photo> getPhotos() {
		return photos;
	}
	
	public void setPhotos(ArrayList<Photo> photos) {
		this.photos=photos;
	}

	public void setProfilePhoto(ImageIcon profilePhoto) {
		this.profilePhoto=profilePhoto;
	}
	
	public ImageIcon getProfilePhoto() {
		return profilePhoto;
	}

	public String getRank() {
		return rank;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getAge() {
		return age;
	}

	public String getEmail() {
		return email;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public void setAge(String age) {
		this.age = age;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setRank(String rank) {
		this.rank = rank;
	}
	/**
	 * for adding new photo to the photos arraylist of user
	 * @param photo: photo
	 */
	public void addPhoto(Photo photo) {
		this.photos.add(photo);
	}

}
