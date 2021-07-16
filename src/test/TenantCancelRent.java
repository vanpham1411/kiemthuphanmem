package test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;

import constant.Constant;
import main.HttpRequest;
import mainThread.AddRoom;
import mainThread.GetRoomList;
import mainThread.Login;

public class TenantCancelRent extends Tenant {
	
	public void init() {
		
	}
	
	public void TenantCancelRentTest() throws ProtocolException, MalformedURLException, IOException {

		Login login_master = new Login(Constant.Login("master","002"));
		String token_master = login_master.AcessToken();
		AddRoom addroom = new AddRoom();
		String[] room_id = addroom.init(token_master);
		
		Login[] login = new Login[11];
		
		String[] g_token = new String[11];
		for(int i = 1; i <= 10;i++) {
			String id = i > 9 ? "0" : "00";
			login[i] = new Login(Constant.Login("guest", id + i));
			g_token[i] = login[i].AcessToken();
		}
		
		System.out.println("Test case Huy thue phong");
		String params;
		String result;
		
		System.out.println("UNIT TEST 1: luong chinh");
		TenantRent rent1 = new TenantRent(g_token[1],room_id[1]);
		rent1.Rent();
		params = "";
		params = "?" + "token=" + g_token[1] + "&" + "room_id=" + room_id[1];
		
		result = HttpRequest.HttpRequestPOST(Constant.CANCEL_RENT, params);
		Response unitest1 = toResponse(result);
		assert(unitest1.code.equals("1000"));
		assert(unitest1.message.equals("OK"));
		System.out.println("UNIT TEST 1: Thanh cong!");
		
		System.out.println("UNIT TEST 2: Doi cho tham so");
		TenantRent rent2 = new TenantRent(g_token[2],room_id[2]);
		rent2.Rent();
		params = "";
		params = "?"   + "room_id=" + room_id[2] + "&" + "token=" + g_token[2];
		
		result = HttpRequest.HttpRequestPOST(Constant.CANCEL_RENT, params);
		Response unitest2 = toResponse(result);
		assert(unitest2.code.equals("1000"));
		assert(unitest2.message.equals("OK"));
		System.out.println("UNIT TEST 2: Thanh cong!");
		
		System.out.println("UNIT TEST 3: Thieu gia tri");
		TenantRent rent3 = new TenantRent(g_token[3],room_id[3]);
		rent3.Rent();
		params = "";
		params = "?" + "token=" + g_token[3] + "&" + "room_id=";
		
		result = HttpRequest.HttpRequestPOST(Constant.CANCEL_RENT, params);
		Response unitest3 = toResponse(result);
		assert(unitest3.code.equals("1004"));
		assert(unitest3.message.equals("Parameter value is invalid"));
		System.out.println("UNIT TEST 3: Thanh cong!");
		
		System.out.println("UNIT TEST 4: Thua tham so");
		TenantRent rent4 = new TenantRent(g_token[4],room_id[4]);
		rent4.Rent();
		params = "";
		params = "?" + "token=" + g_token[4]+ "&" + "user_id" + "1029373" + "&" + "room_id=" + room_id[4];
		
		result = HttpRequest.HttpRequestPOST(Constant.CANCEL_RENT, params);
		Response unitest4 = toResponse(result);
		assert(unitest4.code.equals("1000"));
		assert(unitest4.message.equals("OK"));
		System.out.println("UNIT TEST 4: Thanh cong!");
		
		System.out.println("UNIT TEST 5: Thuc hien 2 lan");
		TenantRent rent5 = new TenantRent(g_token[5],room_id[5]);
		rent5.Rent();
		params = "";
		params = "?" + "token=" + g_token[5] + "&" + "room_id=" + room_id[5];
		
		result = HttpRequest.HttpRequestPOST(Constant.CANCEL_RENT, params);
		result = HttpRequest.HttpRequestPOST(Constant.CANCEL_RENT, params);
		Response unitest5 = toResponse(result);
		assert(unitest5.code.equals("8889"));
		assert(unitest5.message.equals("You do not rent this room"));
		System.out.println("UNIT TEST 5: Thanh cong!");
		
		System.out.println("UNIT TEST 6: Phong chua duoc thue");
		params = "";
		params = "?" + "token=" + g_token[6] + "&" + "room_id=" + room_id[6];
		
		result = HttpRequest.HttpRequestPOST(Constant.CANCEL_RENT, params);
		Response unitest6 = toResponse(result);
		assert(unitest6.code.equals("8889"));
		assert(unitest6.message.equals("You do not rent this room"));
		System.out.println("UNIT TEST 6: Thanh cong!");
		
		System.out.println("UNIT TEST 7: Sai ten params");
		TenantRent rent7 = new TenantRent(g_token[7],room_id[7]);
		rent7.Rent();
		params = "";
		params = "?" + "access_token=" + g_token[7] + "&" + "room_id=" + room_id[7];
		
		result = HttpRequest.HttpRequestPOST(Constant.CANCEL_RENT, params);
		Response unitest7 = toResponse(result);
		assert(unitest7.code.equals("9998"));
		assert(unitest7.message.equals("Token is invalid"));
		System.out.println("UNIT TEST 7: Thanh cong!");
		
		System.out.println("UNIT TEST 8: Sai ten params");
		TenantRent rent8 = new TenantRent(g_token[8],room_id[8]);
		rent7.Rent();
		params = "";
		params = "?" + "token=" + g_token[8] + "&" + "room=" + room_id[8];
		
		result = HttpRequest.HttpRequestPOST(Constant.CANCEL_RENT, params);
		Response unitest8 = toResponse(result);
		assert(unitest8.code.equals("1004"));
		assert(unitest8.message.equals("Parameter value is invalid"));
		System.out.println("UNIT TEST 8: Thanh cong!");
		
		System.out.println("UNIT TEST 9: Dang gui yeu cau cho chu tro khac");
		Login m_login = new Login(Constant.Login("master","011"));
		String host_id = login_master.GetUserID();
		Login g_login = new Login(Constant.Login("guest","009"));
		String user_id9 = g_login.GetUserID();
		TenantRent rent9 = new TenantRent(g_token[9],room_id[9]);
		rent9.Rent();
		params = "?" + "token=" + token_master + "&"+ "host_id=" + host_id+ "&" + "guest_id=" + user_id9 + "&"+ "room_id=" + room_id[9];
		result = HttpRequest.HttpRequestPOST(Constant.TENANT_TRANSFER, params);
		params = "";
		params = "?" + "token=" + g_token[9] + "&" + "room_id=" + room_id[9];
		
		result = HttpRequest.HttpRequestPOST(Constant.CANCEL_RENT, params);
		Response unitest9 = toResponse(result);
		assert(!unitest9.code.equals("1000"));
		assert(!unitest9.message.equals("OK"));
		assert(!unitest9.code.equals("8889"));
		assert(!unitest9.message.equals("You do not rent this room"));
		System.out.println("UNIT TEST 9: Thanh cong!");
		
	}
	
	
}
