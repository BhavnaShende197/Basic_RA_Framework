package com.java.testclasses;

import java.time.LocalDate;
import org.testng.Assert;

import com.java.methods.Basic_Method;
import com.java.payload.Post_Request_Repository;

import io.restassured.path.json.JsonPath;

public class Post_TestCase {
	
	public static void execute() {
		
			
			int res_Status_Code = Basic_Method.response_StatusCode(Post_Request_Repository.base_URI(),
					Post_Request_Repository.post_resource(), Post_Request_Repository.post_TC1());
			
			if(res_Status_Code == 201) {
			
			String response_Body = Basic_Method.response_Body (Post_Request_Repository.base_URI(),
					Post_Request_Repository.post_resource(), Post_Request_Repository.post_TC1());
			
			System.out.println(res_Status_Code);
			System.out.println(response_Body);
				
			// Parse the response body
			JsonPath jsp = new JsonPath(response_Body);
			String res_name = jsp.getString("name");
			String res_job = jsp.getString("job");
			String res_id = jsp.getString("id");
			String res_createdAt=jsp.getString("createdAt"); 
			String currentdate=LocalDate.now().toString();
			
			System.out.println(currentdate);
			
			// Validate the response body parameters
			Assert.assertEquals(res_name, "morpheus");
			Assert.assertEquals(res_job, "leader");
			Assert.assertNotNull(res_id);
			Assert.assertEquals(res_createdAt.substring(0,10), currentdate);
				
			}
	}
}

