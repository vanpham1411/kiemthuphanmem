package mainThread;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import com.google.gson.Gson;
import constant.Constant;
import main.HttpRequest;

public class Login {
	String username;
	String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

	public Login(String s) {
		super();
		this.username = s;
		this.password = s;
	}
	
	public String toString() {
		return "?username="+ this.username+"&password=" + password;
	}
	
	public String AcessToken() throws ProtocolException, MalformedURLException, IOException {
		String params = this.toString();
		String s1= HttpRequest.HttpRequestPOST(Constant.LOGIN, toString());
		Gson g = new Gson();
		LoginResponse lr = g.fromJson(s1,LoginResponse.class);
		return lr.user.token;
	}
	
	public String GetUserID() throws ProtocolException, MalformedURLException, IOException {
		String params = this.toString();
		String s1= HttpRequest.HttpRequestPOST(Constant.LOGIN, toString());
		Gson g = new Gson();
		LoginResponse lr = g.fromJson(s1,LoginResponse.class);
		return lr.user.user_id;
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
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
}
