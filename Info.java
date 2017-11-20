import java.util.ArrayList;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import java.io.*;

public class Info<T> {
	private File file;
	private ArrayList<T> values;
	
	private Gson gson;
	
	public Info() {
		System.out.println("Please type the file name");
	}
	
	public Info(String fileName) {
		file = new File(fileName);
	}
	
	public ArrayList<T> readJsonWith(Class<T> cls, String phoneNumber, double[] userLocation) {
		try {
			FileInputStream stream = new FileInputStream(file);
			JsonReader reader = new JsonReader(new InputStreamReader(stream, "UTF-8"));
			
			values = new ArrayList<T>();
			
			gson = new GsonBuilder().create();

			reader.beginArray();
			
			while(reader.hasNext()) {
				T data = gson.fromJson(reader, cls);
				
				if(cls.getName() == "Cafe" && userLocation != null) {
					double[] cafeLocation = ((Cafe) data).getLocation();
					double dist = Util.getDistance(cafeLocation, userLocation);
					
					// if calculate distance is within 2000 meters
					if (2000 > dist) {
						values.add(data);
					}
					
				} else if(cls.getName() == "User" && phoneNumber != null) {
					if(phoneNumber.equals(((User) data).getInfo().get("phoneNumber"))) {
						values.add(data);
						break;
					}
				} else {
					System.out.println("One of arguments necessary is missing");
				}
			}
			
			reader.endArray();
			reader.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return values;
		
	}
	
	public void writeJsonWith(T value) {
		try {
			FileInputStream stream = new FileInputStream(file);
			JsonReader reader = new JsonReader(new InputStreamReader(stream, "UTF-8"));
			JsonParser parser = new JsonParser();
			parser.parse(reader);
			
		

			FileWriter writer = new FileWriter(file);
			gson = new GsonBuilder().setPrettyPrinting().create();
			
			gson.toJson(value, writer);

			writer.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
