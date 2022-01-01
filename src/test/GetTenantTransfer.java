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

public class GetTenantTransfer {
	String token;
	
	public void GetTenantTransferTest() throws ProtocolException, MalformedURLException, IOException {
		Login[] login = new Login[16];
		String[] m_token = new String[16];
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
			room_id[i] = roomList[i].GetRoomId(0);	
		}
		
		// guest login
		for(int i = 1; i <= 15;i++) {
			
			String id = i>9 ? "0" : "00";
			login[i] = new Login(Constant.Login("guest", id + i));
			g_token[i] = login[i].AcessToken();
			guest_id[i] = login[i].GetUserID();
		}
		
		//Add to room
		String tmpParams = "";
		String tmpResult;
		for(int i = 1; i<= 15; i++) {
			tmpParams = "";
			tmpParams = "?" + "token=" + m_token[i] + "&"+ "user_id=" + guest_id[i] + "&"+ "room_id=" + room_id[i];
			tmpResult  = HttpRequest.HttpRequestPOST(Constant.ADD_TO_ROOM, tmpParams);
		}
		
		System.out.println("TESTCASE: Lay danh sach khach duoc gui.");
		String params;
		String result;
	
		System.out.println("UNIT TEST 4: Yeu cau da duoc chap nhan");
		//user_id
		Login rev10 = new Login(Constant.Login("master", "020"));
		String host_rev = rev10.GetUserID();
		m_token[10] = rev10.AcessToken();
		Login trans12 = new Login(Constant.Login("master", "022"));
		String host_trans = trans12.GetUserID();
		m_token[12] = trans12.AcessToken();
		
		TenantTransfer transfer4 = new TenantTransfer(m_token[10], host_trans, guest_id[10], room_id[10]);
		transfer4.Transfer();
		params = "?" + "token=" + m_token[12] ;
		result = HttpRequest.HttpRequestPOST(Constant.GET_TENANT_TRANSFER, params);
		GetTenantTransferResponse unitest4_1 = toResponse(result);
		//String token, String host_id, String guest_id, String confirm, String room_id)
		ReceiveTenant receiveTenant = new ReceiveTenant(m_token[12],host_rev,guest_id[10],"1", room_id[12]);
		receiveTenant.Receive();
		params = "?" + "token=" + m_token[12] ;
		result = HttpRequest.HttpRequestPOST(Constant.GET_TENANT_TRANSFER, params);
		GetTenantTransferResponse unitest4 = toResponse(result);
		assert(!unitest4.code.equals(null));
		assert(unitest4.code.equals("1000"));
		assert(unitest4.message.equals("OK"));
		assert(unitest4.listTenantTransfer.length == (unitest4_1.listTenantTransfer.length - 1));
		
		System.out.println("UNIT TEST 4: Thanh cong!");
		
		
		System.out.println("UNIT TEST 5: Thua tham so");
		params = "?" + "token=" + m_token[5] +"&" + "value=0" ;
		result = HttpRequest.HttpRequestPOST(Constant.GET_TENANT_TRANSFER, params);
		GetTenantTransferResponse unitest5 = toResponse(result);
		assert(unitest5.code.equals("1000"));
		assert(unitest5.message.equals("OK"));
		System.out.println("UNIT TEST 5: Thanh cong!");
		
		
		System.out.println("UNIT TEST 6: Sai ten tham so");
		params = "?" + "m_token=" + m_token[6] ;
		result = HttpRequest.HttpRequestPOST(Constant.GET_TENANT_TRANSFER, params);
		GetTenantTransferResponse unitest6 = toResponse(result);
		assert(!unitest6.code.equals(null));
		assert(!unitest6.code.equals("1000"));
		assert(!unitest6.message.equals("OK"));
		System.out.println("UNIT TEST 6: Thanh cong!");
		
				
		System.out.println("UNIT TEST 8: Yeu cau da bi tu choi");
		Login rev8 = new Login(Constant.Login("master", "018"));
		Login trans9 = new Login(Constant.Login("master", "019"));
		
		String host_rec8 = rev8.GetUserID();
		String host_trans9 = trans9.GetUserID();
		TenantTransfer transfer8 = new TenantTransfer(m_token[8], host_trans9, guest_id[8], room_id[8]);
		transfer8.Transfer();
		params = "?" + "token=" + m_token[9] ;
		result = HttpRequest.HttpRequestPOST(Constant.GET_TENANT_TRANSFER, params);
		GetTenantTransferResponse unitest8_1 = toResponse(result);
		//String token, String host_id, String guest_id, String confirm, String room_id)
		ReceiveTenant receiveTenant1 = new ReceiveTenant(m_token[9],host_rec8,guest_id[8], room_id[11]);
		receiveTenant1.Refuse();
		params = "?" + "token=" + m_token[9] ;
		result = HttpRequest.HttpRequestPOST(Constant.GET_TENANT_TRANSFER, params);
		GetTenantTransferResponse unitest8 = toResponse(result);
		assert(!unitest8.code.equals(null));
		assert(unitest8.code.equals("1000"));
		assert(unitest8.message.equals("OK"));
		assert(unitest8.listTenantTransfer.length == (unitest8_1.listTenantTransfer.length - 1));
		
		System.out.println("UNIT TEST 8: Thanh cong!");
		
		
	}
	
	public GetTenantTransferResponse toResponse(String json) {
		Gson gson = new Gson();
		GetTenantTransferResponse rp = gson.fromJson(json,GetTenantTransferResponse.class);
		return rp;
	}

}


class GetTenantTransferResponse{
	String code;
	String message;
	ListTenantTransfer[] listTenantTransfer; 
	
}

class ListTenantTransfer{
	String _id;
	String status;
	String userMaster;
	UserMasterTransfer userMasterTransfer;
	UserGuest userGuest;
	
	
}

class UserMasterTransfer{
	
}

class UserGuest{
	
}
