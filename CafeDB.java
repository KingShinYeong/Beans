import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CafeDB {
	ArrayList<Cafe> cafeDB;
	
	CafeDB() throws Exception{
		cafeDB = new ArrayList<Cafe>();
		
		File cafeFile = new File("cafeDB.txt");
		//File coffeeFile = new File("coffeeDB.txt"); // Ŀ�� DB�� ���� �о ī�信 �߰�
		FileInputStream cafe_fis = new FileInputStream(cafeFile);
		
		if(cafeFile.exists()) {
			BufferedReader bufrd = new BufferedReader(new InputStreamReader(cafe_fis));
			
			String str;
			String buf[] = new String[10]; // �Ӽ� 7���� + ����/�浵/ �̸�

			str = bufrd.readLine();
			
			
			while(str != null) {
				buf = str.split("/");
				Cafe c = new Cafe(buf);	
				
				cafeDB.add(c);
				
				str = bufrd.readLine();
			}
			
			bufrd.close();
			cafe_fis.close();
		}
	}
	
	public void addCafe(Cafe c) {
		cafeDB.add(c);
	}
	
	public void printAll() {
		for(int inx = 0 ; inx < cafeDB.size(); inx ++) {
			printCafe(inx);
		}
	}
	
	public void printCafe(int index) {
		System.out.println(cafeDB.get(index).getInfoCafe());
	}
	
	public int getCafeNum() {
		return cafeDB.size();
	}
	
	public Cafe getCafe(int index) {
		return cafeDB.get(index);
	}
}
