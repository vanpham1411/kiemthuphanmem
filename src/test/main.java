package test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.HashMap;
import java.util.Map;

import constant.Constant;
import main.HttpRequest;
import mainThread.Login;
import mainThread.Signup;

public class main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//init
		
//		Signup mt002 = new Signup("002","Pham Van Binh","0918338289","master");
//		String s = mt002.doSignup();
//		
//
//		Signup g001 = new Signup("001","Nguyen Van An","0438338222","guest");
//		g011.doSignup();
//		Login l001 = new Login(Constant.Login("guest", "001"));
//		System.out.println("Guest 001:");
//		System.out.println(l001.AcessToken());
//		
//		Signup g002 = new Signup("012","Nguyen Van Binh","0333338289","guest");
//		g002.doSignup();
//		
//		Signup g003 = new Signup("003","Nguyen Van Cuong","0328338444","guest");
//		g003.doSignup();
//		
//		Signup g003 = new Signup("003","Nguyen Van Cuong","0328338444","guest");
//		g003.doSignup();
		
//		Signup g004 = new Signup("004","Nguyen Van Cuong","0328338455","guest");
//		g004.doSignup();
//		Signup g005 = new Signup("005","Nguyen Van Cuong","0328338445","guest");
//		g005.doSignup();
//		Signup g006 = new Signup("006","Nguyen Van Cuong","0328338412","guest");
//		g006.doSignup();
//		Signup g007 = new Signup("007","Nguyen Van Cuong","0328338434","guest");
//		g007.doSignup();
//		
//		Signup g008 = new Signup("008","Nguyen Van Cuong","0328338490","guest");
//		g008.doSignup();
//		Signup g009 = new Signup("009","Nguyen Van Cuong","0328337544","guest");
//		g009.doSignup();
//		Signup g010 = new Signup("010","Nguyen Van Cuong","0328338486","guest");
//		g010.doSignup();
//		Signup g011 = new Signup("011","Nguyen Van Cuong","0328338864","guest");
//		g011.doSignup();
//		Signup g012 = new Signup("012","Nguyen Van Binh","0333338289","guest");
//		g012.doSignup();
//		
//		Signup g013 = new Signup("013","Nguyen Van Cuong","0328338444","guest");
//		g013.doSignup();
//		
//		Signup g014 = new Signup("014","Nguyen Van Cuong","0328338455","guest");
//		g014.doSignup();
//		Signup g015 = new Signup("015","Nguyen Van Cuong","0328338445","guest");
//		g015.doSignup();
		
//		Signup ad011 = new Signup("011","Tran Van Cuon","0328322764","admin");
//		ad011.doSignup();
//		Signup ad012 = new Signup("012","Tran Van Nam","0328923764","admin");
//		ad012.doSignup();
//		Signup ad013 = new Signup("013","Tran Van Thang","0328842764","admin");
//		ad013.doSignup();
//		Signup ad014 = new Signup("014","Tran Van Hoang","0867927390","admin");
//		ad014.doSignup();
//		Signup ad015 = new Signup("015","Tran Thi Bich","0829483716","admin");
//		ad015.doSignup();
			
//		Signup mt011 = new Signup("011","Pham Van Huong","0918338289","master");
//		mt011.doSignup();
//		
//		Signup mt012 = new Signup("012","Pham Van Nam","0918338329","master");
//		mt012.doSignup();
//		
//		Signup mt013 = new Signup("013","Pham Van Tran","0412238289","master");
//		mt013.doSignup();
//		
//		Signup mt014 = new Signup("014","Pham Van Huong","0918338289","master");
//		mt014.doSignup();
//		
//		Signup mt015 = new Signup("015","Pham Van Nam","0918338329","master");
//		mt015.doSignup();
//		
//		Signup mt016 = new Signup("016","Pham Van Tran","0412238289","master");
//		mt016.doSignup();
//		
//		Signup mt017 = new Signup("017","Pham Van Thai","0383252364","master");
//		mt017.doSignup();
//		
//		Signup mt018 = new Signup("018","Pham Van Nguyen","0902485965","master");
//		mt018.doSignup();
//		
//		Signup mt019 = new Signup("019","Pham Van Duong","0912884462","master");
//		mt019.doSignup();
//		
//		Signup mt020 = new Signup("020","Pham Van Khanh","0961836289","master");
//		mt020.doSignup();
		
//		Signup mt021 = new Signup("021","Pham Van Huong","0918338289","master");
//		mt021.doSignup();
//		
//		Signup mt022 = new Signup("022","Pham Van Nam","0918338329","master");
//		mt022.doSignup();
//		
//		Signup mt023 = new Signup("023","Pham Van Tran","0412238289","master");
//		mt023.doSignup();
//		
//		Signup mt024 = new Signup("024","Pham Van Huong","0918338289","master");
//		mt024.doSignup();
//		
//		Signup mt025 = new Signup("025","Pham Van Nam","0918338329","master");
//		mt025.doSignup();
//		Signup mt026 = new Signup("026","Pham Van Huong","0918338289","master");
//		mt026.doSignup();
//		
//		Signup mt027 = new Signup("027","Pham Van Nam","0918338329","master");
//		mt027.doSignup();
//		
//		Signup mt030 = new Signup("030","Pham Van Tran","0412238289","master");
//		mt030.doSignup();
//		
//		Signup mt028 = new Signup("028","Pham Van Huong","0918338289","master");
//		mt028.doSignup();
//		
//		Signup mt029 = new Signup("029","Pham Van Nam","0918338329","master");
//		mt029.doSignup();
		
		
		TenantRent rent = new TenantRent();
		rent.TenantRentTest();
		TenantCancelRent cancelrent = new TenantCancelRent();
		cancelrent.TenantCancelRentTest();
		AddToRoom addToRoom = new AddToRoom();
		addToRoom.AddToRoomTest();
		GetRoomBill getRoomBill = new GetRoomBill();
		getRoomBill.GetRoomBillTest();
		GetBill getBill = new GetBill();
		getBill.GetBillTest();
		RemoveFromRoom removeFromRoom = new RemoveFromRoom();
		removeFromRoom.RemoveFromRoomTest();
		TenantTransfer tenantTransfer = new TenantTransfer();
		tenantTransfer.TenantTransferTest();
		GetTenantTransfer getTenantTransfer = new GetTenantTransfer();
		getTenantTransfer.GetTenantTransferTest();
		ReceiveTenant receiveTenant = new ReceiveTenant();
		receiveTenant.ReceiveTenantTest();
		RoomSearch roomSearch = new RoomSearch();
		roomSearch.RoomSearchTest();
		UserSearch userSearch = new UserSearch();
		userSearch.UserSearchTest();

	}

}
