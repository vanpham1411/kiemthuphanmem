package test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;

import com.google.gson.Gson;

import constant.Constant;
import mainThread.AddRoom;
import mainThread.GetRoomList;
import mainThread.Login;
import main.HttpRequest;


public class TenantRent extends Tenant {
	
	public TenantRent() {
		super();
	}
	
	public TenantRent(String token, String room_id) {
		super(token,room_id);
	}
	
	
	

	public void TenantRentTest() throws ProtocolException, MalformedURLException, IOException {
		
		System.out.println("Test case thue phong");
		
		Login master = new Login(Constant.Login("master","001"));
		String token_master = master.AcessToken();
		AddRoom addroom = new AddRoom(token_master);
		String[] room_id = addroom.init(token_master);
		//addroom.NewRoom();
//		GetRoomList roomList = new GetRoomList(token_master);
//		room_id[11] = roomList.GetRoomId(0);
		
		System.out.println("UNIT TEST 1: luong chinh");
		//master add new room
		Login guest001 = new Login(Constant.Login("guest","001"));
		String token_guest001 = guest001.AcessToken();
		String params;
		String s1;
		
		params = "?" + "token=" + token_guest001 + "&"+ "room_id=" + room_id[1];
		s1 = HttpRequest.HttpRequestPOST(Constant.RENT, params);
		Response unitest1 = toResponse(s1);
		assert(!unitest1.code.equals(null));
		assert(unitest1.code.equals("1000"));
		assert(unitest1.message.equals("OK"));
		System.out.println("UNIT TEST 1: Thanh cong!");
		
		System.out.println("UNIT TEST 2: doi cho cac tham so");
		//master001 add new room
		Login guest002 = new Login(Constant.Login("guest","002"));
		String token_guest002 = guest002.AcessToken();
		params = "";
		params = "?" + "room_id=" + room_id[2] + "&" + "token=" + token_guest002;
		s1 = HttpRequest.HttpRequestPOST(Constant.RENT, params);
		
		Response unitest2 = toResponse(s1);
		assert(!unitest2.code.equals(null));
		assert(unitest2.code.equals("1000"));
		assert(unitest2.message.equals("OK"));
		System.out.println("UNIT TEST 2: Thanh cong!");
		
		System.out.println("UNIT TEST 3: Thieu gia tri");
		Login guest003 = new Login(Constant.Login("guest","003"));
		String token_guest003 = guest003.AcessToken();
		
		params = "";
		params = "?"+ "token=" + token_guest003+ "&" + "room_id";
		s1 = HttpRequest.HttpRequestPOST(Constant.RENT, params);
		Response unitest3 = toResponse(s1);
		assert(!unitest3.code.equals(null));
		assert(unitest3.code.equals("1004"));
		assert(!unitest3.message.equals("OK"));
		System.out.println("UNIT TEST 3: Thanh cong!");
		
		System.out.println("UNIT TEST 4: them tham so moi o dang sau");
		Login guest004 = new Login(Constant.Login("guest","004"));
		String token_guest004 = guest004.AcessToken();
		//master001 add new room
		params = "";
		params = "?" + "token=" + token_guest004+"&" + "room_id=" + room_id[4] + "&" + "price=1800000";
		s1 = HttpRequest.HttpRequestPOST(Constant.RENT, params);
		Response unitest4 = toResponse(s1);
		assert(!unitest4.code.equals(null));
		assert(unitest4.code.equals("1000"));
		assert(unitest4.message.equals("OK"));
		System.out.println("UNIT TEST 4: Thanh cong!");
	
		
		System.out.println("UNIT TEST 5: thuc hien 2 lan");
		Login guest006 = new Login(Constant.Login("guest","006"));
		String token_guest006 = guest006.AcessToken();
		//master add new room
				
				
		params="";
		params = "?" + "token=" + token_guest006 + "&"+ "room_id=" + room_id[6];
		s1 = HttpRequest.HttpRequestPOST(Constant.RENT, params);
		Response _unitest6 = toResponse(s1);
		assert(_unitest6.code.equals("1000"));
		assert(_unitest6.message.equals("OK"));
		s1 = HttpRequest.HttpRequestPOST(Constant.RENT, params);
		Response unitest6 = toResponse(s1);
		assert(unitest6.code.equals("9000"));
		assert(!unitest6.message.equals("OK"));
		System.out.println("UNIT TEST 5: Thanh cong!");
		
		System.out.println("UNIT TEST 6: hai tai khoan cung thuc hien");
		// guest login
		Login guest007 = new Login(Constant.Login("guest","007"));
		String token_guest007 = guest007.AcessToken();
		
		params="";
		params = "?" + "token=" + token_guest007 + "&"+ "room_id=" + room_id[7];
		s1 = HttpRequest.HttpRequestPOST(Constant.RENT, params);
		Login guest010 = new Login(Constant.Login("guest","010"));
		String token_guest010 = guest010.AcessToken();
		
		
		params="";
		params = "?" + "token=" + token_guest010 + "&"+ "room_id=" + room_id[7];
		s1 = HttpRequest.HttpRequestPOST(Constant.RENT, params);
		Response unitest7 = toResponse(s1);
		assert(unitest7.code.equals("9000"));
		assert(!unitest7.message.equals("OK"));
		System.out.println("UNIT TEST 6: Thanh cong!");
		
		System.out.println("UNIT TEST 7: gia tri sai");
		
		Login guest008 = new Login(Constant.Login("guest","008"));
		String token_guest008 = guest008.AcessToken();
		//master add new room
				
		params="";
		params = "?" + "token=" + token_guest008 + "&"+ "room_id=" + room_id[8] + "000";
		s1 = HttpRequest.HttpRequestPOST(Constant.RENT, params);
		Response unitest8 = toResponse(s1);
		assert(!unitest8.code.equals(null));
		assert(!unitest8.code.equals("1000"));
		assert(!unitest8.message.equals("OK"));
		System.out.println("UNIT TEST 7: Thanh cong!");
		
		System.out.println("UNIT TEST 8: sai ten param");
		
		//master add new room
		Login guest009 = new Login(Constant.Login("guest","009"));
		String token_guest009 = guest009.AcessToken();
		
		params="";
		params = "?" + "access_token=" + token_guest009 + "&"+ "room_id=" + room_id[9];
		s1 = HttpRequest.HttpRequestPOST(Constant.RENT, params);
		Response unitest9 = toResponse(s1);
		assert(!unitest9.code.equals(null));
		assert(!unitest9.code.equals("1000"));
		assert(!unitest9.message.equals("OK"));
		System.out.println("UNIT TEST 8: Thanh cong!");
		
		System.out.println("UNIT TEST 9: Phong tro bi xoa");
		//master add new room
		Login guest011 = new Login(Constant.Login("guest","011"));
		String token_guest011 = guest011.AcessToken(); 
		
		String params_delete = "?" + "token=" + token_master + "&"+ "room_id=" + room_id[10];
		HttpRequest.HttpRequestPOST(Constant.DELETE_ROOM, params_delete);
		
	    params = "?" + "token=" + token_guest011 + "&"+ "room_id=" + room_id[10];
		s1 = HttpRequest.HttpRequestPOST(Constant.RENT, params);
		Response unitest10 = toResponse(s1);
		assert(!unitest10.code.equals(null));
		assert(!unitest10.code.equals("1000"));
		assert(!unitest10.message.equals("OK"));
		System.out.println("UNIT TEST 9: Thanh cong!");
		
		System.out.println("UNIT TEST 10: Thue phong voi vai tro admin");
		//master add new room
		Login admin001 = new Login(Constant.Login("admin","011"));
		String token_admin001 = admin001.AcessToken();
		
		params = "?" + "token=" + token_guest001 + "&"+ "room_id=" + room_id[11];
		s1 = HttpRequest.HttpRequestPOST(Constant.RENT, params);
		Response unitest11 = toResponse(s1);
		assert(!unitest11.code.equals(null));
		assert(unitest11.code.equals("1009"));
		assert(unitest11.message.equals("Not access."));
		System.out.println("UNIT TEST 10: Thanh cong!");
			
		
		System.out.println("UNIT TEST 11: Thue phong voi vai tro master");
		params = "?" + "token=" + token_master + "&"+ "room_id=" + room_id[11];
		s1 = HttpRequest.HttpRequestPOST(Constant.RENT, params);
		Response unitest11_1 = toResponse(s1);
		assert(!unitest11_1.code.equals(null));
		assert(unitest11_1.code.equals("1009"));
		assert(unitest11_1.message.equals("Not access."));
		System.out.println("UNIT TEST 11: Thanh cong!");
//			
	}
	
	
	
	public void Rent() throws ProtocolException, MalformedURLException, IOException {
		String params = toString();
		String s1 = HttpRequest.HttpRequestPOST(Constant.RENT, params);
	}

}


