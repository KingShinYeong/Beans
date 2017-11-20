import java.util.HashMap;
import java.util.ArrayList;

public class FileControl {

	private static void writeUserListSample() {
		HashMap<String, String> u1_info = new HashMap<String, String>();
		u1_info.put("name", "A");
		u1_info.put("phoneNumber", "01085321321");
		double[] u1_location = {13.321231,123.1231232};
		float[] u1_properties = {5,5,5,5,5,5,5};
		
		HashMap<String, String> u2_info = new HashMap<String, String>();
		u2_info.put("name", "B");
		u2_info.put("phoneNumber", "01085213212");
		double[] u2_location = {13.2312312,123.32321321};
		float[] u2_properties = {5,5,5,6,5,5,5};
		
		User u1 = new User(u1_info, u1_location, u1_properties);
		User u2 = new User(u2_info, u2_location, u2_properties);
		
		ArrayList<User> userList = new ArrayList<User>();
		
		userList.add(u1);
		userList.add(u2);
		
		Info<User> info_1 = new Info<User>("./test.json");
		
		for(int i=0; i<userList.size(); i++) {
			info_1.writeJsonWith(userList.get(i));
		}
		
		System.out.println("user list is stored into file");
	}
	
	private static void readUserListSample() {
		ArrayList<User> values;
		User user;
		
		Info<User> info_1 = new Info<User>("./test.json");
		values = info_1.readJsonWith(User.class, "01085213212", null);
		
		for(int i=0; i<values.size(); i++) {
			user = values.get(i);
			System.out.print(user.getInfo().values());
		}
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//readUserListSample();
		writeUserListSample();
	}

}
