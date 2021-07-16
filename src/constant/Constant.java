package constant;

public class Constant {
	public static final String baseURL="https://api-datk.herokuapp.com";
	public static final String tenant = "/tenant";
	public static final String room = "/room";
	
	public static final String SIGNUP = baseURL + "/auth/signup";
	public static final String LOGIN = baseURL + "/auth/login";
	
	public static final String ADD_ROOM = baseURL + room + "/add";
	public static final String DELETE_ROOM = baseURL + room + "/delete";
	public static final String GET_ROOM_BILL = baseURL + room + "/getRoomBill";
	public static final String GET_BILL = baseURL + room + "/getBill";
	public static final String GET_ROOM_LIST = baseURL + room + "/room_list/get";
	public static final String ROOM_SEARCH = baseURL + "/room/search/";

	public static final String RENT = baseURL + tenant + "/rent";
	public static final String CANCEL_RENT = baseURL + tenant + "/cancelRent";
	public static final String ADD_TO_ROOM  = baseURL + tenant +"/addToRoom";
	public static final String REMOVE_FROM_ROOM = baseURL+ tenant + "/removeFromRoom";
	public static final String TENANT_TRANSFER = baseURL+tenant + "/tenantTransfer";
	public static final String RECEIVE_TENANT = baseURL+ tenant + "/receiveTenant";
	public static final String GET_TENANT_TRANSFER = baseURL +tenant+ "/getTenantTransfer";
	
	public static final String GET_NOTIFICATION = baseURL + "/getNotification";
	public static final String USER_SEARCH = baseURL + "/user/search/";
	
	public static final String ACCOUNT_DELETE = "https://api-datk.herokuapp.com/user/account/delete";
	
	public static final String MAIL = "@gmail.com";
	
	
	public static String Login(String role,String id) {
		return role + id+ "ptv";
	}
	

}
