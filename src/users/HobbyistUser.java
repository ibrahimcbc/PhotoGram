package users;

public class HobbyistUser extends User {
	/**
	 * extends user class for hobbyist user
	 * @param username: User username
	 * @param name: User name
	 * @param surname: User surname
	 * @param password: User password
	 * @param age: User age
	 * @param email: User email
	 * @param rank: User rank
	 */
    public HobbyistUser(String username, String name, String surname, String password, String age, String email,String rank) {
        super(username, name, surname, password, age, email,rank);
        // Additional subclass-specific initialization if needed
    }
}
