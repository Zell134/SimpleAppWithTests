package simpleapp;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import simpleapp.util.ExcelDataProvider;
import simpleapp.util.Specifications;
import simpleapp.util.User;

public class ExcelTest {
	
	private final static String URL = "https://reqres.in";

	@ParameterizedTest
	@MethodSource("streamOfUsers")
	public void checkUser(User user) {
				
		Specifications.installSpecifications(Specifications.requestSpecification(URL),
				Specifications.responseSpecificationOk200());
		
		
		User response = given().contentType(ContentType.JSON).when()
				.get("https://reqres.in/api/users/" + user.getId())
				.then().log().body()
				.extract().body().jsonPath().getObject("data", User.class);
		assertThat("Assert user id", response.getId(), is(user.getId()));
		assertThat("Assert user email", response.getEmail(), is(user.getEmail()));
		assertThat("Assert user first name", response.getFirst_name(), is(user.getFirst_name()));
		assertThat("Assert user last name", response.getLast_name(), is(user.getLast_name()));
		assertThat("Assert user avatar", response.getAvatar(), is(user.getAvatar()));
	}

	private static Stream<User> streamOfUsers() throws Exception {

		ExcelDataProvider dataProvider = new ExcelDataProvider();
		return dataProvider.usersFromSheet().stream();
	}
}
