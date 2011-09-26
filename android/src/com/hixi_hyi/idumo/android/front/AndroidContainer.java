package com.hixi_hyi.idumo.android.front;

import java.util.ArrayList;
import java.util.Collection;

import com.hixi_hyi.idumo.android.AndroidController;
import com.hixi_hyi.idumo.core.IdumoComponent;
import com.hixi_hyi.idumo.core.front.IdumoContainer;

public class AndroidContainer extends IdumoContainer {
	private ArrayList<AndroidController>	androidControllers	= new ArrayList<AndroidController>();
	
	@Override
	public void add(IdumoComponent item) {
		super.add(item);
		if (item instanceof AndroidController) {
			androidControllers.add((AndroidController) item);
		}
	}
	
	public Collection<AndroidController> getAndroidControllers() {
		return androidControllers;
	}
	
}
