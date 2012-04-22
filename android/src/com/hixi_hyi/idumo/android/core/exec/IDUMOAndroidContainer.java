package com.hixi_hyi.idumo.android.core.exec;

import java.util.ArrayList;
import java.util.Collection;

import com.hixi_hyi.idumo.android.core.AndroidController;
import com.hixi_hyi.idumo.core.exec.IDUMOContainer;
import com.hixi_hyi.idumo.core.parts.IDUMOConnectable;

public class IDUMOAndroidContainer extends IDUMOContainer {
	private ArrayList<AndroidController>	androidControllers	= new ArrayList<AndroidController>();
	
	@Override
	public void add(IDUMOConnectable item) {
		super.add(item);
		if (item instanceof AndroidController) {
			androidControllers.add((AndroidController) item);
		}
	}
	
	public Collection<AndroidController> getAndroidControllers() {
		return androidControllers;
	}
	
}
