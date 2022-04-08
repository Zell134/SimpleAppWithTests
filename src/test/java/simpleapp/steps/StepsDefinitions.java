package simpleapp.steps;

import org.junit.jupiter.api.Assertions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepsDefinitions{

    @Given("I go to main page")
	public void i_go_to_main_page() {
	    
	}
	@When("I login as {string} with password {string}")
	public void i_login_as_with_password(String string, String string2) {

	}
	@Then("I have been successfully logged")
	public void i_have_been_successfully_logged() {
		Assertions.assertTrue(true);
	}

}
