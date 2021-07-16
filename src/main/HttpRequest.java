package main;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;

import constant.Constant;

public class HttpRequest {
	
	
	
	
	
	
	public static String HttpRequestPOST(String url1, String[] params,int cnt) throws ProtocolException,MalformedURLException,IOException  {
		String s="";
		
		for(int i = 0; i<cnt;i++) {
			s += params[i];
			
		}
			 URL url = new URL(url1+s);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		    connection.setRequestMethod("POST");
		    connection.setDoOutput(true);
		    try (DataOutputStream writer = new DataOutputStream(connection.getOutputStream())) {
		        
		        StringBuilder content;

		        try (BufferedReader in = new BufferedReader(
		                new InputStreamReader(connection.getInputStream()))) {
		        String line;
		        content = new StringBuilder();
		           while ((line = in.readLine()) != null) {
		                content.append(line);
		                content.append(System.lineSeparator());
		            }
		        }
		       // System.out.println("JSON response: " + content.toString());
		        return content.toString();
		}finally {
	        connection.disconnect();
	    }
		

		
	}
	
	
	public static String HttpRequestPOST(String url1, String params) throws ProtocolException,MalformedURLException,IOException  {
			 URL url = new URL(url1+params);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		    connection.setRequestMethod("POST");
		    connection.setDoOutput(true);
		    try (DataOutputStream writer = new DataOutputStream(connection.getOutputStream())) {
		        
		        StringBuilder content;

		        try (BufferedReader in = new BufferedReader(
		                new InputStreamReader(connection.getInputStream()))) {
		        String line;
		        content = new StringBuilder();
		           while ((line = in.readLine()) != null) {
		                content.append(line);
		                content.append(System.lineSeparator());
		            }
		        }
		        System.out.println("JSON response: " + content.toString());
		        return content.toString();
		}finally {
	        connection.disconnect();
	    }
		

		
	}
	
	
	public static String HttpRequestGET(String url1, String params) throws ProtocolException,MalformedURLException,IOException  {
		 URL url = new URL(url1+params);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

	    connection.setRequestMethod("GET");
	    connection.setRequestProperty("User-Agent", "Mozilla/5.0");
	    connection.setRequestProperty("Content-Type", "application/json; utf-8");
	    connection.setDoOutput(true);
	    //int responseCode = connection.getResponseCode();
        //System.out.println("Response Code : " + responseCode);
	    BufferedReader in = new BufferedReader(new InputStreamReader(
	    		connection.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		//in.close();
	    
//	    try (DataOutputStream writer = new DataOutputStream(connection.getOutputStream())) {
//	        
//	        StringBuilder content;
//
//	        try (BufferedReader in = new BufferedReader(
//	                new InputStreamReader(connection.getInputStream()))) {
//	        String line;
//	        content = new StringBuilder();
//	           while ((line = in.readLine()) != null) {
//	                content.append(line);
//	                content.append(System.lineSeparator());
//	            }
//	        }
	        System.out.println("JSON response: " + response.toString());
	        return response.toString();
//	}finally {
//       connection.disconnect();
//   }
	

	
}


	
	
	
	public static void sendPost() throws Exception {

        // url is missing?
        //String url = "https://selfsolve.apple.com/wcResults.do";
        String url = Constant.LOGIN;

        HttpsURLConnection httpClient = (HttpsURLConnection) new URL(url).openConnection();

        //add reuqest header
        httpClient.setRequestMethod("POST");
        httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");
        httpClient.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String urlParameters = "username=master1ptv&password=master1ptv";

        // Send post request
        httpClient.setDoOutput(true);
        try (DataOutputStream wr = new DataOutputStream(httpClient.getOutputStream())) {
            wr.writeBytes(urlParameters);
            wr.flush();
        }

        int responseCode = httpClient.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(httpClient.getInputStream()))) {

            String line;
            StringBuilder response = new StringBuilder();

            while ((line = in.readLine()) != null) {
                response.append(line);
            }

            //print result
            System.out.println(response.toString());

        }

    }


	
	

}


class Response{
	String code;
	String message;
}
	
//	try {
//	con.setRequestMethod("POST");
//	con.setDoOutput(true);
//	
//	StringBuilder content;
//	BufferedReader in;
//	in = new BufferedReader(
//			new InputStreamReader(con.getInputStream(),"utf-8"));
//	String line;
//	content = new StringBuilder();
//	while((line = in.readLine()) != null) {
//		content.append(line);
//		content.append(System.lineSeparator());
//	}
//	System.out.println(content.toString());
//	return content.toString();
//}
//
//finally {
//	con.disconnect();
//}	

