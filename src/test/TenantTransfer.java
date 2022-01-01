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

public class TenantTransfer {

	String token;
	String host_id;
	String guest_id;
	String room_id;
	
	public TenantTransfer() {
		super();
	}
	
	public TenantTransfer(String token, String host_id, String guest_id, String room_id) {
		super();
		this.token = token;
		this.host_id = host_id;
		this.guest_id = guest_id;
		this.room_id = room_id;
	}

	public void Transfer() throws ProtocolException, MalformedURLException, IOException {
		String params = "?" + "token=" + this.token + "&"+ "host_id=" + this.host_id+ "&" + "guest_id=" + this.guest_id + "&"+ "room_id=" + this.room_id;
		String s = HttpRequest.HttpRequestPOST(Constant.TENANT_TRANSFER, params);
	}
	public void TenantTransferTest() throws ProtocolException, MalformedURLException, IOException {
		Login[] login = new Login[16];
		String[] m_token = new String[16];
		String[] room_id = new String[16];
		String[] user_id = new String[16];
		String[] host_id = new String[16];
		String[] g_token = new String[16];
		AddRoom[] newRoom = new AddRoom[16] ;
		GetRoomList[] roomList = new GetRoomList[16];
		
		//master login
		for(int i = 1; i <= 15;i++) {
			int x = i+10;
			String id =  "0";
			login[i] = new Login(Constant.Login("master", id + x));
			m_token[i] = login[i].AcessToken();
			host_id[i-1] = login[i].GetUserID();
		}
		
		//get room_id
		for(int i = 1; i<= 15;i++) {
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
		
		//Add to room
		String tmpParams = "";
		String tmpResult;
		for(int i = 1; i<= 15; i++) {
			if(i != 6) {
				tmpParams = "";
				tmpParams = "?" + "token=" + m_token[i] + "&"+ "user_id=" + user_id[i] + "&"+ "room_id=" + room_id[i];
				tmpResult  = HttpRequest.HttpRequestPOST(Constant.ADD_TO_ROOM, tmpParams);
			}
		}
		
		System.out.println("TESTCASE: Gui khach thue sang chu tro khac.");
		String params;
		String result;
		
		System.out.println("UNIT TEST 1: luong chinh");
		params = "?" + "token=" + m_token[1] + "&"+ "host_id=" + host_id[1]+ "&" + "guest_id=" + user_id[1] + "&"+ "room_id=" + room_id[1];
		result = HttpRequest.HttpRequestPOST(Constant.TENANT_TRANSFER, params);
		Response unitest1 = toResponse(result);
		assert(unitest1.code.equals("1000"));
		assert(unitest1.message.equals("OK"));
		System.out.println("UNIT TEST 1: Thanh cong!");
		
		System.out.println("UNIT TEST 2: Doi cho tham so");
		params = "?" + "token=" + m_token[2] + "&" + "guest_id=" + user_id[2]  + "&"+ "host_id=" + host_id[2] + "&"+ "room_id=" + room_id[2];
		result = HttpRequest.HttpRequestPOST(Constant.TENANT_TRANSFER, params);
		Response unitest2 = toResponse(result);
		assert(unitest2.code.equals("1000"));
		assert(unitest2.message.equals("OK"));
		System.out.println("UNIT TEST 2: Thanh cong!");
		
		
		System.out.println("UNIT TEST 3: Thieu gia tri");
		params = "?" + "token=" + m_token[3] + "&"+ "host_id=" + host_id[3] + "&"+ "guest_id=" + user_id[3] + "&"+ "room_id";
		result = HttpRequest.HttpRequestPOST(Constant.TENANT_TRANSFER, params);
		Response unitest3 = toResponse(result);
		assert(unitest3.code.equals("1004"));
		assert(!unitest3.message.equals("OK"));
		System.out.println("UNIT TEST 3: Thanh cong!");
		
		
		System.out.println("UNIT TEST 4: Thua tham so");
		params = "?" + "token=" + m_token[4] + "&"+ "host=" + host_id[4]  + "&"+ "guest_id=" + user_id[4] + "&"+ "time=" + "20210107" + "&"+ "room_id=" + room_id[4];
		result = HttpRequest.HttpRequestPOST(Constant.TENANT_TRANSFER, params);
		Response unitest4 = toResponse(result);
		assert(unitest4.code.equals("1000"));
		assert(unitest4.message.equals("OK"));
		System.out.println("UNIT TEST 4: Thanh cong!");
		
		
		System.out.println("UNIT TEST 5: Sai ten params");
		params = "?" + "token=" + m_token[5]+ "&" + "host_id=" + host_id[5] + "&"+ "user=" + user_id[5] + "&"+ "room_id=" + room_id[5];
		result = HttpRequest.HttpRequestPOST(Constant.TENANT_TRANSFER, params);
		Response unitest5 = toResponse(result);
		assert(!unitest5.code.equals("1000"));
		assert(!unitest5.message.equals("OK"));
		System.out.println("UNIT TEST 5: Thanh cong!");
		
		System.out.println("UNIT TEST 6: Chua thue phong");
		params = "?" + "token=" + m_token[6] + "&"+ "host_id=" + host_id[6]+ "&" + "guest_id=" + user_id[6] + "&"+ "room_id=" + room_id[6];
		result = HttpRequest.HttpRequestPOST(Constant.TENANT_TRANSFER, params);
		Response unitest6 = toResponse(result);
		assert(!unitest6.code.equals("1000"));
		assert(!unitest6.message.equals("OK"));
		System.out.println("UNIT TEST 6: Thanh cong!");
		
		System.out.println("UNIT TEST 7: Thuc hien 2 lan");
		params = "?" + "token=" + m_token[7] + "&"+ "host_id=" + host_id[7]+ "&" + "guest_id=" + user_id[7] + "&"+ "room_id=" + room_id[7];
		result = HttpRequest.HttpRequestPOST(Constant.TENANT_TRANSFER, params);
		result = HttpRequest.HttpRequestPOST(Constant.TENANT_TRANSFER, params);
		Response unitest7 = toResponse(result);
		assert(!unitest7.code.equals("1000"));
		assert(!unitest7.message.equals("OK"));
		System.out.println("UNIT TEST 7: Thanh cong!");
		
		System.out.println("UNITEST 8: master khac truy cap");
		params = "?" + "token=" + m_token[7] + "&"+ "host_id=" + host_id[8]+ "&" + "guest_id=" + user_id[8] + "&"+ "room_id=" + room_id[8];
		result = HttpRequest.HttpRequestPOST(Constant.TENANT_TRANSFER, params);
		Response unitest8 = toResponse(result);
		assert(!unitest8.code.equals("1000"));
		assert(!unitest8.message.equals("OK"));
		System.out.println("UNIT TEST 8: Thanh cong!");
		
		
		System.out.println("UNITEST 9: Da huy viec thue phong");
		RemoveFromRoom rm = new RemoveFromRoom(m_token[9],user_id[9],room_id[9]);
		rm.Remove();
		
		params = "?" + "token=" + m_token[9] + "&"+ "host_id=" + host_id[9]+ "&" + "guest_id=" + user_id[9] + "&"+ "room_id=" + room_id[9];
		result = HttpRequest.HttpRequestPOST(Constant.TENANT_TRANSFER, params);
		Response unitest9 = toResponse(result);
		assert(!unitest9.code.equals("1000"));
		assert(!unitest9.message.equals("OK"));
		System.out.println("UNIT TEST 9: Thanh cong!");
		
		System.out.println("UNITEST 10: chu tro trung nhau");
		params = "?" + "token=" + m_token[11] + "&"+ "host_id=" + host_id[10]+ "&" + "guest_id=" + user_id[11] + "&"+ "room_id=" + room_id[11];
		result = HttpRequest.HttpRequestPOST(Constant.TENANT_TRANSFER, params);
		Response unitest10 = toResponse(result);
		assert(!unitest10.code.equals("1000"));
		assert(!unitest10.message.equals("OK"));
		System.out.println("UNIT TEST 10: Thanh cong!");
		
		System.out.println("UNITEST 11: host_id cua khach");
		params = "?" + "token=" + m_token[10] + "&"+ "host_id=" + user_id[11]+ "&" + "guest_id=" + user_id[10] + "&"+ "room_id=" + room_id[10];
		result = HttpRequest.HttpRequestPOST(Constant.TENANT_TRANSFER, params);
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
