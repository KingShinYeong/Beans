public class Beans {
	
	public static void main(String[]args) {
		System.out.println("hello");
		
		CafeDB cafeDB = null;
		UserDB userDB = null;
		
		try {
			cafeDB = new CafeDB();
			userDB = new UserDB();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userDB.printAll();
		System.out.println("-----------------");
		cafeDB.printAll();
		System.out.println("-----------------");
		System.out.println("user1 simularity");
		
		userDB.getUser(0).findCafe(cafeDB);


	}

	
	void setUserDB() {
		
	}
}
