package com.rest.automateapi.steps;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import io.restassured.response.Response;

import com.rest.automateapi.steps.Task;

public class RestAssuredTests extends ServerDetails {
	

	
	final String uri = "http://localhost:89/qa-exercise/fake-api-call.php";	

	/**
	 * GET Call to "fake-api-call.php" to check service status
	 * If Successful then Status Code should be 200.
	 * "application/json\r\n" 	
	 */
	@Test
	public void givenFakeApi_one_getStatusCode_ServiceStatusTest() {
//		Assert.assertThat(
//				given().log().all().contentType("text/html; charset=UTF-8")
//				.get("/fake-api-call.php").getStatusCode(), Matchers.equalTo(200)
//				);
		given().log().all().
			when().get("/fake-api-call.php").
			then().contentType("text/html; charset=UTF-8").statusCode(200);
	}
	
	/**
	 * The original fake data (also found in backup_todo.list) has 5
	 * tasks without category. This test checks if this count matches with the fake api.
	 */
	@Test
	public void givenFakeApi_two_getTasks_NoCategoryTest() {
		Response response = given().log().all().
				when().get(uri).
				then().contentType("text/html; charset=UTF-8").extract().response();
		// GSON
		List<Task> taskResponses = response.jsonPath().getList("$", Task.class);
		// Count no. of tasks with empty category
		int count=0;
		for (Task task: taskResponses) {
			if (task.category.equals("")) {
				count++;
			}
		}
		System.out.println("Count: "+count);
		Assert.assertEquals(count, 5);
	}	
	
	/**
	 * The original fake data (also found in backup_todo.list) has 5
	 * tasks without category. This test checks if this count matches with the fake api.
	 */
	@Test
	public void givenFakeApi_three_getTasks_aggregateTaskNamesTest() {
		Response response = given().log().all().
				when().get(uri).
				then().contentType("text/html; charset=UTF-8").extract().response();
		// GSON
		List<Task> taskResponses = response.jsonPath().getList("$", Task.class);

		for (Task task: taskResponses) {
			System.out.println(task.taskName.toString());
		}
		
	}	
	
	
}
