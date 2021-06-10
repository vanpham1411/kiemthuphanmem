package usebody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;

public class SignupTest {
	
	public void SignupTestmain() throws ProtocolException, MalformedURLException, IOException {
		Map<String,String> params = new HashMap<String,String>();
		String urlString = "https://api-datk.herokuapp.com/auth/signup";
		System.out.println("testcase SIGNUP");
		System.out.println("UNITEST 1: dang ki thanh cong ");
		params.clear();
		params.put("username","admin1");
		params.put("fullname","Nguyen Van A");
		params.put("email","ptvUNitest011111@gmail.com");
		params.put("password","127736");
		params.put("mobile","0372648273");
		params.put("role","admin");	
		String s1 = HttpRequest.HttpRequestPOST(urlString, params);
		SignupResponse unitest1 = toSignupResponse(s1);
		assert(unitest1.code.equals("1000")):"that bai";
		assert(unitest1.message.equals("OK"));
		System.out.println("UNITEST 1: Thanh cong!");
		
		System.out.println("UNITEST 2: Tai khoan da ton tai ");
		params.clear();
		params.put("username","admin2");
		params.put("fullname","Nguyen Van N");
		params.put("email","ptvUNitest1@gmail.com");
		params.put("password","127736");
		params.put("mobile","0372648273");
		params.put("role","admin");	
		String s2 = HttpRequest.HttpRequestPOST(urlString, params);
		SignupResponse unitest2 = toSignupResponse(s2);
		assert(! unitest2.code.equals("1000"));
		System.out.println("UNITEST 2: Thanh cong!");
		
		System.out.println("UNITEST 3: Thieu fullname ");
		params.clear();
		params.put("username","user3");
		params.put("email","ptvUNitest3@gmail.com");
		params.put("password","127736");
		params.put("mobile","0372648273");
		params.put("role","admin");	
		String s3 = HttpRequest.HttpRequestPOST(urlString, params);
		SignupResponse unitest3 = toSignupResponse(s3);
		assert(!unitest3.code.equals("1000"));
		System.out.println("UNITEST 3: Thanh cong!");
		
		System.out.println("UNITEST 4: fullname sai ");
		params.clear();
		params.put("username","user4");
		params.put("fullname"," Van");
		params.put("email","ptvUNitest4@gmail.com");
		params.put("password","127736");
		params.put("mobile","0372648273");
		params.put("role","admin");	
		String s4 = HttpRequest.HttpRequestPOST(urlString, params);
		SignupResponse unitest4 = toSignupResponse(s4);
		assert(!unitest4.code.equals("1000"));
		System.out.println("UNITEST :4 Thanh cong!");
		
		System.out.println("UNITEST 5: username khong co ");
		params.clear();
		params.put("fullname","Nguyen Van");
		params.put("email","ptvUNitest5@gmail.com");
		params.put("password","127736");
		params.put("mobile","0372648273");
		params.put("role","admin");	
		String s5 = HttpRequest.HttpRequestPOST(urlString, params);
		SignupResponse unitest5 = toSignupResponse(s5);
		assert(! unitest5.code.equals("1000"));
		System.out.println("UNITEST 5: Thanh cong!");
		
		System.out.println("UNITEST 6: username trung ");
		params.clear();
		params.put("username","admin1");
		params.put("fullname","Nguyen Van");
		params.put("email","ptvUNitest6@gmail.com");
		params.put("password","127736");
		params.put("mobile","0372648273");
		params.put("role","admin");	
		String s6 = HttpRequest.HttpRequestPOST(urlString, params);
		SignupResponse unitest6 = toSignupResponse(s6);
		assert(! unitest6.code.equals("1000"));
		System.out.println("UNITEST 6: Thanh cong!");
		
		System.out.println("UNITEST 7 : email khong co");
		params.clear();
		params.put("username","admin71");
		params.put("fullname","Nguyen Van A");
		params.put("password","127736");
		params.put("mobile","0372648273");
		params.put("role","admin");	
		String s7 = HttpRequest.HttpRequestPOST(urlString, params);
		SignupResponse unitest7 = toSignupResponse(s7);
		assert(!unitest7.code.equals("1000"));
		System.out.println("UNITEST 7 : Thanh cong!");
		
		System.out.println("UNITEST 8: email trong ");
		params.clear();
		params.put("username","admin81");
		params.put("fullname","Nguyen Van U");
		params.put("email","");
		params.put("password","127736");
		params.put("mobile","0372648273");
		params.put("role","admin");	
		String s8 = HttpRequest.HttpRequestPOST(urlString, params);
		SignupResponse unitest8 = toSignupResponse(s8);
		assert(!unitest7.code.equals("1000"));
		System.out.println("UNITEST 8: Thanh cong!");
		
		System.out.println("UNITEST 9 : email sai dinh dang ");
		params.clear();
		params.put("username","admin9");
		params.put("fullname","Nguyen Van A");
		params.put("email","gmailcomabcxysaaa");
		params.put("password","123456");
		params.put("mobile","0372648273");
		params.put("role","admin");	
		String s9 = HttpRequest.HttpRequestPOST(urlString, params);
		SignupResponse unitest9 = toSignupResponse(s9);
		assert(! unitest8.code.equals("1000"));
		System.out.println("UNITEST 9 : Thanh cong!");
		
		System.out.println("UNITEST 10: password khong co ");
		params.clear();
		params.put("username","admin101");
		params.put("fullname","Nguyen Van N");
		params.put("email","ptvUNitest10@gmail.com");
		params.put("mobile","0372648273");
		params.put("role","admin");	
		String s10 = HttpRequest.HttpRequestPOST(urlString, params);
		SignupResponse unitest10 = toSignupResponse(s10);
		assert(!unitest10.code.equals("1000"));
		System.out.println("UNITEST 10: Thanh cong!");
		
		System.out.println("UNITEST 11: password duoi 6 ki tu ");
		params.clear();
		params.put("username","guest111");
		params.put("fullname","Nguyen Van A");
		params.put("email","ptvUNitest11@gmail.com");
		params.put("password","1827");
		params.put("mobile","0372648273");
		params.put("role","guest");	
		String s11 = HttpRequest.HttpRequestPOST(urlString, params);
		SignupResponse unitest11 = toSignupResponse(s11);
		assert(!unitest11.code.equals("1000"));
		System.out.println("UNITEST 11: Thanh cong!");
		
		System.out.println("UNITEST 12: mobile khong co ");
		params.clear();
		params.put("username","guest2");
		params.put("fullname","Nguyen Van An");
		params.put("email","ptvUNitest12@gmail.com");
		params.put("password","1827111");
		params.put("role","guest");	
		String s12 = HttpRequest.HttpRequestPOST(urlString, params);
		SignupResponse unitest12 = toSignupResponse(s12);
		assert(!unitest12.code.equals("1000"));
		System.out.println("UNITEST 12: Thanh cong!");
		
		System.out.println("UNITEST 13: mobile chua chu cai ");
		params.clear();
		params.put("username","guest3");
		params.put("fullname","Nguyen Van An");
		params.put("email","ptvUNitest13@gmail.com");
		params.put("password","1827111");
		params.put("mobile","01920jurjjjj");
		params.put("role","guest");	
		String s13 = HttpRequest.HttpRequestPOST(urlString, params);
		SignupResponse unitest13 = toSignupResponse(s13);
		assert(!unitest13.code.equals("1000"));
		System.out.println("UNITEST 13: Thanh cong!");
		
		System.out.println("UNITEST 14: mobile sai ");
		params.clear();
		params.put("username","guest4");
		params.put("fullname","Nguyen Van An");
		params.put("email","ptvUNitest14@gmail.com");
		params.put("password","1827111");
		params.put("mobile","012345");
		params.put("role","guest");	
		String s14 = HttpRequest.HttpRequestPOST(urlString, params);
		SignupResponse unitest14 = toSignupResponse(s14);
		assert(!unitest14.code.equals("1000"));
		System.out.println("UNITEST 14: Thanh cong!");
		
		System.out.println("UNITEST 15: role khong co ");
		params.clear();
		params.put("username","abc");
		params.put("fullname","Nguyen Van Nam");
		params.put("email","ptvUNitest15@gmail.com");
		params.put("password","1827121");
		params.put("mobile","012345");	
		String s15 = HttpRequest.HttpRequestPOST(urlString, params);
		SignupResponse unitest15 = toSignupResponse(s15);
		assert(!unitest15.code.equals("1000"));
		System.out.println("UNITEST 15: Thanh cong!");
		
		System.out.println("UNITEST 16: role sai ");
		params.clear();
		params.put("username","abc1");
		params.put("fullname","Nguyen Van Na");
		params.put("email","ptvUNitest16@gmail.com");
		params.put("password","1827121");
		params.put("mobile","012345");
		params.put("role","khach");
		String s16 = HttpRequest.HttpRequestPOST(urlString, params);
		SignupResponse unitest16 = toSignupResponse(s16);
		assert(!unitest16.code.equals("1000"));
		System.out.println("UNITEST 16: Thanh cong!");
		
		System.out.println("UNITEST 17: JSON sai ");
		params.clear();
		params.put("name", "abc abc");
		params.put("address", "ha noi");
		String s17 = HttpRequest.HttpRequestPOST(urlString, params);
		SignupResponse unitest17 = toSignupResponse(s17);
		assert(!unitest17.code.equals("1000"));
		System.out.println("UNITEST 17: Thanh cong!");
		
		System.out.println("UNITEST 18: username chua dau cach ");
		params.clear();
		params.put("username","admin 1");
		params.put("fullname","Nguyen Van An");
		params.put("email","ptvUNitest18@gmail.com");
		params.put("password","127736");
		params.put("mobile","0372648273");
		params.put("role","guest");	
		String s18 = HttpRequest.HttpRequestPOST(urlString, params);
		SignupResponse unitest18 = toSignupResponse(s18);
		assert(!unitest18.code.equals("1000"));
		System.out.println("UNITEST 18: Thanh cong!");
		
		
	}
	
	
	public SignupResponse toSignupResponse(String s) {
		Gson g = new Gson();
		SignupResponse sr = g.fromJson(s, SignupResponse.class);
		assert(sr.code != null && !"".equals(sr.code));
		assert(sr.message != null && !"".equals(sr.message));
		return sr;
	}
	
	class SignupResponse{
		String code;
		String message;
	}
}
