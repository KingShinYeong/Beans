import java.util.ArrayList;
import java.util.HashMap;

public class User {
	private String name;
	private String phoneNumber;
	private double[] location;
	
	private float properties[] = new float[7];
	
	private Cafe[] cafeList;
	
	private int favoriteCafeIndex;
	
	User(){

		for(int i=0; i < properties.length; i ++) {
			properties[i] = 5;
		}
		
		name = null;
	}
	
	User(HashMap<String, String> info, double[] location, float[] properties){
		
		// ---------------------------------------------------------------------
		// Need to implement get user's location
		// ---------------------------------------------------------------------
		
		// Chung-Ang University's location
		double latitude = 37.504694;
		double longitude = 126.956741;

		for(int i=0; i < properties.length; i++) {
			this.properties[i] = properties[i];
		}

		this.name = info.get("name");
		this.phoneNumber = info.get("phoneNumber");
		this.location = location;
	}
	
	public HashMap<String, String> getInfo() {
		HashMap<String, String> info = new HashMap<String, String>();
		
		info.put("name", this.name);
		info.put("phoneNumber", this.phoneNumber);
		
		return info;
	}
	
	public double[] getLocation() {
		return location;
	}
	
	public float[] getProperties() {
		return properties;
	}
	
	public ArrayList<Cafe> findCafe() { 
		
		ArrayList<Float> sim_Sum = new ArrayList<Float>();
		//int[] result = new int[3]; // ?????????????????????????????
		
		for(int i= 0; i < cafeList.length; i++) {
			sim_Sum.add(getSimilarityWith(cafeList[i]));
		}		

		// ---------------------------------------------------------------------
		// Need to implement the algorithm to get top N cafes then return the cafeList
		// ---------------------------------------------------------------------
		
		return null;	// ?????????????????
	}

	public float getSimilarityWith(Cafe cafe) {
		float result = 0;
		float cafeProperties[] = cafe.getProperties();
		
		for(int i=0; i < properties.length; i ++) {
			result += Math.pow(this.properties[i]- cafeProperties[i], 2);
		}
		
		return result;
	}
}

