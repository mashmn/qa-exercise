package com.rest.automateapi.steps;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
 
import java.util.Map;
 
import org.apache.commons.lang3.StringUtils;
 
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
 
public class CucumberTest {
 
	private Response response;
	private ValidatableResponse json;
	private RequestSpecification request;
    private String ENDPOINT_GET_BOOK_BY_ISBN = "http://localhost:89/qa-exercise/fake-api-call.php";
 
	@Given("a task exists with an id of (.*)")
	public void a_task_exists_with_id(String task){
		request = given().param("id", "task:" + task);
	}
 
	@When("a user retrieves the task by id")
	public void a_user_retrieves_the_book_by_isbn(){
		response = request.when().get(ENDPOINT_GET_BOOK_BY_ISBN);
		System.out.println("response: " + response.prettyPrint());
	}
 
	@Then("the status code is (\\d+)")
	public void verify_status_code(int statusCode){
		json = response.then().statusCode(statusCode);
	}
 
	/**
	 * asserts on json fields with single values
	 */
	@And("response includes the following$")
	public void response_equals(Map<String,String> responseFields){
		for (Map.Entry<String, String> field : responseFields.entrySet()) {
			if(StringUtils.isNumeric(field.getValue())){
				json.body(field.getKey(), equalTo(Integer.parseInt(field.getValue())));
			}
			else{
				json.body(field.getKey(), equalTo(field.getValue()));
			}
		}
	}
	
}