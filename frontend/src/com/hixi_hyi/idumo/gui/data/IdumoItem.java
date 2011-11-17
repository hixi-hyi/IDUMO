package com.hixi_hyi.idumo.gui.data;

public class IdumoItem {
	
	private String name, klass, option, init;
	private int x, y;
	
	
	public IdumoItem(String klass, String name, int x, int y){
		this.name = name;
		this.klass = klass;
		this.option = "";
		this.init = "";
		this.x = x;
		this.y = y;
	}
	
	public String getName(){
		return name;
	}

	public String getKlass(){
		return klass;
	}
	
	public int getLabelLength(){
		if(name.length() > klass.length()) return name.length();
		else return klass.length();
	}

	public void setPoint(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}
	
	public void addOption(String s){
		option += "," + s; 
	}

	public void addInit(String s){
		init += "," + s; 
	}
	
	public String getOption(){
		return option;
	}

	public String getInit(){
		return init;
	}
	

}
