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

public class ReceiveTenant {
	String token;
	String host_id;
	String guest_id;
	String confirm;
	String room_id;
	
		
	public ReceiveTenant() {
		super();
	}



	public ReceiveTenant(String token, String host_id, String guest_id, String confirm) {
		super();
		this.token = token;
		this.host_id = host_id;
		this.guest_id = guest_id;
		this.confirm = confirm;
	}
	
	

	public ReceiveTenant(String token, String host_id, String guest_id, String confirm, String room_id) {
		super();
		this.token = token;
		this.host_id = host_id;
		this.guest_id = guest_id;
		this.confirm = confirm;
		this.room_id = room_id;
	}



	public void Receive() throws ProtocolException, MalformedURLException, IOException {
		String params = "?" + "token=" + this.token + "&" + "host_id=" + this.host_id + "&" + "guest_id=" + this.guest_id + "&"+ "confirm=" + this.confirm + "&" + "room_id=" + this.room_id;
		String s = HttpRequest.HttpRequestPOST(Constant.RECEIVE_TENANT, params);
	}
	
	public void Refuse() throws ProtocolException, MalformedURLException, IOException {
		String params = "?" + "&" + "token=" + this.token + "&" + "host_id=" + this.host_id + "&" + "guest_id=" + this.guest_id + "&"+ "confirm=" + this.confirm;
		String s = HttpRequest.HttpRequestPOST(Constant.RECEIVE_TENANT, params);
	}
	
	public void ReceiveTenantTest() throws ProtocolException, MalformedURLException, IOException {
		Login[] login = new Login[16];
		String[] m_token = new String[16];
		String[] tmp_room_id = new String[16];
		String[] room_id = new String[16];
		String[] guest_id = new String[16];
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
			tmp_room_id[i] = roomList[i].GetRoomId(0);	
			newRoom[i].NewRoom();
			roomList[i] = new GetRoomList(m_token[i]);
			room_id[i] = roomList[i].GetRoomId(0);	
		}
		
		// guest login
		for(int i = 1; i <= 15;i++) {
			
			String id = i>9 ? "0" : "00";
			login[i] = new Login(Constant.Login("guest", id + i));
			g_token[i] = login[i].AcessToken();
			guest_id[i] = login[i].GetUserID();
		}
		
		//Tenant Rent
		TenantRent[] tn = new TenantRent[16];
		for(int i = 1; i<= 15; i++) {
			tn[i] = new TenantRent(g_token[i], tmp_room_id[i]);
			tn[i].Rent();
		}
		
		// TenantTransfer
		for(int i = 1; i<= 14;i++) {
			TenantTransfer[]  tenant = new TenantTransfer[15];
			tenant[i] = new TenantTransfer(m_token[i], host_id[i], guest_id[i], tmp_room_id[i]);
			tenant[i].Transfer();
		}
		
		System.out.println("TESTCASE: Nhan khach tu chu tro khac.");
		String params;
		String result;
		
		System.out.println("UNITEST 1: Luong chinh: chap nhan");
		params = "?" + "token=" + m_token[2] + "&"+ "host_id=" + host_id[1]+ "&" + "guest_id=" + guest_id[1]+ "&" + "confirm=1" + "&"+ "room_id=" + room_id[2];
		result = HttpRequest.HttpRequestPOST(Constant.RECEIVE_TENANT, params);
		Response unitest1 = toResponse(result);
		assert(!unitest1.code.equals(null));
		assert(unitest1.code.equals("1000"));
		assert(unitest1.message.equals("OK"));
		System.out.println("UNIT TEST 1: Thanh cong!");
		
		System.out.println("UNITEST 1: Luong chinh: Tu choi");
		params = "?" + "token=" + m_token[10] + "&"+ "host_id=" + host_id[9]+ "&" + "guest_id=" + guest_id[9]+ "&" + "confirm=0";
		result = HttpRequest.HttpRequestPOST(Constant.RECEIVE_TENANT, params);
		Response unitest1_2 = toResponse(result);
		assert(!unitest1_2.code.equals(null));
		assert(unitest1_2.code.equals("1000"));
		assert(unitest1_2.message.equals("OK"));
		System.out.println("UNIT TEST 1: Thanh cong!");
		
		
		System.out.println("UNITEST 2: Doi cho tham so");
		params = "?" + "token=" + m_token[3]  + "&" + "guest_id=" + guest_id[2]+ "&" + "confirm=1" + "&"+ "host_id=" + host_id[2] + "&"+ "room_id=" + room_id[3];
		result = HttpRequest.HttpRequestPOST(Constant.RECEIVE_TENANT, params);
		Response unitest2 = toResponse(result);
		assert(!unitest2.code.equals(null));
		assert(unitest2.code.equals("1000"));
		assert(unitest2.message.equals("OK"));
		System.out.println("UNIT TEST 2: Thanh cong!");
		
