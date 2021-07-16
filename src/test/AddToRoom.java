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

public class AddToRoom {

	String token;
	String user_id;
	String room_id;
	
	public void AddToRoomTest() throws ProtocolException, MalformedURLException, IOException {
		
		Login[] login = new Login[16];
		String[] m_token = new String[16];
		String[] room_id = new String[16];
		String[] user_id = new String[16];
		String[] g_token = new String[16];
		AddRoom[] newRoom = new AddRoom[16] ;
		GetRoomList[] roomList = new GetRoomList[16];
		
		//master login
		for(int i = 1; i <= 15;i++) {
			int x = i+10;
			String id =  "0";
			login[i] = new Login(Constant.Login("master", id + x));
			m_token[i] = login[i].AcessToken();
		}
		String m_id12 = login[12].GetUserID();
		
		//get room_id
		for(int i = 11; i<= 11;i++) {
			newRoom[i] = new AddRoom(m_token[i]);
			newRoom[i].NewRoom();
			roomList[i] = new GetRoomList(m_token[i]);
			room_id[i] = roomList[i].GetRoomId(0);
		}
		
		// guest login
		for(int i = 1; i <= 15;i++) {
			
			String id = i>9 ? "0" : "00";
			login[i] = new Login(Constant.Login("guest", id + i));
			g_token[i] = login[i].AcessToken();
			user_id[i] = login[i].GetUserID();
		}
		
		System.out.println("TESTCASE: them khach vao phong tro.");
		String params;
		String result;
		
		System.out.println("UNIT TEST 1: luong chinh");
		params = "?" + "token=" + m_token[1] + "&"+ "user_id=" + user_id[1] + "&"+ "room_id=" + room_id[1];
		result = HttpRequest.HttpRequestPOST(Constant.ADD_TO_ROOM, params);
		Response unitest1 = toResponse(result);
		assert(unitest1.code.equals("1000"));
		assert(unitest1.message.equals("OK"));
		System.out.println("UNIT TEST 1: Thanh cong!");
		
		
		System.out.println("UNIT TEST 2: Doi cho tham so");
		params = "?" + "user_id=" + user_id[2] + "&"+ "token=" + m_token[2] + "&"+  "room_id=" + room_id[2];
		result = HttpRequest.HttpRequestPOST(Constant.ADD_TO_ROOM, params);
		Response unitest2 = toResponse(result);
		assert(unitest2.code.equals("1000"));
		assert(unitest2.message.equals("OK"));
		System.out.println("UNIT TEST 2: Thanh cong!");
		
		
		System.out.println("UNIT TEST 3: Thieu gia tri");
		params = "?" + "token=" + m_token[3] + "&"+ "user_id=" + user_id[3] + "&"+ "room_id=";
		result = HttpRequest.HttpRequestPOST(Constant.ADD_TO_ROOM, params);
		Response unitest3 = toResponse(result);
		assert(!unitest3.code.equals("1000"));
		assert(!unitest3.message.equals("OK"));
		System.out.println("UNIT TEST 3: Thanh cong!");
		
		
		System.out.println("UNIT TEST 4: Thua tham so");
		params = "?" + "token=" + m_token[4] + "&"+ "user_id=" + user_id[4] + "&"+ "time=" + "20210107" + "&"+ "room_id=" + room_id[4];
		result = HttpRequest.HttpRequestPOST(Constant.ADD_TO_ROOM, params);
		Response unitest4 = toResponse(result);
		assert(unitest4.code.equals("1000"));
		assert(unitest4.message.equals("OK"));
		System.out.println("UNIT TEST 4: Thanh cong!");
		
		
		System.out.println("UNIT TEST 5: Sai ten params");
		params = "?" + "user_token=" + m_token[5] + "&"+ "user=" + user_id[5] + "&"+ "room_id=" + room_id[5];
		result = HttpRequest.HttpRequestPOST(Constant.ADD_TO_ROOM, params);
		Response unitest5 = toResponse(result);
		assert(!unitest5.code.equals("1000"));
		assert(!unitest5.message.equals("OK"));
		System.out.println("UNIT TEST 5: Thanh cong!");
		
		
		System.out.println("UNIT TEST 6: Thuc hien 2 lan");
		params = "?" + "token=" + m_token[6] + "&"+ "user_id=" + user_id[6] + "&"+ "room_id=" + room_id[6];
		result = HttpRequest.HttpRequestPOST(Constant.ADD_TO_ROOM, params);
		result = HttpRequest.HttpRequestPOST(Constant.ADD_TO_ROOM, params);
		Response unitest6 = toResponse(result);
		assert(unitest6.code.equals("9000"));
		assert(unitest6.message.equals("Room was rented"));
		System.out.println("UNIT TEST 6: Thanh cong!");
		
		
		System.out.println("UNIT TEST 7: Phong da bi xoa");
		//delete room
		params = "?" + "token=" + m_token[7] + "&"+ "room_id=" + room_id[7];
		result = HttpRequest.HttpRequestPOST(Constant.DELETE_ROOM, params);
		
		params = "?" + "token=" + m_token[7] + "&"+ "user_id=" + user_id[7] + "&"+ "room_id=" + room_id[7];
		result = HttpRequest.HttpRequestPOST(Constant.ADD_TO_ROOM, params);
		Response unitest7 = toResponse(result);
		assert(!unitest7.code.equals(null));
		assert(!unitest7.code.equals("1000"));
		assert(!unitest7.message.equals("OK"));
		System.out.println("UNIT TEST 7: Thanh cong!");
		
		
		System.out.println("UNIT TEST 8: Phong da co nguoi thue");
		params = "?" + "token=" + g_token[1] + "&"+ "room_id=" + room_id[8];
		result = HttpRequest.HttpRequestPOST(Constant.RENT, params);
		
		params = "?" + "token=" + m_token[8] + "&"+ "user_id=" + user_id[8] + "&"+ "room_id=" + room_id[8];
		result = HttpRequest.HttpRequestPOST(Constant.ADD_TO_ROOM, params);
		Response unitest8 = toResponse(result);
		assert(unitest8.code.equals("9000"));
		assert(!unitest8.message.equals("Room was rented"));
		System.out.println("UNIT TEST 8: Thanh cong!");
		
		
		System.out.println("UNIT TEST 9: Tai khoan master khac truy cap");
		params = "?" + "token=" + m_token[2] + "&"+ "user_id=" + user_id[9] + "&"+ "room_id=" + room_id[9];
		result = HttpRequest.HttpRequestPOST(Constant.ADD_TO_ROOM, params);
		Response unitest9 = toResponse(result);
		assert(unitest9.code.equals("1009"));
		assert(unitest9.message.equals("Not access."));
		
		Login ad_login = new Login(Constant.Login("admin", "011"));
		String ad_token = ad_login.AcessToken();
		
		params = "?" + "token=" + ad_token + "&"+ "user_id=" + user_id[11] + "&"+ "room_id=" + room_id[11];
		result = HttpRequest.HttpRequestPOST(Constant.ADD_TO_ROOM, params);
		Response _unitest9 = toResponse(result);
		assert(_unitest9.code.equals("1009"));
		assert(_unitest9.message.equals("Not access."));
		
		System.out.println("UNIT TEST 9: Thanh cong!");
		
		System.out.println("UNIT TEST 10: Them tai khoan master vao phong tro");
		params = "?" + "token=" + m_token[10] + "&"+ "user_id=" + m_id12 + "&"+ "room_id=" + room_id[10];
		result = HttpRequest.HttpRequestPOST(Constant.ADD_TO_ROOM, params);
		Response unitest10 = toResponse(result);
		assert(!unitest10.code.equals("1000"));
		assert(!unitest10.message.equals("OK"));
		System.out.println("UNIT TEST 10: Thanh cong!");
		
		Login ad_login1 = new Login(Constant.Login("admin", "011"));
		String ad_id = ad_login1.GetUserID();
		
		System.out.println("UNIT TEST 11: Them tai khoan admin vao phong tro");
		params = "?" + "token=" + m_token[11] + "&"+ "user_id=" + ad_id + "&"+ "room_id=" + room_id[11];
		result = HttpRequest.HttpRequestPOST(Constant.ADD_TO_ROOM, params);
		Response unitest11 = toResponse(result);
		assert(!unitest11.code.equals("1000"));
		assert(!unitest11.message.equals("OK"));
		System.out.println("UNIT TEST 11: Thanh cong!");
		
	}
	
	public Response toResponse(String json) {
		Gson gson = new Gson();
		Response rp = gson.fromJson(json,Response.class);
		return rp;
	}
	
	
	
}
