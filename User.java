public class User {
	private String name;
	private int adults;
	private int children;
	private int nights;
	private String locationHeight;
	private String locationSide;
	private int totalRooms;
	
	public User() {
		name = "";
		adults = 1;
		children = 0;
		nights = 1;
		locationHeight = "No Preference";
		locationSide = "No Preference";
		totalRooms = 70;
	}

	public User(String name, int adults, int children, int nights, String locationHeight, String locationSide, int totalRooms) {
		this.name = name;
		this.adults = adults;
		this.children = children;
		this.nights = nights;
		this.locationHeight = locationHeight;
		this.locationSide = locationSide;
		this.totalRooms = totalRooms;
	}
	
	public String getName () {
		return name;
	}
	public int getAdults () {
		return adults;
	}
	public int getChildren () {
		return children;
	}
	public int getNights () {
		return nights;
	}
	public String getLocationHeight () {
		return locationHeight;
	}
	public String getLocationSide () {
		return locationSide;
	}
	public int getTotalRooms () {
		return totalRooms;
	}

	public void setName (String name) {
		this.name = name;
	}
	public void setAdults (int adults) {
		this.adults = adults;
	}
	public void setChildren (int children) {
		this.children = children;
	}
	public void setNights (int nights) {
		this.nights = nights;
	}
	public void setLocationHeight (String locationHeight) {
		this.locationHeight = locationHeight;
	}
	public void setLocationSide (String locationSide) {
		this.locationSide = locationSide;
	}
	public void setTotalRooms (int totalRooms) {
		this.totalRooms = totalRooms;
	}
}