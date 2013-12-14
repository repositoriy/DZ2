package databaseService;


import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;



@Entity
@Table (name = "gamer")
public class Gamer {

	
	@Id		
	@GenericGenerator(name="kaugen" , strategy="increment")
	@GeneratedValue(generator="kaugen")
	@Column(name = "id")
	private int userId;
	@Column (name = "userName")
	private String userName;
	@Column (name = "lastDate")
	private Calendar lastDate;
	@Column (name = "bestCount")
	private int bestCount;
	
	
	
	public Gamer(){		
		this.lastDate = Calendar.getInstance();//.getTime());
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Calendar getLastDate() {
		return lastDate;
	}
	public void setLastDate(Calendar lastDate) {
		this.lastDate = lastDate;
	}
	public int getBestCount() {
		return bestCount;
	}
	public void setBestCount(int bestCount) {
		this.bestCount = bestCount;
	}
}
