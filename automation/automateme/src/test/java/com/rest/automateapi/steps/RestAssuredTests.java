package com.rest.automateapi.steps;

import static io.restassured.RestAssured.given;

import java.util.Date;
import java.util.List;

//import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.restassured.response.Response;

import com.rest.automateapi.steps.Task;

public class RestAssuredTests extends ServerDetails {
	
	private static final Logger logger = LoggerFactory.getLogger(RestAssuredTests.class);
	

	// final String uri = "http://localhost:89/qa-exercise/fake-api-call.php";	

	/**
	 * GET Call to "fake-api-call.php" to check service status
	 * If Successful then Status Code should be 200.
	 * "application/json\r\n" 	
	 */
	@Test
	public void givenFakeApi_a_getStatusCode_ServiceStatusTest() {
		logger.info("TEST CASE: Test service status of the API endpoint (only one in this case)");
		logger.info("---TEST STARTS---");
		
//		Assert.assertThat(
//				given().log().all().contentType("text/html; charset=UTF-8")
//				.get("/fake-api-call.php").getStatusCode(), Matchers.equalTo(200)
//				);
		given().log().all().
			when().get("/fake-api-call.php").
			then().contentType("text/html; charset=UTF-8").statusCode(200);
		
		logger.info("---TEST ENDS---");
	}
	
	/**
	 * The original fake data (also found in backup_todo.list) has 5
	 * tasks without category. This test checks if this count matches with the fake api.
	 */
	@Test
	public void givenFakeApi_b_getTasks_NoCategoryTest() {
		logger.info("TEST CASE: Find how many tasks do not have \"category\" assigned");
		logger.info("---TEST STARTS---");
		
		Response response = given().
				when().get("/fake-api-call.php").
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
		Assert.assertEquals(count, 5);
		
		logger.info("Tasks without category assigned: {}",count);
		logger.info("---TEST ENDS---");
	}	
	
	/**
	 * The original fake data (also found in backup_todo.list) has 5
	 * tasks without category. This test checks if this count matches with the fake api.
	 */
	@Test
	public void givenFakeApi_c_getTasks_aggregateTaskNamesTest() {
		logger.info("TEST CASE: Aggregate and print only \"task names\"");
		logger.info("---TEST STARTS---");
		
		Response response = given().
				when().get("/fake-api-call.php").
				then().contentType("text/html; charset=UTF-8").extract().response();
		// GSON
		List<Task> taskResponses = response.jsonPath().getList("$", Task.class);

		for (Task task: taskResponses) {
			System.out.println("Task :"+task.taskName);
			logger.info("Tasks : {}", task.taskName);
		}
		
		logger.info("---TEST ENDS---");
		
	}
	
	/**
	 * The original fake data (also found in backup_todo.list) has 5
	 * tasks without category. This test checks if this count matches with the fake api.
	 */
//	@Test
//	public void givenFakeApi_getTasks_descDueDatesTest() {
//		logger.info("TEST CASE: Read API response and print tasks in descending \"due date\" order");
//		logger.info("---TEST STARTS---");
//		
//		Response response = given().
//				when().get("/fake-api-call.php").
//				then().contentType("text/html; charset=UTF-8").extract().response();
//		// GSON
//		List<Task> taskResponses = response.jsonPath().getList("$", Task.class);
//		
//
//		for (Task task: taskResponses) {
//			String date =task.dueDate;
//			System.out.println(date);
//			logger.info("Tasks without category assigned: {}", task.dueDate);
//		}
//		
//		logger.info("---TEST ENDS---");
//		
//	}	
	
	
}
