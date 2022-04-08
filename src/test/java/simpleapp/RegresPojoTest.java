package simpleapp;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import simpleapp.DAO.Register;
import simpleapp.util.Specifications;

public class RegresPojoTest {

	private final static String URL = "https://reqres.in";

	@Test
	public void checkAvatarsTest() {
		Specifications.installSpecifications(Specifications.requestSpecification(URL),
				Specifications.responseSpecificationOk200());
		Response response = given()
				.when()
				.get("/api/users?page=2")
				.then()
				.body("page", equalTo(2))
				.body("data.id", notNullValue())
				.body("data.email", notNullValue())
				.body("data.first_name", notNullValue())
				.body("data.last_name", notNullValue())
				.body("data.avatar", notNullValue())
				.extract().response();
		
		JsonPath jsonPath = response.jsonPath();
		List<String> emails = jsonPath.get("data.email");
		List<Integer> ids = jsonPath.get("data.id");
		List<String> avatars = jsonPath.get("data.avatar");
		
		for (int i = 0; i < avatars.size(); i++)
		{
			assertThat(avatars.get(i), containsString(ids.get(i).toString()));
		}		
		emails.forEach(el -> assertThat(el, endsWith("@reqres.in")));

	}
	
	@Test
	public void successRegisterTest() {

		Specifications.installSpecifications(Specifications.requestSpecification(URL),
				Specifications.responseSpecificationOk200());
		
		Map<String, String> user = new HashMap<>();
		user.put("email", "eve.holt@reqres.in");
		user.put("password", "pistol");
		Response response = given()
				.body(user)
				.when()
				.post("/api/register")
				.then()
				.body("id", equalTo(4))
				.body("token", equalTo("QpwL5tke4Pnpja7X4"))
				.extract().response();
		
		JsonPath jsonPath = response.jsonPath();
		int id = jsonPath.getInt("id");
		String token = jsonPath.getString("token");
		assertThat(4, is(id));
		assertThat(token, containsString("QpwL5tke4Pnpja7X4"));
	}
	
	@Test
	public void unsuccessRegisterTest() throws JsonProcessingException {
		Specifications.installSpecifications(Specifications.requestSpecification(URL),
				Specifications.responseSpecification400());
		
		Map<String, String> user = new HashMap<>();
		user.put("email", "sydney@fife");
		Response response = given()
			.body(user)
			.when()
			.post("/api/register")
			.then()
			.body("error", equalTo("Missing password"))
			.extract().response();
		
		String error = response.jsonPath().getString("error");
		assertThat(error, containsString("Missing password"));
		
		Register usr = new Register("eve.holt@reqres.in", "pistol");
		ObjectMapper mapper = new ObjectMapper();
		String s = mapper.writeValueAsString(usr);
		System.out.println(s);
	}
}
