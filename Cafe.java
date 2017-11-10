
public class Cafe {
	float properties[] = new float[7];
	String name;
	double longitude; // �浵
	double latitude; // ����
	CoffeeDB coffeeDB;
	
	Cafe(){
		for(int inx = 0; inx < properties.length; inx ++) {
			properties[inx] = 5; // �߰���?
		}
		name = null;
		longitude = 0;
		latitude = 0;
		coffeeDB = null;
	}
	Cafe(String[] str){ // ���� 10�� str�迭�� ���ؼ� cafe�� ����
		for(int inx = 0; inx < properties.length; inx++) {
			properties[inx] = Float.parseFloat(str[inx]);
		}
		name = str[7];
		longitude = Double.parseDouble(str[8]);
		longitude = Double.parseDouble(str[9]); 
	}
	
	String getInfoCafe() {
		String str = new String();
		
		str = "name : " + name + " property : ";
		
		for(int inx = 0 ; inx < properties.length; inx++) {
			str += properties[inx] + "/";
		}
		
		//�浵 ���� ����
		
		return str;
	}
	
	public float[] getProperties() {
		return properties;
	}
}
