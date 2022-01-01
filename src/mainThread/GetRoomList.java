package mainThread;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;

import com.google.gson.Gson;

import constant.Constant;
import main.HttpRequest;

public class GetRoomList {
	String token;
	String index;
	String count;
	
	public GetRoomList(String token) {
		super();
		this.token = token;
		this.index = "0";
		this.count = "100";
	}
	
	public String toString () {
		return "?" + "token="+ this.token + "&" + "index=" + this.index + "&" + "count=" + this.count;
	}
	
	public String GetRoomId(int i) throws ProtocolException, MalformedURLException, IOException{
		String params = this.toString();
		//System.out.println("GetRoomId" + params);
		String s1= HttpRequest.HttpRequestPOST(Constant.GET_ROOM_LIST, params);
		Gson g = new Gson();
		ResponeGetRoomList lr = new ResponeGetRoomList();
		lr = g.fromJson(s1,ResponeGetRoomList.class);
//		System.out.println("GET ID of ROOM: " + lr.room_list[i].get_id());
		return lr.room_list[i].get_id();
	}
	
	public boolean FindUser(String room_id, String userRent) throws ProtocolException, MalformedURLException, IOException {
		boolean check = false;
		String params = this.toString();
		//System.out.println("GetRoomId" + params);
		String s1= HttpRequest.HttpRequestPOST(Constant.GET_ROOM_LIST, params);
		Gson g = new Gson();
		ResponeGetRoomList lr = new ResponeGetRoomList();
		lr = g.fromJson(s1,ResponeGetRoomList.class);
//		System.out.println("GET ID of ROOM: " + lr.room_list[i].get_id());
		for(int i = 0; i < lr.room_list.length;i++) {
			if(lr.room_list[i]._id.equals(room_id)) {
				if(!lr.room_list[i].userRent.equals(null) && lr.room_list[i].userRent.equals(userRent)) check = true;
				break;
			}
		}
		return check;
	}
}

class ResponeGetRoomList{
	String code;
	String message;
	String s;
	RoomList[] room_list;
//	public ResponeGetRoomList() {
//		super();
//		this.room_list = new RoomList[100];
//		this.room_list[0] = new RoomList();
//	}
}

class RoomList{
	String userRent;
	String _id;
	String userMaster;
	String address;
	String price;
	int __v;
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
}