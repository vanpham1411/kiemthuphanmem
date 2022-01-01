package mainThread;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;

import constant.Constant;
import main.HttpRequest;

public class AddRoom {
	String token;
	String address;
	String images;
	String price;
	public String getAccess_token() {
		return token;
	}
	public void setAccess_token(String token) {
		this.token = token;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	public AddRoom() {
		super();
	}
	public AddRoom(String token, String address, String images, String price) {
		super();
		this.token = token;
		this.address = address;
		this.images = images;
		this.price = price;
	}
	
	public AddRoom(String token) {
		super();
		this.token = token;
		this.address = "ha noi";
		this.images = "anh phong";
		this.price = "3000000";
	}
	
	public String toString() {
		
		String s= "?token=" + this.token +"&address=" + this.address +"&images="+ this.images + "&price="+ this.price;
		String s1 = s.replace(" ", "%20");
		return s1;
	}
	
	public void NewRoom() throws ProtocolException, MalformedURLException, IOException {
//		System.out.println("Add room:");
		String params = this.toString();
//		System.out.println("new room " + params);
		String s1= HttpRequest.HttpRequestPOST(Constant.ADD_ROOM,params);
		
	}
	
	public String[] init( String token_master) throws ProtocolException, MalformedURLException, IOException {
		AddRoom  add001 = new AddRoom(token_master);
		add001.NewRoom();
		add001.NewRoom();
		add001.NewRoom();
		add001.NewRoom();
		add001.NewRoom();
		add001.NewRoom();
		add001.NewRoom();
		add001.NewRoom();
		add001.NewRoom();
		add001.NewRoom();
		add001.NewRoom();
		add001.NewRoom();
		add001.NewRoom();
		add001.NewRoom();
		add001.NewRoom();
		GetRoomList roomList = new GetRoomList(token_master);
		
		String[] room_id = new String[16];
		
		for(int i = 0; i<15;i++) {
			room_id[i+1] = roomList.GetRoomId(i);
		}
		
		return room_id;
	
	}
	
}

class Response{
	String code;
	String message;
}
