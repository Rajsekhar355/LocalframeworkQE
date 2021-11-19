package com.restassured.services;

import java.util.Map;

import org.apache.log4j.Logger;

import com.utilities.ConfigFilesUtility;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class APIRestService {


	public static ResponseSpecification getResponseSpecification(Map<String, String> headers) {
		RequestSpecBuilder reqSpecBuilder = new RequestSpecBuilder();
		reqSpecBuilder.addHeaders(headers); 
		reqSpecBuilder.setRelaxedHTTPSValidation();
		reqSpecBuilder.setContentType("application/json; charset=UTF-8");
		RequestSpecification requestSpecification = RestAssured.given(reqSpecBuilder.build());
		return requestSpecification.expect();
	}


	private Cookies addCookie(Map<String,String> cookiesMap) {
		Cookie userNameCookie = new Cookie.Builder("username", "some_value")
				.setSecured(true)
				.setComment("some comment")
				.build();
		Cookies cookies = new Cookies(userNameCookie);
		return cookies;
	}

	public static String post(String url, String query, String body, Map<String,String> headers) {

		Response response = getResponseSpecification(headers).given().
				contentType(ContentType.JSON).
				body(body).
				when().
				post(url);
		System.out.println(response.getStatusCode());
		System.out.println(response.asString());
		return response.asString();

	}


	public static void getRequest(String url, Map<String,String> headers) {
		Response response = getResponseSpecification(headers).given()
				.contentType(ContentType.JSON)
				.when()
				.get(url);
		System.out.println(response.getStatusCode());
		System.out.println(response.asString());
	}

	public static String getRequestWithAuth(String url, String userName, String accessKey) {
		RequestSpecBuilder reqSpecBuilder = new RequestSpecBuilder();
		RequestSpecification requestSpecification = RestAssured.given(reqSpecBuilder.build());
		Response response = requestSpecification.expect().given()
				.auth()
				.preemptive()
				.basic(userName, accessKey)
				.header("Accept", ContentType.JSON.getAcceptHeader())
				.contentType(ContentType.JSON)
				.when()
				.get(url);
		System.out.println(response.getStatusCode());
		return  response.asString();
	}



	public static void putRequest(String url, String query, String body,  Map<String,String> headers) {

		Response response = getResponseSpecification(headers).given()
				.contentType(ContentType.JSON)
				.body(body)
				.when()
				.put(url);
		System.out.println(response.getStatusCode());
		System.out.println(response.asString());
	}

	public static void deleteRequest(String url, Map<String,String> headers) {
		Response response = getResponseSpecification(headers).given()
				.contentType(ContentType.JSON)
				.when()
				.delete(url);
		System.out.println(response.getStatusCode());
		System.out.println(response.asString());
	}

	public static String callRequest(
			ConfigFilesUtility con,
			String apiName, 
			String urlParams,
			String headers,
			int requestType, 
			int bodyType, 
			String inputBody,
			String datatsetHeader,
			String dataResources,
			String authenticationData,
			String formurlEncodedData,
			String formData,
			String savedParameters,
			String statusParameters, 
			Logger logger) {
		
		
		
		
		
		return statusParameters;


	}


}
