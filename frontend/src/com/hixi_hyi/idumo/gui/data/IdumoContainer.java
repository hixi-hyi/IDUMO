package com.hixi_hyi.idumo.gui.data;

import java.util.ArrayList;

public class IdumoContainer {

	private ArrayList<IdumoItem> items;
	private ArrayList<IdumoEdge> edges;

	public IdumoContainer(){
		items = new ArrayList<IdumoItem>();
		edges = new ArrayList<IdumoEdge>();
	}

	private static final IdumoItem[] ITEM_ARRAY_TYPE  = new IdumoItem[]{};
	private static final IdumoEdge[] EDGE_ARRAY_TYPE  = new IdumoEdge[]{};

	public IdumoItem[] getItems(){
		return items.toArray(ITEM_ARRAY_TYPE);
	}

	public IdumoEdge[] getEdges(){
		return edges.toArray(EDGE_ARRAY_TYPE);
	}


	public void removeItem(IdumoItem item){
		items.remove(item);

	}

	public void addItem(String klass, String name, int x, int y){
		items.add(new IdumoItem(klass, name, x, y));
	}
	
	public void addItem(IdumoItem item){
		items.add(item);
	}

	public void addEdge(IdumoItem src, IdumoItem dest){
		edges.add(new IdumoEdge(src, dest));
	}

	public void addEdge(IdumoEdge edge){
		edges.add(edge);
	}	
	
	public void removeEdge(IdumoEdge edge){
		edges.remove(edge);
	}
	
	public void removeEdge(IdumoItem item){
		for(IdumoEdge edge: edges){
			if(edge.getSrcItem()==item || edge.getDestItem()==item){
				removeEdge(edge);
			}
		}
	}
	
}