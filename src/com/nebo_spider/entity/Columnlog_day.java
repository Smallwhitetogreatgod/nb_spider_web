package com.nebo_spider.entity;

public class Columnlog_day {

	int cod_ID;
	String tvcolumn;
	String tvdate;
	String avgnum;
	String reachnum;
	String tvrating;
	String reachrating;
	String marketshare;
	String timelen;
	@Override
	public String toString() {
		return "[" + cod_ID + "," + tvcolumn + ","
				 + avgnum + "," + reachnum + "," + 
				tvrating + "," + reachrating + "," + marketshare + "," + timelen + "]";
	}
	
	public String getTimelen() {
		return timelen;
	}

	public void setTimelen(String timelen) {
		this.timelen = timelen;
	}


	public int getCod_ID() {
		return cod_ID;
	}

	public void setCod_ID(int cod_ID) {
		this.cod_ID = cod_ID;
	}

	public String getTvcolumn() {
		return tvcolumn;
	}

	public void setTvcolumn(String tvcolumn) {
		this.tvcolumn = tvcolumn;
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

