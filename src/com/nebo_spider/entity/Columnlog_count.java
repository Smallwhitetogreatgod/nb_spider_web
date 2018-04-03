package com.nebo_spider.entity;

public class Columnlog_count {

	int coc_ID;
	String tvchannel;
	String tvdate;
	int num;
	int timelen;
	@Override
	public String toString() {
		return "Columnlog_count [coc_ID=" + coc_ID + ", tvchannel=" + tvchannel + ", tvdate="
				+ tvdate + ", num=" + num + ", timelen=" + timelen + "]";
	}
	public int getCoc_ID() {
		return coc_ID;
	}
	public void setCoc_ID(int coc_ID) {
		this.coc_ID = coc_ID;
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
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getTimelen() {
		return timelen;
	}
	public void setTimelen(int timelen) {
		this.timelen = timelen;
	}
	
}
