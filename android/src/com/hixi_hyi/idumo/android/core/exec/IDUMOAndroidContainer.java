package com.hixi_hyi.idumo.android.core.exec;

import java.util.ArrayList;
import java.util.Collection;

import com.hixi_hyi.idumo.android.core.AndroidController;
import com.hixi_hyi.idumo.core.exec.CoreContainer;
import com.hixi_hyi.idumo.core.parts.Connectable;

public class IDUMOAndroidContainer extends CoreContainer {
	private ArrayList<AndroidController>	androidControllers	= new ArrayList<AndroidController>();
	
	@Override
	public void add(Connectable item) {
		super.add(item);
		if (item instanceof AndroidController) {
			androidControllers.add((AndroidController) item);
		}
	}
	
	public Collection<AndroidController> getAndroidControllers() {
		return androidControllers;
	}
	
}
