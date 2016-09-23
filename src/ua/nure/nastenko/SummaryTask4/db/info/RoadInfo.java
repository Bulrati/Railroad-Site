package ua.nure.nastenko.SummaryTask4.db.info;

import java.util.Date;

import com.sun.org.apache.regexp.internal.recompile;

public class RoadInfo {

	private int trainNumber;
	private Date departmentDate;
	private Date departmentTime;	
	private String departmentStation;	
	private Date arrivalDate;
	private Date arrivalTime;	
	private String arrivalStation;	
	private long totalTimeInRoute;	
	private int freePlackart;	
	private int pricePlackart;
	private int freeCupe;	
	private int priceCupe;
	private int freeCommon;	
	private int pricePCommon;
	private String routeLink;
	private String inRoad;
	
	
	public RoadInfo(int trainNubber, Date departmentDate, Date departmentTime,
			String departmentStation, Date arrivalDate, Date arrivalTime,
			String arrivalStation, long totalTimeInRoute, int freePlackart,
			int pricePlackart, int freeCupe, int priceCupe, int freeCommon,
			int pricePCommon, String routeLink) {
		super();
		this.trainNumber = trainNubber;
		this.departmentDate = departmentDate;
		this.departmentTime = departmentTime;
		this.departmentStation = departmentStation;
		this.arrivalDate = arrivalDate;
		this.arrivalTime = arrivalTime;
		this.arrivalStation = arrivalStation;
		this.totalTimeInRoute = totalTimeInRoute;
		this.freePlackart = freePlackart;
		this.pricePlackart = pricePlackart;
		this.freeCupe = freeCupe;
		this.priceCupe = priceCupe;
		this.freeCommon = freeCommon;
		this.pricePCommon = pricePCommon;
		this.routeLink = routeLink;
		this.inRoad = new String((totalTimeInRoute/1000/60/60/24) + " days " 
				+ (totalTimeInRoute/1000/60/60)%24 + " hours " 
				+ (totalTimeInRoute/1000/60)%60 + " min " 
				+ (totalTimeInRoute/1000)%60 + " sec");
	}







	public int getTrainNumber() {
		return trainNumber;
	}







	public Date getDepartmentDate() {
		return departmentDate;
	}







	public Date getDepartmentTime() {
		return departmentTime;
	}







	public String getDepartmentStation() {
		return departmentStation;
	}







	public Date getArrivalDate() {
		return arrivalDate;
	}







	public Date getArrivalTime() {
		return arrivalTime;
	}







	public String getArrivalStation() {
		return arrivalStation;
	}







	public long getTotalTimeInRoute() {
		return totalTimeInRoute;
	}

	public String getInRoad(){
		return inRoad;
	}





	public int getFreePlackart() {
		return freePlackart;
	}







	public int getPricePlackart() {
		return pricePlackart;
	}







	public int getFreeCupe() {
		return freeCupe;
	}







	public int getPriceCupe() {
		return priceCupe;
	}







	public int getFreeCommon() {
		return freeCommon;
	}







	public int getPricePCommon() {
		return pricePCommon;
	}







	public String getRouteLink() {
		return routeLink;
	}







	@Override
	public String toString() {
		return "RoadInfo [trainNubber=" + trainNumber + ", departmentDate="
				+ departmentDate + ", departmentTime=" + departmentTime
				+ ", departmentStation=" + departmentStation + ", arrivalDate="
				+ arrivalDate + ", arrivalTime=" + arrivalTime
				+ ", arrivalStation=" + arrivalStation + ", totalTimeInRoute="
				+ (totalTimeInRoute/1000/60/60/24) + " days " 
				+ (totalTimeInRoute/1000/60/60)%24 + " hours " 
				+ (totalTimeInRoute/1000/60)%60 + " min " 
				+ (totalTimeInRoute/1000)%60 + " sec" + ", freePlackart=" + freePlackart
				+ ", pricePlackart=" + pricePlackart + ", freeCupe=" + freeCupe
				+ ", priceCupe=" + priceCupe + ", freeCommon=" + freeCommon
				+ ", pricePCommon=" + pricePCommon + ", routeLink=" + routeLink
				+ "]";
	}







	public static void main(String[] args) {
		System.out.println(new Date());
	}
	
}