		System.out.println("UNITEST 3: thieu gia tri");
		params = "?" + "token=" + m_token[4] + "&"+ "host_id=" + host_id[3]+ "&" + "guest_id=" + guest_id[3]+ "&" + "confirm=1";
		result = HttpRequest.HttpRequestPOST(Constant.RECEIVE_TENANT, params);
		Response unitest3 = toResponse(result);
		assert(!unitest3.code.equals(null));
		assert(!unitest3.code.equals("1000"));
		assert(!unitest3.message.equals("OK"));
		System.out.println("UNIT TEST 3: Thanh cong!");
		
		System.out.println("UNITEST 4: Thua tham so");
		params = "?" + "token=" + m_token[5] + "&"+ "host_id=" + host_id[4]+ "&" + "guest_id=" + guest_id[4]+ "&" + "confirm=1" + "&"+ "room_id=" + room_id[5] + "&status=0";
		result = HttpRequest.HttpRequestPOST(Constant.RECEIVE_TENANT, params);
		Response unitest4 = toResponse(result);
		assert(!unitest4.code.equals(null));
		assert(unitest4.code.equals("1000"));
		assert(unitest4.message.equals("OK"));
		System.out.println("UNIT TEST 4: Thanh cong!");
		
		System.out.println("UNITEST 5: Sai ten params");
		params = "?" + "token=" + m_token[6] + "&"+ "host_id=" + host_id[5]+ "&" + "guest_id=" + guest_id[5]+ "&" + "confirm=1" + "&"+ "room=" + room_id[6];
		result = HttpRequest.HttpRequestPOST(Constant.RECEIVE_TENANT, params);
		Response unitest5 = toResponse(result);
		assert(!unitest5.code.equals(null));
		assert(unitest5.code.equals("1004"));
		assert(!unitest5.message.equals("OK"));
		System.out.println("UNIT TEST 5: Thanh cong!");
		
		System.out.println("UNITEST 6: Phong da cho thue");
		TenantRent rent6 = new TenantRent(g_token[1], room_id[7]);
		rent6.Rent();
		params = "?" + "token=" + m_token[7] + "&"+ "host_id=" + host_id[6]+ "&" + "guest_id=" + guest_id[6]+ "&" + "confirm=1" + "&"+ "room_id=" + room_id[7];
		result = HttpRequest.HttpRequestPOST(Constant.RECEIVE_TENANT, params);
		Response unitest6 = toResponse(result);
		assert(!unitest6.code.equals(null));
		assert(!unitest6.code.equals("1000"));
		assert(!unitest6.message.equals("OK"));
		System.out.println("UNIT TEST 6: Thanh cong!");
		
		System.out.println("UNITEST 7: Phong da bi xoa");
		String delete_params = "?" + "token=" + m_token[8] + "&"+ "room_id=" + room_id[8];
		HttpRequest.HttpRequestPOST(Constant.DELETE_ROOM, delete_params);
		
		params = "?" + "token=" + m_token[8] + "&"+ "host_id=" + host_id[7]+ "&" + "guest_id=" + guest_id[7]+ "&" + "confirm=1" + "&"+ "room_id=" + room_id[8];
		result = HttpRequest.HttpRequestPOST(Constant.RECEIVE_TENANT, params);
		Response unitest7 = toResponse(result);
		assert(!unitest7.code.equals(null));
		assert(!unitest7.code.equals("1000"));
		assert(!unitest7.message.equals("OK"));
		System.out.println("UNIT TEST 7: Thanh cong!");
		
		System.out.println("UNITEST 8: Gia tri khong hop le");
		params = "?" + "token=" + m_token[9] + "&"+ "host_id=" + host_id[8]+ "&" + "guest_id=" + guest_id[8]+ "&" + "confirm=0" ;
		result = HttpRequest.HttpRequestPOST(Constant.RECEIVE_TENANT, params);
		Response unitest8 = toResponse(result);
		assert(!unitest8.code.equals(null));
		assert(!unitest8.code.equals("1000"));
		assert(!unitest8.message.equals("OK"));
		System.out.println("UNIT TEST 8: Thanh cong!");
		
		
		
	}
	
	public Response toResponse(String json) {
		Gson gson = new Gson();
		Response rp = gson.fromJson(json,Response.class);
		return rp;
	}

}
