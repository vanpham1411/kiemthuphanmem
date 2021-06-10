package usebody;

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



public class HttpRequest {
	public static String HttpRequestPOST(String url1, Map<String,String> params) throws ProtocolException,MalformedURLException,IOException  {
		String urlRequest = url1;
		URL url;
		url = new URL(urlRequest);
		HttpURLConnection con;
		con = (HttpURLConnection) url.openConnection();
		try {
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json;charset=utf-8");
			con.setRequestProperty("Accept","application/json" );
			con.setDoOutput(true);
			String jsonInputString = new String();
			boolean isFirstParam = true;
			for (Entry<String, String> entry : params.entrySet()) {
				if (isFirstParam) {
					jsonInputString += "{";
					isFirstParam = false;
				} else {
					jsonInputString += ", ";
				}
				jsonInputString += "\""+entry.getKey() + "\":\"" + entry.getValue()+"\"";
			}
			jsonInputString +="}";
			
			OutputStream os = con.getOutputStream();
				os.write(jsonInputString.getBytes("utf-8"));
			StringBuilder content;
			BufferedReader in;
			in = new BufferedReader(
					new InputStreamReader(con.getInputStream(),"utf-8"));
			String line;
			content = new StringBuilder();
			while((line = in.readLine()) != null) {
				content.append(line);
				content.append(System.lineSeparator());
			}
			System.out.println(content.toString());
			return content.toString();
		}

		finally {
			con.disconnect();
		}	
		
		
	}
}
