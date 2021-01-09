package com.softapple.url;

public class ServiceUrl {
	
	public static final String HOST_URL="http://localhost:";
	public static final String SERVER_PORT = "8080";
	public static final String BASE_URL = HOST_URL + SERVER_PORT;

	
	public static final String STUDENT_SAVE= BASE_URL + "/student/save";
	public static final String STUDENT_ALL= BASE_URL + "/student/find-all";

}
