package com.nebo_spider.entity;

public class Channellog_day {
	int chd_ID;
	String tvchannel;
	String tvdate;
	String avgnum;
	String reachnum;
	String tvrating;
	String reachrating;
	String marketshare;
	String timelen;
	@Override
	public String toString() {
		return "[" + chd_ID + "," + tvchannel + ","
				 + avgnum + "," + reachnum + "," + 
				tvrating + "," + reachrating + "," + marketshare + "," + timelen + "]";
	}
	
	public String getTimelen() {
		return timelen;
	}

	public void setTimelen(String timelen) {
		this.timelen = timelen;
	}

	public int getChd_ID() {
		return chd_ID;
	}
	public void setChd_ID(int chd_ID) {
		this.chd_ID = chd_ID;
	}
	public String getTvchannel() {
		return tvchannel;
	}
	public void setTvchannel(String tvchannel) {
		this.tvchannel = tvchannel;
	}
	public String getTvdate() {
		return tvdate;
	}
	public void setTvdate(String tvdate) {
		this.tvdate = tvdate;
	}
	public String getAvgnum() {
		return avgnum;
	}
	public void setAvgnum(String avgnum) {
		this.avgnum = avgnum;
	}
	public String getReachnum() {
		return reachnum;
	}
	public void setReachnum(String reachnum) {
		this.reachnum = reachnum;
	}
	public String getTvrating() {
		return tvrating;
	}
	public void setTvrating(String tvrating) {
		this.tvrating = tvrating;
	}
	public String getReachrating() {
		return reachrating;
	}
	public void setReachrating(String reachrating) {
		this.reachrating = reachrating;
	}
	public String getMarketshare() {
		return marketshare;
	}
	public void setMarketshare(String marketshare) {
		this.marketshare = marketshare;
	}
	
}
