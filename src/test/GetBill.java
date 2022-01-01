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

public class GetBill {
	String token;
	String bill_id;
	public GetBill() {
		super();
	}
	
	public void GetBillTest() throws ProtocolException, MalformedURLException, IOException {
		Login[] login = new Login[16];
		String[] m_token = new String[16];
		String[] room_id = new String[16];
		AddRoom[] newRoom = new AddRoom[16] ;
		GetRoomList[] roomList = new GetRoomList[16];
		String[] user_id = new String[16];
		String[] g_token = new String[16];
		String[] bill_id = new String[16];
		
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
		for(int i = 7; i <= 8;i++) {
			
			String id = i>9 ? "0" : "00";
			login[i] = new Login(Constant.Login("guest", id + i));
			g_token[i] = login[i].AcessToken();
			user_id[i] = login[i].GetUserID();
		}
		
		//Add to room
		String tmpParams = "";
		String tmpResult;
		for(int i = 1; i<= 15; i++) {
				tmpParams = "";
				tmpParams = "?" + "token=" + m_token[i] + "&"+ "user_id=" + user_id[i] + "&"+ "room_id=" + room_id[i];
				tmpResult  = HttpRequest.HttpRequestPOST(Constant.ADD_TO_ROOM, tmpParams);
		}
		
		//getRoomID
		GetRoomBill[] getRoomBill = new GetRoomBill[16];
		for(int i=1; i<=15;i++ ) {
			getRoomBill[i] = new GetRoomBill(m_token[i], room_id[i]);
			bill_id[i] = getRoomBill[i].BillID();
		}
		
		System.out.println("TESTCASE: Lay thong tin hoa don.");
		String params;
		String result;
		
		System.out.println("UNIT TEST 1: luong chinh");
		params = "?" + "token=" + m_token[1] + "&"+ "bill_id=" + bill_id[1];
		result = HttpRequest.HttpRequestPOST(Constant.GET_BILL, params);
		GetBillResponse unitest1 = toResponse(result);
		assert(!unitest1.code.equals(null));
		assert(unitest1.code.equals("1000"));
		assert(unitest1.message.equals("OK"));
		System.out.println("UNIT TEST 1: Thanh cong!");
		
		System.out.println("UNIT TEST 2: Doi cho tham so");
		params = "?"+ "bill_id=" + bill_id[2] + "&" + "token=" + m_token[2] ;
		result = HttpRequest.HttpRequestPOST(Constant.GET_BILL, params);
		GetBillResponse unitest2 = toResponse(result);
		assert(!unitest2.code.equals(null));
		assert(unitest2.code.equals("1000"));
		assert(unitest2.message.equals("OK"));
		System.out.println("UNIT TEST 2: Thanh cong!");
		
		System.out.println("UNIT TEST 3: thieu gia tri");
		params = "?" + "token=" + m_token[3] + "&"+ "bill_id" ;
		result = HttpRequest.HttpRequestPOST(Constant.GET_BILL, params);
		GetBillResponse unitest3 = toResponse(result);
		assert(!unitest3.code.equals(null));
		assert(unitest3.code.equals("1004"));
		assert(unitest3.message.equals("Parameter value is invalid"));
		System.out.println("UNIT TEST 3: Thanh cong!");
		
		System.out.println("UNIT TEST 4: Thua tham so");
		params = "?" + "token=" + m_token[4] + "&"+ "bill_id=" + bill_id[4] + "&value=1298";
		result = HttpRequest.HttpRequestPOST(Constant.GET_BILL, params);
		GetBillResponse unitest4 = toResponse(result);
		assert(!unitest4.code.equals(null));
		assert(unitest4.code.equals("1000"));
		assert(unitest4.message.equals("OK"));
		System.out.println("UNIT TEST 4: Thanh cong!");
		
		System.out.println("UNIT TEST 5: Sai ten params");
		params = "?" + "access_token=" + m_token[5] + "&"+ "bill_id=" + bill_id[5];
		result = HttpRequest.HttpRequestPOST(Constant.GET_BILL, params);
		GetBillResponse unitest5 = toResponse(result);
		assert(!unitest5.code.equals(null));
		assert(!unitest5.code.equals("1000"));
		assert(!unitest5.message.equals("OK"));
		System.out.println("UNIT TEST 5: Thanh cong!");
		
		System.out.println("UNIT TEST 6: Phong da bi xoa");
		// Delete Room
		String params_delete = "?" + "token=" + m_token[6] + "&"+ "room_id=" + room_id[6];
		HttpRequest.HttpRequestPOST(Constant.DELETE_ROOM, params_delete);
		
		params = "?" + "token=" + m_token[6] + "&"+ "bill_id=" + bill_id[6];
		result = HttpRequest.HttpRequestPOST(Constant.GET_BILL, params);
		GetBillResponse unitest6 = toResponse(result);
		assert(!unitest6.code.equals(null));
		assert(unitest6.code.equals("1000"));
		assert(unitest6.message.equals("OK"));
		System.out.println("UNIT TEST 6: Thanh cong!");
		
		System.out.println("UNIT TEST 7: Khach thue phong xem hoa don");
		params = "?" + "token=" + m_token[7] + "&"+ "bill_id=" + bill_id[7];
		result = HttpRequest.HttpRequestPOST(Constant.GET_BILL, params);
		GetBillResponse unitest7 = toResponse(result);
		assert(!unitest7.code.equals(null));
		assert(unitest7.code.equals("1000"));
		assert(unitest7.message.equals("OK"));
		System.out.println("UNIT TEST 7: Thanh cong!");
		
		System.out.println("UNIT TEST 8: Tai khoan guest khac xem hoa don");
		params = "?" + "token=" + g_token[7] + "&"+ "bill_id=" + bill_id[8];
		result = HttpRequest.HttpRequestPOST(Constant.GET_BILL, params);
		GetBillResponse unitest8 = toResponse(result);
		assert(!unitest8.code.equals(null));
		assert(unitest8.code.equals("1009"));
		assert(unitest8.message.equals("Not access."));
		System.out.println("UNIT TEST 8: Thanh cong!");
		
		System.out.println("UNIT TEST 8: Tai khoan master khac xem hoa don");
		params = "?" + "token=" + m_token[7] + "&"+ "bill_id=" + bill_id[8];
		result = HttpRequest.HttpRequestPOST(Constant.GET_BILL, params);
		GetBillResponse unitest8_1 = toResponse(result);
		assert(!unitest8_1.code.equals(null));
		assert(unitest8_1.code.equals("1009"));
		assert(unitest8_1.message.equals("Not access."));
		System.out.println("UNIT TEST 8: Thanh cong!");
		
		System.out.println("UNIT TEST 9: Cap nhat tinh trang khi huy thue phong");
		//Cancel Rent
		tmpParams = "?" + "token=" + g_token[9] + "&"+ "room_id=" + room_id[9];
		tmpResult = HttpRequest.HttpRequestPOST(Constant.CANCEL_RENT, tmpParams);			
		
		params = "?" + "token=" + m_token[9] + "&"+ "bill_id=" + bill_id[9];
		result = HttpRequest.HttpRequestPOST(Constant.GET_BILL, params);
		GetBillResponse unitest9 = toResponse(result);
		assert(!unitest9.code.equals(null));
		assert(unitest9.code.equals("1000"));
		assert(unitest9.message.equals("OK"));
		assert(unitest9.billData.status.equals("cancel"));	
		System.out.println("UNIT TEST 9: Thanh cong!");
		
		System.out.println("UNIT TEST 10: Cap nhat tinh trang khi xoa nguoi khoi phong tro");
		//Remove From Room
		RemoveFromRoom delGuest = new RemoveFromRoom(m_token[10],user_id[10], room_id[10]);
		delGuest.Remove();
		
		params = "?" + "token=" + m_token[10] + "&"+ "bill_id=" + bill_id[10];
		result = HttpRequest.HttpRequestPOST(Constant.GET_BILL, params);
		GetBillResponse unitest10 = toResponse(result);
		assert(!unitest10.code.equals(null));
		assert(unitest10.code.equals("1000"));
		assert(unitest10.message.equals("OK"));
		assert(unitest10.billData.status.equals("deleted"));	
		System.out.println("UNIT TEST 10: Thanh cong!");
	}
	
	
	
	public GetBillResponse toResponse(String json) {
		Gson gson = new Gson();
		GetBillResponse rp = gson.fromJson(json,GetBillResponse.class);
		return rp;
	}

}

class GetBillResponse{
	String code;
	String message;
	BillData billData;
}
