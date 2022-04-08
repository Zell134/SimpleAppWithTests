package simpleapp.DAO;

public class UserTimeResponse extends UserTime {

	private String updatedAt;

	public UserTimeResponse(String name, String job, String updatedAt) {
		super(name, job);
		this.updatedAt = updatedAt;
	}

	public UserTimeResponse() {
		super();
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

}
