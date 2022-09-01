package model;

public class Store {
	
	private String storeid;
	private String storename;
	private String storetel;
	private String storetype;
	private String storedayoff;
	private String storeopen;
	private String storeclose;
	private String storepicture;
	private String storeaddress;
	
	
	public String getStoreid() {
		return storeid;
	}
	public void setStoreid(String storeid) {
		this.storeid = storeid;
	}
	public String getStorename() {
		return storename;
	}
	public void setStorename(String storename) {
		this.storename = storename;
	}
	public String getStoretel() {
		return storetel;
	}
	public void setStoretel(String storetel) {
		this.storetel = storetel;
	}
	public String getStoretype() {
		return storetype;
	}
	public void setStoretype(String storetype) {
		this.storetype = storetype;
	}
	public String getStoredayoff() {
		return storedayoff;
	}
	public void setStoredayoff(String storedayoff) {
		this.storedayoff = storedayoff;
	}
	public String getStoreopen() {
		return storeopen;
	}
	public void setStoreopen(String storeopen) {
		this.storeopen = storeopen;
	}
	public String getStoreclose() {
		return storeclose;
	}
	public void setStoreclose(String storeclose) {
		this.storeclose = storeclose;
	}
	public String getStorepicture() {
		return storepicture;
	}
	public void setStorepicture(String storepicture) {
		this.storepicture = storepicture;
	}
	public String getStoreaddress() {
		return storeaddress;
	}
	public void setStoreaddress(String storeaddress) {
		this.storeaddress = storeaddress;
	}
	
	
	@Override
	public String toString() {
		return "Store [storeid=" + storeid + ", storename=" + storename + ", storetel=" + storetel + ", storetype="
				+ storetype + ", storedayoff=" + storedayoff + ", storeopen=" + storeopen + ", storeclose=" + storeclose
				+ ", storepicture=" + storepicture + ", storeaddress=" + storeaddress + "]";
	}
	
	
}
