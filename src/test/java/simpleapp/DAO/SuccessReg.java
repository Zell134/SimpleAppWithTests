package simpleapp.DAO;

public class SuccessReg {
    private Integer id;
    private String token;

    public SuccessReg() {
    }

    public SuccessReg(int id, String token) {
        this.id = id;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
