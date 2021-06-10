package usebody;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import usebody.SignupTest.SignupResponse;

public class LoginTest {
	public void LoginTestmain() throws ProtocolException, MalformedURLException, IOException {
		Map<String,String> params = new HashMap<String,String>();
		String urlString = "https://api-datk.herokuapp.com/auth/login";
		System.out.println("testcase LOGIN");
		
		
		System.out.println("UNITEST 1: dang ki thanh cong ");
		params.clear();
		params.put("username","admin1");
		params.put("password","127736");
		String s1 = HttpRequest.HttpRequestPOST(urlString, params);
		LoginResponse unitest1 = toLoginResponse(s1);
		assert(unitest1.code.equals("1000"));
		assert(unitest1.message.equals("OK"));
		System.out.println("UNITEST 1: Thanh cong!");
		
		System.out.println("UNITEST 2: chua signup ");
		params.clear();
		params.put("user","admin1");
		params.put("pass","127736");
		String s2 = HttpRequest.HttpRequestPOST(urlString, params);
		LoginResponse unitest2 = toLoginResponse(s2);
		assert(! unitest2.code.equals("1000"));
		System.out.println("UNITEST 2: Thanh cong!");
		
		System.out.println("UNITEST 3: json sai ");
		params.clear();
		params.put("user","admin1");
		params.put("password","127736");
		String s3 = HttpRequest.HttpRequestPOST(urlString, params);
		LoginResponse unitest3 = toLoginResponse(s3);
		assert(! unitest3.code.equals("1000"));
		System.out.println("UNITEST 3: Thanh cong!");
		
		System.out.println("UNITEST 4: username chua dau cach o cuoi ");
		params.clear();
		params.put("username","admin1");
		params.put("password","127736");
		String s4 = HttpRequest.HttpRequestPOST(urlString, params);
		LoginResponse unitest4 = toLoginResponse(s4);
		assert(! unitest4.code.equals("1000"));
		System.out.println("UNITEST 4: Thanh cong!");
		
		System.out.println("UNITEST 5: username viet hoa chu dau ");
		params.clear();
		params.put("username","Admin");
		params.put("password","127736");
		String s5 = HttpRequest.HttpRequestPOST(urlString, params);
		LoginResponse unitest5 = toLoginResponse(s5);
		assert(! unitest5.code.equals("1000"));
		System.out.println("UNITEST 5: Thanh cong!");
		
		System.out.println("UNITEST 6: them truong du lieu ");
		params.clear();
		params.put("username","chuasignup");
		params.put("password","127736");
		params.put("add", "1");
		String s6 = HttpRequest.HttpRequestPOST(urlString, params);
		LoginResponse unitest6 = toLoginResponse(s6);
		assert(! unitest6.code.equals("1000"));
		System.out.println("UNITEST 6: Thanh cong!");
		
		System.out.println("UNITEST 7: them truong du lieu trong ");
		params.clear();
		params.put("username","admin1");
		params.put("password","127736qw");
		params.put("","");
		String s7 = HttpRequest.HttpRequestPOST(urlString, params);
		LoginResponse unitest7 = toLoginResponse(s7);
		assert(! unitest7.code.equals("1000"));
		System.out.println("UNITEST 7: Thanh cong!");
		
		System.out.println("UNITEST 8: password dai hon ");
		params.clear();
		params.put("username","admin1");
		params.put("password","127736qw");
		String s8 = HttpRequest.HttpRequestPOST(urlString, params);
		LoginResponse unitest8 = toLoginResponse(s8);
		assert(! unitest8.code.equals("1000"));
		System.out.println("UNITEST 8: Thanh cong!");
		
		System.out.println("UNITEST 9: password viet hoa chu cai dau ");
		params.clear();
		params.put("username","nva");
		params.put("password","Adminne");
		String s9 = HttpRequest.HttpRequestPOST(urlString, params);
		LoginResponse unitest9 = toLoginResponse(s9);
		assert(! unitest9.code.equals("1000"));
		System.out.println("UNITEST : Thanh cong!");
		
		System.out.println("UNITEST 10: password bi dao nguoc ");
		params.clear();
		params.put("username","nva");
		params.put("password","ennimda");
		String s10 = HttpRequest.HttpRequestPOST(urlString, params);
		LoginResponse unitest10 = toLoginResponse(s10);
		assert(! unitest10.code.equals("1000"));
		System.out.println("UNITEST 10: Thanh cong!");
		
	}
	
	public static LoginResponse toLoginResponse(String res) {
		Gson g = new Gson();
		LoginResponse lr = g.fromJson(res,LoginResponse.class);
		return lr;
	}

}

class LoginResponse {
	String code;
	String message;
	UserInfo user;
}

class UserInfo {
	String user_id;
	String role;
	String avatar;
	String fullname;
	String token;
	String refreshToken;
}
