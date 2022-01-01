package test;

import com.google.gson.Gson;

public abstract class Tenant {
	String token;
	String room_id;
	
	public Tenant() {
		super();
	}
	public Tenant(String token, String room_id) {
		super();
		this.token = token;
		this.room_id = room_id;
	}
	
	public Response toResponse(String json) {
		Gson gson = new Gson();
		Response rp = gson.fromJson(json,Response.class);
		return rp;
	}
	
	public String toString() {
		return "?token=" + this.token + "&room_id=" + this.room_id;
	}
	
	
}


class Response{
	String code;
	String message;
}
