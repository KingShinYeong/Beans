import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class UserDB {
	ArrayList<User> userDB;
	
	UserDB() throws Exception{
		userDB = new ArrayList<User>();
		
		File userFile = new File("userDB.txt");
		//File coffeeFile = new File("coffeeDB.txt"); // 커피 DB도 같이 읽어서 카페에 추가
		FileInputStream user_fis = new FileInputStream(userFile);
		
		if(userFile.exists()) {
			BufferedReader bufrd = new BufferedReader(new InputStreamReader(user_fis));
			
			String str;
			String buf[] = new String[8]; // 속성 7가지 + 이름

			str = bufrd.readLine();
			
			
			while(str != null) {
				buf = str.split("/");
				User u = new User(buf);	
				
				userDB.add(u);
				
				str = bufrd.readLine();
			}
			
			bufrd.close();
			user_fis.close();
		}
	}
	
	public void addCafe(User u) {
		userDB.add(u);
	}
	
	
	public void printAll() {
		for(int inx = 0 ; inx < userDB.size(); inx ++) {
			printCafe(inx);
		}
	}
	
	public void printCafe(int index) {
		System.out.println(userDB.get(index).getInfoUser());
	}
	public User getUser(int index) {
		return userDB.get(index);
	}
}
