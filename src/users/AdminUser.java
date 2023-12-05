package users;

public class AdminUser extends User {
	/**
	 * extends user class for admin
	 * @param username: User username
	 * @param name: User name
	 * @param surname: User surname
	 * @param password: User password
	 * @param age: User age
	 * @param email: User email
	 * @param rank: User rank
	 */
    public AdminUser(String username, String name, String surname, String password, String age, String email,String rank) {
        super(username, name, surname, password, age, email,rank);
        // Additional subclass-specific initialization if needed
    }
}
