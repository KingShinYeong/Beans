import java.util.ArrayList;

public class User {
	float properties[] = new float[7];
	String name;
	int favoriteCafeIndex;
	
	User(){

		for(int inx = 0; inx < properties.length; inx ++) {
			properties[inx] = 5; // 중간값?
		}
		
		name = null;
	}
	User(String[] str){ // 길이 8인 str배열을 통해서 cafe를 생성
		for(int inx = 0; inx < properties.length; inx++) {
			properties[inx] = Float.parseFloat(str[inx]);
		}
		name = str[7]; 
		
	}
	
	
	public String getInfoUser() {
		String str = new String();
		
		str = "name : " + name + " property : ";
		
		for(int inx = 0 ; inx < properties.length; inx++) {
			str += properties[inx] + "/";
		}
		
		return str;
	}
	
	public int[] findCafe(CafeDB cafeDB) { 
		
		ArrayList<Float> sim_Sum = new ArrayList<Float>();
		int[] result = new int[3]; // 3개 추천??
		
		for(int inx = 0; inx < cafeDB.getCafeNum(); inx++) {
			sim_Sum.add(getSimulalr((cafeDB.getCafe(inx))));
		}		
		
		System.out.println(sim_Sum.toString());
		
		return null;
	}

	public float getSimulalr(Cafe c) {
		float result = 0;
		float cafeP[] = c.getProperties();
		
		for(int inx = 0; inx < properties.length; inx ++) {
			result += Math.pow(properties[inx]- cafeP[inx],2);
		}
		
		return result;
	}
}
