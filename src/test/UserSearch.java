package test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;

import com.google.gson.Gson;

import constant.Constant;
import main.HttpRequest;
import mainThread.Login;
import mainThread.Signup;

public class UserSearch {

	String token;
	String keyword;
	String index;
	String count;
	
	public void UserSearchTest() throws ProtocolException, MalformedURLException, IOException {
		// Get Token
		String m_token;
		Login m_login = new Login(Constant.Login("master","001"));
		m_token = m_login.AcessToken();
		
		String ad_token;
		Login ad_login = new Login(Constant.Login("admin","011"));
		ad_token = ad_login.AcessToken();
		
		String g_token;
		Login g_login = new Login(Constant.Login("guest","001"));
		g_token = g_login.AcessToken();
		
		System.out.println("TESTCASE: tim kiem nguoi dung");
		String params;
		String result;
		
		System.out.println("UNIT TEST 1: Luong chinh");
		params = "";
		params = "?" + "token=" + ad_token + "&"+"keyword=ph" +"&" + "index=" +"0" + "&" + "count=" + "2"  ;
		
		//params = "?" + "keyword=so%2011" +"&" + "index=" +"0" + "&" + "count=" + "2"  ;
		result = HttpRequest.HttpRequestGET(Constant.USER_SEARCH, params);
		UserSearchResponse unitest1= toResponse(result);
		assert(!unitest1.code.equals(null));
		assert(unitest1.code.equals("1000"));
		assert(unitest1.message.equals("OK"));
		System.out.println("UNIT TEST 1: Thanh cong!");
		
		System.out.println("UNIT TEST 2: Thieu tham so");
		params = "?" + "token=" + ad_token + "&keyword=pham" + "&" + "count=" + "2"  ;
		result = HttpRequest.HttpRequestGET(Constant.USER_SEARCH, params);
		UserSearchResponse unitest2= toResponse(result);
		assert(!unitest2.code.equals(null));
		assert(!unitest2.code.equals("1000"));
		assert(!unitest2.message.equals("OK"));
//		assert(unitest2.code.equals("1002"));
//		assert(unitest2.message.equals("Parameter is not enough"));
		System.out.println("UNIT TEST 2: Thanh cong!");
		
		System.out.println("UNIT TEST 3: Khong co quyen truy cap (tai khoan guest)");
		params = "?" + "token=" + g_token + "&keyword=pham" +"&" + "index=" +"0" + "&" + "count=" + "2"  ;
		result = HttpRequest.HttpRequestGET(Constant.USER_SEARCH, params);
		UserSearchResponse unitest3= toResponse(result);
		assert(!unitest3.code.equals(null));
		assert(unitest3.code.equals("1009"));
		assert(unitest3.message.equals("Not access."));
		System.out.println("UNIT TEST 3: Thanh cong!");
		
		System.out.println("UNIT TEST 3: Khong co quyen truy cap (tai khoan master)");
		params = "?" + "token=" + m_token + "&keyword=pham" +"&" + "index=" +"0" + "&" + "count=" + "2"  ;
		result = HttpRequest.HttpRequestGET(Constant.USER_SEARCH, params);
		UserSearchResponse unitest3_1= toResponse(result);
		assert(!unitest3_1.code.equals(null));
		assert(unitest3_1.code.equals("1009"));
		assert(unitest3_1.message.equals("Not access."));
		System.out.println("UNIT TEST 3: Thanh cong!");
		
		System.out.println("UNIT TEST 4: Kieu tham so khong hop le (index)");
		params = "?" + "token=" + ad_token + "&keyword=pham" +"&" + "index=" +"two" + "&" + "count=" + "2"  ;
		result = HttpRequest.HttpRequestGET(Constant.USER_SEARCH, params);
		UserSearchResponse unitest4= toResponse(result);
		assert(!unitest4.code.equals(null));
		assert(!unitest4.code.equals("1000"));
		assert(!unitest4.message.equals("OK"));
		System.out.println("UNIT TEST 4: Thanh cong!");
		
		System.out.println("Kieu tham so khong hop le (count)");
		params = "?" + "token=" + ad_token + "&keyword=pham" +"&" + "index=" +"0" + "&" + "count=" + "cnt"  ;
		result = HttpRequest.HttpRequestGET(Constant.USER_SEARCH, params);
		UserSearchResponse unitest4_1= toResponse(result);
		assert(!unitest4_1.code.equals(null));
		assert(!unitest4_1.code.equals("1000"));
		assert(!unitest4_1.message.equals("OK"));
		System.out.println("UNIT TEST 4: Thanh cong!");
		
		System.out.println("UNIT TEST 5: gia tri tham so khong hop le");
		params = "?" + "token=" + ad_token + "&keyword=pham" +"&" + "index=" +"-10" + "&" + "count=" + "2"  ;
		result = HttpRequest.HttpRequestGET(Constant.USER_SEARCH, params);
		UserSearchResponse unitest5= toResponse(result);
		assert(!unitest5.code.equals(null));
		assert(unitest5.code.equals("1004"));
		assert(unitest5.message.equals("Parameter value is invalid"));
		System.out.println("UNIT TEST 5: Thanh cong!");
		
		System.out.println("UNIT TEST 5: gia tri tham so khong hop le(count)");
		params = "?" + "token=" + ad_token + "&keyword=pham" +"&" + "index=" +"0" + "&" + "count=" + "-2"  ;
		result = HttpRequest.HttpRequestGET(Constant.USER_SEARCH, params);
		UserSearchResponse unitest5_1= toResponse(result);
		assert(!unitest5_1.code.equals(null));
		assert(unitest5_1.code.equals("1004"));
		assert(unitest5_1.message.equals("Parameter value is invalid"));
		System.out.println("UNIT TEST 5: Thanh cong!");
		
		System.out.println("UNIT TEST 6: Chua chu cai viet hoa");
		Signup sign = new Signup("0611","Pham Van Nam","0918338329","master");
		sign.doSignup();
		params = "?" + "token=" + ad_token + "&keyword=Pham" +"&" + "index=" +"0" + "&" + "count=" + "2"  ;
		result = HttpRequest.HttpRequestGET(Constant.USER_SEARCH, params);
		UserSearchResponse unitest6= toResponse(result);
		assert(!unitest6.code.equals(null));
		assert(unitest6.code.equals("1000"));
		assert(unitest6.message.equals("OK"));
		System.out.println("UNIT TEST 6: Thanh cong!");
		
		System.out.println("UNIT TEST 7: Do dai can tim bang 0");
		params = "?" + "token=" + ad_token + "&keyword=" +"&" + "index=" +"0" + "&" + "count=" + "2"  ;
		result = HttpRequest.HttpRequestGET(Constant.USER_SEARCH, params);
		UserSearchResponse unitest7= toResponse(result);
		assert(!unitest7.code.equals(null));
		assert(unitest7.code.equals("1000"));
		assert(unitest7.message.equals("OK"));
		System.out.println("UNIT TEST 7: Thanh cong!");
		
		System.out.println("UNIT TEST 8: Nguoi dung da bi xoa");
		// New account
		Signup newAccount = new Signup("090","NguoiDung","0918338289","user");
		String s = newAccount.doSignup();
		Login newAccountLogin = new Login(Constant.Login("user", "090"));
		String newAccount_ID = newAccountLogin.GetUserID();
		
		params = "?" + "token=" + ad_token + "&keyword=nguoidungptvv" +"&" + "index=" +"0" + "&" + "count=" + "2"  ;
		result = HttpRequest.HttpRequestGET(Constant.USER_SEARCH, params);
		UserSearchResponse unitest8= toResponse(result);
		assert(!unitest8.code.equals(null));
		assert(unitest8.code.equals("1000"));
		assert(unitest8.message.equals("OK"));
		
		//Delete Account
		
		String delAcc = "?" + "token=" + ad_token + "&user_id=" + newAccount_ID + "&confirm=1";
		String deelAccRes = HttpRequest.HttpRequestPOST(Constant.ACCOUNT_DELETE, delAcc);
		
		params = "?" + "token=" + ad_token + "&keyword=nguoidungpttv" +"&" + "index=" +"0" + "&" + "count=" + "2"  ;
		result = HttpRequest.HttpRequestGET(Constant.USER_SEARCH, params);
		UserSearchResponse unitest8_1= toResponse(result);
		assert(!unitest8_1.code.equals(null));
		assert(unitest8_1.code.equals("9994"));
		assert(unitest8_1.message.equals("No data or end of list data entry"));
		
		System.out.println("UNIT TEST 8: Thanh cong!");
		
		System.out.println("UNIT TEST 9: khong co du lieu");
		params = "?" + "token=" + ad_token + "&keyword=pham@#$#!@" +"&" + "index=" +"0" + "&" + "count=" + "2"  ;
		result = HttpRequest.HttpRequestGET(Constant.USER_SEARCH, params);
		UserSearchResponse unitest9= toResponse(result);
		assert(!unitest9.code.equals(null));
		assert(unitest9.code.equals("9994"));
		assert(unitest9.message.equals("No data or end of list data entry"));
		System.out.println("UNIT TEST 9: Thanh cong!");
		
		System.out.println("UNIT TEST 10: khong con du lieu");
		params = "?" + "token=" + ad_token + "&keyword=pham" +"&" + "index=" +"50" + "&" + "count=" + "2"  ;
		result = HttpRequest.HttpRequestGET(Constant.USER_SEARCH, params);
		UserSearchResponse unitest10= toResponse(result);
		assert(!unitest10.code.equals(null));
		assert(unitest10.code.equals("9994"));
		assert(unitest10.message.equals("No data or end of list data entry"));
		System.out.println("UNIT TEST 10: Thanh cong!");
		
		System.out.println("UNIT TEST 11: Sai ten params( keyword)");
		params = "?" + "token=" + ad_token + "&word=pham" +"&" + "index=" +"0" + "&" + "count=" + "2"  ;
		result = HttpRequest.HttpRequestGET(Constant.USER_SEARCH, params);
		UserSearchResponse unitest11= toResponse(result);
		assert(!unitest11.code.equals(null));
		assert(!unitest11.code.equals("1000"));
		assert(!unitest11.message.equals("OK"));
		assert(unitest11.code.equals("1002"));
		System.out.println("UNIT TEST 11: Thanh cong!");
		
		System.out.println("UNIT TEST 11: Sai ten params(tham so khac)");
		params = "?" + "token=" + ad_token + "&keyword=pham" +"&" + "index_value=" +"0" + "&" + "count=" + "2"  ;
		result = HttpRequest.HttpRequestGET(Constant.USER_SEARCH, params);
		UserSearchResponse unitest11_1= toResponse(result);
		assert(!unitest11_1.code.equals(null));
		assert(!unitest11_1.code.equals("1000"));
		assert(!unitest11_1.message.equals("OK"));
		assert(unitest11.code.equals("1002"));
		System.out.println("UNIT TEST 11: Thanh cong!");
		
	}
	
	public UserSearchResponse toResponse(String json) {
		Gson gson = new Gson();
		UserSearchResponse rp = gson.fromJson(json,UserSearchResponse.class);
		return rp;
	}
}

class UserSearchResponse{
	String code;
	String message;
	UserList[] user_list;	
}

class UserList{
	String _id;
	String username;
	String fullname;
	String avatar;
	String email;
	String mobile;
	String role;
	String isBlock;
}
