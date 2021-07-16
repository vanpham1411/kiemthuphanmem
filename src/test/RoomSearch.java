package test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;

import com.google.gson.Gson;

import constant.Constant;
import main.HttpRequest;
import mainThread.AddRoom;
import mainThread.GetRoomList;
import mainThread.Login;

public class RoomSearch {

	String keyword;
	String index;
	String count;
	
	public RoomSearch() {
		super();
	}
	
	public RoomSearch(String keyword, String index, String count) {
		super();
		this.keyword = keyword;
		this.index = index;
		this.count = count;
	}
	
	public void RoomSearchTest() throws ProtocolException, MalformedURLException, IOException {
		System.out.println("TESTCASE: tim kiem phong tro");
		String params;
		String result;
		
		System.out.println("UNIT TEST 1: luong chinh");
		params = "?" + "keyword=so11" +"&" + "index=" +"0" + "&" + "count=" + "2"  ;
		result = HttpRequest.HttpRequestGET(Constant.ROOM_SEARCH, params);
		RoomSearchResponse unitest1 = toResponse(result);
		assert(!unitest1.code.equals(null));
		assert(unitest1.code.equals("1000"));
		assert(unitest1.message.equals("OK"));
		System.out.println("UNIT TEST 1: Thanh cong!");
		
		System.out.println("UNIT TEST 2: Sai ten params");
		params = "?" + "key=so11" +"&" + "index=" +"0" + "&" + "count=" + "2"  ;
		result = HttpRequest.HttpRequestGET(Constant.ROOM_SEARCH, params);
		RoomSearchResponse unitest2 = toResponse(result);
		assert(!unitest2.code.equals(null));
		assert(!unitest2.code.equals("1000"));
		assert(!unitest2.message.equals("OK"));
		System.out.println("UNIT TEST 2: Thanh cong!");
		
		System.out.println("UNIT TEST 3: Doi cho tham so");
		params = "?" + "index=" +"0" + "&"  + "keyword=so" +"&" +  "count=" + "2"  ;
		result = HttpRequest.HttpRequestGET(Constant.ROOM_SEARCH, params);
		RoomSearchResponse unitest3 = toResponse(result);
		assert(!unitest3.code.equals(null));
		assert(unitest3.code.equals("1000"));
		assert(unitest3.message.equals("OK"));
		System.out.println("UNIT TEST 3: Thanh cong!");
		
		System.out.println("UNIT TEST 4: thua tham so");
		params = "?" + "keyword=so11" +"&" + "index=" +"0" + "&" + "count=" + "2" + "&value=012"  ;
		result = HttpRequest.HttpRequestGET(Constant.ROOM_SEARCH, params);
		RoomSearchResponse unitest4 = toResponse(result);
		assert(!unitest4.code.equals(null));
		assert(unitest4.code.equals("1000"));
		assert(unitest4.message.equals("OK"));
		System.out.println("UNIT TEST 4: Thanh cong!");
		
		System.out.println("UNIT TEST 5: thieu tham so");
		params = "?" + "keyword=so" +"&" + "index=" +"0";
		result = HttpRequest.HttpRequestGET(Constant.ROOM_SEARCH, params);
		RoomSearchResponse unitest5 = toResponse(result);
		assert(!unitest5.code.equals(null));
		assert(!unitest5.code.equals("1000"));
		assert(!unitest5.message.equals("OK"));
		System.out.println("UNIT TEST 5: Thanh cong!");


		System.out.println("UNIT TEST 6: index khong hop le");
		params = "?" + "keyword=so11" +"&" + "index=" +"-1" + "&" + "count=" + "2"  ;
		result = HttpRequest.HttpRequestGET(Constant.ROOM_SEARCH, params);
		RoomSearchResponse unitest6 = toResponse(result);
		assert(!unitest6.code.equals(null));
		assert(!unitest6.code.equals("1000"));
		assert(!unitest6.message.equals("OK"));
		System.out.println("UNIT TEST 6: Thanh cong!");
		
		System.out.println("UNIT TEST 7: count khong hop le");
		params = "?" + "keyword=so11" +"&" + "index=" +"0" + "&" + "count=" + "-25"  ;
		result = HttpRequest.HttpRequestGET(Constant.ROOM_SEARCH, params);
		RoomSearchResponse unitest7 = toResponse(result);
		assert(!unitest7.code.equals(null));
		assert(!unitest7.code.equals("1000"));
		assert(!unitest7.message.equals("OK"));
		System.out.println("UNIT TEST 7: Thanh cong!");
		
		System.out.println("UNIT TEST 8: Chua chu cai viet hoa");
		Login m_login = new Login(Constant.Login("master", "001"));
		String m_token = m_login.AcessToken();
		AddRoom addRoom = new AddRoom(m_token, "HaNoi","img1","1500000");
		params = "?" + "keyword=HaNoi" +"&" + "index=" +"0" + "&" + "count=" + "2"  ;
		result = HttpRequest.HttpRequestGET(Constant.ROOM_SEARCH, params);
		RoomSearchResponse unitest8 = toResponse(result);
		assert(!unitest8.code.equals(null));
		assert(unitest8.code.equals("1000"));
		assert(unitest8.message.equals("OK"));
		System.out.println("UNIT TEST 8: Thanh cong!");
		
		
		System.out.println("UNIT TEST 9: Do dai can tim bang 0");
		params = "?" + "keyword=" +"&" + "index=" +"0" + "&" + "count=" + "2"  ;
		result = HttpRequest.HttpRequestGET(Constant.ROOM_SEARCH, params);
		RoomSearchResponse unitest9 = toResponse(result);
		assert(!unitest9.code.equals(null));
		assert(!unitest9.code.equals("1000"));
		assert(!unitest9.message.equals("OK"));
		System.out.println("UNIT TEST 9: Thanh cong!");
		
		
		System.out.println("UNIT TEST 10: phong tro da bi xoa");
		Login login = new Login(Constant.Login("master", "011"));
		String token = login.AcessToken();
		
		AddRoom addNewRoom = new AddRoom(token,"abx222","image1","1500000d");
		addNewRoom.NewRoom();
		GetRoomList room = new GetRoomList(token);
		String room_id = room.GetRoomId(0);
		params = "?" + "keyword=abx222" +"&" + "index=" +"0" + "&" + "count=" + "20"  ;
		result = HttpRequest.HttpRequestGET(Constant.ROOM_SEARCH, params);
		RoomSearchResponse unitest10 = toResponse(result);
		assert(!unitest10.code.equals(null));
		assert(!unitest10.code.equals("1000"));
		assert(!unitest10.message.equals("OK"));
		String delroom_params = "?"  + "token=" + token + "&"+ "room_id="+ room_id;
		HttpRequest.HttpRequestPOST(Constant.DELETE_ROOM, delroom_params);
		
		
		params = "?" + "keyword=abx222" +"&" + "index=" +"0" + "&" + "count=" + "20"  ;
		result = HttpRequest.HttpRequestGET(Constant.ROOM_SEARCH, params);
		RoomSearchResponse unitest10_2 = toResponse(result);
		assert(!unitest10_2.code.equals(null));
		int cnt=0;
		if(!unitest10_2.code.equals("9994")) cnt = unitest10_2.room_list.length;
		assert( cnt< unitest10.room_list.length);
		System.out.println("UNIT TEST 10: Thanh cong!");
		
	}
	
	public RoomSearchResponse toResponse(String json) {
		Gson gson = new Gson();
		RoomSearchResponse rp = gson.fromJson(json,RoomSearchResponse.class);
		return rp;
	}
}

class RoomSearchResponse{
	String code;
	String message;
	RoomList[] room_list;
}

class RoomList{
	String _id;
	String userRent;
	String userMaster;
	String address;
	String price;
	String image;
}











