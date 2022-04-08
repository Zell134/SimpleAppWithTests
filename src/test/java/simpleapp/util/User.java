package simpleapp.util;

public class User {
	private Integer id;
	private String email;
	private String first_name;
	private String last_name;
	private String avatar;

	public User() {
		super();
	}

	public User(int id, String email, String first_name, String last_name, String avatar) {
		super();
		this.id = id;
		this.email = email;
		this.first_name = first_name;
		this.last_name = last_name;
		this.avatar = avatar;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(id + " : ");
		result.append(email + " : ");
		result.append(first_name + " : ");
		result.append(last_name + " : ");
		result.append(avatar + " : ");
		return result.toString();
	}
	
	

}