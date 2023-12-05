package photo;

public class Photo {
	String address;
	String priv;
	String description;
	
	/**
	 * Photo class for keeping photo related infos
	 * @param address: image address (filepath)
	 * @param priv: privacy of photo
	 * @param description: description of photo
	 */
	public Photo(String address,String priv,String description) {
		this.address=address;
		this.priv=priv;
		this.description=description;
	}
	public String getAddress() {
		return address;
	}
	public String getPriv() {
		return priv;
	}
	public String getdescription() {
		return description;
	}
	
	public void setPriv(String priv) {
		this.priv=priv;
	}
	// could not make the setters because I have not enough time.
}
