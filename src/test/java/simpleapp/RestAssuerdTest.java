package simpleapp;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.time.Clock;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import simpleapp.DAO.ColorsData;
import simpleapp.DAO.Register;
import simpleapp.DAO.SuccessReg;
import simpleapp.DAO.UnsuccessReg;
import simpleapp.DAO.UserData;
import simpleapp.DAO.UserTime;
import simpleapp.DAO.UserTimeResponse;
import simpleapp.util.Specifications;

public class RestAssuerdTest {

	private final static String URL = "https://reqres.in";

	@Test
	public void checkAvatarAndIdTest() {

		Specifications.installSpecifications(Specifications.requestSpecification(URL),
				Specifications.responseSpecificationOk200());
		
		List<UserData> users = given()
				.when()
				.get("/api/users?page=2")
				.then()
				.extract().body().jsonPath().getList("data", UserData.class);
		
		users.stream().forEach(x -> assertThat(x.getAvatar(), containsString(x.getId().toString())));
		users.forEach(x -> assertThat(x.getEmail(), endsWith("@reqres.in")));
	}

	@Test
	public void successRegisterTest() {

		Specifications.installSpecifications(Specifications.requestSpecification(URL),
				Specifications.responseSpecificationOk200());
		
		Register user = new Register("eve.holt@reqres.in", "pistol");
		Integer id = 4;
		String token = "QpwL5tke4Pnpja7X4";

		SuccessReg successReg = given()
				.body(user)
				.when()
				.post("/api/register")
				.then()
				.extract().as(SuccessReg.class);

		assertThat(successReg.getId(), notNullValue());
		assertThat(successReg.getToken(), notNullValue());
		assertThat(successReg.getId(), is(id));
		assertThat(successReg.getToken(), is(token));
	}

	@Test
	public void unsuccessRegisterTest() {

		String error = "Missing password";

		Specifications.installSpecifications(Specifications.requestSpecification(URL),
				Specifications.responseSpecification400());
		Register user = new Register("sydney@fife", "");
		UnsuccessReg unsuccessReg = given()
				.body(user)
				.when()
				.post("/api/register")
				.then()
				.extract()
				.as(UnsuccessReg.class);

		assertThat(unsuccessReg.getError(),  notNullValue());
		assertThat(unsuccessReg.getError(), is(error));
	}

	@Test
	public void sortedFearsTest() {
		Specifications.installSpecifications(Specifications.requestSpecification(URL),
				Specifications.responseSpecificationOk200());

		List<ColorsData> colors = given()
				.when()
				.get("/api/unknown")
				.then()
				.extract().body().jsonPath().getList("data", ColorsData.class);

		List<Integer> years = colors.stream().map(ColorsData::getYear).collect(Collectors.toList());
		List<Integer> sortedYears = years.stream().sorted().collect(Collectors.toList());
		assertThat(sortedYears, is(years));
	}

	@Test
	public void deleteUserTest() {
		Specifications.installSpecifications(Specifications.requestSpecification(URL),
				Specifications.responseSpecUnicue(204));
		given()
		.when()
		.delete("/api/users/2");

	}
	
	@Test
	public void timeTest() {
		Specifications.installSpecifications(Specifications.requestSpecification(URL),
				Specifications.responseSpecificationOk200());
		UserTime user = new UserTime("morpheus", "zion resident"); 
		UserTimeResponse response = given()
				.body(user)
				.when()
				.put("/api/users/2")
				.then()
				.extract().as(UserTimeResponse.class);
		assertThat(user.getName(), is(response.getName()));
		assertThat(user.getJob(), is(response.getJob()));
		
		String currentTime = Clock.systemUTC().instant().toString().replaceAll("(.{11})$", "");
		assertThat(currentTime, is(response.getUpdatedAt().replaceAll("(.{5})$", "")));
				
	}
}
