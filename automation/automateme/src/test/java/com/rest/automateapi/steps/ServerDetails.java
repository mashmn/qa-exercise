package com.rest.automateapi.steps;

import org.junit.BeforeClass;

import io.restassured.RestAssured;

public class ServerDetails {

	/**
	 * For testing this project, XAMPP Apache was set to listen to port 89.
	 * 
	 * API automated testing
	 * 
	 * Port can be changed via terminal run: 
	 * mvn test -Dserver.port=8080 -Dserver.host=http://localhost
	 */
    @BeforeClass
    public static void setup() {
        String port = System.getProperty("server.port");
        if (port == null) {
            RestAssured.port = Integer.valueOf(89);
        }
        else{
            RestAssured.port = Integer.valueOf(port);
        }


        String basePath = System.getProperty("server.base");
        if(basePath==null){
            basePath = "/qa-exercise/";
        }
        RestAssured.basePath = basePath;

        String baseHost = System.getProperty("server.host");
        if(baseHost==null){
            baseHost = "http://localhost";
        }
        RestAssured.baseURI = baseHost;

    }
}
