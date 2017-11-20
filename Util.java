
public class Util {
	private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);

    }
	
	public static double getDistance(double[] location1, double[] location2) {
		double theta = location1[1] - location2[1];
		double dist = Math.sin(deg2rad(location1[0])) * Math.sin(deg2rad(location2[0]))
				+ Math.cos(deg2rad(location1[0])) * Math.cos(deg2rad(location2[0])) * Math.cos(deg2rad(theta));
		
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist *= 60 * 1.1515;
		
		dist *= 1609.344; // meter
		
		return dist;
	}

}
