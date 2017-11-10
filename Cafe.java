
public class Cafe {
	float properties[] = new float[7];
	String name;
	double longitude; // 경도
	double latitude; // 위도
	CoffeeDB coffeeDB;
	
	Cafe(){
		for(int inx = 0; inx < properties.length; inx ++) {
			properties[inx] = 5; // 중간값?
		}
		name = null;
		longitude = 0;
		latitude = 0;
		coffeeDB = null;
	}
	Cafe(String[] str){ // 길이 10인 str배열을 통해서 cafe를 생성
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
		
		//경도 위도 생략
		
		return str;
	}
	
	public float[] getProperties() {
		return properties;
	}
}
