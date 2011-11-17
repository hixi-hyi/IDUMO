package com.hixi_hyi.idumo.gui.data;

public class IdumoEdge {
	
	private final IdumoItem src;
	private final IdumoItem dest;
	
	public IdumoEdge(IdumoItem src, IdumoItem dest){
		this.src = src;
		this.dest = dest;
	}
	
	public IdumoItem getSrcItem(){
		return src;
	}

	public IdumoItem getDestItem(){
		return dest;
	}

}
