package mainThread;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;

import com.google.gson.Gson;

import constant.Constant;
import main.HttpRequest;

public class Signup {
	String username;
	String fullname;
	String email;
	String password;
	String mobile;
	String role;
	public Signup(String s1, String fullname, String mobile, String role) {
		super();
		this.username = Constant.Login(role,s1);
		this.fullname = fullname;
		this.mobile = mobile;
		this.role = role;
		this.email = this.username + Constant.MAIL;
		this.password = this.username;
	}
	
	
	
	public Signup(String username, String fullname, String email, String password, String mobile, String role) {
		super();
		this.username = username;
		this.fullname = fullname;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
		this.role = role;
	}



	public String toString() {
		String s =  "?username="+ this.username+ "&fullname=" + this.fullname + "&email=" +  this.email + "&password=" + this.password + "&mobile=" + this.mobile + "&role=" + this.role;
		return s.replace(" ", "%20");
	}
	
	
	public String doSignup() throws ProtocolException, MalformedURLException, IOException {
		String params = this.toString();
		String s = HttpRequest.HttpRequestPOST(Constant.SIGNUP, params);
		Gson g = new Gson();
		Response lr = g.fromJson(s,Response.class);
		return lr.code;
//		return s;
	}

}

