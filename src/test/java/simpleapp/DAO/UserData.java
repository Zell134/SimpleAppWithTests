package simpleapp.DAO;

import com.fasterxml.jackson.annotation.JsonAlias;
import io.cucumber.messages.internal.com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class UserData {

    private Integer id;
    private String email;
    @JsonAlias("first_name")
    private String firstName;
    @JsonAlias("last_name")
    private String lastName;
    private String avatar;

    public UserData() {
    }

    public UserData(Integer id, String email, String firstName, String lastName, String avatar) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatar = avatar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

}
