package com.nebo_spider.entity;

public class Channellog_count {

	int chc_ID;
	String tvchannel;
	String tvdate;
	int num;
	int timelen;

	public int getChc_ID() {
		return chc_ID;
	}
	public void setChc_ID(int chc_ID) {
		this.chc_ID = chc_ID;
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
	@Override
	public String toString() {
		return "Channellog_count [chc_ID=" + chc_ID + ", tvchannel=" + tvchannel + ", tvdate="
				+ tvdate + ", num=" + num + ", timelen=" + timelen + "]";
	}
}
