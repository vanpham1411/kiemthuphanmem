package usebody;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import usebody.SignupTest.SignupResponse;

public class PersonalInforTest {
	
	public void PersonalInforTest() throws ProtocolException, MalformedURLException, IOException {
		Map<String, String> params = new HashMap<String,String>();
		
		System.out.println("testcase PersonalInfor: ");
		System.out.println("UNITEST 1: lay thong tin thanh cong");
		params.clear();
		params.put("username","admin1");
		params.put("password","127736");
		//String s1 = HttpRequest.HttpRequestPOST(CONSTANT.baseURL, params);
		//LoginResponse unitest1 = LoginResponse.toLoginResponse(s1);
		
	}
	
	public UserInfor toUserInfor(String s) {
		Gson g = new Gson();
		UserInfor ui = g.fromJson(s, UserInfor.class);
		assert(ui.code != null && !"".equals(ui.code));
		assert(ui.message != null && !"".equals(ui.message));
		return ui;
	}
	
	class UserInfor {
		String code;
		String message;
		String _id;
		String username;
		String fullname;
		String avatar;
		String email;
		String mobile;
		
	}
}
