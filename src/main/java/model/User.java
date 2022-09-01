package model;

public class User {
	
	private String userid;
	private String userpass;
	private String username;
	private String usertel;
	private String usertype;
	private String userpicture;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpass() {
		return userpass;
	}
	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsertel() {
		return usertel;
	}
	public void setUsertel(String usertel) {
		this.usertel = usertel;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getUserpicture() {
		return userpicture;
	}
	public void setUserpicture(String userpicture) {
		this.userpicture = userpicture;
	}
	
	@Override
	public String toString() {
		return "User [userid=" + userid + ", userpass=" + userpass + ", username=" + username + ", usertel=" + usertel
				+ ", usertype=" + usertype + ", userpicture=" + userpicture + "]";
	}
	
	
}
