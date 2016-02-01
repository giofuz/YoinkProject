package com.example.cyncyn.YoinkProject;

public class Deal {
	private int mId;
	private String mDescription;
	private String mCategory;
    private String mBusinessName;
	private double mLat;
	private double mLng;

	public Deal(int id, String description, String category, String businessName, double latitude, double longitude) {
		mId = id;
		mDescription = description;
		mCategory = category;
        mBusinessName = businessName;
		mLat = latitude;
		mLng = longitude;
	}

    public Deal(String description, String category, String businessName, double latitude, double longitude) {
        this(-1, description, category, businessName, latitude, longitude);
    }

	public int getId() { return mId; }
	public String getDescription() { return mDescription; }
	public String getCategory() { return mCategory; }
    public String getBusinessName() { return mBusinessName; }
    public double getLatitude() { return mLat; }
    public double getLongitude() { return mLng; }

	public void setId(int id) { mId = id; }
	public void setDescription(String description) { mDescription = description; }
	public void setCategory(String category) { mCategory = category; }
    //public void setBusinessId(int businessId) { mBusinessName = businessId; }
	public void setBusinessName(String businessName) { mBusinessName = businessName; }
	public void setLatitude(double latitude) { mLat = latitude; }
	public void setLogitude(double logitude) { mLng = logitude; }
}