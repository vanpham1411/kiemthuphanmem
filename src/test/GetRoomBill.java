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

public class GetRoomBill {
	String token;
	String room_id;
	public GetRoomBill() {
		super();
	}
	public GetRoomBill(String token, String room_id) {
		super();
		this.token = token;
		this.room_id = room_id;
	}
	
	public String BillID() throws ProtocolException, MalformedURLException, IOException {
		String params;
		params = "?" + "token=" + this.token+"&" + "room_id=" + this.room_id;
		String res;
		res = HttpRequest.HttpRequestPOST(Constant.GET_ROOM_BILL, params);
		GetRoomBillResponse obj = toResponse(res); 
		return obj.billData[0]._id;
	}
	
	public void GetRoomBillTest() throws ProtocolException, MalformedURLException, IOException {
		Login[] login = new Login[16];
		String[] m_token = new String[16];
		String[] room_id = new String[16];
		AddRoom[] newRoom = new AddRoom[16] ;
		GetRoomList[] roomList = new GetRoomList[16];
		String[] user_id = new String[16];
		String[] g_token = new String[16];
		
		//master login
		for(int i = 1; i <= 15;i++) {
			int x = i+10;
			String id =  "0";
			login[i] = new Login(Constant.Login("master", id + x));
			m_token[i] = login[i].AcessToken();
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
			if(i != 9) {
				tmpParams = "";
				tmpParams = "?" + "token=" + m_token[i] + "&"+ "user_id=" + user_id[i] + "&"+ "room_id=" + room_id[i];
				tmpResult  = HttpRequest.HttpRequestPOST(Constant.ADD_TO_ROOM, tmpParams);
			}
		}
		
		
		System.out.println("TESTCASE: Lay hoa don cua mot phong.");
		String params;
		String result;
		
		System.out.println("UNIT TEST 1: luong chinh");
		params = "?" + "token=" + m_token[1] + "&"+ "room_id=" + room_id[1];
		result = HttpRequest.HttpRequestPOST(Constant.GET_ROOM_BILL, params);
		GetRoomBillResponse unitest1 = toResponse(result);
		assert(!unitest1.code.equals(null));
		assert(unitest1.code.equals("1000"));
		assert(unitest1.message.equals("OK"));
		assert(unitest1.billData[0].roomRent.equals(room_id[1]));
		assert(unitest1.billData[0].userRent.equals(user_id[1]));
		System.out.println("UNIT TEST 1: Thanh cong!");
		
		System.out.println("UNIT TEST 2: Doi cho tham so");
		params = "?" + "room_id=" + room_id[2] + "&"+ "token=" + m_token[2] ;
		result = HttpRequest.HttpRequestPOST(Constant.GET_ROOM_BILL, params);
		GetRoomBillResponse unitest2 = toResponse(result);
		assert(!unitest2.code.equals(null));
		assert(unitest2.code.equals("1000"));
		assert(unitest2.message.equals("OK"));
		System.out.println("UNIT TEST 2: Thanh cong");
		
		System.out.println("UNIT TEST 3: Thieu gia tri");
		params = "?" + "token=" + m_token[3] + "&"+ "room_id";
		result = HttpRequest.HttpRequestPOST(Constant.GET_ROOM_BILL, params);
		GetRoomBillResponse unitest3 = toResponse(result);
		assert(!unitest3.code.equals(null));
		assert(unitest3.code.equals("1004"));
		assert(!unitest3.message.equals("OK"));
		System.out.println("UNIT TEST 3: Thanh cong!");
		
		System.out.println("UNIT TEST 4: thua tham so");
		params = "?" + "token=" + m_token[4] + "&"+ "room_id=" + room_id[4] +"&value=0";
		result = HttpRequest.HttpRequestPOST(Constant.GET_ROOM_BILL, params);
		GetRoomBillResponse unitest4 = toResponse(result);
		assert(!unitest4.code.equals(null));
		assert(unitest4.code.equals("1000"));
		assert(unitest4.message.equals("OK"));
		System.out.println("UNIT TEST 4: Thanh cong!");
		
		System.out.println("UNIT TEST 5: sai ten params");
		params = "?" + "token=" + m_token[1] + "&"+ "room=" + room_id[1];
		result = HttpRequest.HttpRequestPOST(Constant.GET_ROOM_BILL, params);
		GetRoomBillResponse unitest5 = toResponse(result);
		assert(!unitest5.code.equals(null));
		assert(!unitest5.code.equals("1000"));
		assert(!unitest5.message.equals("OK"));
		System.out.println("UNIT TEST 5: Thanh cong!");
		
		System.out.println("UNIT TEST 6: Phong da bi xoa");
		//Delete room
		tmpParams = "?" + "token=" + m_token[6] + "&"+ "room_id=" + room_id[6];
		tmpResult = HttpRequest.HttpRequestPOST(Constant.DELETE_ROOM, tmpParams);
		
		params = "?" + "token=" + m_token[6] + "&"+ "room_id=" + room_id[6];
		result = HttpRequest.HttpRequestPOST(Constant.GET_ROOM_BILL, params);
		GetRoomBillResponse unitest6 = toResponse(result);
		assert(!unitest6.code.equals(null));
		assert(unitest6.code.equals("1000"));
		assert(unitest6.message.equals("OK"));
		System.out.println("UNIT TEST 6: Thanh cong!");
		
		System.out.println("UNIT TEST 7: Moi co them hoa don");
		params = "?" + "token=" + m_token[7] + "&"+ "room_id=" + room_id[7];
		result = HttpRequest.HttpRequestPOST(Constant.GET_ROOM_BILL, params);
		//Cancel Rent
		tmpParams = "?" + "token=" + g_token[7] + "&"+ "room_id=" + room_id[7];
		tmpResult = HttpRequest.HttpRequestPOST(Constant.CANCEL_RENT, tmpParams);
		//New Rent
		tmpParams = "?" + "token=" + g_token[1] + "&"+ "room_id=" + room_id[7];
		tmpResult = HttpRequest.HttpRequestPOST(Constant.RENT, tmpParams);
		
		
		params = "?" + "token=" + m_token[7] + "&"+ "room_id=" + room_id[7];
		result = HttpRequest.HttpRequestPOST(Constant.GET_ROOM_BILL, params);
		GetRoomBillResponse unitest7 = toResponse(result);
		assert(!unitest7.code.equals(null));
		assert(unitest7.code.equals("1000"));
		assert(unitest7.message.equals("OK"));
		int pos = -1;
		for(int i = 0; i < unitest7.billData.length;i++) {
			if(unitest7.billData[i].userRent.equals(user_id[1])) {
				pos = 1;
				break;
			}
		}
		assert(pos == 1);
		System.out.println("UNIT TEST 7: Thanh cong!");
		
		System.out.println("UNIT TEST 8: Tai khoan guest truy cap");
		params = "?" + "token=" + g_token[8] + "&"+ "room_id=" + room_id[8];
		result = HttpRequest.HttpRequestPOST(Constant.GET_ROOM_BILL, params);
		GetRoomBillResponse unitest8 = toResponse(result);
		assert(!unitest8.code.equals(null));
		assert(unitest8.code.equals("1009"));
		assert(unitest8.message.equals("Not access."));
		System.out.println("UNIT TEST 8: Thanh cong!");
		
		System.out.println("UNIT TEST 8_1: Tai khoan master khac truy cap");
		params = "?" + "token=" + m_token[9] + "&"+ "room_id=" + room_id[8];
		result = HttpRequest.HttpRequestPOST(Constant.GET_ROOM_BILL, params);
		GetRoomBillResponse unitest8_1 = toResponse(result);
		assert(!unitest8_1.code.equals(null));
		assert(unitest8_1.code.equals("1009"));
		assert(unitest8_1.message.equals("Not access."));
		System.out.println("UNIT TEST 8_1: Thanh cong!");
		
		System.out.println("UNIT TEST 8_1: Tai khoan admin truy cap");
		Login ad_login1 = new Login(Constant.Login("admin", "011"));
		String ad_token = ad_login1.AcessToken();
		params = "?" + "token=" + ad_token + "&"+ "room_id=" + room_id[8];
		result = HttpRequest.HttpRequestPOST(Constant.GET_ROOM_BILL, params);
		GetRoomBillResponse unitest8_2 = toResponse(result);
		assert(!unitest8_2.code.equals(null));
		assert(unitest8_2.code.equals("1009"));
		assert(unitest8_2.message.equals("Not access."));
		System.out.println("UNIT TEST 8_2: Thanh cong!");
		
		System.out.println("UNIT TEST 9: Phong chua co nguoi thue");
		params = "?" + "token=" + m_token[9] + "&"+ "room_id=" + room_id[9];
		result = HttpRequest.HttpRequestPOST(Constant.GET_ROOM_BILL, params);
		GetRoomBillResponse unitest9 = toResponse(result);
		assert(!unitest9.code.equals(null));
		assert(unitest9.code.equals("1000"));
		assert(unitest9.message.equals("OK"));
		assert(unitest9.billData.length == 0);
		System.out.println("UNIT TEST 9: Thanh cong!");
		
		System.out.println("UNIT TEST 10: Cap nhat tinh trang hoa don");
		//Cancel Rent
		tmpParams = "?" + "token=" + g_token[10] + "&"+ "room_id=" + room_id[10];
		tmpResult = HttpRequest.HttpRequestPOST(Constant.CANCEL_RENT, tmpParams);	
		
		
		params = "?" + "token=" + m_token[10] + "&"+ "room_id=" + room_id[10];
		result = HttpRequest.HttpRequestPOST(Constant.GET_ROOM_BILL, params);
		GetRoomBillResponse unitest10 = toResponse(result);
		assert(!unitest10.code.equals(null));
		assert(unitest10.code.equals("1000"));
		assert(unitest10.message.equals("OK"));
		int l = unitest10.billData.length;
		int check = 0;
		for(int i = 0; i<= l;i++) {
			if(unitest10.billData[i].userRent.equals(user_id[10])) {
				if(unitest10.billData[i].status.equals("cancel")) {
					check = 1;
					break;
				}
			}
		}
		assert(check==1);
		System.out.println("UNIT TEST 10: Thanh cong!");
		
	}
	
	
	public GetRoomBillResponse toResponse(String json) {
		Gson gson = new Gson();
		GetRoomBillResponse rp = gson.fromJson(json,GetRoomBillResponse.class);
		return rp;
	}

}

class GetRoomBillResponse{
	String code;
	String message;
	BillData[] billData;
	
}

class BillData{
	String _id;
	String userRent;
	String roomRent;
	String status;
	String time;
	
}
